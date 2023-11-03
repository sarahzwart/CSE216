import React, { useState, useEffect } from 'react';
import {User} from '../../entitites/User';

const UserProfile = () => {
    // get /user/id --> returns all information including id
    return (
      <div className="user-profile">
        <h2>Idea List</h2>
          <div key={userData.Id} className="idea-card">
            <p>UserName: {userData.uName}</p>
            <p>Email: {userData.uEmail}</p>
            <p>Sexual Orientation: {userData.uSO}</p>
            <p>Gender Identity: {userData.uGI}</p>
            <p>Note: {userData.uNote} </p>
          </div>
      </div>
    );
  };
  
  export default UserProfile;