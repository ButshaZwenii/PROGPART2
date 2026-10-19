/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpart2;

/**
 *
 * @author butsha
 */
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;

public class Message {
    // Instance variables for each specific message
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Static variables to track data across the whole application session
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static int totalMessages = 0;

    public Message(String recipient, String messageText, int messageNumber) {
        // Generate a random 10-digit number for the ID
        long randomNum = (long) (Math.random() * 9000000000L) + 1000000000L;
        this.messageID = String.valueOf(randomNum);
        
        // Uses the loop counter passed from Main
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    // Ensures ID is not empty and max 10 characters
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Ensures recipient number is valid
    public String checkRecipientCell() {
        // Regex matches +27 or 0 followed by 9 digits
        if (recipient != null && recipient.matches("^(\\+27|0)[6-8]\\d{8}$")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Checks if message is under the 250 character limit
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            // Calculates how many characters over the limit the message is
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Creates the hash using string manipulation
    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) return "";
        
        // Extract the first two characters of the ID
        String idPrefix = messageID.substring(0, 2);
        
        // Split the message into an array of words
        String[] words = messageText.trim().split("\\s+");
        
        // Remove punctuation from the first and last words
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        
        // Format the hash: XX:Y:FIRSTLAST and convert to uppercase
        return (idPrefix + ":" + messageNumber + ":" + (firstWord + lastWord)).toUpperCase();
    }

    // Handles the user's choice of sending, disregarding, or storing
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                sentMessages.add(this);
                totalMessages++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored";
            default:
                return "Invalid choice.";
        }
    }

    // Returns the static total count of messages sent
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // Stores the message in a JSON file format
    // Reference: GeeksforGeeks - Writing to JSON in Java
    // URL: https://www.geeksforgeeks.org/parse-json-java/
    public void storeMessage() {
        try (FileWriter file = new FileWriter("messages.json", true)) {
            // Appends JSON object to the file
            file.write("{\n");
            file.write("  \"messageID\": \"" + messageID + "\",\n");
            file.write("  \"messageHash\": \"" + messageHash + "\",\n");
            file.write("  \"recipient\": \"" + recipient + "\",\n");
            file.write("  \"message\": \"" + messageText + "\"\n");
            file.write("}\n");
        } catch (IOException e) {
            System.out.println("Error storing message to JSON.");
        }
    }

    // Displays the details of a single message
    public void printMessageDetails() {
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
    }
    
    // Setter used for unit testing hash generation
    public void setMessageID(String id) {
        this.messageID = id;
        this.messageHash = createMessageHash(); // Update hash if ID changes
    }
    
    public String getMessageID() {
        return messageID;
    }
    
    public String getMessageHash() {
        return messageHash;
    }
}
