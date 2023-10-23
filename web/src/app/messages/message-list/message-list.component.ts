import { Component, OnInit } from '@angular/core';
import { Message } from 'src/app/models/Message';
import { MessagesService } from 'src/app/services/messages.service';
import { DUMMYMESSAGES } from 'src/app/Message-Dummy';
import { Router } from '@angular/router';

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent implements OnInit{
  // Variables
  // messages: Message[] = DUMMYMESSAGES;
  messages: Message[] = [];

  // Constructor
  constructor(private messageService: MessagesService, private router: Router) {}

  // Methods
  ngOnInit(): void {
    this.messages = this.messageService.getMessages();/*.subscribe(
      data => {
        console.log(data)
        if (data.length!==0){
          for (let i = 0; i < data.length; i++) {
            let message = new Message();
            message.mId = data[i].mId;
            message.mTitle = data[i].mTitle;
            message.mContent = data[i].mMessage;
            message.mLikes = data[i].mLikes;
            alert('message length ' + data[i].mId);
            this.messages.push(message);
          }
        }

      })      
    /*  next: (response) => {
        console.log('GET request successful, returned: ', response);
        // 'response' is a json with mStatus and mData. mData contains the list of messages.
        this.messages = response['mData'];
      },
      error: (err) => {
        console.log('GET request failed: ', err);
      },
      complete: () => {
        console.log('GET complete!');
      }
    })//*/
    //alert('message length ' + this.messages[0].mId);
    //alert('message length ' + this.messages.length);
  }

  public aboutButtonClick(): void {
    this.router.navigate(['/about']);
  }
  public addButtonClick(): void{
    this.router.navigate(['/addMessage']);    
  }
}