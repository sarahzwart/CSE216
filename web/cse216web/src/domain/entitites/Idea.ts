export class Idea {
    mId: number; // Unique identifier for the idea
    mTitle: string; // Title or subject of the idea
    mMessage: string; // Main content or description of the idea
    mComments: Comment[];
    constructor(id: number, title: string, content: string, comments: Comment[]){
      this.mId = id;
      this.mTitle = title;
      this.mMessage = content;
      this.mComments = comments;
    }
}