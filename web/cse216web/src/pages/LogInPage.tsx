import { useEffect, useState } from "react";
import {JwtPayload, jwtDecode} from 'jwt-decode';
import {useNavigate} from "react-router-dom";
import axios from "axios";
import { addSessionKey } from "../api/api";
const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/users/';
const headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
};

//https://stackoverflow.com/questions/40399873/initializing-and-using-sessionstorage-in-react
function LogInPage() {
  const [ user, setUser ] = useState({});
  const navigate = useNavigate();
  async function handleCallbackResponse(response: { credential: string }){
    console.log("Encoded JWT ID token: " + response.credential);
    const userObject = jwtDecode(response.credential);
    try {
      const sessionKey = await handleUserInfo(userObject);
      sessionStorage.setItem("sessionKey", sessionKey);
      // Navigates to idealist page
      navigate("/list");
    } catch (error) {
      console.error("Error in handleCallbackResponse:", error);
      // Handle the error, maybe log it or perform some other action
    }
  }
  
  
  async function handleUserInfo(userObject: JwtPayload){
    try {
      const sessionKey = await addSessionKey(userObject);
      return sessionKey;
    } catch (error) {
      console.error("Error in handleUserInfo:", error);
      throw error;
    }
  }
  
  
  useEffect(() => {
    /* global google */ 
    google.accounts.id.initialize({
      client_id: "69750154488-v2ko8le0do3lcrsmr2cj6dqnl4untjls.apps.googleusercontent.com",
      callback: handleCallbackResponse
    });

    google.accounts.id.renderButton(
      document.getElementById("signInDiv"),
      {theme: "outline", size: "large"}
    );
  }, []);

  return (
    <div className= "App">
      <div id="signInDiv"></div>
    </div>  
    
  );
}
  
export default LogInPage;