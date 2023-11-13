import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
// Interfaces
import { Message } from "../../../entitites/Message";
import { Comment } from "../../../entitites/Comment";
import { User } from "../../../entitites/User";
//Parts of Comments/Messages
import CommentForm from "../comments/CommentForm";
import MessageForm from "./MessageForm";
import VoteButtons from "./VoteButtons";

import EditCommentForm from "../comments/EditCommentForm";
import {
  fetchUsers,
  fetchComments,
  fetchMessages,
  addComment,
  addMessage,
  editComment,
} from "../../../../api/api";
console.log(sessionStorage.getItem("sessionKey"))
//CSS imports
import "../../styles/IdeaList.css";

function IdeaList() {
  const [messages, setMessages] = useState<Message[]>([]);
  const [comments, setComments] = useState<Comment[]>([]);
  const [users, setUsers] = useState<User[]>([]);
  // Variable contains information of use logged in
  const [isLoading, setIsLoading] = useState(true);
  const [editingComment, setEditingComment] = useState({
    cId: 0,
    cContent: " ",
  });

  // Fetched all messages/comments/users
  useEffect(() => {
    async function fetchData() {
      try {
        const messageData = await fetchMessages();
        const commentData = await fetchComments();
        const userData = await fetchUsers();
        setMessages(messageData);
        setComments(commentData);
        setUsers(userData);
        setIsLoading(false);
      } catch (error) {
        console.error("Error when fetching data:", error);
        setIsLoading(false);
      }
    }
    fetchData();
  }, []);

  // Puts a comment under a message based on messageID
  function messageComments(messageId: number) {
    const commentsForMessage = comments.filter(
      (comment) => comment.mId === messageId
    );
  
    return commentsForMessage.map((comment) => (
      <div className="comment-list-container" key={comment.cId}>
        <p style={{ color: "blue" }}>{displayUsername(comment.uId)}</p>
        {comment.cId === editingComment.cId ? (
          <EditCommentForm
            onEditComment={(editedComment) =>
              handleEditComment(comment.cId, editedComment)
            }
            comment={comment.cContent as string}
          />
        ) : (
          <div>
            {comment.cContent}
            <button
              className="edit-button"
              onClick={() =>
                setEditingComment({
                  cId: comment.cId,
                  cContent: comment.cContent as string,
                })
              }
            >
              {editingComment.cId === comment.cId ? "Cancel" : "Edit"}
            </button>
          </div>
        )}
      </div>
    ));
  }

  // Displays username with a link to user profile
  function displayUsername(userId: number | undefined) {
    if (userId === undefined) {
      return "Unknown User";
    }
    const user = users.find((user) => user.uId === userId);
    return user ? (
      <Link to={`/profile/${user.uId}`}>{user.uName}</Link>
    ) : (
      "Unknown User"
    );
  }

  // adds a Message (POST)
  async function handleAddMessage(message: string) {
    try {
      const newMessageData: Promise<{mId:number,uId:number}> = addMessage(message);
      const data = {
        mMessage: message,
        mTitle: "Title",
        mId: 0,
        mLikes: 0,
        uId: 0,
      };
      const resolvedmId = (await newMessageData).mId;
      const resolveduId = (await newMessageData).uId;
      setMessages([...messages, { ...data, mId: resolvedmId, uId: resolveduId }]);
    } catch (error) {
      console.error("Error when adding a message:", error);
    }
  }

  // Adds a comment to a Message(Idea)
  async function handleAddComment(userId: number, comment: string, mId: number) {
    try {
      const newCommentData: Promise<number> = addComment(comment, mId);
      const data = {
        cContent: comment,
        uId: userId,
        mId: mId,
        cId: newCommentData,
      };
      const resolvedcId = await newCommentData;
      setComments([...comments, { ...data, cId: resolvedcId }]);
    } catch (error) {
      console.error("Error when adding comment:", error);
    }
  }

  // Edit Comment with Put request
  const handleEditComment = (cId: number, cContent: string) => {
    try {
      editComment(cId, cContent);
      const updatedComments = comments.map((comment) =>
        comment.cId === cId ? { ...comment, cContent } : comment
      );
      setComments(updatedComments);
    } catch (error) {
      console.error("Error editing comment: ", error);
    }
  };

  // The actual user Interface
  return (
    <div className="idea-list-container">
      <div className="message-list">
        {isLoading ? (
          <p>Loading...</p>
        ) : (
          messages.map((message, index) => (
            <div key={index} className="message-container">
              <p>{message.mId}</p>
              <h2>{displayUsername(message.uId)}</h2>
              <div className="message-box">
                <p>{message.mMessage}</p>
              </div>
              <VoteButtons message={message} />
              <p>Likes: {message.mLikes} </p>
              <h3>Comments:</h3>
              <p>{messageComments(message.mId)}</p>
              <h3>
                <CommentForm
                  onAddComment={(comment) =>
                    handleAddComment(1, comment, message.mId)
                  }
                />
              </h3>
            </div>
          ))
        )}
      </div>
      <MessageForm onAddMessage={(message) => handleAddMessage(message)} />
    </div>
  );
}

export default IdeaList;
