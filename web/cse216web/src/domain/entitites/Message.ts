export class Message {
    mId: number; // Unique identifier for the idea
    mTitle: string; // Title or subject of the idea
    mMessage: string; // Main content or description of the idea
    mLikes: number;
    uId: number;
    constructor(id: number, title: string, content: string, user: number, likes: number){
      this.mId = id;
      this.mTitle = title;
      this.mMessage = content;
      this.uId= user; 
      this.mLikes = likes;
    }
}