import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MessageListComponent } from './messages/message-list/message-list.component';
import { AboutComponent } from './about/about/about.component';
import { AddComponent } from './addMessage/addMessage/addMessage.component';

const routes: Routes = [
  {path: 'messages', component: MessageListComponent},
  {path: 'about', component: AboutComponent},
  {path: 'addMessage', component: AddComponent},
  {path: '', pathMatch: 'full', redirectTo: 'messages'} // This is the default path (redirects to messages page)
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }