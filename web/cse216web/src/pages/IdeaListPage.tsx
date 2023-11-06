import React from 'react';
import './styles/IdeaListPage.css';
import IdeaList from '../domain/ui/components/IdeaList';
import { Link } from 'react-router-dom';

function IdeaListPage() {
    return (
    <div className="centered-container">
     <h1 style={{ color: '#9370db' }} >Margarita Villians</h1>
     <Link to="/profile">Profile</Link>
     <IdeaList />
     
    </div>
    );
  }
  
  export default IdeaListPage;