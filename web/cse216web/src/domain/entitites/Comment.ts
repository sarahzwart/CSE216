export class Comment {
    mId: number; 
    mMessage: string; 
    mTitle: String; 
    mUser: String;
    constructor(id: number, content: string, user: string) {
        this.mId = id;
        this.mMessage = content;
        this.mTitle = 'Title';
        this.mUser = user;
    }
}