package edu.lehigh.cse216.kag624.backend;

/**
 * 
 */
public class AllUserData {
    /*
    user id of users in db
     */
    public final int uId;

    /*
     * names of users in db
     */
    public String uName;

    /**
     * 
     * @param id
     * @param name
     */
    AllUserData(int id, String name){
        uId = id;
        uName = name;
    }
}
