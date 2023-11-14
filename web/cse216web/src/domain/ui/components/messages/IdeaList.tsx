import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
// Interfaces
import { Message } from "../../../entitites/Message";
import { Comment } from "../../../entitites/Comment";
//Parts of Comments/Messages
import CommentForm from "../comments/CommentForm";
import MessageForm from "./MessageForm";
import VoteButtons from "./VoteButtons";

import EditCommentForm from "../comments/EditCommentForm";

const userId = sessionStorage.getItem("userId");
const uId = Number(userId);

import {
  fetchComments,
  fetchMessages,
  addComment,
  addMessage,
  editComment,
  fetchUserName,
} from "../../../../api/api";
console.log(sessionStorage.getItem("sessionKey"))
//CSS imports
import "../../styles/IdeaList.css";

function IdeaList() {
  const [messages, setMessages] = useState<Message[]>([]);
  const [comments, setComments] = useState<Comment[]>([]);
  // Variable contains information of use logged in
  const [messageUsernames, setMessageUsernames] = useState<{ [key: number]: string }>({});
  const [commentUsernames, setCommentUsernames] = useState<{ [key: number]: string }>({});
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
        setMessages(messageData);
        setComments(commentData);
        setIsLoading(false);
      } catch (error) {
        console.error("Error when fetching data:", error);
        setIsLoading(false);
      }
    }
    fetchData();
  }, []);

  useEffect(() => {
    // Fetch usernames for each message's author
    messages.forEach(async (message) => {
      try {
        const userName = await fetchUserName(message.uId);
        setMessageUsernames((prevUsernames) => ({
          ...prevUsernames,
          [message.uId]: userName,
        }));
      } catch (error) {
        console.error('Error fetching username:', error);
      }
    });
  }, [messages]);

  useEffect(() => {
    // Fetch usernames for each comment's author
    comments.forEach(async (comment) => {
      try {
        const userName = await fetchUserName(comment.uId);
        setCommentUsernames((prevUsernames) => ({
          ...prevUsernames,
          [comment.uId]: userName,
        }));
      } catch (error) {
        console.error('Error fetching username:', error);
      }
    });
  }, [comments]);


  // Puts a comment under a message based on messageID
  function messageComments(messageId: number) {
    const commentsForMessage = comments.filter(
      (comment) => comment.mId === messageId
    );

    return commentsForMessage.map((comment) => (
      <div className="comment-list-container" key={comment.cId}>
        <p style={{ color: "blue" }}><Link to={`/profile/${userId}`}>{commentUsernames[comment.uId]}</Link></p>
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




  // adds a Message (POST)
  async function handleAddMessage(message: string) {
    try {
      const newMessageData: Promise<number> = addMessage(message);
      const data = {
        mMessage: message,
        mTitle: "Title",
        mId: 0,
        mLikes: 0,
        uId: uId, 
      };
      const resolvedmId = await newMessageData;
      setMessages([...messages, { ...data, mId: resolvedmId}]);
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
  console.log("comments:" + comments);
  // The actual user Interface
  return (
    <div className="idea-list-container">
      <div className="message-list">
        {isLoading ? (
          <p>Loading...</p>
        ) : (
          messages?.map((message, index) => (
            <div key={index} className="message-container">
              <p>{message.mId}</p>
              <h2><Link to={`/profile/${userId}`}>{messageUsernames[message.uId]}</Link></h2>
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
