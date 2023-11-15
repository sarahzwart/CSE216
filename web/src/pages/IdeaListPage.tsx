import "./styles/IdeaListPage.css";
import IdeaList from "../domain/ui/components/messages/IdeaList";
import { Link } from "react-router-dom";

function IdeaListPage() {
  // get current users id and navigate to their profile
  const currentUser = window.sessionStorage.getItem("userId");
  console.log(currentUser)
  return (
    <div className="centered-container">
      <h1 style={{ color: "#9370db" }}>Margarita Villians</h1>
      <Link to={`/currentuserprofile`}>Profile</Link>
      <IdeaList />
    </div>
  );
}

export default IdeaListPage;
