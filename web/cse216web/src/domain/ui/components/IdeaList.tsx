import React, { useState } from 'react';
const ideas = [
    { id: 1, title: 'Idea 1', content: 'This is the first idea.', author: 'User 1', likes: 10 },
    { id: 2, title: 'Idea 2', content: 'This is the second idea.', author: 'User 2', likes: 15 },
    { id: 3, title: 'Idea 3', content: 'This is the third idea.', author: 'User 3', likes: 5 },
  ];
  //Get all ideas \messages --> {mStatus: "OK", mData: "[mTitle, mMessage, mLikes, mId, uId, mCreated], mTitle, mMessage, mLikes, mId, uId, mCreated]...""}
  //Get specific idea {mId} \messages\id --> {mStatus: "OK", mData: "[mTitle, mMessage, mLikes, mId, uId, mCreated]"}
  //Post comment --> \messages\id --> sending{mId, cContent, uId} returns --> {mStatus: "OK", mData: "[cId: 0]"}
      //The comment is associated with a commentID, USerId, messageId
  //Edit a comment --> put {new cContent} comment/cId --> {may return 0?}
  const IdeaList = () => {
    return (
      <div className="idea-list">
        <h2>Idea List</h2>
        {ideas.map((idea) => (
          <div key={idea.id} className="idea-card">
            <h3>{idea.title}</h3>
            <p>{idea.content}</p>
            <p>Author: {idea.author}</p>
            <p>Likes: {idea.likes}</p>
            <button className="upvote-button">Upvote</button>
            <button className="downvote-button">Downvote</button>
            <button className="comment-button">Comment</button>
          </div>
        ))}
      </div>
    );
  };
  
  export default IdeaList;