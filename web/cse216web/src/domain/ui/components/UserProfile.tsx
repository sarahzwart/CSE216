import React, { useState, useEffect } from 'react';
import {User} from '../../entitites/User';
import axios from 'axios';

const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/users/';
function UserProfile(userInfo : User){
  const [user, setUserInfo] = useState<User>();
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() =>{
    axios.get(`${url}${userInfo.uId}`)
    .then((response) => {
      setUserInfo(response.data.mData);
      setIsLoading(false);
    })
    .catch((error) =>{
      console.error('Error when fetching user information:', error);
      setIsLoading(false);
    });
  });

  return(
    <div>
      <h1>Profile</h1>
      {isLoading ? (
        <p>Loading...</p>
      ) : user ? (
        <div>
            <p>User: {user.uName}</p>
            <p>Email: {user.uEmail}</p>
            <p>Sexual Orientation: {user.uSO}</p>
            <p>Gender Orientation: {user.uGO}</p>
            <p>Note: {user.uNote}</p>
        </div>
      ) : (
        <p>No user data Available</p>
      )}
    </div>
  );
}
  
export default UserProfile;