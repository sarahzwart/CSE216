import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MessageListComponent } from './messages/message-list/message-list.component';
import { MessageDetailComponent } from './messages/message-detail/message-detail.component';
import { HttpClientModule } from '@angular/common/http';
import { AboutComponent } from './about/about/about.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MessagesService } from './services/messages.service';

@NgModule({
  declarations: [
    AppComponent,
    MessageListComponent,
    MessageDetailComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    RouterModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }