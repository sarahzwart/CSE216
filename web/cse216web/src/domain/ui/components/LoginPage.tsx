import { RouterProvider, createBrowserRouter, useNavigate } from 'react-router-dom';
import React, { useState , useEffect} from 'react';
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import axios from 'axios';
import {User} from '../../entitites/User';

// Ensures cookie is sent
axios.defaults.withCredentials = true;

const clientId = '69750154488-v2ko8le0do3lcrsmr2cj6dqnl4untjls.apps.googleusercontent.com ';

function GoogleOAuthLogin() {

  const google = window.google;
  const handleCallbackResponse = () => {
    console.log("Encoded JWT ID token: " + response.credential)
  }
  useEffect(() =>{
    google.accounts.id.initialize({
      client_id: "69750154488-v2ko8le0do3lcrsmr2cj6dqnl4untjls.apps.googleusercontent.com",
      callback: handleCallbackResponse
    })
    google.accounts.is.renderButton(
      document.getElementById("sign-in"),
      { theme: "outline", size: "large" }
    )
  }, [])
  return (
    <div className = "Login">
      <div id="sign-in-div"></div>
    </div>
  );
}

export default LoginPage;