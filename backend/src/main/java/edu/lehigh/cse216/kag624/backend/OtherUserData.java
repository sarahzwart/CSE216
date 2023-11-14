package edu.lehigh.cse216.kag624.backend;

/**
 * 
 */
public class OtherUserData {
    //
    /**
     * name returned of other user 
     */
    public String uName;

    //
    /**
     * email returned of other user
     */
    public String uEmail;

    //
    /**
     * note returned of other user
     */
    public String uNote;

    /**
     * 
     * @param name
     * @param email
     * @param note
     */
    OtherUserData(String name, String email, String note){
        uName = name;
        uEmail = email;     //constructors used to set values
        uNote = note;
    }

    /**
     * 
     * @param data
     */
    OtherUserData(OtherUserData data){
        uName = data.uName;
        uEmail = data.uEmail;
        uNote = data.uNote;
    }
}
