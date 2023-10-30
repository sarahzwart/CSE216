import React, { useState } from 'react';

const VoteButtons = () => {
    const [voteCount, setVoteCount] = useState(0);

    const handleUpVote = () => {
      setVoteCount(voteCount + 1);
    };
  
    const handleDownVote = () => {
      setVoteCount(voteCount - 1);
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