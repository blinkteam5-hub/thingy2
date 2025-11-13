package edu.ncsu.csc216.wolf_proceedings.model.proceedings;

/**
 * A paper presentation at the conference.
 * Papers are special because they can have custom durations, unlike panels and talks.
 */
public class Paper extends AcceptedItem {
    
    /** Standard duration for a paper if not specified */
    private static final int DEFAULT_PAPER_DURATION = 15;
    
    /** The type string for papers */
    private static final String ITEM_TYPE = "Paper";
    
    /**
     * Creates a paper with the default 15 minute duration.
     * 
     * @param authors who wrote it
     * @param title the paper title
     * @throws IllegalArgumentException with message "Invalid accepted item." if any field is invalid
     */
    public Paper(String authors, String title) {
        super(ITEM_TYPE, authors, title, DEFAULT_PAPER_DURATION);
    }
    
    /**
     * Creates a paper with a custom duration.
     * Useful for papers that need more or less time than the default.
     * 
     * @param authors who wrote it
     * @param title the paper title
     * @param duration how long to present
     * @throws IllegalArgumentException with message "Invalid accepted item." if any field is invalid
     */
    public Paper(String authors, String title, int duration) {
        super(ITEM_TYPE, authors, title, duration);
    }
    
    /**
     * Converts the paper to string format for file output.
     * Includes custom duration if it's not the default, otherwise leaves it out.
     * 
     * @return string representation
     */
    @Override
    public String toString() {
        String baseString = super.toString();
        // Only include duration if it's custom (not the default)
        if (getDuration() != DEFAULT_PAPER_DURATION) {
            return baseString + "|" + getDuration();
        }
        return baseString;
    }
}
