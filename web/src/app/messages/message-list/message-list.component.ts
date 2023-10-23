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
  //get all the messages from the backend
  ngOnInit(): void {
    this.messages = this.messageService.getMessages();
    //alert('message length ' + this.messages.length); //debugging
  }
  //Buttons that route your to various other folders
  public aboutButtonClick(): void {
    this.router.navigate(['/about']);
  }
  public addButtonClick(): void{
    this.router.navigate(['/addMessage']);    
  }
}