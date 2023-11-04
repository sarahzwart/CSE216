import { RouterProvider, createBrowserRouter, useNavigate } from 'react-router-dom';
import axios from 'axios';

// Ensures cookie is sent
axios.defaults.withCredentials = true;

const authUrl = `${process.env.REACT_APP_SERVER_URL}/auth/google`; // OAuth authentication URL from your server

function LoginPage() {
  const handleLogin = async () => {
    try {
      const response = await axios.get(authUrl);
      const { url } = response.data;
      // Redirect the user to the Google OAuth consent screen
      window.location.assign(url);
    } catch (error) {
      console.error('Error during login:', error);
    }
  };
  return (
    <div>
      <h1>Login Page</h1>
      <button onClick={handleLogin}>Log In with Google</button>
    </div>
  )
}

export default LoginPage;