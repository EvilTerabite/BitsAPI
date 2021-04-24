package me.evilterabite.bitsapi;

import me.evilterabite.bitsapi.libraries.User;

import java.io.IOException;
import java.util.Scanner;

public class BitsAPI {
    public static void main(String[] args) throws IOException {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String login = scanner.next();
            if (login.equalsIgnoreCase("register")) {
                User.register(scanner);
            }
            if (login.equalsIgnoreCase("login")) {
                break;
            }
        }

    }
}
