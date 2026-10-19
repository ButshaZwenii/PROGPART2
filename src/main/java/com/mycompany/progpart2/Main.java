/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progpart2;

/**
 *
 * @author butsha
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Scanner;
  public class Main {
    public static void main (String[]args){
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("WELCOME TO CHATAPP");
            // ask for the registration info
            System.out.print("Enter your first name: ");
            String firstName = input.nextLine();
            System.out.print("Enter your last name: ");
            String lastName = input.nextLine();
            System.out.print("Enter a username: ");
            String username = input.nextLine();
            System.out.print("Enter a password: ");
            String password = input.nextLine();
            System.out.print(
                    "Enter your South African cell phone number (+27...): "
            );          String cellPhoneNumber = input.nextLine();
            Login login = new Login(
                    firstName,
                    lastName,
                    username,
                    password,
                    cellPhoneNumber
            );          // Execute registration and store the outcome message.
            String registrationMessage = login.registerUser();
            // Output the registration result to the console.
            System.out.println();
            System.out.println(registrationMessage);
            // Stop execution if registration failed to prevent login attempts.
            if (!login.isRegistered()) {
                System.out.println();
                System.out.println("Registration was not completed.");
                input.close();
                return;
            }           // Proceed to login only after successful registration.
            System.out.println();
            System.out.println("---- LOGIN ----");
            // Prompt for login credentials to verify against stored data.
            System.out.print("Enter your username : ");
            String loginUsername = input.nextLine();
            System.out.print("Enter your password to : ");
            String loginPassword = input.nextLine();
            // Record the user's login attempt in the Login object.
            login.setEnteredUsername(loginUsername);
            login.setEnteredPassword(loginPassword);
            // Validate credentials against stored registration data.
// loginUser() returns true if the username and password match.
login.loginUser();
// Retrieve and display the final login status message.
String loginMessage = login.returnLoginStatus();
System.out.println();
System.out.println(loginMessage);
// Close the Scanner to release system resources.
   
  // --- STOP IF LOGIN FAILED ---
            if (!login.loginUser()) {
                return;

        }
                 // ==========================================
            // --- QUICKCHAT PART 2 PHASE ---
            // ==========================================
            System.out.println("\nWelcome to QuickChat.");
            
            System.out.print("How many messages do you wish to enter? ");
            int numMessages = input.nextInt();
            input.nextLine(); // Consume the leftover newline character

            boolean running = true;

            // Main application loop
            while (running) {
                System.out.println("\nMenu:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");
                int choice = input.nextInt();
                input.nextLine();

                     switch (choice) {
                    case 1:
                        // Check if the user has already sent the max amount
                        if (Message.returnTotalMessages() >= numMessages) {
                            System.out.println("You have already entered your set number of messages.");
                            break;
                        }
                        
                        // Loop to process the specified number of messages
                        for (int i = Message.returnTotalMessages(); i < numMessages; i++) {
                            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
                            
                            System.out.print("Enter recipient cell number: ");
                            String recipient = input.nextLine();
                            System.out.print("Enter message: ");
                            String text = input.nextLine();

                            // Passes the loop counter 'i' into the Message constructor
                            Message msg = new Message(recipient, text, i);

                            // Validates the message
                            String recCheck = msg.checkRecipientCell();
                            String lenCheck = msg.checkMessageLength();
                            
                            System.out.println(recCheck);
                            System.out.println(lenCheck);

                            // Proceed only if validation passed
                            if (recCheck.contains("successfully") && lenCheck.contains("ready")) {
                                System.out.println("Message ID generated: " + msg.getMessageID());
                                System.out.println("Message Hash: " + msg.getMessageHash());

                                System.out.println("\nChoose an option:");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message to send later");
                                System.out.print("> ");
                                int sendChoice = input.nextInt();
                                input.nextLine(); // Consume newline

                                System.out.println("\n" + msg.SentMessage(sendChoice));

                                // Print full details if message is sent
                                if (sendChoice == 1) {
                                    System.out.println("\n--- Message Details ---");
                                    msg.printMessageDetails();
                                }
                            } else {
                                System.out.println("Message could not be processed due to validation errors.");
                                break; // Exits the for loop if validation fails
                            }
                            
                            // Break out of the for loop if the limit is reached
                            if (Message.returnTotalMessages() >= numMessages) {
                                System.out.println("\nYou have reached the maximum number of messages.");
                                break; 
                            }
                        }
                        break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:
                        // Display final total before exiting
                        System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
                        System.out.println("Exiting QuickChat.");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
            }
        }
     
    }
}
     
  



