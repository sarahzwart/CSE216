import * as React from 'react';
import { Link } from 'react-router-dom'; // Assuming you're using React Router
import {User} from '../../entitites/User';
// get /users/id -->
function ProfileButton(user: User){
  return(
    <div>
      <Link to={`/user/${user.uId}`}>
        <button>Profile Button</button>
      </Link>
    </div>
  )
}

export default ProfileButton;
 