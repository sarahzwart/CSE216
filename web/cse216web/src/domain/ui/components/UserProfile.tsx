import { useState, useEffect } from 'react';
import {User} from '../../entitites/User';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/users/';
function UserProfile(){
  const { userId } = useParams();
  const [user, setUserInfo] = useState<User>();
  const [isLoading, setIsLoading] = useState(true);
  // JSON Headers
  const headers = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
  };
  // Get the user using a get request
  useEffect(() =>{
    axios.get(`${url}${userId}`, {headers})
    .then((response) => {
      setUserInfo(response.data.mData);
      setIsLoading(false);
    })
    .catch((error) =>{
      console.error('Error when fetching user information:', error);
      setIsLoading(false);
    });
  }, []);

  // Testing
  useEffect(() => {
    if (userId === '1') {
      setUserInfo(mockUser1);
    } else if (userId === '2') {
      setUserInfo(mockUser2);
    } else if (userId === '3') {
      setUserInfo(mockUser3);
    } else {
      setUserInfo(undefined); // Handle the case where userInfo doesn't match any user
    }
    setIsLoading(false);
  }, []);

  // Put Requests for the user info
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

//Testing
const mockUser1 = {
  uName: "User1",
  uEmail: "user1@example.com",
  uSO: "Heterosexual",
  uGO: "Male",
  uNote: "Some note about User1",
  uId: 1,
};

const mockUser2 = {
  uName: "User2",
  uEmail: "user2@example.com",
  uSO: "Heterosexual",
  uGO: "Female",
  uNote: "Some note about User2",
  uId: 2,
};

const mockUser3 = {
  uName: "User3",
  uEmail: "user3@example.com",
  uSO: "Bisexual",
  uGO: "Non-binary",
  uNote: "Some note about User3",
  uId: 3,
};

export default UserProfile;