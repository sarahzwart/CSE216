import axios from 'axios';
import { JwtPayload } from 'jwt-decode';
import { User } from '../domain/entitites/User';

const headers = {
  'Content-Type': 'application/json',
  Accept: 'application/json',
};

const sessionKey = sessionStorage.getItem("sessionKey");
// Get request for all messages
export async function fetchMessages() {
  try {
    const response = await axios.get(
      'https://team-margaritavillians.dokku.cse.lehigh.edu/messages',
      { headers }
    );
    return response.data.mData;
  } catch (error) {
    console.error('Error when fetching messages:', error);
    throw error;
  }
};

// Get request for all comments
 export async function fetchComments(){
  try {
    const response = await axios.get(
      'https://team-margaritavillians.dokku.cse.lehigh.edu/comments',
      { headers }
    );
    return response.data.mData;
  } catch (error) {
    console.error('Error when fetching comments:', error);
    throw error;
  }
};

// Get request for all users
export async function fetchUsers(){
  try {
    const response = await axios.get(
      'https://team-margaritavillians.dokku.cse.lehigh.edu/users',
      { headers }
    );
    return response.data.mData;
  } catch (error) {
    console.error('Error when fetching users:', error);
    throw error;
  }
};
///messages?sessionKey=<insert session key>
// MESSAGES
// Post request for message
export async function addMessage(message: string): Promise<{mId: number, uId:number}> {
  try {
    const response = await axios.post(
      `https://team-margaritavillians.dokku.cse.lehigh.edu/messages?sessionKey=${sessionKey}`,
      { mTitle: 'Title', mMessage: message},
      { headers }
    );
    return response.data.mData
  } catch (error) {
    console.error('Error when adding a message:', error);
    throw error;
  }
};


// COMMENTS
// Post request to comments
export async function addComment(comment: string, mId: number): Promise<number>  {
  try {
    const response = await axios.post(
      `https://team-margaritavillians.dokku.cse.lehigh.edu/comments?sessionKey=${sessionKey}`,
      { cContent: comment, mId: mId },
      { headers }
    );
    return response.data.mData;
  } catch (error) {
    console.error('Error when adding a comment:', error);
    throw error;
  }
};

// Put request to comments
export async function editComment(cId: number, cContent: string){
  try {
    await axios.put(
      `https://team-margaritavillians.dokku.cse.lehigh.edu/comments/${cId}?sessionKey=${sessionKey}`,
      { cContent: cContent },
      { headers }
    );
  } catch (error) {
    console.error('Error editing comment:', error);
    throw error;
  }
};

//USERS

// Information of specific User (GET)
export async function fetchUser(uId: number){
  try {
    const response = await axios.get(
      `https://team-margaritavillians.dokku.cse.lehigh.edu/users/`,
      {headers}
    );
    return response.data.mData; // Change
  } catch (error) {
    console.error('Error fetching User Data: ', error)
    throw error;
  }
}

// Edit Sexual Orientation (PUT)
export async function editUserInfo(user: User){
  try {
    await axios.put(
      `https://team-margaritavillians.dokku.cse.lehigh.edu/users/${uId}?sessionKey=${sessionKey}`,
      { user },
      {headers}
    );
  } catch (error) {
    console.error('Error editing user SO:', error);
    throw error;
  }
};


export async function addSessionKey(userJWT: JwtPayload): Promise<string> {
  try{
    const response = await axios.post(
      `https://team-margaritavillians.dokku.cse.lehigh.edu/users/`,
      {userJWT},
      {headers}
    );
    const sessionKey = response.data.mData; // int id
    console.log(sessionKey);
    return sessionKey;
  } catch (error) {
    console.error('Error when fetching user token:', error);
    throw error; // Re-throw the error if you want to propagate it
  }
}


