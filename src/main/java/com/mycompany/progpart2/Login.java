/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpart2;

/**
 *
 * @author butsha
 */
public class Login {
    // The user's first and last names are needed to display the greeting.
    private String firstName;
    private String lastName;

    // These details are provided when the user signs up.
       private String username;
       private String password;
       private String cellPhoneNumber;

    // These values are entered later when the user tries to log in.
     private String enteredUsername;
     private String enteredPassword;

     // Indicates if the registration process succeeded.
      private boolean registered;

     // Indicates if the latest login attempt was successful.
        private boolean loginSuccessful;
        
        
  /*
 * ===========================================
 * CONSTRUCTOR
 * ===========================================
 *
 * The constructor takes in the registration details
 * when a new Login object is initialized.
 */
public Login(String firstName,
             String lastName,
             String username,
             String password,
             String cellPhoneNumber) {

    // "this" points to the instance variables of this object.
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.cellPhoneNumber = cellPhoneNumber;
    // A new user has not been registered or logged in yet.
    this.registered = false;
    this.loginSuccessful = false;
   
}
/*
-------------------------
CHECEK USERname

--------------------------------------
*/

public boolean checkUserName() {
    // Validate username: must exist, include "_", and be <= 5 chars.
return username != null
       && username.contains("_")
       && username.length() <= 5;

}
        /*
_------------------------------------------------
Checking passwordComplexity
-----------------------------------------------------
*/
    /*
 * Password requirements:
 * - At least 8 characters
 * - Includes a capital letter
 * - Includes a number
 * - Includes a special character
 */
public boolean checkPasswordComplexity() {

    // Reject if password is null.
    if (password == null) {
        return false;
    }

    // Check minimum length.
    boolean hasMinimumLength = password.length() >= 8;
    
    //
     boolean hasCapitalLetter = false;
     boolean hasNumber = false;
     boolean hasSpecialCharacter = false;

for (int i = 0; i < password.length(); i++) {
    char currentCharacter = password.charAt(i);

    if (Character.isUpperCase(currentCharacter)) {
        hasCapitalLetter = true;
    }
    // Check if the character is a digit.
if (Character.isDigit(currentCharacter)) {
    hasNumber = true;
    }
// Check if the character is a special symbol (not a letter or digit).
if (!Character.isLetterOrDigit(currentCharacter)) {
    hasSpecialCharacter = true;
}
}
// Ensure all password criteria are met.
return hasMinimumLength
       && hasCapitalLetter
       && hasNumber
       && hasSpecialCharacter;
}

/*
 ================================================
 checkCellPhoneNumber()
 ----------------------------------------------
 Regex adapted from:
  Author/Website:Regular Expressions 101 (regex101.com)
  Page/Article: South African Mobile Number Validation
 URL:
  Accessed:  18 September 2026
 
 
 */
public boolean checkCellPhoneNumber() {
    return cellPhoneNumber != null
       && cellPhoneNumber.matches("^(\\+27|0)[6-8]\\d{8}$");

}
    public String registerUser() {

    // Start each registration attempt as unsuccessful.
    registered = false;

    // First check the username.
    if (!checkUserName()) {
        return "Username is not correctly formatted; please ensure that "
            + "your username contains an underscore and is no more "
            + "than five characters in length.";
    }

    // If username is valid, check the password.
    if (!checkPasswordComplexity()) {
        return "Password is not correctly formatted; please ensure that "
            + "the password contains at least eight characters, "
            + "a capital letter, a number, and a special character.";
    }
    // If username and password are valid, check the cellphone number.
if (!checkCellPhoneNumber()) {

    return "Cell number is incorrectly formatted or does not contain "
        + "an international code; please correct the number and try again.";
}
// Mark registration as successful since all validations passed.
registered = true;

// Return the official success messages defined by the project brief.
return "Username successfully captured.\n"
    + "Password successfully captured.\n"
    + "Cell number successfully captured.";
}

public boolean loginUser() {

   
    if (!registered) {
        loginSuccessful = false;
        return false;
    }

    /*
      These checks prevent NullPointerException.
     */
    if (enteredUsername == null || enteredPassword == null) {
        loginSuccessful = false;
        return false;
    }
    loginSuccessful
        = username.equals(enteredUsername)
        && password.equals(enteredPassword);

return loginSuccessful;
}

public String returnLoginStatus() {

    // Successful login.
    if (loginSuccessful) {
        return "Welcome " + firstName + lastName + ","
            + " it is great to see you again.";
    }

    // Failed login.
    return "Username or password incorrect, please try again.";
}
public void setEnteredUsername(String enteredUsername) {
    this.enteredUsername = enteredUsername;
}

public void setEnteredPassword(String enteredPassword) {
    this.enteredPassword = enteredPassword;
}
public boolean isRegistered() {
    return registered;
}
}

