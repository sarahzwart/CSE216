import { useState, useEffect } from "react";
import { User } from "../../../entitites/User";
import axios from "axios";
import { useParams } from "react-router-dom";
import { editSO, editGO, editNote, fetchUser } from "../../../../api/api";
import EditProfileForm from "./EditUserInfo";
const url = "https://team-margaritavillians.dokku.cse.lehigh.edu/users/";

function UserProfile() {
  // userId of profile clicked on
  const { userId } = useParams();
  // Need to switch to number
  const userIdNum = Number(userId);

  // Current User that is logged in
  // We have this so we can keep track of if the information is edittable
  const currentUser = window.sessionStorage.getItem("user");

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
  function handleEditSO(uId: number, sexualOrientation: string) {
    try {
      editSO(uId, sexualOrientation);
    } catch (error) {}
  }
  // Put Gender Orientation Edits
  function handleEditGO(uId: number, genderOrientation: string) {
    try {
      editGO(uId, genderOrientation);
    } catch (error) {}
  }
  // Put Note Edits
  function handleEditNote(uId: number, note: string) {
    try {
      editNote(uId, note);
    } catch (error) {}
  }

  // Get the user using a get request
  useEffect(() => {
    async function fetchData() {
      try {
        const userData = await fetchUser(userIdNum);
        setUserInfo(userData);
      } catch (error) {
        console.error("Error when specific user data:", error);
      } finally {
        setIsLoading(false);
      }
    }
    fetchData();
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
          {currentUser === userId && (
            <div>
              <p>
                Sexual Orientation: {sexualOrientation}{" "}
                <EditProfileForm
                  onEditInfo={(newSO) => handleEditSO(user.uId, newSO)}
                  info={sexualOrientation || ""}
                />
              </p>
              <p>
                Gender Orientation: {genderOrientation}{" "}
                <EditProfileForm
                  onEditInfo={(newGO) => handleEditGO(user.uId, newGO)}
                  info={genderOrientation || ""}
                />
              </p>
              <p>
                Note: {note}{" "}
                <EditProfileForm
                  onEditInfo={(newNote) => handleEditNote(user.uId, newNote)}
                  info={note || ""}
                />
              </p>
            </div>
          )}
        </div>
      ) : (
        <p>No user data available</p>
      )}
    </div>
  );
}

//Testing

export default UserProfile;
