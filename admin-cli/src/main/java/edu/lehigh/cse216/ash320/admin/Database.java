package edu.lehigh.cse216.ash320.admin;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

/**
 * THE DATABASE
 */
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
     * Prepared statement for creating the document table
     */
    private PreparedStatement dCreateTable;
    /**
     * Prepared statement for deleting the document table
     */
    private PreparedStatement dDropTable;
    /**
     * Prepared statement for inserting into document table
     */
     private PreparedStatement dInsertOne;
    /**
     * Prepared statement for deleting in the document table
     */
    private PreparedStatement dDeleteOne;

    private PreparedStatement linkCreateTable;

    private PreparedStatement linkDropTable;

    private PreparedStatement linkDeleteOne;

    private PreparedStatement linkInsertOne;

    private PreparedStatement linkValidate;



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
        /**
         * The message is valid?
         */
        public Boolean mInvalid;
        /**
         * 
         * @param id message id
         * @param subject message title
         * @param message message content
         * @param userId  user id
         * @param likes message likes
         * @param invalid  message valid
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
        /**
         * The user Id for this user
         */        
        public final int uId;
        /**
         * The user name for this user
         */
        public String uName;
        /**
         * The user email for this user
         */
        public String uEmail;
        /**
         * The user GI for this user
         */       
        public String uGI;
        /**
         * The user SO for this user
         */        
        public String uSO;
        /**
         * The user note for this user
         */        
        public String uNote;
        /**
         * The user token for this user
         */        
        public String uIdToken;
        /**
         * Test table creation
         */
        public Boolean uInvalid;
        /**
         * 
         * @param id user id
         * @param u user name
         * @param c user email
         * @param g user gender
         * @param so user so
         * @param n user note
         * @param i user token
         * @param invalid user invalid
         */
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
    /**
     * Comment Data Table
     */
    public static class CommentData {
        /**
         * The comment id for this comment
         */        
        public final int cId;
        /**
         * The comment content for this comment
         */          
        public String cContent;
        /**
         * The message id for this comment
         */           
        public int mId;
        /**
         * The user id for this comment
         */           
        public int uId;
        /**
         * @param id comment id
         * @param message message
         * @param messageId message id
         * @param userId user id
         */
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
    /**
     * Get a fully-configured table of likes
    */
    public static class LikeData {
        /**
         * The like id for this like
         */          
         public final int lId;
        /**
         * The message id for this like
         */                
        public int mId;
        /**
         * The user id for this like
         */                
         public int uId;
        /**
         * The like value for this like
         */                
        public int isLike;
        /**
         * Get a fully-configured connection to the database
         * 
         * @param lid   The IP address of the database server
         * @param mid The port on the database server to which connection requests
         * @param uid The user ID to use when connecting
         * @param islike The password to use when connecting     
        */    
        public LikeData(int lid, int mid, int uid, int islike){
            lId = lid;
            mId = mid;          
            uId = uid;
            isLike = islike;
        }
    }
    /***
     * Document Data Table
     */
    public static class DocumentData{

        /**
         * Name of document
         */
        public String dName;

        /**
         * Name of Owner
         */
        public String dOwner;

        /**
         * When document was last accessed
         */

        public java.sql.Timestamp dLastAccessed;

        public int dId;

        /**
         * @param documentId
         * @param documentName 
         * @param documentOwner 
         * @param documentAccessed 
         */
        public DocumentData(int documentId, String documentName, String documentOwner,java.sql.Timestamp documentLastAccessed){
            dId = documentId;
            dName = documentName;
            dOwner = documentOwner;
            dLastAccessed = documentLastAccessed;
        }

        public void updateLastAccessed(java.sql.Timestamp lastAccessed) {
            dLastAccessed = lastAccessed;
        }
    }

    public static class LinkData{

        /*
         * Link Id 
         */
        public int linkId;

        /*
         * link
         */
        public String link;

        /*
         * declare whether link is invalid or valid
         */
        public Boolean lInvalid;

        /**
         * 
         * @param linkedId
         * @param linkLink
         * @param linkInvalid
         */
        public LinkData(int linkedId, String linkLink, Boolean linkInvalid){
            linkId = linkedId;
            link = linkLink;
            lInvalid = linkInvalid;
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
            "CREATE TABLE tblData (id SERIAL, title VARCHAR(50) NOT NULL, message VARCHAR(500) NOT NULL, user_Id int, likes int, invalid Boolean)");
            db.mDropTable = db.mConnection.prepareStatement("DROP TABLE tblData");
            // Standard CRUD operations
            db.mDeleteOne = db.mConnection.prepareStatement("DELETE FROM tblData WHERE id = ?");
            db.mInsertOne = db.mConnection.prepareStatement("INSERT INTO tblData VALUES (default, ?, ?, ?, 0, false)");
            db.mValidate = db.mConnection.prepareStatement("UPDATE tblData SET invalid = ? WHERE id = ?");

            // creation/deletion, so multiple executions will cause an exception
            // public UserData(int id, String name, String email, String GI, String SO, String note, String idToken){
            db.uCreateTable = db.mConnection.prepareStatement(
            "CREATE TABLE usrData (uid SERIAL, name VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, GI VARCHAR(50) NOT NULL, SO VARCHAR(50) NOT NULL, note VARCHAR(50) NOT NULL, idToken VARCHAR(50) NOT NULL, invalid Boolean)");
            db.uDropTable = db.mConnection.prepareStatement("DROP TABLE usrData");
            // Standard CRUD operations
            db.uDeleteOne = db.mConnection.prepareStatement("DELETE FROM usrData WHERE uid = ?");
            db.uInsertOne = db.mConnection.prepareStatement("INSERT INTO usrData VALUES (default, ?, ?, ?, ?, ?, ?, false)");
            db.uValidate = db.mConnection.prepareStatement("UPDATE usrData SET invalid = ? WHERE id = ?");

            // creation/deletion, so multiple executions will cause an exception
            // CommentData(int id, String content, int messageId, int userId)
            db.cCreateTable = db.mConnection.prepareStatement(
            "CREATE TABLE commentData (cid SERIAL, content VARCHAR(50) NOT NULL, mid int, uid int)");
            db.cDropTable = db.mConnection.prepareStatement("DROP TABLE commentData");
            // Standard CRUD operations
            db.cDeleteOne = db.mConnection.prepareStatement("DELETE FROM commentData WHERE cid = ?");
            db.cInsertOne = db.mConnection.prepareStatement("INSERT INTO commentData VALUES (default, ?, ?, ?)");

            // creation/deletion, so multiple executions will cause an exception
            // LiketData(default, ?, ?, ?)
            db.lCreateTable = db.mConnection.prepareStatement(
            "CREATE TABLE likeData (lid SERIAL, mid int, uid int, islike int)");
            db.lInsertOne = db.mConnection.prepareStatement("INSERT INTO likeData VALUES (default, ?, ?, ?)");
            db.lDropTable = db.mConnection.prepareStatement("DROP TABLE likeData");
            db.lDeleteOne = db.mConnection.prepareStatement("DELETE FROM likeData WHERE mId = ? and uId = ?");
            
            // creation/deletion, so multiple executions will cause an exception
            // DocumentData(int documentId, String documentName, String documentOwner, String documentAccessed)
            db.dCreateTable = db.mConnection.prepareStatement(
                "CREATE TABLE documentData (documentId SERIAL, documentName VARCHAR(50) NOT NULL, documentOwner VARCHAR(50) NOT NULL, documentLastAccessed TIMESTAMP NOT NULL )"
            );
            db.dDropTable = db.mConnection.prepareStatement(
                "DROP TABLE documentData"
            );
            db.dDeleteOne = db.mConnection.prepareStatement(
                "DELETE FROM documentData WHERE documentId = (SELECT documentId FROM documentData ORDER BY documentLastAccessed ASC LIMIT 1)"
            );
            db.dInsertOne = db.mConnection.prepareStatement(
                "INSERT INTO documentData VALUES (default, ?, ?, ?)"
            );

            //LinkData(int linkedId, String linkLink, Boolean linkInvalid)
            db.linkCreateTable = db.mConnection.preparedStatement(
                "CREATE TABLE linkData (linkId SERIAL, link VARCHAR(50) NOT NULL, lInvalid Boolean)"
            );
            db.linkDropTable = db.mConnection.prepareStatement(
                "DROP TABLE linkData"
            );
            db.linkDeleteOne = db.mConnection.preparedStatement(
                "DELETE FROM linkData WHERE linkId = ?"
            );
            db.linkInsertOne = db.mConnection.preparedStatement(
                "INSERT INTO linkData VALUES (default, ?, false)"
            );
            db.linkValidate = db.mConnection.prepareStatement(
                "UPDATE linkData SET lInvalid = ? WHERE linkId = ?"
            );



        /**
         * 
         * @param linkedId
         * @param linkLink
         * @param linkInvalid
         */
            


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
     * @param use user id
     * @param title title of the message
     * @param message the content of the message
     * @return it returns
     */
    int insertRow(int use, String title, String message) {
        int count = 0;        
        //PreparedStatement getRecentId;
        try {
            mInsertOne.setString(1, title);
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
     * 
     * @param id id of the idea
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
     * 
     * @param id id of the idea
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
     * @param idToken The message body for this new row
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
     * @param id it the id of the user
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
     * @param id it the id of the user
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
     * 
     * @param mid message id
     * @param uid user id
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

    /**
     * Create documentData.  If it already exists, this will print an error
     */
    void createDocumentTable() {
        try {
            dCreateTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove documentData from the database.  If it does not exist, this will print
     * an error.
     */
    void dropDocumentTable() {
        try {
            dDropTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DocumentData(int documentId, String documentName, String documentOwner, String documentAccessed)
    int insertDocument(String documentName, String documentOwner, java.sql.Timestamp documentAccessed){
        int count = 0;        
        try{
            dInsertOne.setString(1, documentName);
            dInsertOne.setString(2, documentOwner);     //set all the user's info into prepared statement
            dInsertOne.setTimestamp(3, documentAccessed);
            count += dInsertOne.executeUpdate();    
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return count;   
    }

    /**
     * Delete a row by ID
     * 
     * @return The number of rows that were deleted.  -1 indicates an error.
     */
    int deleteDocument() {
        int res = -1;
        try {
            res = dDeleteOne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    //LinkData(int linkedId, String linkLink, Boolean linkInvalid)

    /**
     * Creates the link table
     * @return void
     */
    void createLinkTable() {
        try {
            linkCreateTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets rid of link table
     * @return void
     */
    void dropLinkTable() {
        try {
            linkDropTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    /**
     * Adds a link to linkData
     * @return int of count
     */
    int insertLink(String linkLink, Boolean linkInvalid){
        int count = 0;
        try{
            linkInsertOne.setString(1, linkLink);
            count += linkInsertOne.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    /**
     * Deletes a link in the linkData
     * @return int of res
     */
    int deleteLink(int linkedId){
        int res = -1;
        try {
            linkDeleteOne.setInt(1, linkedId);
            res = linkDeleteOne.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    void linkInvalidate(int linkId) {
        try {
            linkValidate.setBoolean(1, true);
            linkValidateValidate.setInt(2, linkId);
            linkValidate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void linkValidate(int id) {
        try {
            linkValidate.setBoolean(1, false);
            linkValidate.setInt(2, id);
            linkValidate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    


}