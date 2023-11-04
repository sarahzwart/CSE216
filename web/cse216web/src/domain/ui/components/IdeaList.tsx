import React, { useState , useEffect} from 'react';
import { Component } from 'react';
import {Message} from '../../entitites/Message';
import {Comment} from '../../entitites/Comment';
import {User} from '../../entitites/User';
import axios from 'axios';
import CommentForm from './CommentForm';
import MessageForm from './MessageForm';

function IdeaList(){
  const [messages, setMessages] = useState<Message[]>([]);
  const [comments, setComments] = useState<Comment[]>([]);
  const [users, setUsers] = useState<User[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  
  const handleAddMessage = (message: string) => {
    const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/messages';
    axios.post(url, {mMessage: message, mTitle: 'Title'})
    .then((response) => {
      const newMessageData: number = response.data.mMessage; //int id
      setMessages([...messages, {mMessage: message, mId: newMessageData, mLikes:0, mTitle: 'title'}]);
      console.log(newMessageData);
    })
    .catch(error => {
      console.error('Error when adding a message:', error);
    })
  }

  const handleAddComment = (messageId: number, comment: string) => {
    const url = `https://team-margaritavillians.dokku.cse.lehigh.edu/messages/${messageId}`;
    axios.post(url, {cContent: comment, mId: messageId})
    .then((response) => {
      const newCommentData: Comment = response.data.mData;
      setComments([...comments, newCommentData]);
    })
    .catch(error => {
      console.error('Error when adding comment:', error);
    });
  }

  useEffect(() => {
    const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/messages'
    axios.get(url).then((response) => {
        const messageData: Message[] = response.data.mData;
        setMessages(messageData);
        setIsLoading(false);
      })
      .catch(error => {
        console.error('Error when fetching message:', error);
        setIsLoading(false);
      });
  }, []);

  useEffect(() => {
    const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/comments'
    axios.get(url).then((response) => {
        const commentData: Comment[] = response.data.mData;
        setComments(commentData);
      })
      .catch(error => {
        console.error('Error when fetching message:', error);
      });
  }, []);

  useEffect(() => {
    const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/users'
    axios.get(url).then((response) => {
        const userData: User[] = response.data.mData;
        setUsers(userData);
      })
      .catch(error => {
        console.error('Error when fetching message:', error);
      });
  }, []);

  /*
  useEffect(() => {
    if (process.env.NODE_ENV === 'development') {
      setMessages(mockMessages);
      setComments(mockComments);
      setUsers(mockUsers);
      setIsLoading(false);
    } else {
      // Fetch your data from the backend
      // You can make actual API requests here
    }
  }, []);
  */

  return(
    <div>
      <h1>Idea List</h1>
      <MessageForm onAddMessage={handleAddMessage} />
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        messages.map((message, index) => (
          <div key={index}>
            <h2>{message.uId}</h2>
            <p>{message.mMessage}</p>
            <p>Likes: {message.mLikes}</p>
            <h3>Comments:</h3>
            <ul>
              {messageComments(message.mId)}
            </ul>
            <CommentForm onAddComment={(comment) => handleAddComment(message.mId, comment)} />
          </div>
        ))
      )}
    </div>
  );

  function messageComments(messageId: number) {
    const commentsForMessage = comments.filter(comment => comment.mId === messageId);
    return commentsForMessage.map((comment) => (
      <li key={comment.cId}>{comment.cContent}</li>
    ));
  }

  function displayUsername(userId: number){
    const user = users.find(user => user.uId === userId);
    return user ? user.uName : 'Unknown User';
  }

}


//Mock Data
  const mockMessages: Message[] = [
    {
      mId: 1,
      mTitle: 'Sample Idea 1',
      mMessage: 'This is a sample idea.',
      mLikes: 5,
      uId: 1,
    },
    {
      mId: 2,
      mTitle: 'Sample Idea 2',
      mMessage: 'Another sample idea here.',
      mLikes: 3,
      uId: 2,
    },
    // Add more sample messages as needed
  ];

  const mockComments: Comment[] = [
    {
      cId: 1,
      mId: 1,
      uId: 3,
      cContent: 'Nice idea!',
    },
    {
      cId: 2,
      mId: 2,
      uId: 4,
      cContent: 'I agree with this!',
    },
    // Add more sample comments as needed
  ];

  const mockUsers: User[] = [
    {
      uName: 'User1',
      uEmail: 'user1@example.com',
      uSO: 'Heterosexual',
      uGO: 'Male',
      uNote: 'Some note about User1',
      uId: 1,
    },
    {
      uName: 'User2',
      uEmail: 'user2@example.com',
      uSO: 'Heterosexual',
      uGO: 'Female',
      uNote: 'Some note about User2',
      uId: 2,
    },
    // Add more sample users as needed
  ];

export default IdeaList;