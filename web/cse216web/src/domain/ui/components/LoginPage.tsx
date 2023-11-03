import React from 'react';
import { Link } from 'react-router-dom'; // Assuming you're using React Router
import { Component } from 'react';
import axios from 'axios';

class LoginPage extends Component{
  constructor(props){
    super(props);
    this.handleOAuthLogin = this.handleOAuthLogin.bind(this);
  }
  handleOAuthLogin(){
    
  }
  render() {
    return(
      <div>
        <button onClick={this.handleOAuthLogin}>
            LogIn
        </button>
      </div>
    )
  }
}

export default LoginPage;