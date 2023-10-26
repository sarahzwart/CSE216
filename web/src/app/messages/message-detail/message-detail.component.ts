import { Component, Input, OnInit } from '@angular/core';
import { Message } from 'src/app/models/Message';
import { MessagesService } from 'src/app/services/messages.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-message-detail',
  templateUrl: './message-detail.component.html',
  styleUrls: ['./message-detail.component.css']
})
export class MessageDetailComponent implements OnInit{
  // Variables
  @Input() message!: Message;
  //messages: Message[] = []; 
  clickedLike = false;
  constructor(private messageService: MessagesService, private router: Router) {}

  ngOnInit(): void {
    //this.messages = this.messageService.getMessages();
  }

  //ClickLike not finished yet
  public onLikeClick(): void {
    const id = this.message.mId;
    let likes = this.message.mLikes;
    if (this.clickedLike == true){
      likes = likes-1;
      this.clickedLike = false;
    } else {
      likes = likes+1;
      this.clickedLike = true;
    }
    this.messageService.likeMessage(id, likes)
    this.router.navigate([''])  
  }

  public onEditClick(): void{
    alert('Edit functionality not implemented yet');
  }

  public onDeleteClick(): void {
    const id = this.message.mId;
    //this.http.delete(localUrl + id + '.json')

    this.messageService.deleteMessage(id);
    this.router.navigate([''])  
  }
}