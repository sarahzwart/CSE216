package edu.lehigh.cse216.ash320.admin;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
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
        System.out.println("  [M] Create msgData");
        System.out.println("  [U] Create usrData");
        System.out.println("  [C] Create comData");
        System.out.println("  [L] Create likData");
        System.out.println("  [D] Drop Table Data");
        System.out.println("  [-] Delete a row");
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
        String actions = "TD-q?";

        // We repeat until a valid single-character option is selected        
        while (true) {
            System.out.print("[" + actions + "] :> ");
            String action;
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
            if (action == '?') { //Show table
                menu();
            } else if (action == 'q') { //Quit app
                break;
            } else if (action == 'M') {//Create a table
                db.createMsgTable();
            } else if (action == 'U') {//Create a table
                db.createUsrTable();
            } else if (action == 'C') {//Create a table
                db.createComTable();
            } else if (action == 'L') {//Create a table
                db.createLikeTable();
            } else if (action == 'D') {//Drop a table
                System.out.print("Drop which table (M, U, C, L): ");
                char option = prompt(in);
                if (option == 'M'){
                    db.dropMsgTable();
                } else if (option == 'R') {//Drop a table
                    db.dropUsrTable();
                } else if (option == 'O') {//Drop a table
                    db.dropComTable();
                } else if (option == 'P') {//Drop a table
                    db.dropLikeTable();
                }
            } else if (action == '-') { //Deletes a post based on its ID
                System.out.print("Delete from which table (M, U, C, L): ");
                char option = prompt(in);
                if (option == 'M') { //Deletes a post based on its ID
                    int id = getInt(in, "Enter the row ID");
                    if (id == -1)
                        continue;
                    int res = db.deleteMsgRow(id);
                    if (res == -1){
                        System.out.println("No such index exists");
                        continue;
                    } 
                    System.out.println("  " + res + " rows deleted");
                } else if (option == 'U') { //Deletes a post based on its ID
                    int id = getInt(in, "Enter the row ID");
                    if (id == -1)
                        continue;
                    int res = db.deleteUsrRow(id);
                    if (res == -1){
                        System.out.println("No such index exists");
                        continue;
                    } 
                    System.out.println("  " + res + " rows deleted");
                } else if (option == 'C') { //Deletes a post based on its ID
                    int id = getInt(in, "Enter the row ID");
                    if (id == -1)
                        continue;
                    int res = db.deleteUsrRow(id);
                    if (res == -1){
                        System.out.println("No such index exists");
                        continue;
                    } 
                    System.out.println("  " + res + " rows deleted");
                } else if (option == 'L') { //Deletes a post based on its ID
                    int id = getInt(in, "Enter the row ID");
                    if (id == -1)
                        continue;
                    int res = db.deleteLikeRow(id);
                    if (res == -1){
                        System.out.println("No such index exists");
                        continue;
                    } 
                    System.out.println("  " + res + " rows deleted");
                }
            }
        }
        // Always remember to disconnect from the database when the program 
        // exits
        db.disconnect(); //Quits app
    }
}