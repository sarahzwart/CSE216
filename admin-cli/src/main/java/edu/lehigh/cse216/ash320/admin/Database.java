package edu.lehigh.cse216.ash320.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class Database {
    /**
     * The connection to the database.  When there is no connection, it should
     * be null.  Otherwise, there is a valid open connection
     */
    private Connection mConnection;
    /**
     * A prepared statement for deleting a row from the database
     */
    private PreparedStatement mDeleteOne;
    /**
     * A prepared statement for inserting into the database
     */
    private PreparedStatement mInsertOne;
    /**
     * A prepared statement for creating the table in our database
     */
    private PreparedStatement mCreateTable;
    /**
     * A prepared statement for dropping the table in our database
     */
    private PreparedStatement mDropTable;
    /**
     * A prepared statement for validating/invalidating a user
     */
    private PreparedStatement mValidate;
    //USER STUFF
    /**
     * A prepared statement for deleting a row from the database
     */
    private PreparedStatement uDeleteOne;
    /**
     * A prepared statement for inserting into the database
     */
    private PreparedStatement uInsertOne;
    /**
     * A prepared statement for creating the table in our database
     */
    private PreparedStatement uCreateTable;
    /**
     * A prepared statement for dropping the table in our database
     */
    private PreparedStatement uDropTable;
    /**
     * A prepared statement for validating/invalidating a user
     */
    private PreparedStatement uValidate;
    //COMMENT STUFF
    /**
     * A prepared statement for deleting a row from the database
     */
    private PreparedStatement cDeleteOne;
    /**
     * A prepared statement for inserting into the database
     */
    private PreparedStatement cInsertOne;
    /**
     * A prepared statement for creating the table in our database
     */
    private PreparedStatement cCreateTable;
    /**
     * A prepared statement for dropping the table in our database
     */
    private PreparedStatement cDropTable;
    //LIKE STUFF
    /**
     * A prepared statement for inserting into the database
     */
    private PreparedStatement lInsertOne;
    /**
     * A prepared statement for creating the table in our database
     */
    private PreparedStatement lCreateTable;
    /**
     * A prepared statement for dropping the table in our database
     */
    private PreparedStatement lDropTable;
    /**
     * A prepared statement for deleting the table in our database
     */
    private PreparedStatement lDeleteOne;

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
    public static class RowData {
        /**
         * The ID of this row of the database
         */
        int mId;
        /**
         * The subject stored in this row
         */
        String mTitle;
        /**
         * The message stored in this row
         */
        String mMessage;
        /**
         * The user Id for this Idea
         */
        int uId;
        /**
         * The Like count for this Idea
         */
        int mLikes;
        //is the user invalid?
        public Boolean mInvalid;
        /**
         * Construct a RowData object by providing values for its fields
         */
        public RowData(int id, String subject, String message, int userId, int likes, Boolean invalid) {
            mId = id;
            mTitle = subject;
            if(message.length()<=2048){ //ensures correct length
                mMessage = message;
            }
            else{ //shortens message if it is too large
                mMessage = message.substring(0, Math.min(message.length(), 2048));
            }            mLikes = likes;
            uId = userId;
            mInvalid = invalid;
        }
    }
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
    public static class UserData {
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
        //user's id token
        public String uIdToken;
        //is the user invalid?
        public Boolean uInvalid;
    
        public UserData(int id, String u, String c, String g, String so, String n, String i, Boolean invalid){
            uId = id;
            if(u.length()<=50){ //ensures correct length
                uName = u;
            }
            else{ //shortens message if it is too large
                uName = u.substring(0, Math.min(c.length(), 50));
            }
            if(c.length()<=50){ //ensures correct length
                uEmail = c;
            }
            else{ //shortens message if it is too large
                uEmail = c.substring(0, Math.min(c.length(), 50));
            }
            if(g.length()<=50){ //ensures correct length
                uGI = g;
            }
            else{ //shortens message if it is too large
                uGI = g.substring(0, Math.min(c.length(), 50));
            }
            if(so.length()<=50){ //ensures correct length
                uSO = so;
            }
            else{ //shortens message if it is too large
                uSO = so.substring(0, Math.min(c.length(), 50));
            }
            if(n.length()<=2048){ //ensures correct length
                uNote = n;
            }
            else{ //shortens message if it is too large
                uNote = n.substring(0, Math.min(c.length(), 2048));
            }
            if(i.length()<=2048){ //ensures correct length
                uIdToken = i;
            }
            else{ //shortens message if it is too large
                uIdToken = i.substring(0, Math.min(c.length(), 2048));
            }
            uInvalid = invalid;
        }
    }

    public static class CommentData {
        //id of the comment
        public final int cId;
        //content of the comment
        public String cContent;
        //id of the message that the comment is posted on
        public int mId;
        //if of the user who posted the comment
        public int uId;

        public CommentData(int id, String message, int messageId, int userId){
            cId = id;
            mId = messageId;
            if(message.length()<=2048){ //ensures correct length
                cContent = message;
            }
            else{ //shortens message if it is too large
                cContent = message.substring(0, Math.min(message.length(), 2048));
            }            
            uId = userId;
        }
    }
    /* //Prepared Statements for likes
     db.lInsertOne = db.mConnection.prepareStatement("INSERT INTO likeData VALUES (default, ?, ?, ?)");
     db.lRemoveOne = db.mConnection.prepareStatement("DELETE FROM likeData WHERE mId = ? and uId = ?");
     db.lSelectOne = db.mConnection.prepareStatement("Select * FROM likeData WHERE mId = ? AND uId = ?");
    */
    public static class LikeData {
        //id of the like
        public final int lId;
        //id of the message that liked
        public int mId;
        //id of the user that liked
        public int uId;
        //number of likes
        public int isLike;

        public LikeData(int lid, int mid, int uid, int islike){
            lId = lid;
            mId = mid;          
            uId = uid;
            isLike = islike;
        }
    }

    /**
     * The Database constructor is private: we only create Database objects 
     * through the getDatabase() method.
     */
    private Database() {
    }

    /**
     * Get a fully-configured connection to the database
     * 
     * @param ip   The IP address of the database server
     * @param port The port on the database server to which connection requests
     *             should be sent
     * @param user The user ID to use when connecting
     * @param pass The password to use when connecting
     * 
     * @return A Database object, or null if we cannot connect properly
     */
    static Database getDatabase(String ip, String port, String user, String pass) {
        // Create an un-configured Database object
        Database db = new Database();

        // Give the Database object a connection, fail if we cannot get one
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/", user, pass);
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
            db.mCreateTable = db.mConnection.prepareStatement(
            "CREATE TABLE tblData (id SERIAL PRIMARY KEY, subject VARCHAR(50) NOT NULL, message VARCHAR(500) NOT NULL, user_Id int, likes int, invalid Boolean)");
            db.mDropTable = db.mConnection.prepareStatement("DROP TABLE tblData");
            // Standard CRUD operations
            db.mDeleteOne = db.mConnection.prepareStatement("DELETE FROM tblData WHERE id = ?");
            db.mInsertOne = db.mConnection.prepareStatement("INSERT INTO tblData VALUES (default, ?, ?, ?, 0, false)");
            db.mValidate = db.mConnection.prepareStatement("UPDATE tblData SET invalid = ? WHERE id = ?");

            // creation/deletion, so multiple executions will cause an exception
            // public UserData(int id, String name, String email, String GI, String SO, String note, String idToken){
            db.uCreateTable = db.mConnection.prepareStatement(
            "CREATE TABLE usrData (uid SERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, GI VARCHAR(50) NOT NULL, SO VARCHAR(50) NOT NULL, note VARCHAR(50) NOT NULL, idToken VARCHAR(50) NOT NULL, invalid Boolean)");
            db.uDropTable = db.mConnection.prepareStatement("DROP TABLE usrData");
            // Standard CRUD operations
            db.uDeleteOne = db.mConnection.prepareStatement("DELETE FROM usrData WHERE uid = ?");
            db.uInsertOne = db.mConnection.prepareStatement("INSERT INTO usrData VALUES (default, ?, ?, ?, ?, ?, ?, false)");
            db.uValidate = db.mConnection.prepareStatement("UPDATE usrData SET invalid = ? WHERE id = ?");

            // creation/deletion, so multiple executions will cause an exception
            // CommentData(int id, String content, int messageId, int userId)
            db.cCreateTable = db.mConnection.prepareStatement(
            "CREATE TABLE commentData (cid SERIAL PRIMARY KEY, content VARCHAR(50) NOT NULL, mid int, uid int)");
            db.cDropTable = db.mConnection.prepareStatement("DROP TABLE commentData");
            // Standard CRUD operations
            db.cDeleteOne = db.mConnection.prepareStatement("DELETE FROM commentData WHERE cid = ?");
            db.cInsertOne = db.mConnection.prepareStatement("INSERT INTO commentData VALUES (default, ?, ?, ?)");

            // creation/deletion, so multiple executions will cause an exception
            // LiketData(default, ?, ?, ?)
            db.lCreateTable = db.mConnection.prepareStatement(
            "CREATE TABLE likeData (lid SERIAL PRIMARY KEY, mid int, uid int, islike int)");
            db.lInsertOne = db.mConnection.prepareStatement("INSERT INTO likeData VALUES (default, ?, ?, ?)");
            db.lDropTable = db.mConnection.prepareStatement("DROP TABLE likeData");
            db.lDeleteOne = db.mConnection.prepareStatement("DELETE FROM likeData WHERE mId = ? and uId = ?");

        } catch (SQLException e) {
            System.err.println("Error creating prepared statement");
            e.printStackTrace();
            db.disconnect();
            return null;
        }
        return db;
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
    int insertRow(int use, String subject, String message) {
        int count = 0;        
        //PreparedStatement getRecentId;
        try {
            mInsertOne.setString(1, subject);
            mInsertOne.setString(2, message);
            mInsertOne.setInt(3, use);
            count += mInsertOne.executeUpdate();            
            /*
            mInsertOne.executeUpdate();
            getRecentId = mConnection.prepareStatement("SELECT id FROM tblData ORDER BY id DESC limit 1");
            ResultSet resSet = getRecentId.executeQuery();
            while (resSet.next()) {
                newestId = (resSet.getInt("id"));
            }
            System.out.println(newestId);//*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return newestId;
        return count;
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
    /**
     * Create tblData.  If it already exists, this will print an error
     */
    void createTable() {
        try {
            mCreateTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Remove tblData from the database.  If it does not exist, this will print
     * an error.
     */
    void dropTable() {
        try {
            mDropTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validate a message
     * an error.
     * db.mValidation = db.mConnection.prepareStatement("UPDATE tblData SET invalid = ? WHERE id = ?");
     */
    void ideaValidate(int id) {
        try {
            mValidate.setBoolean(1, false);
            mValidate.setInt(2, id);
            mValidate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invalidate a message
     * an error.
     * db.mValidation = db.mConnection.prepareStatement("UPDATE tblData SET invalid = ? WHERE id = ?");
     */
    void ideaInvalidate(int id) {
        try {
            mValidate.setBoolean(1, true);
            mValidate.setInt(2, id);
            mValidate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a row into the database
     * 
     * @param name The subject for this new row
     * @param email The message body for this new row
     * @param GI The subject for this new row
     * @param SO The message body for this new row
     * @param note The message body for this new row
     * @param idtoken The message body for this new row
     * @return The number of rows that were inserted
     */
    //inserts a new user into the user DB
    int insertUser(String name, String email, String GI, String SO, String note, String idToken){
        int count = 0;        
        try{
            uInsertOne.setString(1, name);
            uInsertOne.setString(2, email);     //set all the user's info into prepared statement
            uInsertOne.setString(3, GI);
            uInsertOne.setString(4, SO);
            uInsertOne.setString(5, note);
            uInsertOne.setString(6, idToken);
            count += uInsertOne.executeUpdate();    
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return count;    //return that user's id
    }
    /**
     * Create tblData.  If it already exists, this will print an error
     */
    void createUserTable() {
        try {
            uCreateTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Remove tblData from the database.  If it does not exist, this will print
     * an error.
     */
    void dropUserTable() {
        try {
            uDropTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Delete a row by ID
     * 
     * @param id The id of the row to delete
     * 
     * @return The number of rows that were deleted.  -1 indicates an error.
     */
    int deleteUser(int id) {
        int res = -1;
        try {
            uDeleteOne.setInt(1, id);
            res = uDeleteOne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    /**
     * Validate a message
     * an error.
     * db.mValidation = db.mConnection.prepareStatement("UPDATE tblData SET invalid = ? WHERE id = ?");
     */
    void userValidate(int id) {
        try {
            uValidate.setBoolean(1, false);
            uValidate.setInt(2, id);
            uValidate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invalidate a message
     * an error.
     * db.mValidation = db.mConnection.prepareStatement("UPDATE tblData SET invalid = ? WHERE id = ?");
     */
    void userInvalidate(int id) {
        try {
            uValidate.setBoolean(1, true);
            uValidate.setInt(2, id);
            uValidate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Insert a row into the database
     * @param content The subject for this new row
     * @param messageId The message body for this new row
     * @param userId The subject for this new row
     * 
     * @return The number of rows that were inserted
     */
    //inserts a new user into the user DB
    // CommentData(int id, String content, int messageId, int userId)
    int insertComment(String content, int messageId, int userId) {
        int count = 0;        
        try{
            cInsertOne.setString(1, content);
            cInsertOne.setInt(2, messageId);     //set all the user's info into prepared statement
            cInsertOne.setInt(3, userId);
            count += cInsertOne.executeUpdate();    
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return count;    //return that user's id
    }
    /**
     * Create tblData.  If it already exists, this will print an error
     */
    void createCommentTable() {
        try {
            cCreateTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Remove tblData from the database.  If it does not exist, this will print
     * an error.
     */
    void dropCommentTable() {
        try {
            cDropTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Delete a row by ID
     * 
     * @param id The id of the row to delete
     * 
     * @return The number of rows that were deleted.  -1 indicates an error.
     */
    int deleteComment(int id) {
        int res = -1;
        try {
            cDeleteOne.setInt(1, id);
            res = cDeleteOne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    /**
     * Insert a row into the database
     * @param mid The message id for this new row
     * @param uid The user id for this new row
     * @param islike number of likes for this new row
     * 
     * @return The number of rows that were inserted
     */
    // LikeData(int id, String content, int messageId, int userId)
    int insertLike(int mid, int uid, int islike) {
        int count = 0;        
        try{
            lInsertOne.setInt(1, mid);
            lInsertOne.setInt(2, uid);     //set all the user's info into prepared statement
            lInsertOne.setInt(3, islike);
            count += lInsertOne.executeUpdate();    
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return count;    //return that user's id
    }
    /**
     * Create likeData.  If it already exists, this will print an error
     */
    void createLikeTable() {
        try {
            lCreateTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Remove likeData from the database.  If it does not exist, this will print
     * an error.
     */
    void dropLikeTable() {
        try {
            lDropTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Remove likeData from the database.  If it does not exist, this will print
     * an error.             
     * db.lDeleteOne = db.mConnection.prepareStatement("DELETE FROM likeData WHERE mId = ? and uId = ?");
     */
    void dropLikeTable(int mid, int uid) {
        try {
            lDeleteOne.setInt(1, mid);
            lDeleteOne.setInt(2, uid);
            lDeleteOne.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}