package edu.lehigh.cse216.kag624.backend;

//import java.util.Date;

public class UserData {

    //id of the user
    public final int uId;

    //user's name
    public String uName;
    
    //user's email
    public String uEmail;

    //user's gender identity
    public String uGI;

    //user's sexual orientation
    public String uSO;

    //user's note
    public String uNote;

    UserData(int id, String name, String email, String GI, String SO, String note){
        uId = id;
        uName = name;
        uEmail = email;
        uGI = GI;
        uSO = SO;
        uNote = note;
    }

    UserData(UserData data){
        uId = data.uId;
        uName = data.uName;
        uEmail = data.uEmail;
        uGI = data.uGI;
        uSO = data.uSO;
        uNote = data.uNote;
    }
}
