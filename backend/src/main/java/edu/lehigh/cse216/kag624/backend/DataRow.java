package edu.lehigh.cse216.kag624.backend;

import java.util.Date;

/**
 * DataRow holds a row of information.  A row of information consists of
 * an identifier, strings for a "title" and "content", and a creation date.
 * 
 * Because we will ultimately be converting instances of this object into JSON
 * directly, we need to make the fields public.  That being the case, we will
 * not bother with having getters and setters... instead, we will allow code to
 * interact with the fields directly.
 */
public class DataRow {
    /**
     * The unique identifier associated with this element.  It's final, because
     * we never want to change it.
     */
    public final int mId;

    /**
     * The title for this row of data
     */
    public String mTitle;

    /**
     * The content for this row of data
     */
    public String mMessage;

    /**
     * Number of likes
     */
    public int mLikes;

    /**
     * The creation date for this row of data.  Once it is set, it cannot be 
     * changed
     */
    public final Date mCreated;

    //
    /**
     * id of user who created the message
     */
    public final int uId;

    /**
     * 
     * @param id
     * @param title
     * @param message
     * @param userId
     * @param likes
     */
    DataRow(int id, String title, String message, int userId, int likes) {
        mId = id;
        mTitle = title;
        mMessage = message;
        mLikes = likes;
        uId = userId;
        mCreated = new Date();
    }

    /**
     * 
     * @param data
     */
    DataRow(DataRow data) {
        mId = data.mId;
        // NB: Strings and Dates are immutable, so copy-by-reference is safe
        mTitle = data.mTitle;
        mMessage = data.mMessage;
        mLikes = data.mLikes;
        uId = data.uId;
        mCreated = data.mCreated;
    }
}