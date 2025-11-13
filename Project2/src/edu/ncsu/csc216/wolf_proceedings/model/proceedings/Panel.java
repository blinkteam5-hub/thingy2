package edu.ncsu.csc216.wolf_proceedings.model.proceedings;

/**
 * A panel discussion at the conference.
 * Panels always take 75 minutes.
 */
public class Panel extends AcceptedItem {
    
    /** How long a panel takes */
    private static final int PRESENTATION_LENGTH = 75;
    
    /** Type identifier for panels */
    private static final String ITEM_TYPE = "Panel";
    
    /**
     * Creates a new panel.
     * 
     * @param authors the panelists
     * @param title the panel topic
     */
    public Panel(String authors, String title) {
        super(ITEM_TYPE, authors, title, PRESENTATION_LENGTH);
    }
}
