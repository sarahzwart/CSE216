package edu.lehigh.cse216.ash320.admin;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Database {
    //MESSAGE STATEMENTS
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
     * A prepared statement for creating the table in our database
     */
    private PreparedStatement mCreateTable;
    /**
     * A prepared statement for dropping the table in our database
     */
    private PreparedStatement mDropTable;
    //USER STATEMENTS
    /**
     * A prepared statement for deleting a row from the database
     */
    private PreparedStatement uDeleteOne;
    /**
     * A prepared statement for creating the table in our database
     */
    private PreparedStatement uCreateTable;
    /**
     * A prepared statement for dropping the table in our database
     */
    private PreparedStatement uDropTable;


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
        String mSubject;
        /**
         * The message stored in this row
         */
        String mMessage;

        /**
         * Stores number of likes a post has
         */
        int mLikes;

        /**
         * Construct a RowData object by providing values for its fields
         */
        public RowData(int id, String subject, String message, int likes) {
            mId = id;
            mSubject = subject;
            if(message.length()<=2048){ //ensures correct length
                mMessage = message;
            }
            else{ //shortens message if it is too large
                mMessage = message.substring(0, Math.min(message.length(), 2048));
            }
            
            mLikes=likes; //Set likes added to a rowData constructor
        }
    }

    public static class userData {
        /**
         * The ID of this row of the database
         */
        int uId;
        /**
         * The ID of this row of the database
         */
        String uName;
        /**
         * The subject stored in this row
         */
        String uEmail;
        /**
         * The message stored in this row
         */
        String uGI;
        /**
         * Stores number of likes a post has
         */
        String uSO;
        /**
         * Stores Notes about users
        */
        String uNote;

        /**
         * Construct a RowData object by providing values for its fields
         */
        public userData(int id, String u, String c, String g, String so, String n) {
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
            //MESSAGE DATA
            db.mCreateTable = db.mConnection.prepareStatement(
            "CREATE TABLE tblData (id SERIAL PRIMARY KEY, subject VARCHAR(50) NOT NULL, message VARCHAR(2048) NOT NULL, likes int)"); //messages limited to 2048 characters and likes added as a table factor
            db.mDropTable = db.mConnection.prepareStatement("DROP TABLE tblData");
            // Standard CRUD operations
            db.mDeleteOne = db.mConnection.prepareStatement("DELETE FROM tblData WHERE id = ?");

            //USER STUFF
            db.uCreateTable = db.mConnection.prepareStatement(
            "CREATE TABLE usrData (uid SERIAL PRIMARY KEY, uName VARCHAR(50) NOT NULL, uEmail VARCHAR(50) NOT NULL, uGI VARCHAR(50) NOT NULL, uSO VARCHAR(50) NOT NULL, uSO VARCHAR(2048) NOT NULL)");
            db.uDropTable = db.mConnection.prepareStatement("DROP TABLE usrData");
            // Standard CRUD operations
            db.uDeleteOne = db.mConnection.prepareStatement("DELETE FROM usrData WHERE id = ?");

            //IMPLIMENT IN BACKEND
            /*db.mInsertOne = db.mConnection.prepareStatement("INSERT INTO tblData (id, subject, message, likes) VALUES (DEFAULT, ?, ?, ?)"); //prepared statement altered to inclues likes
            db.mSelectAll = db.mConnection.prepareStatement("SELECT * FROM tblData");
            db.mSelectOne = db.mConnection.prepareStatement("SELECT * FROM tblData WHERE id=?");
            db.mUpdateOne = db.mConnection.prepareStatement("UPDATE tblData SET message = ? WHERE id = ?");
            db.mLikeOne = db.mConnection.prepareStatement("UPDATE tblData SET likes = ? WHERE id = ?"); //New prepared statement to increment likes*/
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
        if (mConnection == null) { //No connection exists
            System.err.println("Unable to close connection: Connection was null");
            return false;
        }
        try {
            mConnection.close();
        } catch (SQLException e) { //SQL exception
            System.err.println("Error: Connection.close() threw a SQLException");
            e.printStackTrace();
            mConnection = null;
            return false;
        }
        mConnection = null;
        return true;
    }

    /**
     * Delete a row by ID
     * 
     * @param id The id of the row to delete
     * 
     * @return The number of rows that were deleted.  -1 indicates an error.
     */
    int deleteMsgRow(int id) {
        int res = -1;
        try {
            mDeleteOne.setInt(1, id);
            res = mDeleteOne.executeUpdate();
            //for () {
            //}
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
    int deleteUsrRow(int id) {
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
     * Create tblData.  If it already exists, this will print an error
     */
    void createMsgTable() {
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
    void dropMsgTable() {
        try {
            mDropTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Create usrData.  If it already exists, this will print an error
     */
    void createUsrTable() {
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
    void dropUsrTable() {
        try {
            uDropTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
