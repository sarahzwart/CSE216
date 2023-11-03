import React, { useState , useEffect} from 'react';
import { Component } from 'react';
import {Message} from '../../entitites/Message';
import axios from 'axios';

function IdeaList(){
  const [messages, setMessages] = useState<Message[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    // Fetch the specific message using a GET request
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
  return(
    <div>
      <h1>Idea List</h1>
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        messages.map((message, index) => (
          <div key={index}>
            <h2>{message.mTitle}</h2>
            <p>{message.mMessage}</p>
            <p>Likes: {message.mLikes}</p>
            <p>User ID: {message.uId}</p>
          </div>
        ))
      )}
    </div>
  );
}

export default IdeaList;