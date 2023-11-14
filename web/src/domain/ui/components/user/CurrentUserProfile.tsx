import { useState, useEffect} from "react";
import { User } from "../../../entitites/User";
import { fetchUser, editUserInfo } from "../../../../api/api";
import EditProfileForm from "./EditUserInfo";

function CurrentUserProfile() {
  // userId of profile clicked on
  const uIdString = sessionStorage.getItem("userId");
  const uId = Number(uIdString);
  console.log(uId);
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

  // Put Sexual Orientation Edits
  function handleEditSO(user: User, uSO: string ) {
    const editedUserSO: User = {
      uId: uId,
      uName: user.uName,
      uEmail: user.uEmail,
      uGI: user.uGI,
      uSO: uSO,
      uNote: user.uNote,
    }
    setSexualOrientation(uSO);
    try {
      editUserInfo(editedUserSO);
    } catch (error) {

    }
  }
  function handleEditGI(user: User, uGI: string) {
    const editedUserGI: User = {
      uId: uId,
      uName: user.uName,
      uEmail: user.uEmail,
      uGI: uGI,
      uSO: user.uSO,
      uNote: user.uNote,
    }
    setGenderIdentity(uGI);
    try {
      editUserInfo(editedUserGI);
    } catch (error) {

    }
  }
  function handleEditNote(user: User, uNote: string ) {
    const editedUserNote: User = {
      uId: uId,
      uName: user.uName,
      uEmail: user.uEmail,
      uGI: user.uGI,
      uSO: user.uSO,
      uNote: uNote,
    };
    setNote(uNote);
    try {
      editUserInfo(editedUserNote);
    } catch (error) {

    }
  }

  // Get the user using a get request
  useEffect(() => {
    async function fetchData() {
      try {
        const userData = await fetchUser(uId);
        console.log(userData);
        setUserInfo(userData);
        setSexualOrientation(userData.uSO);
        setGenderIdentity(userData.uGI);
        setNote(userData.uNote);
      } catch (error) {
        console.error("Error when fetching specific user data:", error);
      } finally {
        setIsLoading(false);
      }
    }
    fetchData();
  }, [uId]);

  // ...

  return (
    <div>
      <h1>Profile</h1>
      {isLoading ? (
        <p>Loading...</p>
      ) : user ? (
        <div>
          <h2>User: {user.uName}</h2>
          <p>Email: {user.uEmail}</p>
          <div>
            <h3>
              <div>
                <h4>
                  Sexual Orientation: {sexualOrientation}{" "}
                  <EditProfileForm
                    onEditInfo={(newSO) => handleEditSO(user, newSO)}
                    info={sexualOrientation || ""}
                  />
                </h4>
              </div>
              <div>
                <h4>
                  Gender Orientation: {genderIdentity}{" "}
                  <EditProfileForm
                    onEditInfo={(newGI) => handleEditGI(user, newGI)}
                    info={genderIdentity || ""}
                  />
                </h4>
              </div>
              <div>
                <h4>
                  Note: {note}{" "}
                  <EditProfileForm
                    onEditInfo={(newNote) => handleEditNote(user, newNote)}
                    info={note || ""}
                  />
                </h4>
              </div>
            </h3>
          </div>
        </div>
      ) : (
        <p>No user data available</p>
      )}
    </div>
  );
}
  

export default CurrentUserProfile;