package edu.ncsu.csc216.wolf_proceedings.model.proceedings;

import edu.ncsu.csc216.wolf_proceedings.model.session.Session;

/**
 * Represents something that got accepted to the conference.
 * Could be a paper, panel, or lightning talk - those all extend this.
 * This handles all the common stuff like authors, title, duration.
 */
public abstract class AcceptedItem implements Comparable<AcceptedItem> {
    
    /** Shortest allowed presentation time in minutes */
    public static final int MIN_DURATION = 5;
    
    /** Longest allowed presentation time in minutes */
    public static final int MAX_DURATION = 120;
    
    /** Who wrote or is presenting this item */
    private String authors;
    
    /** The title of the item */
    private String title;
    
    /** What kind of item this is (Paper, Panel, etc) */
    private String type;
    
    /** How long the presentation will take in minutes */
    private int duration;
    
    /** The session this item is assigned to, if any */
    private Session selectedSession;
    
    /**
     * Creates an accepted item with all the necessary info.
     * Delegates validation to the setter methods.
     * 
     * @param type what kind of item (paper, panel, etc)
     * @param authors who's presenting
     * @param title the title
     * @param duration how long it takes
     * @throws IllegalArgumentException with message "Invalid accepted item." if any field is invalid
     */
    public AcceptedItem(String type, String authors, String title, int duration) {
        setType(type);
        setAuthors(authors);
        setTitle(title);
        setDuration(duration);
        selectedSession = null;
    }
    
    /**
     * Gets the authors.
     * 
     * @return the authors
     */
    public String getAuthors() {
        return authors;
    }
    
    /**
     * Sets the authors after trimming whitespace. Can't be empty or null.
     * 
     * @param authors the authors to set
     * @throws IllegalArgumentException with message "Invalid accepted item." if authors is null or empty
     */
    public void setAuthors(String authors) {
        if (authors == null || authors.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid accepted item.");
        }
        this.authors = authors.trim();
    }
    
    /**
     * Gets the title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets the title after trimming whitespace. Can't be empty or null.
     * 
     * @param title the title to set
     * @throws IllegalArgumentException with message "Invalid accepted item." if title is null or empty
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid accepted item.");
        }
        this.title = title.trim();
    }
    
    /**
     * Gets the type of item.
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }
    
    /**
     * Sets the type after trimming whitespace. Can't be empty or null.
     * 
     * @param type the type to set
     * @throws IllegalArgumentException with message "Invalid accepted item." if type is null or empty
     */
    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid accepted item.");
        }
        this.type = type.trim();
    }
    
    /**
     * Gets how long the presentation is.
     * 
     * @return the duration in minutes
     */
    public int getDuration() {
        return duration;
    }
    
    /**
     * Sets the duration. Must be between min and max, inclusive.
     * 
     * @param duration the duration in minutes
     * @throws IllegalArgumentException with message "Invalid accepted item." if duration out of range
     */
    public void setDuration(int duration) {
        if (duration < MIN_DURATION || duration > MAX_DURATION) {
            throw new IllegalArgumentException("Invalid accepted item.");
        }
        this.duration = duration;
    }
    
    /**
     * Assigns this item to a session. Has to check several conditions first.
     * 
     * @param session the session to assign to
     * @throws IllegalArgumentException with message "Cannot add item." if item can't be added
     */
    public void addSession(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Cannot add item.");
        }
        if (selectedSession != null) {
            throw new IllegalArgumentException("Cannot add item.");
        }
        if (session.getRemainingCapacity() < this.duration) {
            throw new IllegalArgumentException("Cannot add item.");
        }
        selectedSession = session;
    }
    
    /**
     * Removes this item from its current session by setting it to null.
     */
    public void removeSession() {
        selectedSession = null;
    }
    
    /**
     * Gets the session this item is in.
     * 
     * @return the session, or null if not in one
     */
    public Session getSession() {
        return selectedSession;
    }
    
    /**
     * Compares this item to another based on authors then title.
     * Case insensitive comparison so "Smith" and "smith" are treated the same.
     * 
     * @param other the item to compare to
     * @return negative if this comes first, positive if after, 0 if equal
     */
    @Override
    public int compareTo(AcceptedItem other) {
        // First compare authors (case insensitive)
        int authorCompare = this.authors.toLowerCase().compareTo(other.authors.toLowerCase());
        if (authorCompare != 0) {
            return authorCompare;
        }
        // If authors are the same, compare titles (case insensitive)
        return this.title.toLowerCase().compareTo(other.title.toLowerCase());
    }
    
    /**
     * Creates a string representation for saving to a file.
     * Format is: type|authors|title (or type|authors|title|duration for papers with custom duration)
     * 
     * @return the string format without the leading *
     */
    @Override
    public String toString() {
        return type + "|" + authors + "|" + title;
    }
}
