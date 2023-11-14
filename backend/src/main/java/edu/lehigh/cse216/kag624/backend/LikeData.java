package edu.lehigh.cse216.kag624.backend;

/**
 * 
 */
public class LikeData {
    //
    /**
     * id of message liked
     */
    public int mId;

    //
    /**
     * title of message liked
     */
    public String mTitle;

    //
    /**
     * body of message liked
     */
    public String mMessage;

    /**
     * 
     * @param messageId
     * @param title
     * @param message
     */
    LikeData(int messageId, String title, String message){
        mId = messageId;
        mTitle = title;         //sets all the values
        mMessage = message;
    }

    /**
     * 
     * @param data
     */
    LikeData(LikeData data){
        mId = data.mId;
        mTitle = data.mTitle;
        mMessage = data.mMessage;
    }
}
