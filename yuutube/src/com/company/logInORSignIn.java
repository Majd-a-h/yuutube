package com.company;

import java.io.*;
import java.util.Scanner;

public class logInORSignIn{

    public void signIn (){ //to sign in if new user
        try{
            Scanner input = new Scanner(System.in);
            System.out.println("Enter The Email to Sign-In: ");
            String email= input.nextLine();
            System.out.println("Enter The Password to Sign-In: ");
            String password= input.nextLine();
            File createInCase = new File("UserInfo.txt");
            if (!createInCase.exists()) {
                PrintWriter createUserInfo = new PrintWriter(new FileOutputStream("UserInfo.txt",true));
                createUserInfo.close();
            }
            if(checkEmail(email)==false){ //if the email does not exits
                //following lines in this if sleection is to take all lines to temp, then add the new account info, then trensfare back to Userinfo file
                System.out.println("Enter The Channel Name: ");
                String channel= input.nextLine();
                PrintWriter outTempUserInfo = new PrintWriter(new FileOutputStream("TempUserInfo.txt"));
                Scanner scanUserInfo = new Scanner(new FileInputStream("UserInfo.txt"));
                boolean isThereAnyAccounts = false;
                if(scanUserInfo.hasNextLine()) {
                    outTempUserInfo.print(scanUserInfo.nextLine());
                    isThereAnyAccounts=true;
                }
                while (scanUserInfo.hasNextLine()) {
                    outTempUserInfo.print("\n"+scanUserInfo.nextLine());
                }
                if(isThereAnyAccounts==true){
                    outTempUserInfo.print("\n"+email+" "+password+" "+channel);
                }
                if(isThereAnyAccounts==false){
                    outTempUserInfo.print(email+" "+password+" "+channel);
                }
                scanUserInfo.close();
                outTempUserInfo.close();
                File deleteUserInfoFileForNow = new File("UserInfo.txt");
                if (deleteUserInfoFileForNow.exists()) {
                    deleteUserInfoFileForNow.delete();
                }
                Scanner scanTempUserInfo = new Scanner(new FileInputStream("TempUserInfo.txt"));
                PrintWriter outUserInfo = new PrintWriter(new FileOutputStream("UserInfo.txt"));
                if(scanTempUserInfo.hasNextLine()){
                    outUserInfo.print(scanTempUserInfo.nextLine());
                }
                while (scanTempUserInfo.hasNextLine()){
                    outUserInfo.print("\n"+scanTempUserInfo.nextLine());
                }
                scanTempUserInfo.close();
                outUserInfo.close();
                File deleteTempUserInfoFile = new File("TempUserInfo.txt");
                if (deleteTempUserInfoFile.exists()) {
                    deleteTempUserInfoFile.delete();
                }
            }
            else{ //if the email is old then go to log in method
                System.out.println("\nThe Email Already Exists, please log in:\n");
                logIn();
            }
        }
        catch (IOException e){
        }
    }

    public boolean logIn (){ //if the user is not new
        boolean enter = false;
        try{
            File createInCase = new File("UserInfo.txt");
            if (!createInCase.exists()) {
                PrintWriter createUserInfo = new PrintWriter(new FileOutputStream("UserInfo.txt",true));
                createUserInfo.close();
            }
            Scanner input = new Scanner(System.in);
            boolean checkExits = false;
            String email="", password="";
            System.out.println("Enter The Email to Log-In: ");
            email= input.nextLine();
            if(checkEmail(email)==true){ //if the email is already exits then continue
                while(checkExits==false) {
                    Scanner in = new Scanner(new FileInputStream("UserInfo.txt"));
                    System.out.println("Enter The Password to Log-In: ");
                    password = input.nextLine();
                    while (in.hasNextLine()) {
                        String findEmail = in.next();
                        String findPassword = in.next();
                        String findChannel = in.next();
                        if (email.equalsIgnoreCase(findEmail) && password.equalsIgnoreCase(findPassword)) { //make sure the email and password is true
                            enter = true;
                            checkExits = true;
                            break;
                        }
                        if (email.equalsIgnoreCase(findEmail) && !password.equalsIgnoreCase(findPassword)) {
                            System.out.println("The password is wrong!");
                        }
                    }
                    in.close();
                }
            }
            if(checkEmail(email)==false&&checkExits==false){ //if the email is new then go to signin
                System.out.println("\nThe Email does not Exists, please sign in:\n");
                enter =true;
                signIn();
            }
        }
        catch (IOException e) {
        }
        return enter;
    }

    public boolean checkEmail(String email){ //to check if the email exits or no
        boolean EmailExists = false;
        try {
            Scanner in = new Scanner(new FileInputStream("UserInfo.txt"));
            while (in.hasNextLine()) {
                String findEmail = in.next();
                in.nextLine();
                if (email.equalsIgnoreCase(findEmail)) {
                    EmailExists=true;
                    break;
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
        }
        return EmailExists;
    }

    public void showInfo(){ //to show all information of the user
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Email: ");
            String email =input.nextLine();
            Scanner in = new Scanner(new FileInputStream("UserInfo.txt"));
            if(checkEmail(email)==true) {
                while (in.hasNextLine()) {
                    String findEmail = in.next();
                    String findPassword = in.next();
                    String findChannel = in.next();
                    if (email.equalsIgnoreCase(findEmail)) {
                        System.out.println("Email: " + findEmail);
                        System.out.println("Password: " + findPassword);
                        System.out.println("Channel Name: " + findChannel);
                        break;
                    }
                }
                in.close();
            }
            if(checkEmail(email)==false){
                System.out.println("\nThe Email is wrong, please type your email:\n");
                showInfo();
            }
        } catch (FileNotFoundException e) {
        }
    }

    public void changePassword(){ //to change password
        try{
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Email: ");
            String email =input.nextLine();
            Scanner in = new Scanner(new FileInputStream("UserInfo.txt"));
            PrintWriter outTempUserInfo = new PrintWriter(new FileOutputStream("TempUserInfo.txt"));
            boolean check = false;
            if(checkEmail(email)==true) {
                System.out.println("Enter new Password: ");
                String password = input.nextLine();
                while (in.hasNextLine()) {
                    String findEmail = in.next();
                    String findPassword = in.next();
                    String findChannel = in.next();
                    if (!email.equalsIgnoreCase(findEmail)) {
                        outTempUserInfo.print(findEmail + " " + findPassword + " " + findChannel + "\n");
                    }
                    if (email.equalsIgnoreCase(findEmail)) {
                        outTempUserInfo.print(findEmail + " " + password + " " + findChannel + "\n");
                        check = true;
                    }
                }
                in.close();
                outTempUserInfo.close();
                File deleteUserInfoFileForNow = new File("UserInfo.txt");
                if (deleteUserInfoFileForNow.exists()) {
                    deleteUserInfoFileForNow.delete();
                }
                Scanner scanTempUserInfo = new Scanner(new FileInputStream("TempUserInfo.txt"));
                PrintWriter outUserInfo = new PrintWriter(new FileOutputStream("UserInfo.txt"));
                if (scanTempUserInfo.hasNextLine()) {
                    outUserInfo.print(scanTempUserInfo.nextLine());
                }
                while (scanTempUserInfo.hasNextLine()) {
                    outUserInfo.print("\n" + scanTempUserInfo.nextLine());
                }
                scanTempUserInfo.close();
                outUserInfo.close();
                File deleteTempUserInfoFile = new File("TempUserInfo.txt");
                if (deleteTempUserInfoFile.exists()) {
                    deleteTempUserInfoFile.delete();
                }
            }
            if(checkEmail(email)==false){
                System.out.println("\nThe Email is wrong, please type your email:\n");
                changePassword();
            }
        }
        catch (IOException e){
        }
    }
    public void changeChannel(){ //to change account
        try{
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Email: ");
            String email =input.nextLine();
            Scanner in = new Scanner(new FileInputStream("UserInfo.txt"));
            PrintWriter outTempUserInfo = new PrintWriter(new FileOutputStream("TempUserInfo.txt"));
            boolean check = false;
            if(checkEmail(email)==true) {
                System.out.println("Enter new Channel Name: ");
                String channel = input.nextLine();
                while (in.hasNextLine()) {
                    String findEmail = in.next();
                    String findPassword = in.next();
                    String findChannel = in.next();
                    if (!email.equalsIgnoreCase(findEmail)) {
                        outTempUserInfo.print(findEmail + " " + findPassword + " " + findChannel + "\n");
                    }
                    if (email.equalsIgnoreCase(findEmail)) {
                        outTempUserInfo.print(findEmail + " " + findPassword + " " + channel + "\n");
                        check = true;
                    }
                }
                in.close();
                outTempUserInfo.close();
                File deleteUserInfoFileForNow = new File("UserInfo.txt");
                if (deleteUserInfoFileForNow.exists()) {
                    deleteUserInfoFileForNow.delete();
                }
                Scanner scanTempUserInfo = new Scanner(new FileInputStream("TempUserInfo.txt"));
                PrintWriter outUserInfo = new PrintWriter(new FileOutputStream("UserInfo.txt"));
                if (scanTempUserInfo.hasNextLine()) {
                    outUserInfo.print(scanTempUserInfo.nextLine());
                }
                while (scanTempUserInfo.hasNextLine()) {
                    outUserInfo.print("\n" + scanTempUserInfo.nextLine());
                }
                scanTempUserInfo.close();
                outUserInfo.close();
                File deleteTempUserInfoFile = new File("TempUserInfo.txt");
                if (deleteTempUserInfoFile.exists()) {
                    deleteTempUserInfoFile.delete();
                }
            }
            if(checkEmail(email)==false){
                System.out.println("\nThe Email is wrong, please type your email:\n");
                changePassword();
            }
        }
        catch (IOException e){
        }
    }

    public void closeAccount(){ //to close account
        try{
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Email: ");
            String email =input.nextLine();
            Scanner in = new Scanner(new FileInputStream("UserInfo.txt"));
            PrintWriter outTempUserInfo = new PrintWriter(new FileOutputStream("TempUserInfo.txt"));
            if(checkEmail(email)==true) {
                while (in.hasNextLine()) {
                    String findEmail = in.next();
                    String findPassword = in.next();
                    String findChannel = in.next();
                    if (!email.equalsIgnoreCase(findEmail)) {
                        outTempUserInfo.print(findEmail + " " + findPassword + " " + findChannel + "\n");
                    }
                }
                in.close();
                outTempUserInfo.close();
                File deleteUserInfoFileForNow = new File("UserInfo.txt");
                if (deleteUserInfoFileForNow.exists()) {
                    deleteUserInfoFileForNow.delete();
                }
                Scanner scanTempUserInfo = new Scanner(new FileInputStream("TempUserInfo.txt"));
                PrintWriter outUserInfo = new PrintWriter(new FileOutputStream("UserInfo.txt"));
                if (scanTempUserInfo.hasNextLine()) {
                    outUserInfo.print(scanTempUserInfo.nextLine());
                }
                while (scanTempUserInfo.hasNextLine()) {
                    outUserInfo.print("\n" + scanTempUserInfo.nextLine());
                }
                scanTempUserInfo.close();
                outUserInfo.close();
                File deleteTempUserInfoFile = new File("TempUserInfo.txt");
                if (deleteTempUserInfoFile.exists()) {
                    deleteTempUserInfoFile.delete();
                }
            }
        }
        catch (IOException e){
        }
    }

}