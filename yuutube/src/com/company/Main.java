package com.company;
import java.util.Scanner;
public class Main {   //Main class that has the main method
    public static void main(String[] args) {
        System.out.println("\n                Welcome to YuuTube\n");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter [1/2/3]\n1- Log-in\n2- Sign-in\n3- Exit");
        int LogORSign = in.nextInt();
        logInORSignIn logInORSignIn = new logInORSignIn();
        if(LogORSign==1){
            if(logInORSignIn.logIn()==true){   //if the return of logIn method from logInORSignIn class is true then the the user can access his account
                MainScreen.mainScreen();  //to go to Main Screen class, which has the choices of the program features
            }
        }
        if(LogORSign==2){
            logInORSignIn.signIn();
            MainScreen.mainScreen(); //to go to Main Screen class, which has the choices of the program features
        }
    }
}