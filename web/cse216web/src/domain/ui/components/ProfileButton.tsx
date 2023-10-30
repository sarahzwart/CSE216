import React from 'react';
import { Link } from 'react-router-dom'; // Assuming you're using React Router

const ProfileButton = () => {
  return (
    <Link to="/profile" className="profile-button">
      Profile
    </Link>
  );
};

export default ProfileButton;