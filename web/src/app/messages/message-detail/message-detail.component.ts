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
  @Input() message!: Message;
  // Variables
  constructor(private messageService: MessagesService, private router: Router) {}

  ngOnInit(): void {
    this.router.navigate(['http://localhost:4567']);
  }

  public onLikeClick(): void {
    const id = this.message.mId;
    const likes = this.message.mLikes+1;
    //this.http.l(localUrl + id + '.json')
    this.messageService.likeMessage(id, likes)/*.subscribe({
      next: (response) => {
        console.log('DELETE request successful, returned: ', response);
        // 'response' is a json with mStatus and mData. mData contains the list of messages.
        this.message = response['messages'];
      },
      error: (err) => {
        alert('Delete functionality broke down');
        console.log(id + ' DELETE request failed: ', err);
      },
      complete: () => {
        console.log('DELETE complete!');
      }
    })//*/
    //let messages = this.messageService.getMessages();
    this.ngOnInit();
  }

  public onEditClick(): void{
    alert('Edit functionality not implemented yet');
  }

  public onDeleteClick(): void {
    const id = this.message.mId;
    //this.http.delete(localUrl + id + '.json')

    this.messageService.deleteMessage(id)/*.subscribe({
      next: (response) => {
        console.log('DELETE request successful, returned: ', response);
        // 'response' is a json with mStatus and mData. mData contains the list of messages.
        this.message = response['messages'];
      },
      error: (err) => {
        alert('Delete functionality broke down for ' + id);
        console.log(id + ' DELETE request failed: ', err);
      },
      complete: () => {
        console.log('DELETE complete!');
      }
    })*/
    //this.router.navigate([this.router.url]);
    this.ngOnInit();
  }
}