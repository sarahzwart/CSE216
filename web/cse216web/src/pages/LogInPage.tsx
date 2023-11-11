import { useEffect, useState } from "react";
import {jwtDecode} from 'jwt-decode';
import {useNavigate} from "react-router-dom";
import axios from "axios";
const url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/users/';
const headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
};
//https://stackoverflow.com/questions/40399873/initializing-and-using-sessionstorage-in-react
function LogInPage() {
  const [ user, setUser ] = useState({});
  const navigate = useNavigate();
  function handleCallbackResponse(response: { credential: string }){
    console.log("Encoded JWT ID token: " + response.credential);
    const userObject = jwtDecode(response.credential);
    console.log(userObject);
    setUser(userObject);
    sessionStorage.setItem("user", JSON.stringify(userObject));
    handleUserInfo(userObject);
    // Navigates to idealist page 
    navigate("/ideas");
  }
  
  function handleUserInfo(userObject: any){
    axios
    .post(url, {userObject}, {headers})
    .then((response) => {
      const userData = response.data.mData; //int id
      setUser(userData);
      console.log(userData)
    })
    .catch((error) => {
      console.error("Error when fetching message:", error);
    });
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