import React, { useState, useEffect } from 'react';
import {User} from '../../entitites/User';
import Axios from 'axios';
import { Component } from 'react';

  class UserProfile extends Component{
    constructor(props) {
        super(props);
        this.state = {}
    }
    render() {
        return(
            <div className="user-profile">
            <h2>Idea List</h2>
            <div key={userData.uId} className="idea-card">
                <p>UserName: {userData.uName}</p>
                <p>Email: {userData.uEmail}</p>
                <p>Sexual Orientation: {userData.uSO}</p>
                <p>Gender Identity: {userData.uGI}</p>
                <p>Note: {userData.uNote} </p>
            </div>
        </div>
    );
  }
}
  
export default UserProfile;