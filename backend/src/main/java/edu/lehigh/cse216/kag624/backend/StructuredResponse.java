package edu.lehigh.cse216.kag624.backend;

/**
 * StructuredResponse provides a common format for success and failure messages,
 * with an optional payload of type Object that can be converted into JSON.
 * 
 * NB: since this will be converted into JSON, all fields must be public.
 */
public class StructuredResponse {
    /**
     * The status is a string that the application can use to quickly determine
     * if the response indicates an error.  Values will probably just be "ok" or
     * "error", but that may evolve over time.
     */
    public String mStatus;

    /**
     * The message is only useful when this is an error, or when data is null.
     */
    public String mMessage;

    /**
     * Any JSON-friendly object can be referenced here, so that we can have a
     * rich reply to the client
     */
    public Object mData;

    //
    /**
     * session key returned when user is posted
     */
    public String sessionKey;

    //
    /**
     * user id also returned when user is posted
     */
    public Object newUId;

    /**
     * 
     * @param status
     * @param message
     * @param data
     */
    public StructuredResponse(String status, String message, Object data) {
        mStatus = (status != null) ? status : "invalid";
        mMessage = message;
        mData = data;
    }

    //
    /**
     * another constructor created specifically for returning a session key and user id when a user is created
     * @param status
     * @param message
     * @param data1
     * @param data2
     */
    public StructuredResponse(String status, String message, String data1, Object data2){
        mStatus = (status != null) ? status: "invalid";
        mMessage = message;
        sessionKey = data1;
        newUId = data2;
    }
}