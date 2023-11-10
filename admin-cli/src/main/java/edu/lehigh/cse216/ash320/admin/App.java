package edu.lehigh.cse216.ash320.admin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Map;

/**
 * App is our basic admin app.  For now, it is a demonstration of the six key 
 * operations on a database: connect, insert, update, query, delete, disconnect
 */
public class App {

    /**
     * Print the menu for our program
     */
    static void menu() {
        System.out.println("Main Menu");
        System.out.println("  [T] Create Data Table");
        System.out.println("  [D] Drop Data Table");
        System.out.println("  [-] Delete a row");
        System.out.println("  [+] Insert a new row");
        System.out.println("  [q] Quit Program");
        System.out.println("  [?] Help (this message)");
    }

    /**
     * Ask the user to enter a menu option; repeat until we get a valid option
     * 
     * @param in A BufferedReader, for reading from the keyboard
     * 
     * @return The character corresponding to the chosen menu option
     */
    static char prompt(BufferedReader in) {
        // The valid actions:
        String actions = "TD1*-+~q?";

        // We repeat until a valid single-character option is selected        
        while (true) {
            System.out.print("[" + actions + "] :> ");
            String action = "";
            try {
                action = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            if (action.length() != 1)
                continue;
            if (actions.contains(action)) {
                return action.charAt(0);
            }
            System.out.println("Invalid Command");
        }
    }

    /**
     * Ask the user to enter a String message
     * 
     * @param in A BufferedReader, for reading from the keyboard
     * @param message A message to display when asking for input
     * 
     * @return The string that the user provided.  May be "".
     */
    static String getString(BufferedReader in, String message) {
        String s;
        try {
            System.out.print(message + " :> ");
            s = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return s;
    }

    /**
     * Ask the user to enter a char message
     * 
     * @param in A BufferedReader, for reading from the keyboard
     * @param message A message to display when asking for input
     * 
     * @return The string that the user provided.  May be "".
     */
    static String getChar(BufferedReader in, String message) {
        String s;
        try {
            System.out.print(message + " :> ");
            s = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return s;
    }

    /**
     * Ask the user to enter an integer
     * 
     * @param in A BufferedReader, for reading from the keyboard
     * @param message A message to display when asking for input
     * 
     * @return The integer that the user provided.  On error, it will be -1
     */
    static int getInt(BufferedReader in, String message) {
        int i = -1;
        try {
            System.out.print(message + " :> ");
            i = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    //public void reset() throws IOException;

    /**
     * The main routine runs a loop that gets a request from the user and
     * processes it
     * 
     * @param argv Command-line options.  Ignored by this program.
     */
    public static void main(String[] argv) {
        // get the Postgres configuration from the environment
        Map<String, String> env = System.getenv();
        String ip = env.get("POSTGRES_IP");
        String port = env.get("POSTGRES_PORT");
        String user = env.get("POSTGRES_USER");
        String pass = env.get("POSTGRES_PASS");

        // Get a fully-configured connection to the database, or exit 
        // immediately
        Database db = Database.getDatabase(ip, port, user, pass);
        if (db == null)
            return;

        // Start our basic command-line interpreter:
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            // Get the user's request, and do it
            //
            // NB: for better testability, each action should be a separate
            //     function call
            char action = prompt(in);
            if (action == '?') {
                menu();
            } else if (action == 'q') {
                break;
            } else if (action == 'T') {
                char option = (getChar(in, "Create which table (M, U, C)")).charAt(0);
                if (option == 'M'){
                    db.createTable();
                } else if (option == 'U'){
                    db.createUserTable();
                } else if (option == 'C'){
                    db.createCommentTable();
                }
            } else if (action == 'D') {
                char option = (getChar(in, "Drop which table (M, U, C)")).charAt(0);
                if (option == 'M'){
                    db.dropTable();
                } else if (option == 'U'){
                    db.dropUserTable();
                }  else if (option == 'C'){
                    db.dropCommentTable();
                }
            } else if (action == '-') {
                int res = 0;
                char option = (getChar(in, "Remove which row type (M, U, C)")).charAt(0);
                if (option == 'M'){
                    int id = getInt(in, "Enter the row ID");
                    if (id == -1)
                        continue;
                    res = db.deleteRow(id);
                    if (res == -1)
                        continue;
                } else if (option == 'U'){
                    int id = getInt(in, "Enter the row ID");
                    if (id == -1)
                        continue;
                    res = db.deleteUser(id);
                    if (res == -1)
                        continue;
                } else if (option == 'C'){
                    int id = getInt(in, "Enter the row ID");
                    if (id == -1)
                        continue;
                    res = db.deleteComment(id);
                    if (res == -1)
                        continue;
                    }                
                System.out.println("  " + res + " rows deleted");
            } else if (action == '+') {
                char option = (getChar(in, "Add which table (M, U, C)")).charAt(0);
                if (option == 'M'){
                    int use = getInt(in, "Enter the User Id");
                    String subject = getString(in, "Enter the subject");
                    String message = getString(in, "Enter the message");
                    if (subject.equals("") || message.equals(""))
                        continue;
                    int res = db.insertRow(use, subject, message);
                    System.out.println(res + " rows added");
                } else if (option == 'U'){
                    String name = getString(in, "Enter the name");
                    String email = getString(in, "Enter the email");
                    String GI = getString(in, "Enter the GI");
                    String SO = getString(in, "Enter the SO");
                    String note = getString(in, "Enter a Note");
                    String idToken = getString(in, "Enter the idToken");
                    if (name.equals("") || email.equals("") || GI.equals("") || SO.equals("")|| note.equals("") || idToken.equals(""))
                        continue;
                    int res = db.insertUser(name, email, GI, SO, note, idToken);
                    System.out.println(res + " rows added");
                } else if (option == 'C'){
                    // CommentData(int id, String content, int messageId, int userId)
                    String content = getString(in, "Enter some Content");
                    int mId = getInt(in, "Enter the Message Id");
                    int uId = getInt(in, "Enter the User Id");
                    if (content.equals(""))
                        continue;
                    int res = db.insertComment(content, mId, uId);
                    System.out.println(res + " rows added");
                }
            } 
        }
        // Always remember to disconnect from the database when the program 
        // exits
        db.disconnect();
    }
}