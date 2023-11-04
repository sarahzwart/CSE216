import React from 'react';
import IdeaList from '../domain/ui/components/IdeaList';
import { Link } from 'react-router-dom';
function IdeaListPage() {
    return (
    <div className="IdeaListPage">
     <p>This is the IdeaList Page</p>
     <Link to="/profile">Profile</Link>
     <IdeaList />
     </div>
    );
  }
  
  export default IdeaListPage;