package edu.ncsu.csc216.wolf_proceedings.model.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.ncsu.csc216.wolf_proceedings.model.proceedings.AcceptedItem;
import edu.ncsu.csc216.wolf_proceedings.model.session.Session;
import edu.ncsu.csc216.wolf_proceedings.model.util.ISortedList;

/**
 * Writes conference data to a file in the proper format.
 * Handles all the # and * markers and formatting.
 */
public class ConferenceWriter {
    
    /**
     * Writes a conference to a file in the expected format.
     * 
     * @param conferenceFile where to write
     * @param conferenceName name of the conference
     * @param sessions all the sessions
     * @param proceedings all the accepted items
     * @throws IllegalArgumentException with message "Unable to save file." if there's a problem
     */
    public static void writeConferenceFile(File conferenceFile, String conferenceName,
            ISortedList<Session> sessions, ISortedList<AcceptedItem> proceedings) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(conferenceFile));
            
            // TODO: Write conference name on first line
            // TODO: Write each session with # prefix
            // TODO: Write items in each session with * prefix
            // TODO: Write unassigned items under "# +++"
            
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to save file.");
        }
    }
}
