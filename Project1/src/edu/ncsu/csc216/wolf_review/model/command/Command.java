package edu.ncsu.csc216.wolf_review.model.command;

/**
 * The purpose of this class is to represent a specific action undertaken by the user
 * It is part of the Command pattern that aids in separating the user interface from 
 * the model's state logic. 
 * 
 * @author Ajaunie White 
 */
public class Command {

    /**
     * Outlines the actions that a user can take that could impact the 
     * Lifecycle of a paper. 
     */
    public enum CommandValue { ASSIGN, REJECT, CLOSE, ACCEPT, SUBMIT, RECOMMEND, PROPOSE, MODIFY, PROCESS }

    private CommandValue command;
    private String commandInformation;

    /**
     * This sets up a new Command. For instance, when a user selects someone to review, 
     * the command used would be ASSIGN, and the reviewer's ID would be passed in as 
     * commandInformation.
     *
     * @param command: the chosen action to perform, taken from CommandValue.
     * @param commandInformation: any additional details required to carry out the command.
     */
    public Command(CommandValue command, String commandInformation) {
        // Constructor logic will go here later
    }

    /**
     * Retrieves the main action this command represents 
     * @return the command's primary action type
     */
    public CommandValue getCommand() {
        return null;
    }

    /**
     * If there is any extra data associated with the command this returns it. 
     * @return the supporting information for the command
     */
    public String getCommandInformation() {
        return null;
    }
}