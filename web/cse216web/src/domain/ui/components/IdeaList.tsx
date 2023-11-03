import React, { useState } from 'react';
import { Component } from 'react';
  class IdeaList extends Component {
    constructor (props){
        super(props);
    }
    render() {
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
    }
  }
  export default IdeaList;