package org.hw6;

import org.hw6.entity.Article;
import org.hw6.entity.User;
import org.hw6.service.ArticleService;
import org.hw6.service.UserArticleService;
import org.hw6.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    //check function input
    public static int check(int first,int last){
        System.out.print("Enter Your Function : ");
        int button;
        while (true){
            if (input.hasNextInt()){
                int temp = input.nextInt();
                if (temp >= first && temp <= last){
                    button = temp;
                    return button;
                }else {
                    System.out.print("Enter Number Between" +first+ "and" + last + " : ");
                }
            }else {
                System.out.print("Enter Number! : ");
                input.next();
            }
        }
    }
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();
        ArticleService articleService = new ArticleService();
        UserArticleService userArticleService = new UserArticleService();

        User user = new User();
        System.out.println("Menu : ");
        System.out.println("Sign Up : 1");
        System.out.println("Enter : 2");
        int button = check(1,2);
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
                if(userService.enter(userName,passWord) != null){
                    System.out.println("Welcome!");
                    user = userService.enter(userName,passWord);
                    break;
                }else {
                    System.out.println("Please Sign Up First!");
                    button = 1;
                }
            }
        }

        do {
            System.out.println();
            System.out.println("Menu :");
            System.out.println("View all published articles : 1");
            System.out.println("View the selected article of all published articles : 2");
            System.out.println("View your articles : 3");
            System.out.println("Edit your article : 4");
            System.out.println("Publish or unpublished your article : 5");
            System.out.println("Enter new article : 6");
            System.out.println("Change your password : 7");
            System.out.println("Exit : 8");
            button = check(1, 8);
            if (button == 1) {
                articleService.loadAll();
                System.out.println();
            }
            if (button == 2) {
                System.out.print("Enter selected article's title : ");
                String title = input.next();
                articleService.loadByTitle(title);
            }
            if (button == 3) {
                userArticleService.loadMyArticles(user.getId());
            }
            if (button == 4) {
                int id;
                while (true){
                    System.out.print("Enter article's id : ");
                    if (input.hasNextInt()){
                        id = input.nextInt();
                        break;
                    }else {
                        System.out.println("Enter Number!");
                        input.next();
                    }
                }
                Article article = new Article();
                System.out.print("Enter new title : ");
                input.nextLine();
                article.setTitle(input.nextLine());
                System.out.print("Enter new brief : ");
                article.setBrief(input.nextLine());
                System.out.print("Enter new content : ");
                article.setContent(input.nextLine());
                while (true) {
                    System.out.print("Publish or unpublished your article : ");
                    String publish = input.next();
                    if (publish.equals("published") || publish.equals("unpublished")) {
                        article.setPublished(publish);
                        break;
                    } else {
                        System.out.println("Enter published or unpublished!");
                    }
                }
                userArticleService.editMyArticle(article, user.getId(),id);
                System.out.println("Your article edited.");
            }
            if (button == 5) {
                System.out.print("Enter your article's title : ");
                String title = input.next();
                String publish;
                while (true) {
                    System.out.print("Publish or unpublished your article : ");
                    String publish2 = input.next();
                    if (publish2.equals("published") || publish2.equals("unpublished")) {
                        publish = publish2;
                        break;
                    } else {
                        System.out.println("Enter published or unpublished!");
                    }
                }
                userArticleService.publish(publish, title, user.getId());
            }
            if (button == 6) {
                Article article = new Article();
                System.out.print("Enter title : ");
                input.nextLine();
                article.setTitle(input.nextLine());
                System.out.print("Enter brief : ");
                article.setBrief(input.nextLine());
                System.out.print("Enter content : ");
                article.setContent(input.nextLine());
                System.out.print("Enter create date : ");
                article.setCreateDate(input.next());
                articleService.save(article, user.getId());
                System.out.println("Your article saved.");
            }
            if (button == 7) {
                System.out.print("Enter your new password : ");
                String newPassword = input.next();
                userService.changePass(user.getUserName(), user.getPassword(), newPassword);
                System.out.println("Your password changed.");
            }

        } while (button != 8);
    }
}