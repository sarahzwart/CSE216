import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Message } from 'src/app/models/Message';
//import { ApiService } from './api.service';
import { MessagesService } from 'src/app/services/messages.service';

@Component({
  selector: 'app-addMessage',
  templateUrl: './addMessage.component.html',
  styleUrls: ['./addMessage.component.css']
})
export class AddComponent implements OnInit { 
  //REAL LINKS
  //private url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/index.html/'
  //private url = 'https://team-margaritavillians.dokku.cse.lehigh.edu'???
  
  //TEST LINKS
  //Test with make database
  //private url = 'https://cse216-angular.onrender.com'; //Tutorial Test
  //private url = 'http://localhost:3000'; //Fake Database Test
  private url = 'http://localhost:4567'; //Run on Local Server with our backend functionality

  // Constructors
  constructor(private messageService: MessagesService, private router: Router) {}
  messages: Message[] = [];
  message = new Message();

  // Method to start adding a message
  ngOnInit(): void {
    this.messages = this.messageService.getMessages();
  }
  // Method to return to messages Page
  public returnToMessages(): void {
    // This gets us back to the messages component page.
    this.router.navigate(['/messages']);
  }
  // Method to add messages
  public addToMessages(): void {
    //alert('POST check for ' + message.mId);
    //pull all the data from the addMessage html file
    this.message.mTitle = "" + (<HTMLInputElement>document.getElementById("newTitle")).value;
    this.message.mContent = "" + (<HTMLInputElement>document.getElementById("newMessage")).value;
    if (this.message.mTitle === "" || this.message.mContent === "") {
        window.alert("Error: title or message is not valid");
        return;
    }
    this.message.mLikes = 0;
    //alert('POST check for ' + this.message.mTitle + ' and '+ this.message.mContent);
    //send the necessary data to the database, only title and content since database creates likes and id
    try {
      this.messageService.postMessage(this.message.mTitle, this.message.mContent);
    } catch(err){
      //alert to a breakdown but show what data is involved
      alert('POST functionality broke down for ' + this.message.mTitle + ' and '+ this.message.mContent);
      console.log('POST request failed: ', err);
    }
    this.router.navigate(['/messages']);
  }
}