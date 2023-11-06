import React, { useState } from 'react';
import axios from 'axios';
import { useEffect } from 'react';
import { FaRegThumbsDown} from "react-icons/fa";
import { FaRegThumbsUp} from "react-icons/fa";
import { faArrowUp, faArrowDown } from '@fortawesome/free-solid-svg-icons';
//put likes --> /messages/id --> increment/decrements likes
import { Message } from '../../entitites/Message';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
let url = "https://team-margaritavillians.dokku.cse.lehigh.edu/messages/";

function VoteButtons({ message }: { message: Message }){
  const [likes, setLikes] = useState(message.mLikes);
  const [upVoteStatus, setUpVoteStatus] = useState(false);
  const [downVoteStatus, setDownVoteStatus] = useState(false);
  const headers = {
    "Content-Type": "application/json",
    Accept: "application/json",
  };
  // Handle UpVote Button Press
  const handleUpVote = () => {
    if (upVoteStatus == false) {
      setLikes(likes + 1);
      setUpVoteStatus(true);
      axios.put(`${url}${message.mId}/like`, {uId: 1}, {headers} )
        .then(() => {
          
        })
        .catch(error => {
          console.error('Error when upVoting', error);
        });
    } else {
      setLikes(likes - 1);
      setUpVoteStatus(false);
      axios.put(`${url}${message.mId}/like`, {uId: 1}, {headers})
        .then(() => {
          console.log("successfully updated likes");
        })
        .catch(error => {
          console.error('Error when updating likes:', error);
        });
    }
  }

  // Handle Downvote Data
  const handleDownVote = () => {
    if(downVoteStatus == false){
      axios.put(`${url}${message.mId}/dislike`,  {uId: 1}, {headers})
        .then(() => {
          // Successfully updated on the server
          setLikes(likes - 1);
          setDownVoteStatus(true);
        })
        .catch(error => {
          console.error('Error when downvoting:', error);
        });
    }
    else{
      axios.put(`${url}${message.mId}/dislike`, {uId: 1}, {headers})
        .then(() => {
          console.log("successfully updated likes");
          setLikes(likes + 1);
        })
        .catch(error => {
          console.error('Error when updating likes:', error);
          setLikes(likes + 1);
        });
    }
  };
  
  return(
    <div>
      <button
        onClick={handleUpVote}
      >
        <FaRegThumbsUp/>
      </button>
      <button
        onClick={handleDownVote}
      >
         <FaRegThumbsDown/>
      </button>
      <p>Likes: {likes}</p>
    </div>
  )
}
export default VoteButtons;