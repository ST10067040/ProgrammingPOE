package za.edu.vcconnect.st10067040.poe;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.io.*;

/*
 * @author Chelden Booysen (ST10067040)
 * Programming POE 2022
 * 3 May 2022
 */
public class RegisterUser {

    // init new JsonParser
    private static final JsonParser jsonParser = new JsonParser();

    // init variables for User
    private String username, password, firstname, lastname;


    // constructor for registering the user
    public RegisterUser(String username, String password, String firstname, String lastname) {
        // set variables from constructor
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;

        // declare file for saving user data (very primitive json file right now)
        File userDatabase = new File("UserDatabase.json");

        // check if the json file exists, if not create the file
        if (!userDatabase.exists()) {
            try {
                userDatabase.createNewFile();

                // make a new json object with the username, password, firstname and lastname of the user and save it
                // to the json under `user1` as there is no other users in the database yet since
                // we just made the file
                JsonObject json = new JsonObject();
                JsonObject jsonData = new JsonObject();
                jsonData.addProperty("username", this.username);
                jsonData.addProperty("password", this.password);
                jsonData.addProperty("firstname", this.firstname);
                jsonData.addProperty("lastname", this.lastname);
                json.add("user1", jsonData);
                PrintWriter saveJson = new PrintWriter(new FileWriter(userDatabase));
                saveJson.println(new GsonBuilder().setPrettyPrinting().create().toJson(json));
                saveJson.close();
            } catch (IOException e) {
                // if something goes wrong when creating the file, exit the program and notify the user.
                System.err.println("Could not create user database json file!\nProcess will exit with error code 1.");
                System.exit(1);
            }
        } else {
            try {
                // if the file does exist, get all existing users as a json object,
                // check whether there is a user with the same username and if not,
                // append the new user to the end of it and save the json file
                BufferedReader loadJson;
                loadJson = new BufferedReader(new FileReader(userDatabase));
                JsonObject jsonObject = (JsonObject) jsonParser.parse(loadJson);
                loadJson.close();

                // iterate through each user to check if the username is taken and if so, notify the user and restart the program
                for(int i = 0; i < jsonObject.size(); i++){
                    if(jsonObject.get("user"+(i+1)).getAsJsonObject().get("username").getAsString().equalsIgnoreCase(this.username)){
                        JOptionPane.showMessageDialog(null, "Sorry this username is already taken.\nEither register with another username or login.", "Login System", JOptionPane.ERROR_MESSAGE);
                        Main.main(null);
                        return;

                    }
                }

                JsonObject jsonData = new JsonObject();
                jsonData.addProperty("username", this.username);
                jsonData.addProperty("password", this.password);
                jsonData.addProperty("firstname", this.firstname);
                jsonData.addProperty("lastname", this.lastname);

                jsonObject.add("user" + (jsonObject.size() + 1), jsonData);
                PrintWriter saveJson = new PrintWriter(new FileWriter(userDatabase));
                saveJson.println(new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
                saveJson.close();

            } catch (IOException e) {
                // if something goes wrong when reading/writing to the database file, exit the program and notify the user.
                System.err.println("Could not read/write to the user database json file!\nProcess will exit with error code 1.");
                System.exit(1);
            }
        }
    }
}
