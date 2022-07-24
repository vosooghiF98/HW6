package org.hw6;

import org.hw6.entity.User;
import org.hw6.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();
        User user = new User();
        System.out.println("Menu : ");
        System.out.println("Sign Up : 1");
        System.out.println("Enter : 2");
        System.out.print("Enter Your Function : ");
        //check function input
        int button;
        while (true){
            if (input.hasNextInt()){
                int temp = input.nextInt();
                if (temp >= 1 && temp <= 2){
                    button = temp;
                    break;
                }else {
                    System.out.print("Enter Number Between 1 and 2 : ");
                }
            }else {
                System.out.print("Enter Number! : ");
                input.next();
            }
        }
        //star menu function
        while (true){
            if (button == 1){
                System.out.print("Enter username : ");
                user.setUserName(input.next());
                System.out.print("Enter national code : ");
                String nationalCode;
                // check national code input
                while (true){
                    if (input.hasNextInt()){
                        String temp = input.next();
                        if (temp.length() == 10){
                            nationalCode = temp;
                            break;
                        }else {
                            System.out.print("Enter 10 Number! : ");
                        }
                    }else {
                        System.out.print("Enter Number! : ");
                        input.next();
                    }
                }
                user.setNationalCode(nationalCode);
                System.out.print("Enter birthday : ");
                user.setBirthday(input.next());
                System.out.println("Your password is your national code.");
                userService.save(user);
                System.out.println("Sign Up Completed");
                userService.enter(user.getUserName(),user.getPassword());
                break;
            }

            if (button == 2){
                System.out.print("Enter username : ");
                String userName = input.next();
                System.out.print("Enter password : ");
                String passWord = input.next();
                if(userService.enter(userName,passWord)){
                    System.out.println("Welcome!");
                    break;
                }else {
                    System.out.println("Please Sign Up First!");
                    button = 1;
                }
            }
        }
        System.out.println("Menu :");



    }
}