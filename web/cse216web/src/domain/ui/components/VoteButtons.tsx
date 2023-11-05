import React, { useState } from 'react';
import axios from 'axios';
import { useEffect } from 'react';
import { faArrowUp, faArrowDown } from '@fortawesome/free-solid-svg-icons';
//put likes --> /messages/id --> increment/decrements likes
import { Message } from '../../entitites/Message';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
let url = "https://team-margaritavillians.dokku.cse.lehigh.edu/messages/";

function VoteButtons({ message }: { message: Message }){
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
      axios.put(`${url}${message.mId}`, {mLikes: likes - 1})
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
        <FontAwesomeIcon icon={faArrowUp} className='fa-2x' ></FontAwesomeIcon>
      </button>
      <button
        onClick={handleDownVote}
        className={selectedVote === 'downvote' ? 'selected' : ''}
      >
         <FontAwesomeIcon icon={faArrowDown} className="fa-2x"/>
      </button>
      <p>Likes: {likes}</p>
    </div>
  )
}
export default VoteButtons;