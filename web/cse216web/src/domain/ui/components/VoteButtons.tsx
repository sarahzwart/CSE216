import React, { useState } from 'react';
//put likes --> /messages/id --> increment/decrements likes
const VoteButtons = () => {
    const [voteCount, setVoteCount] = useState(0);

    const handleUpVote = () => {
      setVoteCount(voteCount + 1);
      //put request
    };
  
    const handleDownVote = () => {
      setVoteCount(voteCount - 1);
      //put request
    };
  
    return (
      <div className="vote-buttons">
        <button onClick={handleUpVote}>Upvote</button>
        <span>{voteCount}</span>
        <button onClick={handleDownVote}>Downvote</button>
      </div>
    );
};

export default VoteButtons;