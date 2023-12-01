package edu.lehigh.cse216.ash320.admin;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    /**
     * Test message creation
     */
    public void testMsgConstructor(){
        //Basic input for a post id SERIAL, title VARCHAR(50) NOT NULL, message VARCHAR(2048) NOT NULL, uid int, likes int)")
        int id = 1; //the first element added should have id=1
        String subject = "testing";
        String message = "testing 123";
        int uid = 1; //the first element added should have id=1
        int likes = 0; //all posts start at 0 likes
        Boolean inV = false;
        //Create the row example
        Database.RowData test = new Database.RowData(id, subject, message, uid, likes, inV);
        //ensures the row was correctly created
        assertFalse(test==null); //ensures it was created at all
        assertTrue(test.mTitle.equals(subject));
        assertTrue(test.mMessage.equals(message));
        assertTrue(test.mId==id);
        assertTrue(test.uId==uid);
        assertTrue(test.mLikes==likes);
        assertTrue(test.mInvalid==inV);
    }
    /**
     * Test user creation
     */
    public void testUsrConstructor(){
        //Basic input for a post 

        String name = "testing";
        String email = "testing@lehigh.edu";
        String GI = "Male";
        String SO = "Shitting Myself";
        int id = 1; //the first element added should have id=1
        String note = "noted"; //String
        String idtoken = "noted"; //String
        Boolean inV = false;
        //Create the row exampleso
        Database.UserData test = new Database.UserData(id, name, email, GI, SO, note, idtoken, inV);
        //ensures the row was correctly created
        assertFalse(test==null); //ensures it was created at all
        assertTrue(test.uName.equals(name));
        assertTrue(test.uEmail.equals(email));
        assertTrue(test.uGI.equals(GI));
        assertTrue(test.uSO.equals(SO));
        assertTrue(test.uId==id);
        assertTrue(test.uNote.equals(note));
        assertTrue(test.uIdToken.equals(idtoken));
        assertTrue(test.uInvalid==inV);

    }
    /**
     * Test comment creation
     */
    public void testComConstructor(){
        //Basic input for a post comData(int id, int cid, int uid, String message) {
        int mid = 1;
        int cid = 1;        
        int uid = 1;
        String message = "comentary"; //String
        //Create the row exampleso
        Database.CommentData test = new Database.CommentData(cid, message, mid, uid);
        //ensures the row was correctly created
        assertFalse(test==null); //ensures it was created at all
        assertTrue(test.mId==mid);
        assertTrue(test.cId==cid);
        assertTrue(test.uId==uid);
        assertTrue(test.cContent.equals(message));
    }
/*
    public void testLikeConstructor(){
        //Basic input for a post likeData(int id, int l, int up, int down) {
        int mid = 1;
        int lid = 1;        
        int up = 1;
        //int down = 1;
        //Create the row exampleso
        Database.likeData test = new Database.likeData(mid, lid, up);
        //ensures the row was correctly created
        assertFalse(test==null); //ensures it was created at all
        assertTrue(test.mId==mid);
        assertTrue(test.lId==lid);
        assertTrue(test.like==up);
        //assertTrue(test.downVote==down);
    }// */
    /**
     * Test table creation
     */
    public void methodTests(){
        //tests creating database
        Database db = Database.getDatabase("berry.db.elephantsql.com", "8998", "pqfneahl", "eRnYfJkr5W8wwhRePitFVDx8CVP1iKYa");
        
        //Tests creating a message table
        db.createTable();
        //testing deleteRow
        int wasDeleted = db.deleteRow(2);
        //esureing row 2 was deleted
        assertTrue(wasDeleted==1);
        //testing dropTable
        db.dropTable();
        
        //Tests creating a user table
        db.createUserTable();
        //testing deleteRow
        wasDeleted = db.deleteUser(2);
        //esureing row 2 was deleted
        assertTrue(wasDeleted==1);
        //testing dropTable
        db.dropUserTable();

        //Tests creating a comment table
        db.createCommentTable();
        //testing deleteRow
        wasDeleted = db.deleteComment(2);
        //esureing row 2 was deleted
        assertTrue(wasDeleted==1);
        //testing dropTable
        db.dropCommentTable();

        // Testing to see if document was deleted
        db.createDocumentTable();
        db.insertDocument("Document 1", "Sarah Zwart 1", java.sql.Timestamp.valueOf("2023-11-30 10:10:10.0"));
        db.insertDocument("Document 2", "Sarah Zwart 2", java.sql.Timestamp.valueOf("2023-12-1 10:10:10.0"));
        int deletedDoc = db.deleteDocument();
        assertTrue(deletedDoc==1);

        //Testing valid/invalid of links
        db.createLinkTable();
        db.insertLink("www.google.com");
        db.insertLink("www.webkinz.com");
        db.linkInvalidate(1);
        db.linkValidate(2);


        db.createLinkTable();



     


        /*//Tests creating a comment table
        db.createLikeTable();
        //testing deleteRow
        wasDeleted = db.deleteLikeRow(2);
        //esureing row 2 was deleted
        assertTrue(wasDeleted==1);
        //testing dropTable
        db.dropLikeTable();//*/
        
        //testing disconnect
        db.disconnect();
    }
    /**
     * Test the Word Link
     */
    public void testWordLimit(){
        //Basic subject for a post
        String subject = "1";
        //2049 characters (1 more than the alowed limit)
        String message = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean.";
        //Create the row example
        Database.RowData testLimit = new Database.RowData(1, subject, message, 1, 0, false);
        //Ensure the message limit was cut off at 2048 characters
        assertFalse(testLimit.mMessage.equals(message));
    }

}
