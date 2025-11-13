package edu.ncsu.csc216.wolf_proceedings.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_proceedings.model.conference.Conference;
import edu.ncsu.csc216.wolf_proceedings.model.proceedings.AcceptedItem;
import edu.ncsu.csc216.wolf_proceedings.model.proceedings.LightningTalk;
import edu.ncsu.csc216.wolf_proceedings.model.proceedings.Panel;
import edu.ncsu.csc216.wolf_proceedings.model.proceedings.Paper;
import edu.ncsu.csc216.wolf_proceedings.model.session.Session;

/**
 * Reads conference data from a file.
 * Handles parsing the special format with sessions and items.
 * Invalid records get skipped instead of crashing everything.
 */
public class ConferenceReader {
    
    /**
     * Reads a conference from a file and creates the Conference object.
     * 
     * @param conferenceFile the file to read
     * @return the loaded conference
     * @throws IllegalArgumentException with message "Unable to load file." if file doesn't exist
     */
    public static Conference readConferenceFile(File conferenceFile) {
        try {
            Scanner fileScanner = new Scanner(conferenceFile);
            StringBuilder fileContent = new StringBuilder();
            
            // Read whole file into a string, adding newlines back
            while (fileScanner.hasNextLine()) {
                fileContent.append(fileScanner.nextLine()).append("\n");
            }
            fileScanner.close();
            
            String content = fileContent.toString();
            
            // TODO: Parse conference name from first line
            // TODO: Split file into session tokens using \\r?\\n?[#] delimiter
            // TODO: Process each session token
            // TODO: Handle the "+++" token for unassigned items
            
            return null;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to load file.");
        }
    }
    
    /**
     * Processes a session token from the file.
     * Creates the session, parses items in it, and adds them.
     * 
     * @param conference the conference to add to
     * @param sessionText the text for this session
     * @return the created session, or null if session is invalid
     */
    private static Session processSession(Conference conference, String sessionText) {
        // TODO: Parse first line for session name and duration
        // TODO: Split remaining text into item tokens using \\r?\\n?[*] delimiter
        // TODO: Create session and add items to it
        // TODO: Add session to conference
        return null;
    }
    
    /**
     * Processes an accepted item token and creates the right type.
     * Figures out if it's a paper, panel, or lightning talk.
     * 
     * @param acceptedItemText the text for this item
     * @return the created accepted item, or null if invalid
     */
    private static AcceptedItem processAcceptedItem(String acceptedItemText) {
        // TODO: Split by | delimiter
        // TODO: Parse type, authors, title
        // TODO: For papers, check if there's a custom duration
        // TODO: Create appropriate subclass and return it
        return null;
    }
}