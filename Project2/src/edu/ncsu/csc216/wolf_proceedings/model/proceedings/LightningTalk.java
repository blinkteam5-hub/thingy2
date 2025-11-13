package edu.ncsu.csc216.wolf_proceedings.model.proceedings;

/**
 * A quick lightning talk at the conference.
 * These are always 5 minutes long.
 */
public class LightningTalk extends AcceptedItem {
    
    /** Standard length for lightning talks */
    private static final int PRESENTATION_LENGTH = 5;
    
    /** Type identifier */
    private static final String ITEM_TYPE = "LightningTalk";
    
    /**
     * Creates a new lightning talk.
     * 
     * @param authors who's presenting
     * @param title the talk title
     */
    public LightningTalk(String authors, String title) {
        super(ITEM_TYPE, authors, title, PRESENTATION_LENGTH);
    }
}
