export class Comment {
    cId: number;
    mId: number; 
    uId: number;
    cContent: String
    constructor(commentId: number, messageId: number, userId: number, commentContent: string) {
        this.cId = commentId;
        this.mId = messageId;
        this.uId = userId;
        this.cContent = commentContent;
    }
}