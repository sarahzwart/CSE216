import { useEffect } from "react";
import {useNavigate} from "react-router-dom";
import { addSessionKey } from "../api/api";

//https://stackoverflow.com/questions/40399873/initializing-and-using-sessionstorage-in-react
function LogInPage() {
  const navigate = useNavigate();
  async function handleCallbackResponse(response: { credential: string }){
    console.log("Encoded JWT ID token: " + response.credential);
    try {
      const userSessionAndID = await handleUserInfo(response.credential);
      sessionStorage.setItem("sessionKey", userSessionAndID.sessionKey);
      sessionStorage.setItem("userId", String(userSessionAndID.uId));
      console.log(userSessionAndID.sessionKey);
      // Navigates to idealist page
      navigate("/list");
    } catch (error) {
      console.error("Error in handleCallbackResponse:", error);
      // Handle the error, maybe log it or perform some other action
    }
  }

  // Uses axios method from api.ts
  async function handleUserInfo(jwtToken: string){
    try {
      //makes a post request to users
      const sessionKey = await addSessionKey(jwtToken);
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