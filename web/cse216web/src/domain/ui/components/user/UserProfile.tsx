import { useState, useEffect } from "react";
import { User } from "../../../entitites/User";
import axios from "axios";
import { useParams } from "react-router-dom";

const url = "https://team-margaritavillians.dokku.cse.lehigh.edu/users/";

function UserProfile() {
  const { userId } = useParams();
  const [user, setUserInfo] = useState<User>();
  const [isLoading, setIsLoading] = useState(true);
  //Sexual Orientation
  const [sexualOrientation, setSexualOrientation] = useState<string>();
  //Gender
  const [genderOrientation, setGenderOrientation] = useState<string>();
  //Note
  const [note, setNote] = useState<string>();

  // JSON Headers
  const headers = {
    "Content-Type": "application/json",
    Accept: "application/json",
  };

  // Put Sexual Orientation Edits
  function handleEditSO(sexualOrientation: string) {
    try{

    } catch (error) {

    }
  }
  // Put Gender Orientation Edits
  function handleGO(genderOrientatio: string) {
    try{

    } catch (error) {

    }
  }
  // Put Note Edits
  function handleNote(note: string) {
    try{

    } catch (error) {

    }
  }

  // Get the user using a get request
  useEffect(() => {
    axios
      .get(`${url}${userId}`, { headers })
      .then((response) => {
        setUserInfo(response.data.mData);
        setIsLoading(false);
      })
      .catch((error) => {
        console.error("Error when fetching user information:", error);
        setIsLoading(false);
      });
  }, []);
  return (
    <div>
      <h1>Profile</h1>
      {isLoading ? (
        <p>Loading...</p>
      ) : user ? (
        <div>
          <p>User: {user.uName}</p>
          <p>Email: {user.uEmail}</p>
          <p>
            Sexual Orientation: {user.uSO} <button></button>
          </p>
          <p>
            Gender Orientation: {user.uGO}
            <button></button>
          </p>
          <p>
            Note: {user.uNote}
            <button></button>
          </p>
        </div>
      ) : (
        <p>No user data Available</p>
      )}
    </div>
  );
}

//Testing

export default UserProfile;
