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
public class Login{
    // constructor for Login instance
    public Login(String username, String password) {
        // login if the username and password match the registered user
        if(username.equals(Main.registeredUser.getUsername()) &&
                password.equals(Main.registeredUser.getPassword())){
            Main.registeredUser.setLoggedIn(true);
        }

    }
}
