import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Message } from 'src/app/models/Message';
//import { ApiService } from './api.service';
import { MessagesService } from 'src/app/services/messages.service';

@Component({
  selector: 'app-editMessage',
  templateUrl: './editMessage.component.html',
  styleUrls: ['./editMessage.component.css']
})
export class EditComponent implements OnInit {
  // Variables
  //private url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/index.html/'
  //private url = 'https://team-margaritavillians.dokku.cse.lehigh.edu'???
  //private url = 'https://cse216-angular.onrender.com';
  //private url = 'http://localhost:3000';
  private url = 'http://localhost:4567';

  // Constructors
  constructor(private messageService: MessagesService, private router: Router) {}
  message = new Message();

  // Methods
  ngOnInit(): void {
  }
  

  // Method to return to messages Page
  public returnToMessages(): void {
    // This gets us back to the messages component page.
    this.router.navigate(['/messages']);
  }
  // Method to add messages
  public editMessages(): void {
    //messsage = new Message();
    //let mId = this.messages.length;
    //alert('POST check for ' + mId);
    //this.message.mId = this.messages.length;
    //if (this.message.mId==undefined){
    //  this.message.mId = 0;
    //}
    //alert('POST check for ' + message.mId);
    this.message.mTitle = "" + (<HTMLInputElement>document.getElementById("newTitle")).value;
    this.message.mContent = "" + (<HTMLInputElement>document.getElementById("newMessage")).value;
    if (this.message.mTitle === "" || this.message.mContent === "") {
        window.alert("Error: title or message is not valid");
        return;
    }
    this.message.mLikes = 0;
    alert('POST check for ' + this.message.mTitle + ' and '+ this.message.mContent);
    //{id; title; msg; like: 0};
    //message: [{id, title, msg, like: 0}];
    //this.http.delete(localUrl + id + '.json')
    try {
    this.messageService.editMessage(this.message.mTitle, this.message.mContent);
    /*.subscribe(data => {
      alert('POST functionality broke down for ' + this.message.mTitle + ' and '+ this.message.mContent + ' and id: ' + this.message.mId);
      console.log(data)
    })     /*subscribe({
      next: (response) => {
        console.log('POST request successful, returned: ', response);
        // 'response' is a json with mStatus and mData. mData contains the list of messages.
      },
      error: (err) => {
        alert('POST functionality broke down for ' + this.message.mTitle + ' and '+ this.message.mContent + ' and id: ' + this.message.mId);
        console.log('POST request failed: ', err);
      },
      complete: () => {
        alert('POST completes the subscribe part');
        console.log('POST complete!');
      }
    })//*/
    } catch(err){
      alert('Edit functionality broke down for ' + this.message.mTitle + ' and '+ this.message.mContent);
      console.log('POST request failed: ', err);
    }
    this.router.navigate(['/messages']);
  }
}