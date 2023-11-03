import React, { useState } from 'react';
import axios from 'axios';
//put likes --> /messages/id --> increment/decrements likes
import { Message } from '../../entitites/Message';
let url = "https://team-margaritavillians.dokku.cse.lehigh.edu/messages/";

function VoteButtons(message: Message){
  const [likes, setLikes] = useState(message.mLikes);
  const [selectedVote, setSelectedVote] = useState<'upvote' | 'downvote' | null>(null);
  const handleUpVote = () => {
    if (selectedVote === 'upvote') {
      setSelectedVote(null);
      axios.put(`${url}${message.mId}`, { mLikes: likes - 1 })
        .then(() => {
          // Successfully updated on the server
          setLikes(likes - 1);
        })
        .catch(error => {
          console.error('Error when downvoting:', error);
        });
    } else {
      setSelectedVote('upvote');
      axios.put(`${url}${message.mId}`, {mLikes: likes + 1})
        .then(response => {
          console.log("successfully updated likes");
          setLikes(likes + 1);
        })
        .catch(error => {
          console.error('Error when updating likes:', error);
          setLikes(likes + 1);
        });
    }
  }
  const handleDownVote = () => {
    if(selectedVote === 'downvote'){
      setSelectedVote(null);
      axios.put(`${url}${message.mId}`, { mLikes: likes + 1 })
        .then(() => {
          // Successfully updated on the server
          setLikes(likes - 1);
        })
        .catch(error => {
          console.error('Error when downvoting:', error);
        });
    }
    else{
      setSelectedVote('downvote')
      axios.put(`${url}${message.mId}`, {mLikes: likes + 1})
        .then(() => {
          console.log("successfully updated likes");
          setLikes(likes - 1);
        })
        .catch(error => {
          console.error('Error when updating likes:', error);
          setLikes(likes - 1);
        });
    }
  };
  
  return(
    <div>
      <button
        onClick={handleUpVote}
        className={selectedVote === 'upvote' ? 'selected' : ''}
      >
        Upvote
      </button>
      <button
        onClick={handleDownVote}
        className={selectedVote === 'downvote' ? 'selected' : ''}
      >
        Downvote
      </button>
      <p>Likes: {likes}</p>
    </div>
  )
}
export default VoteButtons;