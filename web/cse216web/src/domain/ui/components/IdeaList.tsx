import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Message } from "../../entitites/Message";
import { Comment } from "../../entitites/Comment";
import { User } from "../../entitites/User";
import axios from "axios";
import CommentForm from "./CommentForm";
import MessageForm from "./MessageForm";
import VoteButtons from "./VoteButtons";
import EditCommentForm from "./EditCommentForm";

//CSS imports
import "../styles/IdeaList.css";

function IdeaList() {
  const [messages, setMessages] = useState<Message[]>([]);
  const [comments, setComments] = useState<Comment[]>([]);
  const [users, setUsers] = useState<User[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [editingComment, setEditingComment] = useState({
    cId: 0,
    cContent: " ",
  });
  const headers = {
    "Content-Type": "application/json",
    Accept: "application/json",
  };
  //Mock users
  
const mockMessages: Message[] = [
  {
    mId: 1,
    mTitle: "Sample Idea 1",
    mMessage: "This is a sample idea.",
    mLikes: 5,
    uId: 1,
  },
  {
    mId: 2,
    mTitle: "Sample Idea 2",
    mMessage: "Another sample idea here.",
    mLikes: 3,
    uId: 2,
  },
  // Add more sample messages as needed
];

const mockComments: Comment[] = [
  {
    cId: 1,
    mId: 1,
    uId: 1,
    cContent: "Nice idea!",
  },
  {
    cId: 2,
    mId: 2,
    uId: 2,
    cContent: "I agree with this!",
  },
];

const mockUsers: User[] = [
  {
    uName: "JimmyBuffet",
    uEmail: "jimmyBuff@emargarita.com",
    uSO: "Heterosexual",
    uGO: "Male",
    uNote: "Jimmy Buffett is best known for his music, which often portrays an 'island escapism' lifestyle.",
    uId: 1,
  },
  {
    uName: "TaylorSwift",
    uEmail: "taylorswift@margarita.com",
    uSO: "Heterosexual",
    uGO: "Female",
    uNote: "Taylors Swift's cat's net worth is $97 million",
    uId: 2,
  },
  {
    uName: "Pitbull",
    uEmail: "pitbull@margarita.com",
    uSO: "Heterosexual",
    uGO: "Male",
    uNote: "This for everybody going through tough times \n Believe me: been there, done that \n But everyday above ground is a great day, remember that",
    uId: 3,
  },
  // Add more sample users as needed
];
  // Gets all messages to display
  useEffect(() => {
    if (process.env.NODE_ENV === "development") {
      setMessages([...messages ,...mockMessages]);
      setComments([...comments, ...mockComments]);
      setUsers([...users,...mockUsers]);
      setIsLoading(false);
    } else {
    }
  }, []);

  useEffect(() => {
    const url = "https://team-margaritavillians.dokku.cse.lehigh.edu/messages";
    axios
      .get(url, { headers })
      .then((response) => {
        const messageData: Message[] = response.data.mData;
        setMessages(messageData);
        setIsLoading(false);
      })
      .catch((error) => {
        console.error("Error when fetching message:", error);
        setIsLoading(false);
      });
  }, []);

  // Get comment info
  useEffect(() => {
    const url = "https://team-margaritavillians.dokku.cse.lehigh.edu/comments";
    axios
      .get(url, { headers })
      .then((response) => {
        const commentData: Comment[] = response.data.mData;
        setComments(commentData);
      })
      .catch((error) => {
        console.error("Error when fetching message:", error);
      });
  }, []);

  // Get user info
  useEffect(() => {
    const url = "https://team-margaritavillians.dokku.cse.lehigh.edu/users";
    axios
      .get(url, { headers })
      .then((response) => {
        const userData: User[] = response.data.mData;
        setUsers(userData);
      })
      .catch((error) => {
        console.error("Error when fetching message:", error);
      });
  }, []);

  // ADD a Message
  const handleAddMessage = (message: string) => {
    const url = "https://team-margaritavillians.dokku.cse.lehigh.edu/messages";
    const data = { mTitle: "Title", mMessage: message, uId: 2};
    axios
      .post(url, data, { headers })
      .then((response) => {
        const newMessageData: number = response.data.mMessage; //int id
        setMessages([
          ...messages,
          {
            mMessage: message,
            mId: newMessageData,
            uId: 2,
            mLikes: 0,
            mTitle: "title",
          },
        ]);
        console.log(newMessageData);
      })
      .catch((error) => {
        console.error("Error when adding a message:", error);
      });
  };

  // Adds a comment to a Message(Idea)
  const handleAddComment = (userId: number, comment: string, mId: number) => {
    const url = "https://team-margaritavillians.dokku.cse.lehigh.edu/comments";
    axios
      .post(`${url}`, { cContent: comment, uId: userId, mId: mId }, { headers })
      .then((response) => {
        const newCommentData: number = response.data.mData;
          setComments(
          [...comments,
          {cContent: comment, mId: mId, uId: userId, cId: newCommentData}]);
      })
      .catch((error) => {
        console.error("Error when adding comment:", error);
      });
  };

  //Edit Comment with Put request
  const handleEditComment = (cId: number, cContent: string) => {
    const url = "https://team-margaritavillians.dokku.cse.lehigh.edu/comments/";
    axios
      .put(`${url}${cId}`, { cContent: cContent }, { headers })
      .then(() => {
        // Update the comments state with the updated comment
        const updatedComments = comments.map((comment) =>
          comment.cId === cId ? { ...comment, cContent } : comment
        );
        setComments(updatedComments);
      })
      .catch((error) => {
        console.error("Error editing message", error);
      });
  };

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
      <MessageForm onAddMessage={handleAddMessage} />
    </div>
  );

  // Puts a comment under a message based on messageID
  function messageComments(messageId: number) {
    const commentsForMessage = comments.filter(
      (comment) => comment.mId === messageId
    );
    return commentsForMessage.map((comment) => (
      <div className="comment-list-container" key={comment.cId}>
        <p style={{color: 'blue'}}>{displayUsername(comment.uId)}</p>
        {comment.cId === editingComment.cId ? (
          <EditCommentForm
          onEditComment={(editedComment) =>
            handleEditComment(comment.cId, editedComment)
          }
          comment={comment.cContent as string} // Include the 'comment' prop here
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
              }>
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
}

export default IdeaList;

