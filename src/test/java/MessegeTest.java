/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author butsha
 */

package MessageTest;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Message class.
 * These tests verify the validation logic for message length, recipient numbers,
 * hash generation, and the different send/store options.
 */
public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        // Create a valid message under 250 characters
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        
        // Verify that the method returns the exact success string
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Create a string of 'a's with exactly 260 characters 
        // (10 characters over the 250 limit)
        String longMsg = "a".repeat(260); 
        Message msg = new Message("+27718693002", longMsg, 0);
        
        // Verify that the method calculates the excess correctly (260 - 250 = 10)
        // and returns the required failure message
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientCellSuccess() {
        // Create a message with a valid South African international number
        Message msg = new Message("+27718693002", "Hi", 0);
        
        // Verify the recipient is validated successfully
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        // Create a message with an invalid number (missing international code, too short)
        Message msg = new Message("08966553", "Hi", 0);
        
        // Verify that the invalid number triggers the correct error message
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashGeneration() {
        // Create a message containing multiple words and punctuation
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        
        // Force the Message ID to start with "00" so the output perfectly matches 
        // the required test data output ("00:0:HITONIGHT")
        msg.setMessageID("0012345678");
        
        // Verify the hash format: first two digits of ID + message number + first and last words (uppercase)
        assertEquals("00:0:HITONIGHT", msg.getMessageHash());
    }

    @Test
    public void testMessageIDCreated() {
        // Create a new message
        Message msg = new Message("+27718693002", "Hi", 0);
        
        // Verify that a random 10-digit ID was successfully generated and passes validation
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testSentMessageOptions() {
        // Test Option 1: User selects "Send Message"
        Message msg1 = new Message("+27718693002", "Hi", 0);
        assertEquals("Message successfully sent.", msg1.SentMessage(1));
        
        // Test Option 2: User selects "Disregard Message"
        Message msg2 = new Message("+27718693002", "Hi", 1);
        assertEquals("Press 0 to delete the message.", msg2.SentMessage(2));
        
        // Test Option 3: User selects "Store Message"
        Message msg3 = new Message("+27718693002", "Hi", 2);
        assertEquals("Message successfully stored", msg3.SentMessage(3));
    }
}
