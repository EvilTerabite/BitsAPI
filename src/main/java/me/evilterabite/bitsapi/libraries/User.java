package me.evilterabite.bitsapi.libraries;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {

    private String username;
    private String password;

    private static FileWriter file;
    private static FileReader reader;
    private static JSONArray users;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public static void save(User user) {
        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        try {
            FileReader reader = new FileReader("src/main/resources/users.json");
            obj = jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        users = (JSONArray) obj;
        if(users != null) {
            JSONParser parser = new JSONParser();
            JSONObject userJSON = new JSONObject();
            userJSON.put(user.username, user.password);
            String pass = user.password;
            users.add(userJSON);
            try {
                file = new FileWriter("src/main/resources/users.json");
                file.write(users.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    file.flush();
                    file.close();
                    System.out.println(user.username + "saved!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            users = new JSONArray();
        }
    }

    public static User register(Scanner scanner) {
        System.out.println("Username");
        String username = scanner.next();
        System.out.println("Password");
        String rawPassword = scanner.next();
        String password = PasswordEncoder.encodeSCrypt(rawPassword);
        User user = new User(username, password);
        User.save(user);
        return user;
    }
}
