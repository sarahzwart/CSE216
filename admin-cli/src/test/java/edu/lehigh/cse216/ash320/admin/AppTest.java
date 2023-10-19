package edu.lehigh.cse216.ash320.admin;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;

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


    public void testBasicConstructor(){
        //Basic input for a post
        String subject = "testing";
        String message = "testing 123";
        int id = 1; //the first element added should have id=1
        int likes = 0; //all posts start at 0 likes
        //Create the row example
        Database.RowData test = new Database.RowData(id, subject, message, likes);
        //ensures the row was correctly created
        assertFalse(test==null); //ensures it was created at all
        assertTrue(test.mSubject.equals(subject));
        assertTrue(test.mMessage.equals(message));
        assertTrue(test.mId==id);
        assertTrue(test.mLikes==likes);
    }

    public void methodTests(){
        //tests creating database
        Database db = Database.getDatabase("berry.db.elephantsql.com", "8998", "pqfneahl", "eRnYfJkr5W8wwhRePitFVDx8CVP1iKYa");
        //Tests creating a table
        db.createTable();
        //testing deleteRow
        int wasDeleted = db.deleteRow(2);
        //esureing row 2 was deleted
        assertTrue(wasDeleted==1);
        //testing dropTable
        db.dropTable();
        //testing disconnect
        db.disconnect();
    }

    public void testWordLimit(){
         //Basic subject for a post
        String subject = "1";
        //2049 characters (1 more than the alowed limit)
        String message = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean.";
        //Create the row example
        Database.RowData testLimit = new Database.RowData(1, subject, message, 0);
        //Ensure the message limit was cut off at 2048 characters
        assertFalse(testLimit.mMessage.equals(message));
    }

}
