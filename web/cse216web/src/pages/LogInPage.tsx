import { useEffect, useState } from "react";
import {jwtDecode} from 'jwt-decode';

function LogInPage() {
  const [ user, setUser ] = useState({});
  function handleCallbackResponse(response: { credential: string }){
    console.log("Encoded JWT ID token: " + response.credential);
    const userObject = jwtDecode(response.credential);
    console.log(userObject);
    setUser(userObject);
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