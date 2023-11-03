import React, { useState , useEffect} from 'react';
import { Component } from 'react';
import {Message} from '../../entitites/Message';
import {Comment} from '../../entitites/Comment';
import {User} from '../../entitites/User';
import axios from 'axios';

function IdeaList(){
  const [messages, setMessages] = useState<Message[]>([]);
  const [comments, setComments] = useState<Comment[]>([]);
  const [users, setUsers] = useState<User[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/messages/'
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
    const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/comments/'
    axios.get(url).then((response) => {
        const commentData: Comment[] = response.data.mData;
        setComments(commentData);
      })
      .catch(error => {
        console.error('Error when fetching message:', error);
      });
  }, []);

  useEffect(() => {
    const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/users/'
    axios.get(url).then((response) => {
        const userData: User[] = response.data.mData;
        setUsers(userData);
      })
      .catch(error => {
        console.error('Error when fetching message:', error);
      });
  }, []);

  return(
    <div>
      <h1>Idea List</h1>
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        messages.map((message, index) => (
          <div key={index}>
            <h2>{displayUsername(message.uId)}</h2>
            <p>{message.mMessage}</p>
            <p>Likes: {message.mLikes}</p>
            <h3>Comments:</h3>
            <ul>
              {messageComments(message.mId)}
            </ul>
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

export default IdeaList;