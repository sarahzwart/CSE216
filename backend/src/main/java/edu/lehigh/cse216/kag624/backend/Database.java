package edu.lehigh.cse216.kag624.backend;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import org.postgresql.core.SqlCommand;

public class Database {
    /**
     * The connection to the database.  When there is no connection, it should
     * be null.  Otherwise, there is a valid open connection
     */
    private Connection mConnection;

    /**
     * A prepared statement for getting all data in the database
     */
    private PreparedStatement mSelectAll;

    /**
     * A prepared statement for getting one row from the database
     */
    private PreparedStatement mSelectOne;

    /**
     * A prepared statement for deleting a row from the database
     */
    private PreparedStatement mDeleteOne;

    /**
     * A prepared statement for inserting into the database
     */
    private PreparedStatement mInsertOne;

    /**
     * A prepared statement for add a like to a post
     */
    private PreparedStatement mUpdateLike;

    //Prepared Statement for getting all comments of a post
    private PreparedStatement cSelectAll;

    //Prepared Statement for editing a comment in the comment DB
    private PreparedStatement cEdit;

    //Prepared Statement for adding a comment to the comment DB
    private PreparedStatement cInsertOne;

    //Prepared Statement for getting all comments in comment DB
    private PreparedStatement cSelectEverything;

    private PreparedStatement cSelectOne;

    //Prepared Statement for getting one user's data from the user DB
    private PreparedStatement uSelectOne;

    //Prepared Statement for inserting a user into the user DB
    private PreparedStatement uInsertOne;

    //Prepared Statement for updating a user's info
    private PreparedStatement uUpdateOne;

    //Prepared Statement for getting the info of a different user
    private PreparedStatement uSelectOneOther;

    //Prepared Statement for getting the id and name of all users in user table
    private PreparedStatement uSelectAll;

    //Prepared Statement for inserting a like/dislike into the like DB
    private PreparedStatement lInsertOne;

    //Prepared Statement for removing a like/dislike from the like DB
    private PreparedStatement lRemoveOne;

    //Prepared Statement for getting the data from one like from the like DB
    private PreparedStatement lSelectOne;

    //Prepared Statement for getting all mIds of messages a specific user liked
    private PreparedStatement lSelectAll;
    
    //private PreparedStatement uDeleteOne;

    /**
     * A prepared statement for creating the table in our database
     */
    //private PreparedStatement mCreateTable;

    /**
     * A prepared statement for dropping the table in our database
     */
    //private PreparedStatement mDropTable;

    /**
     * RowData is like a struct in C: we use it to hold data, and we allow 
     * direct access to its fields.  In the context of this Database, RowData 
     * represents the data we'd see in a row.
     * 
     * We make RowData a static class of Database because we don't really want
     * to encourage users to think of RowData as being anything other than an
     * abstract representation of a row of the database.  RowData and the 
     * Database are tightly coupled: if one changes, the other should too.
     */

    /**
     * The Database constructor is private: we only create Database objects 
     * through the getDatabase() method.
     */
    private Database() {
    }

    /**
    * Get a fully-configured connection to the database
    * 
    * @param host The IP address or hostname of the database server
    * @param port The port on the database server to which connection requests
    *             should be sent
    * @param path The path to use, can be null
    * @param user The user ID to use when connecting
    * @param pass The password to use when connecting
    * 
    * @return A Database object, or null if we cannot connect properly
    */
    static Database getDatabase(String host, String port, String path, String user, String pass) {
        if( path==null || "".equals(path) ){
            path="/";
        }

        // Create an un-configured Database object
        Database db = new Database();

        // Give the Database object a connection, fail if we cannot get one
        try {
            String dbUrl = "jdbc:postgresql://" + host + ':' + port + path;
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            if (conn == null) {
                System.err.println("Error: DriverManager.getConnection() returned a null object");
                return null;
            }
            db.mConnection = conn;
        } catch (SQLException e) {
            System.err.println("Error: DriverManager.getConnection() threw a SQLException");
            e.printStackTrace();
            return null;
        }

        // Attempt to create all of our prepared statements.  If any of these 
        // fail, the whole getDatabase() call should fail
        try {
            // NB: we can easily get ourselves in trouble here by typing the
            //     SQL incorrectly.  We really should have things like "tblData"
            //     as constants, and then build the strings for the statements
            //     from those constants.

            // Note: no "IF NOT EXISTS" or "IF EXISTS" checks on table 
            // creation/deletion, so multiple executions will cause an exception
            /* CREATE and DROP table have been moved to admin, backend can only edit the tables
            db.mCreateTable = db.mConnection.prepareStatement(
                    "CREATE TABLE tblData (id SERIAL PRIMARY KEY, subject VARCHAR(50) "
                    + "NOT NULL, message VARCHAR(500) NOT NULL), likes INTEGER NOT NULL");
            db.mDropTable = db.mConnection.prepareStatement("DROP TABLE tblData");
            */

            // Standard CRUD operations
            //Prepared Statements for messages
            db.mDeleteOne = db.mConnection.prepareStatement("DELETE FROM tblData WHERE id = ?");
            db.mInsertOne = db.mConnection.prepareStatement("INSERT INTO tblData VALUES (default, ?, ?, ?, 0)");
            db.mUpdateLike = db.mConnection.prepareStatement("UPDATE tblData SET likes = ? WHERE id = ?");
            db.mSelectOne = db.mConnection.prepareStatement("SELECT * from tblData WHERE id=?");
            db.mSelectAll = db.mConnection.prepareStatement("SELECT * FROM tblData");

            //Prepared Statements for comments
            db.cSelectAll = db.mConnection.prepareStatement("SELECT * from commentData where mId = ?");
            db.cEdit = db.mConnection.prepareStatement("UPDATE commentData SET content = ? WHERE id = ?");
            db.cInsertOne = db.mConnection.prepareStatement("INSERT INTO commentData VALUES (default, ?, ?, ?)");
            db.cSelectEverything = db.mConnection.prepareStatement("Select * from commentData");
            db.cSelectOne = db.mConnection.prepareStatement("SELECT * from commentData WHERE id = ?");

            //Prepared Statements for users
            db.uInsertOne = db.mConnection.prepareStatement("INSERT INTO userData VALUES (default, ?, ?, ?, ?, ?)");
            db.uSelectOne = db.mConnection.prepareStatement("SELECT * FROM userData WHERE id = ?");
            db.uUpdateOne = db.mConnection.prepareStatement("UPDATE userData SET name = ?, GI = ?, SO = ?, note = ? WHERE id = ?");
            db.uSelectOneOther = db.mConnection.prepareStatement("SELECT name, email, note FROM userData WHERE id = ?");
            db.uSelectAll = db.mConnection.prepareStatement("SELECT id, name FROM userData");

            //Prepared Statements for likes
            db.lInsertOne = db.mConnection.prepareStatement("INSERT INTO likeData VALUES (default, ?, ?, ?)");
            db.lRemoveOne = db.mConnection.prepareStatement("DELETE FROM likeData WHERE mId = ? and uId = ?");
            db.lSelectOne = db.mConnection.prepareStatement("Select * FROM likeData WHERE mId = ? AND uId = ?");
            db.lSelectAll = db.mConnection.prepareStatement("SELECT mId FROM likeData WHERE uId = ?");
        } catch (SQLException e) {
            System.err.println("Error creating prepared statement");
            e.printStackTrace();
            db.disconnect();
            return null;
        }
        return db;
    } 

    /**
    * Get a fully-configured connection to the database
    * 
    * @param db_url The url to the database
    * @param port_default port to use if absent in db_url
    * 
    * @return A Database object, or null if we cannot connect properly
    */
    static Database getDatabase(String db_url, String port_default) {
        try {
            URI dbUri = new URI(db_url);
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String port = dbUri.getPort() == -1 ? port_default : Integer.toString(dbUri.getPort());

            return getDatabase(host, port, path, username, password);
        } catch (URISyntaxException s) {
            System.out.println("URI Syntax Error");
            return null;
        }
    } 

    /**
     * Close the current connection to the database, if one exists.
     * 
     * NB: The connection will always be null after this call, even if an 
     *     error occurred during the closing operation.
     * 
     * @return True if the connection was cleanly closed, false otherwise
     */
    boolean disconnect() {
        if (mConnection == null) {
            System.err.println("Unable to close connection: Connection was null");
            return false;
        }
        try {
            mConnection.close();
        } catch (SQLException e) {
            System.err.println("Error: Connection.close() threw a SQLException");
            e.printStackTrace();
            mConnection = null;
            return false;
        }
        mConnection = null;
        return true;
    }

    /**
     * Insert a row into the database
     * 
     * @param subject The subject for this new row
     * @param message The message body for this new row
     * 
     * @return The number of rows that were inserted
     */
    int insertRow(int uId, String subject, String message) {
        int newestId = -1;
        PreparedStatement getRecentId;
        try {
            mInsertOne.setInt(3, uId);      //now also inserts uId into DB
            mInsertOne.setString(1, subject);
            mInsertOne.setString(2, message);
            mInsertOne.executeUpdate();
            getRecentId = mConnection.prepareStatement("SELECT id FROM tblData ORDER BY id DESC limit 1");
            ResultSet resSet = getRecentId.executeQuery();
            while (resSet.next()) {
                newestId = (resSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newestId;
    }

    /**
     * Query the database for a list of all subjects and their IDs
     * 
     * @return All rows, as an ArrayList
     */
    ArrayList<DataRow> selectAll() {
        ArrayList<DataRow> res = new ArrayList<DataRow>();
        try {
            ResultSet rs = mSelectAll.executeQuery();
            while (rs.next()) {
                res.add(new DataRow(rs.getInt("id"), rs.getString("title"), rs.getString("message"), rs.getInt("uId"), rs.getInt("likes")));
            }
            rs.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all data for a specific row, by ID
     * 
     * @param id The id of the row being requested
     * 
     * @return The data for the requested row, or null if the ID was invalid
     */
    DataRow selectOne(int id) {
        DataRow res = null;
        try {
            mSelectOne.setInt(1, id);
            ResultSet rs = mSelectOne.executeQuery();   //now also gets uId
            if (rs.next()) {
                res = new DataRow(rs.getInt("id"), rs.getString("title"), rs.getString("message"), rs.getInt("uId"), rs.getInt("likes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Delete a row by ID
     * 
     * @param id The id of the row to delete
     * 
     * @return The number of rows that were deleted.  -1 indicates an error.
     */
    int deleteRow(int id) {
        int res = -1;
        try {
            mDeleteOne.setInt(1, id);
            res = mDeleteOne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    //adds 1 like to the message and created entry in like DB for that user
    int addLike(int mId, int uId) {
        int res = -1;
        try {
            DataRow msgData = selectOne(mId);    //get the number of likes for the post
            int likes = msgData.mLikes;
            mUpdateLike.setInt(1, (likes + 1)); //set the likes in the DB to the current likes + 1
            mUpdateLike.setInt(2, mId);
            res = mUpdateLike.executeUpdate();
            lInsertOne.setInt(1, mId);  //create an entry in the like DB with a 1 for like
            lInsertOne.setInt(2, uId);
            lInsertOne.setInt(3, 1);
            lInsertOne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    //removes 1 like from the message and deletes the entry in like DB for that user
    int removeLike(int mId, int uId){
        int res = -1;
        try{
            DataRow msgData = selectOne(mId);    //get number of likes for the message
            int likes = msgData.mLikes;
            mUpdateLike.setInt(1, (likes - 1)); //change the number of likes of message to likes - 1
            mUpdateLike.setInt(2, mId);
            res = mUpdateLike.executeUpdate();
            lRemoveOne.setInt(1, mId);  //remove row in likes DB for specific user
            lRemoveOne.setInt(2, uId);
            lRemoveOne.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    //removes 1 dislike from the message and deletes the entry in like DB for that user
    int removeDislike(int mId, int uId){
        int res = -1;
        try{
            DataRow msgData = selectOne(mId);    //get number of likes for message
            int likes = msgData.mLikes;
            mUpdateLike.setInt(1, (likes + 1)); //change number of likes of message to likes + 1 (remove dislike)
            mUpdateLike.setInt(2, mId);
            res = mUpdateLike.executeUpdate();
            lRemoveOne.setInt(1, mId);  //remove row in likes DB for specific user
            lRemoveOne.setInt(2, uId);
            lRemoveOne.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    //adds a dislike to the message and creates entry in like DB for that user
    int addDislike(int mId, int uId){
        int res = -1;
        try{
            DataRow msgData = selectOne(mId);    //get number of likes for the message
            int likes = msgData.mLikes;
            mUpdateLike.setInt(1, (likes - 1)); //set likes of message to likes - 1 
            mUpdateLike.setInt(2, mId);
            res = mUpdateLike.executeUpdate();
            lInsertOne.setInt(1, mId);  //insert a row in like DB for specific user
            lInsertOne.setInt(2, uId);    //set like = 0 
            lInsertOne.setInt(3, 0);
            lInsertOne.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    //used to check if a user already like or dislikes a message
    int isLiked(int mId, int uId){
        int res = 0;    //reponse is 0 by default
        try{
            lSelectOne.setInt(1, mId);  //get like data for a message from a specific user
            lSelectOne.setInt(2, uId);
            ResultSet rs = lSelectOne.executeQuery();
            if(rs.next()){
                if(rs.getInt("like") == 1){ //if the user liked the message
                    res = 1;                            //set response to 1
                }
                else{ //if the user disliked the message
                    res = 2;                                    //set the response to 2
                }
            }                                           //else response stays at 0
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;     //return response
    }

    //returns an arraylist of mIds, mTitles, and mMessages of all messages a specific user liked
    ArrayList<LikeData> getLikedMessages(int uId){
        ArrayList<LikeData> res = new ArrayList<LikeData>();    //create arraylist to store mIds, mTitles, and mMessages
        PreparedStatement getMessages;
        try{
            ArrayList<Integer> mIds = new ArrayList<Integer>();
            lSelectAll.setInt(1, uId);
            ResultSet rs = lSelectAll.executeQuery();       //execute query to get all mIds of liked messages from specified uId
            while(rs.next()){
                mIds.add(rs.getInt("mId")); //add all mIds to arrayList
            }
            getMessages = mConnection.prepareStatement("SELECT id, title, message FROM tblData WHERE id = ?");
            for(int i = 0; i < mIds.size(); i++){
                getMessages.setInt(1, mIds.get(i)); //get id, title, and message from all the mIds
                rs = getMessages.executeQuery();
                res.add(new LikeData(rs.getInt("id"), rs.getString("title"), rs.getString("message")));
            }   //add all ids, titles, and messages to response arrayList
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;     //return arrayList
    }

    //Returns one comment given a comment id
    CommentData selectOneComment(int cId){
        CommentData res = null;

        try{
            cSelectOne.setInt(1, cId);  //set cId in prepared statement
            ResultSet rs = cSelectOne.executeQuery();
            if(rs.next()){
                res = new CommentData(rs.getInt("id"), rs.getString("content"), rs.getInt("mId"), rs.getInt("uId"));
            }       //add result to commentdata constructor
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;     //return all comment data
    }

    //returns all the comments on a message in an arraylist of type CommentData
    ArrayList<CommentData> selectAllComments(int messageId){

        ArrayList<CommentData> res = new ArrayList<CommentData>();  //arraylist used to store comments

        try{
            cSelectAll.setInt(1, messageId);    //get resultset of comments
            ResultSet rs = cSelectAll.executeQuery();
            while(rs.next()){   //add comments to arraylist 
                res.add(new CommentData(rs.getInt("id"), rs.getString("content"), rs.getInt("mId"), rs.getInt("uId")));
            }
            rs.close();
            return res; //return arraylist
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    //returns all comments from comment DB
    ArrayList<CommentData> getAllComments(){

        ArrayList<CommentData> res = new ArrayList<CommentData>();  //arraylist used to store comments

        try{
            ResultSet rs = cSelectEverything.executeQuery();    //get resultset of comments
            while(rs.next()){                       //add comments to arraylist
                res.add(new CommentData(rs.getInt("id"), rs.getString("content"), rs.getInt("mId"), rs.getInt("uId")));
            }
            rs.close();
            return res; //return arraylist
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    //edits a comment 
    int updateComment(int commentId, String newContent){
        int res = -1;
        try{
            cEdit.setInt(2, commentId); //updating the comment with new content in DB
            cEdit.setString(1, newContent);
            res = cEdit.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    //insert a new comment to the comment DB
    int insertComment(int mId, String content, int uId){
        int newestId = -1;
        PreparedStatement getRecentId;
        try{
            cInsertOne.setString(1, content);
            cInsertOne.setInt(2, mId);      //set mId and uId so you can get comments from a message and from a user
            cInsertOne.setInt(3, uId);
            cInsertOne.executeUpdate();
            getRecentId = mConnection.prepareStatement("SELECT id from commentData ORDER BY id DESC limit 1");
            ResultSet resSet = getRecentId.executeQuery();
            if(resSet.next()){
                newestId = resSet.getInt("id");   //get the new id from this comment
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return newestId;        //return the new id
    }

    //inserts a new user into the user DB
    int insertUser(String name, String email, String GI, String SO, String note){
        int newestId = -1;
        PreparedStatement getRecentId;
        try{
            uInsertOne.setString(1, name);
            uInsertOne.setString(2, email);     //set all the user's info into prepared statement
            uInsertOne.setString(3, GI);
            uInsertOne.setString(4, SO);
            uInsertOne.setString(5, note);
            uInsertOne.executeUpdate();                      //execute prepared statement
            getRecentId = mConnection.prepareStatement("Select id from userData ORDER BY id DESC limit 1");
            ResultSet resSet = getRecentId.executeQuery();
            while(resSet.next()){
                newestId = (resSet.getInt("id"));   //get the new id for the user
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return newestId;    //return that user's id
    }

    //get one user's data from the user DB
    UserData selectOneUser(int id){
        UserData res = null;
        try{
            uSelectOne.setInt(1, id);   //pass in user's id
            ResultSet rs = uSelectOne.executeQuery();
            if(rs.next()){  //set respose equal to UserData
                res = new UserData(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("GI"), rs.getString("SO"), rs.getString("note"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res; //return user data
    }

    //get a different user's info (only returns name, email, and note)
    OtherUserData selectOneOtherUser(int id){
        OtherUserData res = null;
        try{
            uSelectOneOther.setInt(1, id);
            ResultSet rs = uSelectOneOther.executeQuery();  //set id in query
            if(rs.next()){
                res = new OtherUserData(rs.getString("name"), rs.getString("email"), rs.getString("note"));
            }       //add name, email, and note to otheruserdata constructor
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;     //return data added to otheruserdata
    }

    //get id and name of all users in user table
    ArrayList<AllUserData> selectAllUsers(){                        //we probably dont need this but sarah asked for it
        ArrayList<AllUserData> res = new ArrayList<AllUserData>();
        try{
            ResultSet rs = uSelectAll.executeQuery();   //execute the query to run the select statement in db
            while(rs.next()){
                res.add(new AllUserData(rs.getInt("id"), rs.getString("name")));
            }       //add all users to response arraylist
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;     //return arraylist
    }    

    //updates a user's info in user table
    int updateUser(int id, String name, String GI, String SO, String note){
        int res = -1;
        try{
            uUpdateOne.setString(1, name);  //set all the data given by the user 
            uUpdateOne.setString(2, GI);
            uUpdateOne.setString(3, SO);
            uUpdateOne.setString(4, note);
            uUpdateOne.setInt(5, id);
            res = uUpdateOne.executeUpdate();       //execute the query
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Create tblData.  If it already exists, this will print an error
    
    void createTable() {
        try {
            mCreateTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     * Remove tblData from the database.  If it does not exist, this will print
     * an error.
    
    void dropTable() {
        try {
            mDropTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    */
}