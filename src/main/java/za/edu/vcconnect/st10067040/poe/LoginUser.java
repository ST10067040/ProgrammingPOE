package za.edu.vcconnect.st10067040.poe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * @author Chelden Booysen (ST10067040)
 * Programming POE 2022
 * 3 May 2022
 */
public class LoginUser {

    // init variables for user
    private final String username, password;

    // declare file for saving user data (very primitive json file right now)
    private static final File userDatabase = new File("UserDatabase.json");
    private static final JsonParser jsonParser = new JsonParser();

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;

        try {

            BufferedReader loadJson;
            loadJson = new BufferedReader(new FileReader(userDatabase));
            JsonObject jsonObject = (JsonObject) jsonParser.parse(loadJson);
            loadJson.close();

            // iterate through each user to check if they have an account and if so, log them in
            for (int i = 0; i < jsonObject.size(); i++) {
                if (jsonObject.get("user" + (i + 1)).getAsJsonObject().get("username").getAsString().equalsIgnoreCase(this.username) &&
                        jsonObject.get("user" + (i + 1)).getAsJsonObject().get("password").getAsString().equalsIgnoreCase(this.password)) {
                    JOptionPane.showMessageDialog(null, "Welcome, " +  jsonObject.get("user" + (i + 1)).getAsJsonObject().get("firstname").getAsString() + " " +  jsonObject.get("user" + (i + 1)).getAsJsonObject().get("lastname").getAsString() +". Its great to see you again.", "Login System", JOptionPane.DEFAULT_OPTION);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again", "Login System", JOptionPane.ERROR_MESSAGE);
            Main.main(null);
         } catch (IOException e) {

        }


    }

}
