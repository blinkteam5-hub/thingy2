package edu.ncsu.csc216.wolf_proceedings.model.conference;

import java.io.File;

import edu.ncsu.csc216.wolf_proceedings.model.io.ConferenceWriter;
import edu.ncsu.csc216.wolf_proceedings.model.proceedings.AcceptedItem;
import edu.ncsu.csc216.wolf_proceedings.model.session.Session;
import edu.ncsu.csc216.wolf_proceedings.model.util.ISortedList;
import edu.ncsu.csc216.wolf_proceedings.model.util.SortedList;

/**
 * Represents an entire conference with sessions and accepted items.
 * This is basically the main model class that ties everything together.
 */
public class Conference {
    
    /** The name of this conference */
    private String conferenceName;
    
    /** Whether changes have been made since last save */
    private boolean isChanged;
    
    /** All the sessions in this conference, kept in sorted order */
    private ISortedList<Session> sessions;
    
    /** All accepted items (papers, panels, talks), kept in sorted order */
    private ISortedList<AcceptedItem> proceedings;
    
    /**
     * Creates a new conference with the given name.
     * 
     * @param conferenceName the name of the conference
     * @throws IllegalArgumentException with message "Invalid name." if conferenceName is invalid
     */
    public Conference(String conferenceName) {
        setConferenceName(conferenceName);
        sessions = new SortedList<Session>();
        proceedings = new SortedList<AcceptedItem>();
        isChanged = true;
    }
    
    /**
     * Saves the conference to a file using the ConferenceWriter.
     * 
     * @param conferenceFile where to save
     */
    public void saveConference(File conferenceFile) {
        ConferenceWriter.writeConferenceFile(conferenceFile, conferenceName, sessions, proceedings);
        isChanged = false;
    }
    
    /**
     * Gets the conference name.
     * 
     * @return the name
     */
    public String getConferenceName() {
        return conferenceName;
    }
    
    /**
     * Sets the conference name after trimming. Can't be empty.
     * 
     * @param conferenceName the name to set
     * @throws IllegalArgumentException with message "Invalid name." if conferenceName is invalid
     */
    private void setConferenceName(String conferenceName) {
        if (conferenceName == null || conferenceName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name.");
        }
        this.conferenceName = conferenceName.trim();
    }
    
    /**
     * Checks if the conference has unsaved changes.
     * 
     * @return true if changed
     */
    public boolean isChanged() {
        return isChanged;
    }
    
    /**
     * Sets the changed flag manually.
     * 
     * @param changed whether there are changes
     */
    public void setChanged(boolean changed) {
        this.isChanged = changed;
    }
    
    /**
     * Adds a new session to the conference. Can't have duplicate names.
     * 
     * @param toAdd the session to add
     * @return the index where it was added
     * @throws IllegalArgumentException with message "Invalid session." if session can't be added
     */
    public int addSession(Session toAdd) {
        try {
            sessions.add(toAdd);
            isChanged = true;
            return sessions.indexOf(toAdd);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid session.");
        }
    }
    
    /**
     * Edits an existing session by removing old one and adding new one.
     * Need to maintain sorted order after editing.
     * 
     * @param idx which session to edit
     * @param toEdit the updated session info
     * @return the new index after resorting
     * @throws IllegalArgumentException with message "Invalid session." if session can't be edited
     * @throws IndexOutOfBoundsException if idx is out of bounds
     */
    public int editSession(int idx, Session toEdit) {
        Session original = sessions.get(idx);
        try {
            sessions.remove(idx);
            sessions.add(toEdit);
            isChanged = true;
            return sessions.indexOf(toEdit);
        } catch (IllegalArgumentException e) {
            // Put back the original if adding failed
            sessions.add(original);
            throw new IllegalArgumentException("Invalid session.");
        }
    }
    
    /**
     * Removes a session from the conference. Items in it aren't deleted though.
     * 
     * @param idx which session to remove
     * @throws IndexOutOfBoundsException if idx is out of bounds
     */
    public void removeSession(int idx) {
        Session session = sessions.remove(idx);
        // Remove the session from all items in it
        for (int i = 0; i < session.getItemList().size(); i++) {
            session.getItemList().get(i).removeSession();
        }
        isChanged = true;
    }
    
    /**
     * Gets a session at a specific index.
     * 
     * @param idx the index
     * @return the session
     * @throws IndexOutOfBoundsException if idx is out of bounds
     */
    public Session getSession(int idx) {
        return sessions.get(idx);
    }
    
    /**
     * Adds an accepted item to the proceedings.
     * 
     * @param toAdd the item to add
     * @throws IllegalArgumentException with message "Invalid accepted item." if item can't be added
     */
    public void addAcceptedItem(AcceptedItem toAdd) {
        try {
            proceedings.add(toAdd);
            isChanged = true;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid accepted item.");
        }
    }
    
    /**
     * Edits an accepted item by removing old one and adding new one.
     * Need to maintain sorted order.
     * 
     * @param idx which item to edit
     * @param toEdit the updated item
     * @throws IllegalArgumentException with message "Invalid accepted item." if item can't be edited
     * @throws IndexOutOfBoundsException if idx is out of bounds
     */
    public void editAcceptedItem(int idx, AcceptedItem toEdit) {
        AcceptedItem original = proceedings.get(idx);
        try {
            proceedings.remove(idx);
            proceedings.add(toEdit);
            isChanged = true;
        } catch (IllegalArgumentException e) {
            // Put back the original if adding failed
            proceedings.add(original);
            throw new IllegalArgumentException("Invalid accepted item.");
        }
    }
    
    /**
     * Removes an accepted item. Also removes it from any session it's in.
     * 
     * @param idx which item to remove
     * @throws IndexOutOfBoundsException if idx is out of bounds
     */
    public void removeAcceptedItem(int idx) {
        AcceptedItem item = proceedings.remove(idx);
        // If it's in a session, remove it from there too
        if (item.getSession() != null) {
            Session session = item.getSession();
            for (int i = 0; i < session.getItemList().size(); i++) {
                if (session.getItemList().get(i).equals(item)) {
                    session.getItemList().remove(i);
                    break;
                }
            }
        }
        isChanged = true;
    }
    
    /**
     * Adds an item to a session. Handles the relationship on both sides.
     * 
     * @param sessionIdx which session
     * @param itemIdx which item
     * @throws IllegalArgumentException with message "Cannot add item." if item can't be added
     * @throws IndexOutOfBoundsException if either index is out of bounds
     */
    public void addItemToSession(int sessionIdx, int itemIdx) {
        Session session = sessions.get(sessionIdx);
        AcceptedItem item = proceedings.get(itemIdx);
        try {
            session.addAcceptedItem(item);
            isChanged = true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Cannot add item.");
        }
    }
    
    /**
     * Removes an item from a session. The item stays in proceedings though.
     * 
     * @param sessionIdx which session
     * @param itemIdx which item in that session's list
     * @throws IndexOutOfBoundsException if either index is out of bounds
     */
    public void removeItemFromSession(int sessionIdx, int itemIdx) {
        Session session = sessions.get(sessionIdx);
        session.removeAcceptedItem(itemIdx);
        isChanged = true;
    }
    
    /**
     * Gets sessions as a 2D array for display in the GUI table.
     * Columns are: name, duration, remaining capacity
     * 
     * @return the session data
     */
    public String[][] getSessionsAsArray() {
        String[][] array = new String[sessions.size()][3];
        for (int i = 0; i < sessions.size(); i++) {
            Session s = sessions.get(i);
            array[i][0] = s.getName();
            array[i][1] = String.valueOf(s.getDuration());
            array[i][2] = String.valueOf(s.getRemainingCapacity());
        }
        return array;
    }
    
    /**
     * Gets items in a specific session as an array for GUI.
     * Columns are: type, authors, title, duration
     * 
     * @param idx which session
     * @return the items in that session
     * @throws IndexOutOfBoundsException if idx is out of bounds
     */
    public String[][] getAcceptedItemsInSessionAsArray(int idx) {
        Session session = sessions.get(idx);
        String[][] array = new String[session.getItemList().size()][4];
        for (int i = 0; i < session.getItemList().size(); i++) {
            AcceptedItem item = session.getItemList().get(i);
            array[i][0] = item.getType();
            array[i][1] = item.getAuthors();
            array[i][2] = item.getTitle();
            array[i][3] = String.valueOf(item.getDuration());
        }
        return array;
    }
    
    /**
     * Gets all accepted items as an array for GUI.
     * Columns are: type, authors, title, duration, session name (or empty if none)
     * 
     * @return all the items
     */
    public String[][] getAcceptedItemsAsArray() {
        String[][] array = new String[proceedings.size()][5];
        for (int i = 0; i < proceedings.size(); i++) {
            AcceptedItem item = proceedings.get(i);
            array[i][0] = item.getType();
            array[i][1] = item.getAuthors();
            array[i][2] = item.getTitle();
            array[i][3] = String.valueOf(item.getDuration());
            array[i][4] = item.getSession() != null ? item.getSession().getName() : "";
        }
        return array;
    }
}