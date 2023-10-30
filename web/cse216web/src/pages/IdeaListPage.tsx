import React from 'react';
import { Link } from 'react-router-dom';
function IdeaListPage() {
    return (
    <div>
     <p>This is the IdeaList Page</p>
     <Link to="/profile">Profile</Link>
     </div>
    );
  }
  
  export default IdeaListPage;