import { useState, useEffect} from "react";
import { User } from "../../../entitites/User";
import { useParams } from "react-router-dom";
import { fetchUser, editUserInfo } from "../../../../api/api";
import EditProfileForm from "./EditUserInfo";

function UserProfile() {
  // userId of profile clicked on
  const { userId } = useParams();
  const uId = Number(userId)
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
      uName: user.uName,
      uGI: user.uGI,
      uSO: uSO,
      uNote: user.uNote,
    }
    try {
      editUserInfo(editedUserSO);
      setSexualOrientation(uSO);
    } catch (error) {
      console.error("Error when  editing SO: ",error)
    }
  }
  function handleEditGI(user: User, uGI: string) {

    const editedUserGI: User = {
      uName: user.uName,
      uGI: uGI,
      uSO: user.uSO,
      uNote: user.uNote,
    }
    try {
      editUserInfo(editedUserGI);
      setGenderIdentity(uGI);
    } catch (error) {
      console.error("Error when  editing GI: ",error)
    }
  }


  function handleEditNote(user: User, uNote: string ) {
    const editedUserNote: User = {
      uName: user.uName,
      uGI: user.uGI,
      uSO: user.uSO,
      uNote: uNote,
    };
    try {
      editUserInfo(editedUserNote);
      setNote(uNote);
    } catch (error) {
      console.error("Error when  editing Note: ",error)
    }
  }

  // Get the user using a get request
  useEffect(() => {
    async function fetchData() {
      try {
        const userData = await fetchUser(uId);
        console.log(userData);
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
        <h2>User: {user.uName}</h2>
        <p>Email: {user.uEmail}</p>
        <div>
          <h3>
            <div>
              <h4>
                Sexual Orientation: {sexualOrientation}{user.uSO}
                <EditProfileForm
                  onEditInfo={(newSO) => handleEditSO(user, newSO)}
                  info={sexualOrientation || ""}
                />
              </h4>
            </div>
            <div>
              <h4>
                Gender Identity: {genderIdentity}{user.uGI}
                <EditProfileForm
                  onEditInfo={(newGI) => handleEditGI(user, newGI)}
                  info={genderIdentity || ""}
                />
              </h4>
            </div>
            <div>
              <h4>
                Note: {note}{user.uNote}
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

export default UserProfile;
