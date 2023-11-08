package edu.lehigh.cse216.kag624.backend;

import java.util.Date;

public class CommentData {

    //id of the comment
    public final int cId;

    //content of the comment
    public String cContent;

    //id of the message that the comment is posted on
    public int mId;

    //if of the user who posted the comment
    public int uId;

    //date that the comment was posted
    public final Date cDate;

    CommentData(int id, String content, int messageId, int userId){
        cId = id;
        mId = messageId;
        cContent = content;
        uId = userId;
        cDate = new Date();
    }

    CommentData(CommentData data){
        cId = data.cId;
        cContent = data.cContent;
        mId = data.mId;
        uId = data.uId;
        cDate = data.cDate;
    }
}
