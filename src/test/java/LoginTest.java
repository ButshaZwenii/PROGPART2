/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author butsha
 */


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        // Initialize a default valid user for tests that need a baseline
        login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
    }

    // ==========================================
    // assertEquals Tests (String responses)
    // ==========================================

    @Test
    public void testUsernameCorrectlyFormatted() {
        String expected = "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
        String actual = login.registerUser();
        assertEquals(expected, actual);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login invalidLogin = new Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = invalidLogin.registerUser();
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordMeetsComplexity() {
        String expected = "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
        String actual = login.registerUser();
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login invalidLogin = new Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = invalidLogin.registerUser();
        assertEquals(expected, actual);
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        String expected = "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
        String actual = login.registerUser();
        assertEquals(expected, actual);
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login invalidLogin = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        String actual = invalidLogin.registerUser();
        assertEquals(expected, actual);
    }

    // ==========================================
    // assertTrue / assertFalse Tests (Boolean responses)
    // ==========================================

    @Test
    public void testLoginSuccessful() {
        login.setEnteredUsername("kyl_1");
        login.setEnteredPassword("Ch&&sec@ke99!");
        assertTrue(login.loginUser());
    }

   @Test
public void testLoginFailed() {
    login.setEnteredUsername("wrong_user");
    login.setEnteredPassword("wrong_pass");
    assertFalse(login.loginUser());
}
}
