export interface Message {
    mId: number; // Unique identifier for the idea
    mTitle: string; // Title or subject of the idea
    mMessage: string; // Main content or description of the idea
    mLikes?: number;
    isLike?: boolean;
    uId: number;
}