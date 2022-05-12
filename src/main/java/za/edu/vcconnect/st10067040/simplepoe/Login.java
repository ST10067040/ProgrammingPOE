package za.edu.vcconnect.st10067040.simplepoe;

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

        // print the users' login status
        System.out.println(Main.registeredUser.returnLoginStatus());

        // welcome the user if they have logged in, else prompt the user to login again
        if(Main.registeredUser.isLoggedIn()){
            System.out.println("Welcome " + Main.registeredUser.getFirstname()+", " + Main.registeredUser.getLastname() + " it is great to see you.");
        } else {
            Main.loginUser();
        }
    }
}
