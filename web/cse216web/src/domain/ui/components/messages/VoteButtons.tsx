import { useState } from "react";
import axios from "axios";
import { FaRegThumbsDown } from "react-icons/fa";
import { FaRegThumbsUp } from "react-icons/fa";
//put likes --> /messages/id --> increment/decrements likes
import { Message } from "../../../entitites/Message";
let url = "https://team-margaritavillians.dokku.cse.lehigh.edu/messages/";

function VoteButtons({ message }: { message: Message }) {
  const [upVoteStatus, setUpVoteStatus] = useState(false);
  const [downVoteStatus, setDownVoteStatus] = useState(false);
  const headers = {
    "Content-Type": "application/json",
    Accept: "application/json",
  };
  // Handle UpVote Button Press
  const handleUpVote = () => {
    if (upVoteStatus == false) {
      setUpVoteStatus(true);
      axios
        .put(`${url}${message.mId}`, { uId: 1, isLike: 1 }, { headers })
        .then(() => {})
        .catch((error) => {
          console.error("Error when upVoting", error);
        });
    } else {
      setUpVoteStatus(false);
      axios
        .put(`${url}${message.mId}`, { uId: 1, isLike: 1 }, { headers })
        .then(() => {
          console.log("successfully updated likes");
        })
        .catch((error) => {
          console.error("Error when updating likes:", error);
        });
    }
  };

  // Handle Downvote Data
  const handleDownVote = () => {
    if (downVoteStatus == false) {
      setDownVoteStatus(true);
      axios
        .put(`${url}${message.mId}`, { uId: 1, isLike: 0 }, { headers })
        .then(() => {
          console.log("successfully updated dislikes");
        })
        .catch((error) => {
          console.error("Error when downvoting:", error);
        });
    } else {
      setDownVoteStatus(false);
      axios
        .put(`${url}${message.mId}`, { uId: 1, isLike: 0 }, { headers })
        .then(() => {
          console.log("successfully updated dislikes");
        })
        .catch((error) => {
          console.error("Error when updating likes:", error);
        });
    }
  };

  return (
    <div>
      <button onClick={handleUpVote}>
        <FaRegThumbsUp />
      </button>
      <button onClick={handleDownVote}>
        <FaRegThumbsDown />
      </button>
    </div>
  );
}
export default VoteButtons;
