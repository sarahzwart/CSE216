import { useState, useEffect } from "react";
import { User } from "../../../entitites/User";
import { useParams } from "react-router-dom";
import { fetchUser, editUserInfo } from "../../../../api/api";
import EditProfileForm from "./EditUserInfo";

function UserProfile() {
  // userId of profile clicked on
  const { userId } = useParams();
  // Need to switch to number

  // Current User that is logged in
  // We have this so we can keep track of if the information is edittable

  const [user, setUserInfo] = useState<User>();
  const [isLoading, setIsLoading] = useState(true);
  //Sexual Orientation
  const [sexualOrientation, setSexualOrientation] = useState<string>();
  //Gender
  const [genderIdentity, setGenderIdentity] = useState<string>();
  //Note
  const [note, setNote] = useState<string>();

  // JSON Headers
  const headers = {
    "Content-Type": "application/json",
    Accept: "application/json",
  };

  // Put Sexual Orientation Edits
  function handleEditUser() {
    try {
      editUserInfo({
        uIdToken: userIdToken
        

      });
    } catch (error) {

    }
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
          <div>
            <p>
              Sexual Orientation: {sexualOrientation}{" "}
              <EditProfileForm
                onEditInfo={(newSO) => handleEditUser()}
                info={sexualOrientation || ""}
              />
            </p>
            <p>
              Gender Orientation: {genderIdentity}{" "}
              <EditProfileForm
                onEditInfo={(newGO) => handleEditUser()}
                info={genderIdentity || ""}
              />
            </p>
            <p>
              Note: {note}{" "}
              <EditProfileForm
                onEditInfo={(newNote) => handleEditUser()}
                info={note || ""}
              />
            </p>
          </div>
        </div>
      ) : (
        <p>No user data available</p>
      )}
    </div>
  );
}

export default UserProfile;
