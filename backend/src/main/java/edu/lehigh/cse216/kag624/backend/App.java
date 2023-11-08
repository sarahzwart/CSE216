package edu.lehigh.cse216.kag624.backend;

// Import the Spark package, so that we can make use of the "get" function to 
// create an HTTP GET route
import spark.Spark;

// Import Google's JSON library
import com.google.gson.*;

import java.util.Map;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.Hashtable;
import java.util.Random;

/**
 * For now, our app creates an HTTP server that can only get and add data.
 */
public class App {
    public static void main(String[] args) {
        // gson provides us with a way to turn JSON into objects, and objects
        // into JSON.
        //
        // NB: it must be final, so that it can be accessed from our lambdas
        //
        // NB: Gson is thread-safe.  See 
        // https://stackoverflow.com/questions/10380835/is-it-ok-to-use-gson-instance-as-a-static-field-in-a-model-bean-reuse
        final Gson gson = new Gson();

        // dataStore holds all of the data that has been provided via HTTP 
        // requests
        //
        // NB: every time we shut down the server, we will lose all data, and 
        //     every time we start the server, we'll have an empty dataStore,
        //     with IDs starting over from 0.
        //final DataStore dataStore = new DataStore();
        final Database db = getDatabaseConnection();

        //Hashtable<Integer, GoogleIdToken> users = new Hashtable<>();
        //Random rand = new Random();

        // Set the port on which to listen for requests from the environment
        Spark.port(getIntFromEnv("PORT", DEFAULT_PORT_SPARK));

        // Set up the location for serving static files.  If the STATIC_LOCATION
        // environment variable is set, we will serve from it.  Otherwise, serve
        // from "/web"
        String static_location_override = System.getenv("STATIC_LOCATION");
        if (static_location_override == null) {
            Spark.staticFileLocation("/build"); // could also be problematic
        } else {
            Spark.staticFiles.externalLocation(static_location_override);
        }

        if ("True".equalsIgnoreCase(System.getenv("CORS_ENABLED"))) {
            final String acceptCrossOriginRequestsFrom = "*";
            final String acceptedCrossOriginRoutes = "GET,PUT,POST,DELETE,OPTIONS";
            final String supportedRequestHeaders = "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin";
            enableCORS(acceptCrossOriginRequestsFrom, acceptedCrossOriginRoutes, supportedRequestHeaders);
        }

        // Set up a route for serving the main page
        Spark.get("/", (req, res) -> {
            res.redirect("/public/index.html"); //might become a problem if its wrong
            return "";
        });

        // GET route that returns all message titles and Ids.  All we do is get 
        // the data, embed it in a StructuredResponse, turn it into JSON, and 
        // return it.  If there's no data, we return "[]", so there's no need 
        // for error handling.
        Spark.get("/messages", (request, response) -> {
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            return gson.toJson(new StructuredResponse("ok", null, db.selectAll()));
            //return gson.toJson(new StructuredResponse("ok", null, dataStore.readAll()));
        });

        // GET route that returns everything for a single row in the DataStore.
        // The ":id" suffix in the first parameter to get() becomes 
        // request.params("id"), so that we can get the requested row ID.  If 
        // ":id" isn't a number, Spark will reply with a status 500 Internal
        // Server Error.  Otherwise, we have an integer, and the only possible 
        // error is that it doesn't correspond to a row with data.
        Spark.get("/messages/:id", (request, response) -> {
            int idx = Integer.parseInt(request.params("id"));
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            DataRow data = db.selectOne(idx);
            if (data == null) {
                return gson.toJson(new StructuredResponse("error", idx + " not found", null));
            } else {
                return gson.toJson(new StructuredResponse("ok", null, data));
            }
        });

        // POST route for adding a new element to the DataStore.  This will read
        // JSON from the body of the request, turn it into a SimpleRequest 
        // object, extract the title and message, insert them, and return the 
        // ID of the newly created row.
        Spark.post("/messages", (request, response) -> {
            // NB: if gson.Json fails, Spark will reply with status 500 Internal 
            // Server Error
            SimpleRequest req = gson.fromJson(request.body(), SimpleRequest.class);
            // ensure status 200 OK, with a MIME type of JSON
            // NB: even on error, we return 200, but with a JSON object that
            // describes the error.
            response.status(200);
            response.type("application/json");
            // NB: createEntry checks for null title and message
            int newId = db.insertRow(req.uId, req.mTitle, req.mMessage);
            if (newId == -1) {
                return gson.toJson(new StructuredResponse("error", "error performing insertion", null));
            } else {
                return gson.toJson(new StructuredResponse("ok", "" + newId, null));
            }
        });

        //  PUT route for updating the likes on a message. Will read JSON from the 
        //  body of the request, turn it into a SimpleRequest object, extract the id
        //  from the request and the likes and update the message with the given id
        //  to the new passed value of likes
        Spark.put("/messages/:id", (request, response) -> {
            // If we can't get an ID or can't parse the JSON, Spark will send
            // a status 500
            int idx = Integer.parseInt(request.params("id"));
            LikeRequest req = gson.fromJson(request.body(), LikeRequest.class);
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            // NB: updateLikes checks for null ids

            int result = -1;

            if(req.isLike == 1){
                if(db.isLiked(idx, req.uId) == 1){  //if message is already liked, remove the like
                    result = db.removeLike(idx, req.uId);
                }
                else if(db.isLiked(idx, req.uId) == 2){ //if message is disliked,
                    db.removeDislike(idx, req.uId);         //remove dislike,
                    result = db.addLike(idx, req.uId);  //then like the message
                }
                else if(db.isLiked(idx, req.uId) == 0){ //if message is not liked or disliked,
                    result = db.addLike(idx, req.uId);  //add a like
                }
            }
            else if(req.isLike == 0){
                if(db.isLiked(idx, req.uId) == 1){  //if message is currently liked
                    db.removeLike(idx, req.uId);        //remove the like in DB
                    result = db.addDislike(idx, req.uId);   //then add the dislike to the DB
                }
                else if(db.isLiked(idx, req.uId) == 2){ //if message is currently disliked
                    result = db.removeDislike(idx, req.uId);    //remove dislike from DB
                }
                else if(db.isLiked(idx, req.uId) == 0){ //if message is not liked or disliked
                    result = db.addDislike(idx, req.uId);   //add dislike to DB
                }
            }

            if (result == -1) {     //if requests worked send ok response, else send error response
                return gson.toJson(new StructuredResponse("error", "unable to update row " + idx, null));
            } else {    
                return gson.toJson(new StructuredResponse("ok", null, result));
            }
        });

        // DELETE route for removing a row from the DataStore
        Spark.delete("/messages/:id", (request, response) -> {
            // If we can't get an ID, Spark will send a status 500
            int idx = Integer.parseInt(request.params("id"));
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            // NB: we won't concern ourselves too much with the quality of the 
            //     message sent on a successful delete
            int result = db.deleteRow(idx);
            if (result == -1) {
                return gson.toJson(new StructuredResponse("error", "unable to delete row " + idx, null));
            } else {
                // proposed code to edit the database such that data can be kept throughout sessions
                return gson.toJson(new StructuredResponse("ok", null, null));
            }
        });

        //Get route to get all comments on a message
        Spark.get("/comments/:mId", (request, response) -> {
            int idx = Integer.parseInt(request.params("mId"));   //get message id from route
            response.status(200);
            response.type("application/json");
            return gson.toJson(new StructuredResponse("ok", null, db.selectAllComments(idx)));
        });

        //get route for all comments currently in comment DB
        Spark.get("/comments", (request, response) -> {
            response.status(200);
            response.type("application/json");      //return the result of db.getallcomments which gets all comments
            return gson.toJson(new StructuredResponse("ok", null, db.getAllComments()));
        });

        //Put route to edit comment
        Spark.put("/comments/:commentId", (request, response) -> {
            int idx = Integer.parseInt(request.params("commentId"));    //get comment id from route
            response.status(200);
            response.type("application/json");
            CommentRequest req = gson.fromJson(request.body(), CommentRequest.class);   //get new comment content from json

            int result = db.updateComment(idx, req.cContent);   //update the comment in DB
            if(result == -1){
                return gson.toJson(new StructuredResponse("error", "unable to update comment " + idx, null));
            }           //if update comment worked send ok response, else send error response
            else{
                return gson.toJson(new StructuredResponse("ok", null, result));
            }
        });

        //Post route to add new comment to specified message
        Spark.post("/comments", (request, response) -> {
            CommentRequest req = gson.fromJson(request.body(), CommentRequest.class);   //get request json data
            response.status(200);
            response.type("application/json");
            int newId = db.insertComment(req.mId, req.cContent, req.uId);  //add comment in DB
            if(newId == -1){
                return gson.toJson(new StructuredResponse("error", "unable to add comment", null));
            }           //if add comment worked send ok response, else send error response
            else{
                return gson.toJson(new StructuredResponse("ok", "" + newId, null));
            }
        });

        /* 
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory()).setAudience(Collections.singletonList("797357831545-l17deqglq3col68qffmofhr3kbgv09fk.apps.googleusercontent.com")).build();

        Spark.post("/users", (request, response) -> {
            UserRequest req = gson.fromJson(request.body(), UserRequest.class);
            response.status(200);
            response.type("application/json");
            String idTokenString = request.params("idToken");
            //String GI = request.params("uGI");
            //String SO = request.params("uSO");
            //String note = request.params("uNote");

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if(idToken != null){
                Payload payload = idToken.getPayload();
    
                String userId = payload.getSubject();
                System.out.println("User Id: " + userId);
    
                String email = payload.getEmail();
                //boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                //String pictureUrl = (String) payload.get("picture");
                //String locale = (String) payload.get("locale");
                //String familyName = (String) payload.get("family_name");
                //String givenName = (String) payload.get("given_name");
     
                int newID = db.insertUser(name, email, GI, SO, note);
                users.add(Integer.valueOf(rand.nextInt(128)), idToken);
                return gson.toJson(new StructuredResponse("ok", "" + newId, null));
            }
            else{
                System.out.println("Invalid ID token");
                return gson.toJson(new StructuredResponse("error", "could not create new user", null));
            }
        });

        */

    }

    private static final String DEFAULT_PORT_DB = "5432";
    private static final int DEFAULT_PORT_SPARK = 4567;

    /**
    * Get a fully-configured connection to the database, or exit immediately
    * Uses the Postgres configuration from environment variables
    * 
    * NB: now when we shutdown the server, we no longer lose all data
    * 
    * @return null on failure, otherwise configured database object
    */
    private static Database getDatabaseConnection(){
        if( System.getenv("DATABASE_URL") != null ){
            return Database.getDatabase(System.getenv("DATABASE_URL"), DEFAULT_PORT_DB);
        }

        Map<String, String> env = System.getenv();
        String ip = env.get("POSTGRES_IP");
        String port = env.get("POSTGRES_PORT");
        String user = env.get("POSTGRES_USER");
        String pass = env.get("POSTGRES_PASS");
        return Database.getDatabase(ip, port, "", user, pass);
    } 

    /**
    * Get an integer environment variable if it exists, and otherwise return the
    * default value.
    * 
    * @envar      The name of the environment variable to get.
    * @defaultVal The integer value to use as the default if envar isn't found
    * 
    * @returns The best answer we could come up with for a value for envar
    */
    static int getIntFromEnv(String envar, int defaultVal) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get(envar) != null) {
            return Integer.parseInt(processBuilder.environment().get(envar));
        }
        return defaultVal;
    }

    /**
     * Set up CORS headers for the OPTIONS verb, and for every response that the
     * server sends.  This only needs to be called once.
     * 
     * @param origin The server that is allowed to send requests to this server
     * @param methods The allowed HTTP verbs from the above origin
     * @param headers The headers that can be sent with a request from the above
     *                origin
     */
    private static void enableCORS(String origin, String methods, String headers) {
        // Create an OPTIONS route that reports the allowed CORS headers and methods
        Spark.options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        // 'before' is a decorator, which will run before any 
        // get/post/put/delete.  In our case, it will put three extra CORS
        // headers into the response
        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
        });
    }
}
