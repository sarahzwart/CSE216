import React, { useState } from 'react';
import axios from 'axios';
import {Message} from '../../domain/entitites/Message';

const MessageLikes = (Message message) => {
  const [likes, setLikes] = useState(0);
  const messageId = 1; // Replace with the actual message ID

  const incrementLikes = async () => {
    try {
      const response = await axios.put(`/messages/${message.messageId}`);
      if (response.status === 200) {
        setLikes(likes + 1); // Update the local state to reflect the new likes count
      }
    } catch (error) {
      console.error('Error incrementing likes:', error);
    }
  };

  const decrementLikes = async () => {
    try {
      const response = await axios.put(`/messages/${messageId}`);
      if (response.status === 200) {
        setLikes(likes - 1); // Update the local state to reflect the new likes count
      }
    } catch (error) {
      console.error('Error decrementing likes:', error);
    }
  };
};

export default MessageLikes;