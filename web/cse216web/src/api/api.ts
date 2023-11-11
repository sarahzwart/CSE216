import axios from 'axios';

const headers = {
  'Content-Type': 'application/json',
  Accept: 'application/json',
};

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
// MESSAGES
// Post request for message
export async function addMessage(message: string, userId: number): Promise<number> {
  try {
    const response = await axios.post(
      'https://team-margaritavillians.dokku.cse.lehigh.edu/messages',
      { mTitle: 'Title', mMessage: message, uId: userId },
      { headers }
    );
    return response.data.mMessage;
  } catch (error) {
    console.error('Error when adding a message:', error);
    throw error;
  }
};


// COMMENTS
// Post request to comments
export async function addComment(userId: number, comment: string, mId: number): Promise<number> {
  try {
    const response = await axios.post(
      'https://team-margaritavillians.dokku.cse.lehigh.edu/comments',
      { cContent: comment, uId: userId, mId: mId },
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
      `https://team-margaritavillians.dokku.cse.lehigh.edu/comments/${cId}`,
      { cContent },
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
    const response = await axios.post(
      `https://team-margaritavillians.dokku.cse.lehigh.edu/user/${uId}`,
      {headers}
    );
    return response.data.mData; // Change
  } catch (error) {
    console.error('Error fetching User Data: ', error)
    throw error;
  }
}

// Edit Sexual Orientation (PUT)
export async function editSO(uId: number, uSO: string){
  try {
    await axios.put(
      `https://team-margaritavillians.dokku.cse.lehigh.edu/user/${uId}`,
      { uSO: uSO },
      {headers}
    );
  } catch (error) {
    console.error('Error editing user SO:', error);
    throw error;
  }
};

// Edit Gender Orientation (PUT)
export async function editGO(uId: number, uGO: string){
  try {
    await axios.put(
      `https://team-margaritavillians.dokku.cse.lehigh.edu/user/${uId}`,
      { uGO: uGO },
      {headers}
    );
  } catch (error) {
    console.error('Error editing user GO:', error);
    throw error;
  }
};

// Edit Note (PUT)
export async function editNote(uId: number, uNote: string){
  try {
    await axios.put(
      `https://team-margaritavillians.dokku.cse.lehigh.edu/user/${uId}`,
      { uNote: uNote },
      {headers}
    );
  } catch (error) {
    console.error('Error editing user Note:', error);
    throw error;
  }
};



