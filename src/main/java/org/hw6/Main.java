package org.hw6;

import org.hw6.entity.Article;
import org.hw6.entity.User;
import org.hw6.service.ArticleService;
import org.hw6.service.UserArticleService;
import org.hw6.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    //check function input
    public static int check(int first, int last) {
        System.out.print("Enter Your Function : ");
        int button;
        while (true) {
            if (input.hasNextInt()) {
                int temp = input.nextInt();
                if (temp >= first && temp <= last) {
                    button = temp;
                    return button;
                } else {
                    System.out.print("Enter Number Between" + first + "and" + last + " : ");
                }
            } else {
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
        int button;
        //star menu function
        while (true) {
            System.out.println();
            System.out.println("Menu : ");
            System.out.println("Sign Up : 1");
            System.out.println("Enter : 2");
            System.out.println("View all published articles : 3");
            System.out.println("View the selected article of all published articles : 4");
            System.out.println("Exit : 5");
            button = check(1, 5);
            if (button == 1) {
                System.out.print("Enter username : ");
                user.setUserName(input.next());
                System.out.print("Enter national code : ");
                String nationalCode;
                // check national code input
                while (true) {
                    if (input.hasNextInt()) {
                        String temp = input.next();
                        if (temp.length() == 10) {
                            nationalCode = temp;
                            break;
                        } else {
                            System.out.print("Enter 10 Number! : ");
                        }
                    } else {
                        System.out.print("Enter Number! : ");
                        input.next();
                    }
                }
                user.setNationalCode(nationalCode);
                String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
                while (true){
                    System.out.print("Enter birthday : ");
                    String date = input.next();
                    if (Pattern.matches(regex,date)){
                        user.setBirthday(date);
                        break;
                    }else {
                        System.out.println("Enter date in this format : yyyy-mm-dd ");
                    }
                }
                if (userService.save(user)){
                    break;
                }
            }

            if (button == 2) {
                System.out.print("Enter username : ");
                String userName = input.next();
                System.out.print("Enter password : ");
                String passWord = input.next();
                if (userService.enter(userName, passWord) != null) {
                    System.out.println("Welcome!");
                    user = userService.enter(userName, passWord);
                    break;
                } else {
                    System.out.println("Please Sign Up First!");
                }
            }
            if (button == 3) {
                articleService.loadAll();
                System.out.println();
            }
            if (button == 4) {
                System.out.print("Enter selected article's title : ");
                String title = input.next();
                articleService.loadByTitle(title);
            }
            if (button == 5) {
                break;
            }
        }
        if (button != 5) {
            do {
                System.out.println();
                System.out.println("Menu :");
                System.out.println("View your articles : 1");
                System.out.println("Edit your article : 2");
                System.out.println("Publish or unpublished your article : 3");
                System.out.println("Enter new article : 4");
                System.out.println("Change your password : 5");
                System.out.println("Exit : 6");
                button = check(1, 6);
                if (button == 1) {
                    userArticleService.loadMyArticles(user.getId());
                }
                if (button == 2) {
                    int id;
                    while (true) {
                        System.out.print("Enter article's id : ");
                        if (input.hasNextInt()) {
                            id = input.nextInt();
                            break;
                        } else {
                            System.out.println("Enter Number!");
                            input.next();
                        }
                    }
                    if (userArticleService.checkEdit(id,user.getId())) {
                        Article article = new Article();
                        System.out.print("Enter new title : ");
                        input.nextLine();
                        article.setTitle(input.nextLine());
                        System.out.print("Enter new brief : ");
                        article.setBrief(input.nextLine());
                        System.out.print("Enter new content : ");
                        article.setContent(input.nextLine());
                        while (true) {
                            System.out.print("Would you like your article to be published? : ");
                            String publish = input.next();
                            if (publish.equalsIgnoreCase("yes")) {
                                article.setPublished("published");
                                break;
                            } else if (publish.equalsIgnoreCase("no")) {
                                article.setPublished("unpublished");
                                break;
                            } else {
                                System.out.println("Enter 'yes' or 'no' !");
                            }
                        }
                        userArticleService.editMyArticle(article, user.getId(), id);
                        System.out.println("Your article edited.");
                    }
                }
                if (button == 3) {
                    System.out.print("Enter your article's id : ");
                    int id = input.nextInt();
                    if (userArticleService.checkEdit(id, user.getId())) {
                        String publish;
                        while (true) {
                            System.out.print("Would you like your article to be published? : ");
                            String publish2 = input.next();
                            if (publish2.equalsIgnoreCase("yes")) {
                                publish = "published";
                                System.out.println("Your article has been published.");
                                break;
                            } else if (publish2.equalsIgnoreCase("no")) {
                                publish = "unpublished";
                                System.out.println("Your article has been unpublished.");
                                break;
                            } else {
                                System.out.println("Enter 'yes' or 'no' !");
                            }
                        }
                        userArticleService.publish(publish, id, user.getId());
                    }
                }
                if (button == 4) {
                    Article article = new Article();
                    System.out.print("Enter title : ");
                    input.nextLine();
                    article.setTitle(input.nextLine());
                    System.out.print("Enter brief : ");
                    article.setBrief(input.nextLine());
                    System.out.print("Enter content : ");
                    article.setContent(input.nextLine());
                    String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
                    while (true){
                        System.out.print("Enter create date : ");
                        String date = input.next();
                        if (Pattern.matches(regex,date)){
                            article.setCreateDate(date);
                            break;
                        }else {
                            System.out.println("Enter date in this format : yyyy-mm-dd ");
                        }
                    }
                    articleService.save(article, user.getId());
                    System.out.println("Your article saved.");
                }
                if (button == 5) {
                    System.out.print("Enter your new password : ");
                    String newPassword = input.next();
                    userService.changePass(user.getUserName(), user.getPassword(), newPassword);
                    System.out.println("Your password changed.");
                }

            } while (button != 6);
        }
    }
}