package edu.lehigh.cse216.kag624.backend;

import java.util.ArrayList;

/**
 * DataStore provides access to a set of objects, and makes sure that each has
 * a unique identifier that remains unique even after the object is deleted.
 * 
 * We follow the convention that member fields of a class have names that start
 * with a lowercase 'm' character, and are in camelCase.
 * 
 * NB: The methods of DataStore are synchronized, since they will be used from a 
 * web framework and there may be multiple concurrent accesses to the DataStore.
 */
public class DataStore {
    /**
     * The rows of data in our DataStore
     */
    private ArrayList<DataRow> mRows;

    /**
     * A counter for keeping track of the next ID to assign to a new row
     */
    private int mCounter;

    /**
     * Construct the DataStore by resetting its counter and creating the
     * ArrayList for the rows of data.
     */
    DataStore() {
        mCounter = 0;
        mRows = new ArrayList<>();
    }

    /**
     * 
     * @param title
     * @param content
     * @return
     */
    public synchronized int createEntry(String title, String content) {
        if (title == null || content == null)
            return -1;
        // NB: we can safely assume that id is greater than the largest index in 
        //     mRows, and thus we can use the index-based add() method
        int id = mCounter++;
        //DataRow data = new DataRow(id, title, content);
        //mRows.add(id, data);
        return id;
    }

    /**
     * 
     * @param id
     * @return
     */
    public synchronized DataRow readOne(int id) {
        if (id >= mRows.size())
            return null;
        DataRow data = mRows.get(id);
        if (data == null)
            return null;
        return new DataRow(data);
    }

    /**
     * 
     * @return
     */
    public synchronized ArrayList<DataRow> readAll() {
        ArrayList<DataRow> data = new ArrayList<>();
        // NB: we copy the data, so that our ArrayList only has ids and titles
        for (DataRow row : mRows) {
            if (row != null)
                data.add(new DataRow(row));
        }
        return data;
    }

    /**
     * 
     * @param id
     * @param likes
     * @return
     */
    public synchronized DataRow updateLikes(int id, int likes) {
        // Only update if the current entry is valid (not null)
        if (id >= mRows.size())
            return null;
        DataRow data = mRows.get(id);
        if (data == null)
            return null;
        // Update and then return a copy of the data, as a DataRow
        data.mLikes = likes;
        return new DataRow(data);
    }

    /**
     * Delete a row from the DataStore
     * 
     * @param id The Id of the row to delete
     * @return true if the row was deleted, false otherwise
     */
    public synchronized boolean deleteOne(int id) {
        // Deletion fails for an invalid Id or an Id that has already been 
        // deleted
        if (id >= mRows.size())
            return false;
        if (mRows.get(id) == null)
            return false;
        // Delete by setting to null, so that any Ids used by other clients
        // still refer to the same positions in the ArrayList.
        mRows.set(id, null);
        return true;
    }
}