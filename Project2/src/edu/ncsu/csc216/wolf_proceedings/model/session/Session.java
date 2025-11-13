package edu.ncsu.csc216.wolf_proceedings.model.session;

import edu.ncsu.csc216.wolf_proceedings.model.proceedings.AcceptedItem;
import edu.ncsu.csc216.wolf_proceedings.model.util.ISwapList;
import edu.ncsu.csc216.wolf_proceedings.model.util.SwapList;

/**
 * A session at a conference that contains multiple accepted items.
 * Has a name, duration, and list of items that can be reordered.
 */
public class Session implements Comparable<Session> {
    
    /** Minimum session length in minutes */
    private static final int MIN_DURATION = 5;
    
    /** Maximum session length in minutes */
    private static final int MAX_DURATION = 120;
    
    /** Name of this session */
    private String name;
    
    /** Total time allocated for this session in minutes */
    private int duration;
    
    /** The items scheduled for this session in presentation order */
    private ISwapList<AcceptedItem> itemList;
    
    /**
     * Creates a new session with given name and duration.
     * 
     * @param name the session name
     * @param duration how long the session is in minutes
     * @throws IllegalArgumentException with message "Invalid session." if parameters are invalid
     */
    public Session(String name, int duration) {
        setName(name);
        setDuration(duration);
        itemList = new SwapList<AcceptedItem>();
    }
    
    /**
     * Gets the session name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the session name after trimming. Can't be null or empty.
     * 
     * @param name the name to set
     * @throws IllegalArgumentException with message "Invalid session." if name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid session.");
        }
        this.name = name.trim();
    }
    
    /**
     * Gets the session duration.
     * 
     * @return duration in minutes
     */
    public int getDuration() {
        return duration;
    }
    
    /**
     * Sets the duration. Must be within allowed range.
     * 
     * @param duration the duration in minutes
     * @throws IllegalArgumentException with message "Invalid session." if duration out of bounds
     */
    public void setDuration(int duration) {
        if (duration < MIN_DURATION || duration > MAX_DURATION) {
            throw new IllegalArgumentException("Invalid session.");
        }
        this.duration = duration;
    }
    
    /**
     * Gets the list of items in this session.
     * 
     * @return the item list
     */
    public ISwapList<AcceptedItem> getItemList() {
        return itemList;
    }
    
    /**
     * Adds an accepted item to this session at the end of the list.
     * Makes sure there's enough time for it and handles the bidirectional relationship.
     * 
     * @param item the item to add
     * @throws IllegalArgumentException with message "Invalid session." if item can't be added
     */
    public void addAcceptedItem(AcceptedItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Invalid session.");
        }
        if (getRemainingCapacity() < item.getDuration()) {
            throw new IllegalArgumentException("Invalid session.");
        }
        try {
            item.addSession(this);
            itemList.add(item);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid session.");
        }
    }
    
    /**
     * Removes an item from the session at the given index.
     * Also removes this session from the item.
     * 
     * @param idx where to remove from
     */
    public void removeAcceptedItem(int idx) {
        AcceptedItem item = itemList.remove(idx);
        item.removeSession();
    }
    
    /**
     * Calculates how much time is left in the session.
     * Subtracts all the item durations from the total duration.
     * 
     * @return remaining minutes
     */
    public int getRemainingCapacity() {
        int usedTime = 0;
        for (int i = 0; i < itemList.size(); i++) {
            usedTime += itemList.get(i).getDuration();
        }
        return duration - usedTime;
    }
    
    /**
     * Compares sessions by name, case insensitive.
     * 
     * @param other the session to compare to
     * @return comparison result
     */
    @Override
    public int compareTo(Session other) {
        return this.name.toLowerCase().compareTo(other.name.toLowerCase());
    }
    
    /**
     * Converts session to string format for file output.
     * Format is: name,duration (without the leading #)
     * 
     * @return string representation
     */
    @Override
    public String toString() {
        return name + "," + duration;
    }
}
