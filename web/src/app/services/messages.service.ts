import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Message } from 'src/app/models/Message';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {
  /**
   *  Make sure to replace this url to your own 
   *  backend url when you complete the tutorial
   */
  //private url = 'https://team-margaritavillians.dokku.cse.lehigh.edu/index.html/'
  //private url = 'https://team-margaritavillians.dokku.cse.lehigh.edu'???
  //private url = 'https://cse216-angular.onrender.com';
  //private url = 'http://localhost:3000';
  private url = 'http://localhost:4567';

  constructor(public httpClient: HttpClient) { }

  //These functions are GET POST DELETE and PUT for the backend
  public getMessages() {
    let messages: Message[] = [];
    //return this.httpClient.get<any>(this.url + "/mData");
    //return this.httpClient.get<any>(this.url + "/messages");
    const doAjax = async () => {
      ///*
      await fetch(`${this.url}/messages`, {
          method: 'GET',
          headers: {
              'Content-type': 'application/json; charset=UTF-8'
          }
      }).then( (response) => {
          // If we get an "ok" message, clear the form
          if (response.ok) {
              return Promise.resolve( response.json() );
          }
          // Otherwise, handle server errors with a detailed popup message
          else{
              window.alert(`The server replied not ok: ${response.status}\n` + response.statusText);
          }
          return Promise.reject(response);
      }).then( (data) => {
          for (let i = 0; i < data.mData.length; i++) {
            let message = new Message();
            message.mId = data.mData[i].mId;
            message.mTitle = data.mData[i].mTitle;
            message.mContent = data.mData[i].mMessage;
            message.mLikes = data.mData[i].mLikes;
            //alert('message length ' + data.mData[i].mId);
            messages.push(message);
          }
          console.log(messages);
          return messages;
      }).catch( (error) => {
          console.warn('Something went wrong.', error);
          window.alert("Unspecified error");
      });//*/
    }
    doAjax().then(console.log).catch(console.log);
    //return this.httpClient.get<any>(this.url + "/messages");
    return messages;
  }
 //postMessage works by adding in the title and string
 //Then it runs the ajax to pass it to the database
 //but angular wont be satisfied unless we return an observable so we have to do that too
  //public postMessage(message: Message): Observable<any>{
  //public postMessage(title: string, content: string): void{
  public postMessage(title: string, content: string): Observable<any>{
    //let id = mID;
    //let title = mTitle;
    //let msg = mContent;
    //const id = (<HTMLElement>any).getAttribute("data-value");
    ///*    // set up an AJAX POST. 
    // When the server replies, the result will go to onSubmitResponse
    const headers = { 'content-type': 'application/json'}  
    const body=JSON.stringify({"mTitle": title, "mMessage": content});
    //const body=JSON.stringify(message);
    //alert('POST gets to console.log body');
    console.log(body)
    //alert('POST gets to the end of the postMessage Function');
    //return this.httpClient.post(this.url + '/messages', body,{'headers':headers})
    //return this.httpClient.post(this.url + '/mData', body,{'headers':headers})
    //*/    
    ///*
    const doAjax = async () => {
      //await fetch(`${this.url}/messages`, {
      await fetch(`/messages`, {
        method: 'POST',
        body: JSON.stringify({
          //mId: message.mId,
          mTitle: title,
          mMessage: content,
        }),
        headers: {
          'Content-type': 'application/json; charset=UTF-8'
        }
        }).then( (response) => {
        // If we get an "ok" message, return the json
        if (response.ok) {
          return Promise.resolve( response.json() );
        }
        // Otherwise, handle server errors with a detailed popup message
        else{
          window.alert(`The server replied not ok: ${response.status}\n` + response.statusText);
        }
        return Promise.reject(response);
        }).then( (data) => {
          //newEntryForm.onSubmitResponse(data);
          console.log(data);
        }).catch( (error) => {
          console.warn('Something went wrong.', error);
          window.alert("Unspecified error");
        });
      }
      // make the AJAX post and output value or error message to console
      doAjax().then(console.log).catch(console.log);
      //*/
      return this.httpClient.post(this.url + '/messages', body,{'headers':headers})

  }
  public likeMessage(id: number, likes: number): void {//: Observable<any>{
    //const id = (<HTMLElement>any).getAttribute("data-value");
    //return this.httpClient.put<any>(this.url + "/messages/" + id);
    //return this.httpClient.delete<any>(this.url + "/mData/" + id);
    //await fetch(`${this.url}/messages`, {
    // as in clickLike, we need the ID of the row
    
    // Issue an AJAX GET and then pass the result to editEntryForm.init()
    const doAjax = async () => {
      //await fetch(`${this.url}/messages`, {
      await fetch(`${this.url}/messages/${id}`, {
        method: 'PUT',
        body: JSON.stringify({
          //mId: message.mId,
          mLikes: likes
        }),
        headers: {
          'Content-type': 'application/json; charset=UTF-8'
        }
        }).then( (response) => {
        // If we get an "ok" message, return the json
        if (response.ok) {
          return Promise.resolve( response.json() );
        }
        // Otherwise, handle server errors with a detailed popup message
        else{
          window.alert(`The server replied not ok: ${response.status}\n` + response.statusText);
        }
        return Promise.reject(response);
        }).then( (data) => {
          //newEntryForm.onSubmitResponse(data);

          console.log(data);
        }).catch( (error) => {
          console.warn('Something went wrong.', error);
          window.alert("Unspecified error");
        });
      }
      // make the AJAX post and output value or error message to console
      doAjax().then(console.log).catch(console.log);
      //*/
  }
    //*/
  public deleteMessage(id: number): Observable<any>{
    //const id = (<HTMLElement>any).getAttribute("data-value");
            // Issue an AJAX DELETE and then invoke refresh()
            const doAjax = async () => {
              await fetch(`${this.url}/messages/${id}`, {
                  method: 'DELETE',
                  headers: {
                      'Content-type': 'application/json; charset=UTF-8'
                  }
              }).then( (response) => {
                  if (response.ok) {
                      return Promise.resolve( response.json() );
                  }
                  else{
                      window.alert(`The server replied not ok: ${response.status}\n` + response.statusText);
                  }
                  return Promise.reject(response);
              }).then( (data) => {
                  //mainList.refresh();
                  console.log(data);
              }).catch( (error) => {
                  console.warn('Something went wrong.', error);
                  window.alert("Unspecified error");
              });
          }
  
          // make the AJAX post and output value or error message to console
          doAjax().then(console.log).catch(console.log);
          // TODO: we've repeated the same pattern 3+ times now, so we should really
          //   think about refactoring and abstracting this boilerplate into something
          //   easier to reuse, if possible 
    return this.httpClient.delete<any>(this.url + "/messages/:" + id);
    //return this.httpClient.delete<any>(this.url + "/mData/" + id);
  }
  public editMessage(title: string, content: string): void{
    //const id = (<HTMLElement>any).getAttribute("data-value");
    //return this.httpClient.delete<any>(this.url + "/messages/:" + id);
    //return this.httpClient.delete<any>(this.url + "/mData/" + id);
  }
}