package za.edu.vcconnect.st10067040.poe;

/*
 * @author Chelden Booysen (ST10067040)
 * Programming POE 2022
 * 10 May 2022
 * This is the simple one because you
 * said the other one was too complex
 * Also I don't need to reference since
 * I didn't use any code from the internet
 */
public class RegisterUser {

    // declare variables for user
    private String firstname, lastname, username, password;
    private boolean isLoggedIn = false;

    // constructor for registering the user
    public RegisterUser(String firstname, String lastname, String username, String password) {
        // set variables for user
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;

        // set the registered user to this instance
        Main.registeredUser = this;
        System.out.println("You have successfully registered!");
    }

    // setters and getters for User

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }


    // this returns a string based on the users' login status
    public String returnLoginStatus() {
        if (this.isLoggedIn) {
            return "You have successfully logged in!";
        } else {
            return "You have failed to log in!";
        }
    }
}
