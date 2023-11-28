package edu.lehigh.cse216.kag624.backend;

// Import the Spark package, so that we can make use of the "get" function to 
// create an HTTP GET route
import spark.Spark;

// Import Google's JSON library
import com.google.gson.*;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.*;

//import memcachier
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;


/**
 * For now, our app creates an HTTP server that can only get and add data.
 */
public class App {
    /**
     * 
     * @param args
     */
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

        Hashtable<UUID, Integer> users = new Hashtable<>();
        users.put(UUID.fromString("9cb1f884-8190-11ee-b962-0242ac120002"), 1);

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
            if(request.queryParams().contains("sessionKey")){       //get sessionKey query to check if user is authorized
                String sessionKey = request.queryParams("sessionKey");
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }
                return gson.toJson(new StructuredResponse("ok", null, db.selectAll()));
                //return gson.toJson(new StructuredResponse("ok", null, dataStore.readAll()));
            }
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
            }
        });

        // GET route that returns everything for a single row in the DataStore.
        // The ":id" suffix in the first parameter to get() becomes 
        // request.params("id"), so that we can get the requested row ID.  If 
        // ":id" isn't a number, Spark will reply with a status 500 Internal
        // Server Error.  Otherwise, we have an integer, and the only possible 
        // error is that it doesn't correspond to a row with data.
        Spark.get("/messages/:id/", (request, response) -> {
            int idx = Integer.parseInt(request.params("id"));
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            DataRow data = db.selectOne(idx);

            if(request.queryParams().contains("sessionKey")){
                String sessionKey = request.queryParams("sessionKey");  //more sessionKeys
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }
                if (data == null) {
                    return gson.toJson(new StructuredResponse("error", idx + " not found", null));
                } else {
                    return gson.toJson(new StructuredResponse("ok", null, data));
                }
            }
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
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
            if(request.queryParams().contains("sessionKey")){
                String sessionKey = request.queryParams("sessionKey");
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }
                int newId = db.insertRow(users.get(UUID.fromString(sessionKey)), req.mTitle, req.mMessage);
                if (newId == -1) {
                    return gson.toJson(new StructuredResponse("error", "error performing insertion", null));
                } else {
                    return gson.toJson(new StructuredResponse("ok", null, newId));
                }
            }
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
            }
        });

        //  PUT route for updating the likes on a message. Will read JSON from the 
        //  body of the request, turn it into a SimpleRequest object, extract the id
        //  from the request and the likes and update the message with the given id
        //  to the new passed value of likes
        Spark.put("/messages/:id/", (request, response) -> {
            // If we can't get an ID or can't parse the JSON, Spark will send
            // a status 500
            int idx = Integer.parseInt(request.params("id"));
            LikeRequest req = gson.fromJson(request.body(), LikeRequest.class);
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            // NB: updateLikes checks for null ids

            if(request.queryParams().contains("sessionKey")){
                String sessionKey = request.queryParams("sessionKey");
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }

                int result = -1;

                int uId = users.get(UUID.fromString(sessionKey));

                if(req.isLike == 1){
                    if(db.isLiked(idx, uId) == 1){  //if message is already liked, remove the like
                        result = db.removeLike(idx, uId);
                    }
                    else if(db.isLiked(idx, uId) == 2){ //if message is disliked,
                        db.removeDislike(idx, uId);         //remove dislike,
                        result = db.addLike(idx, uId);  //then like the message
                    }
                    else if(db.isLiked(idx, uId) == 0){ //if message is not liked or disliked,
                        result = db.addLike(idx, uId);  //add a like
                    }
                }
                else if(req.isLike == 0){
                    if(db.isLiked(idx, uId) == 1){  //if message is currently liked
                        db.removeLike(idx, uId);        //remove the like in DB
                        result = db.addDislike(idx, uId);   //then add the dislike to the DB
                    }
                    else if(db.isLiked(idx, uId) == 2){ //if message is currently disliked
                        result = db.removeDislike(idx, uId);    //remove dislike from DB
                    }
                    else if(db.isLiked(idx, uId) == 0){ //if message is not liked or disliked
                        result = db.addDislike(idx, uId);   //add dislike to DB
                    }
                }

                if (result == -1) {     //if requests worked send ok response, else send error response
                    return gson.toJson(new StructuredResponse("error", "unable to update row " + idx, null));
                } else {    
                    return gson.toJson(new StructuredResponse("ok", null, result));
                }
            }
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
            }
        });

        /* 
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
        */

        //Get route to get all comments on a message
        Spark.get("/comments/:mId/", (request, response) -> {
            int idx = Integer.parseInt(request.params("mId"));   //get message id from route
            response.status(200);
            response.type("application/json");
            if(request.queryParams().contains("sessionKey")){
                String sessionKey = request.queryParams("sessionKey");  //check sessionKey
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }
                return gson.toJson(new StructuredResponse("ok", null, db.selectAllComments(idx)));  //return all comments
            }
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
            }
        });

        //get route for all comments currently in comment DB
        Spark.get("/comments", (request, response) -> {
            response.status(200);
            response.type("application/json");      //return the result of db.getallcomments which gets all comments
            if(request.queryParams().contains("sessionKey")){
                String sessionKey = request.queryParams("sessionKey");  //check sessionKey
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }
                return gson.toJson(new StructuredResponse("ok", null, db.getAllComments()));
            }
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
            }
        });

        //Put route to edit comment
        Spark.put("/comments/:commentId/", (request, response) -> {
            int idx = Integer.parseInt(request.params("commentId"));    //get comment id from route
            response.status(200);
            response.type("application/json");
            CommentRequest req = gson.fromJson(request.body(), CommentRequest.class);   //get new comment content from json

            if(request.queryParams().contains("sessionKey")){
                CommentData comment = db.selectOneComment(idx);
                String sessionKey = request.queryParams("sessionKey");
                if(!(users.get(UUID.fromString(sessionKey)) == comment.uId)){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }

                int result = db.updateComment(idx, req.cContent);   //update the comment in DB
                if(result == -1){
                    return gson.toJson(new StructuredResponse("error", "unable to update comment " + idx, null));
                }           //if update comment worked send ok response, else send error response
                else{
                    return gson.toJson(new StructuredResponse("ok", null, result));
                }
            }
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
            }
        });

        //Post route to add new comment to specified message
        Spark.post("/comments", (request, response) -> {
            CommentRequest req = gson.fromJson(request.body(), CommentRequest.class);   //get request json data
            response.status(200);
            response.type("application/json");

            if(request.queryParams().contains("sessionKey")){
                String sessionKey = request.queryParams("sessionKey");
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }

                int newId = db.insertComment(req.mId, req.cContent, users.get(UUID.fromString(sessionKey)));  //add comment in DB
                if(newId == -1){
                    return gson.toJson(new StructuredResponse("error", "unable to add comment", null));
                }           //if add comment worked send ok response, else send error response
                else{
                    return gson.toJson(new StructuredResponse("ok", null, newId));
                }
            }
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
            }
        });

        //create verifier object to verify a google id token
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory()).setAudience(Collections.singletonList("69750154488-v2ko8le0do3lcrsmr2cj6dqnl4untjls.apps.googleusercontent.com")).build();

        Spark.post("/users", (request, response) -> {
            UserRequest req = gson.fromJson(request.body(), UserRequest.class);
            response.status(200);
            response.type("application/json");
            String idTokenString = req.idToken;     //get id token and other info from body of request
            String GI = req.uGI;
            String SO = req.uSO;
            String note = req.uNote;

            GoogleIdToken idToken = verifier.verify(idTokenString);     //verify the id token
            if(idToken != null){
                Payload payload = idToken.getPayload(); //get info from token
    
                String email = payload.getEmail();                                          //get email and name from paylaod
                //boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                //String pictureUrl = (String) payload.get("picture");
                //String locale = (String) payload.get("locale");
                //String familyName = (String) payload.get("family_name");
                //String givenName = (String) payload.get("given_name");

                if(payload.getHostedDomain() == null){  //if not part of a g suite, send error
                    return gson.toJson(new StructuredResponse("error", "only lehigh.edu domains supported", null));
                }
                else if(payload.getHostedDomain().equals("lehigh.edu")){    //if g suite is not lehigh.edu, send error
                    UUID sessionKey = UUID.randomUUID();
                    int newId = db.insertUser(name, email, GI, SO, note);   //if user is part of lehigh.edu, get random UUID for session key
                    users.put(sessionKey, newId);                              //add session key and new user id to hash table
                    return gson.toJson(new StructuredResponse("ok", null, sessionKey.toString(), newId));
                }                               //send session key and user id in response
                else{
                    return gson.toJson(new StructuredResponse("error", "hosted domain not supported", null));
                }
            }
            else{
                return gson.toJson(new StructuredResponse("error", "could not create new user, id token invalid", null));
            }
        });

        Spark.get("/users/:id/", (request, response) -> {
            response.status(200);
            response.type("application/json");

            int idx = Integer.parseInt(request.params("id"));

            if(request.queryParams().contains("sessionKey")){
                String sessionKey = request.queryParams("sessionKey");  //check session key
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }
                else if(users.get(UUID.fromString(sessionKey)) == idx){ //if user is getting his/her own profile, give all data
                    return gson.toJson(new StructuredResponse("ok", null, db.selectOneUser(idx)));
                }
                else{       //if user is getting someone else's profile, only give name, email, and note
                    return gson.toJson(new StructuredResponse("ok", null, db.selectOneOtherUser(idx)));
                }
            }
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
            }
        });

        Spark.get("/users", (request, response) -> {        //might not need this, sarah asked to make it
            response.status(200);
            response.type("application/json");

            if(request.queryParams().contains("sessionKey")){
                String sessionKey = request.queryParams("sessionKey");  //check session key
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }
                else{           //return uId and name of all users in user table
                    return gson.toJson(new StructuredResponse("ok", null, db.selectAllUsers()));
                }
            }
            else{
                return gson.toJson(new StructuredResponse("error", "could not get all users", null));
            }
        });

        Spark.put("/users", (request, response) -> {
            response.status(200);
            response.type("application/json");
            UserRequest req = gson.fromJson(request.body(), UserRequest.class);

            if(request.queryParams().contains("sessionKey")){
                String sessionKey = request.queryParams("sessionKey");  //check session key
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user note authorized", null));
                }
                int uId = users.get(UUID.fromString(request.queryParams("sessionKey")));    //get uId from sessionKey

                int result = db.updateUser(uId, req.uName, req.uGI, req.uSO, req.uNote);    //update user in db
                if(result == -1){
                    return gson.toJson(new StructuredResponse("error", "could not update user " + uId, null));
                }
                else{
                    return gson.toJson(new StructuredResponse("ok", null, result));
                }
            }
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
            }
        });

        Spark.get("/likes", (request, response) -> {
            response.status(200);
            response.type("application/json");

            if(request.queryParams().contains("sessionKey")){
                String sessionKey = request.queryParams("sessionKey");  //check session key
                if(!users.containsKey(UUID.fromString(sessionKey))){
                    return gson.toJson(new StructuredResponse("error", "user not authorized", null));
                }
                int uId = users.get(UUID.fromString(sessionKey));       //get uid from sessionkey
                return gson.toJson(new StructuredResponse("ok", null, db.getLikedMessages(uId)));
            }               //call getliked messages from database.java to get liked messages
            else{
                return gson.toJson(new StructuredResponse("error", "must include sessionKey query", null));
            }
        });
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
     * 
     * @param envar
     * @param defaultVal
     * @return
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

