package com.company;

import java.util.Scanner;

public class MainScreen { //Here is the main class for the choices that the user will see after signing in or logging in.

    public static void mainScreen(){
        Video video = new Video();
        PlayVideo playVideo = new PlayVideo();
        Trending trending = new Trending();
        Search search = new Search();
        WatchList watchList = new WatchList();
        Scanner in = new Scanner(System.in);
        logInORSignIn logInORSignIn = new logInORSignIn();
        while (true){  //here is the choices: each choice will go to the invoked method from the invoked class.
            System.out.println("");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(
                    "What do you want to do (type the number of your choice):" +
                    "\n1- Upload a video" +
                    "\n2- Search/type the name of a video to Play" +
                    "\n3- Delete a video" +
                    "\n4- Trending Videos" +
                    "\n5- Show videos" +
                    "\n6- search for similar titles and keywords" +
                    "\n7- WatchList" +
                    "\n8- Account Information" +
                    "\n9- Sign Out / Exit"
            );
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            int choice = in.nextInt();
            System.out.println();
            System.out.println();
            if (choice==1){
                video.upload();
            }
            else if(choice==2){
                playVideo.play();
            }
            else if(choice==3){
                video.delete();
            }
            else if(choice==4){
                System.out.println("Trending Videos are: ");
                trending.trending();
            }
            else if (choice==5){
                String[] showVids = search.showVideos();  //to give the return of showVideos to be the value of showVids array
                System.out.println("The Videos : ");
                for(int i=0; i< showVids.length;i++){  //to print the showvids array
                    System.out.println((i+1)+": "+showVids[i]);
                }
            }
            else if (choice==6){
                search.Similar();
            }
            else if(choice==7){
                watchList.watchListMain();
            }
            else if(choice==8){
                System.out.println("Enter [1/2/3/4]" +
                        "\n1-Show User Information" +
                        "\n2-Change Password" +
                        "\n3-Change Channel Name" +
                        "\n4-Close account" +
                        "\n5-Exit");
                int choose = in.nextInt();
                if(choose==1){
                    logInORSignIn.showInfo();
                }
                if(choose==2){
                    logInORSignIn.changePassword();
                }
                if(choose==3){
                    logInORSignIn.changeChannel();
                }
                if(choose==4){
                    logInORSignIn.closeAccount();
                    break;
                }
            }
            else if(choice==9){
                System.out.println("      ~~~~~~ Thank you for using YUUTUBE ~~~~~~");
                break;  // exit the program
            }
            else{
                System.out.println("Please enter a correct number");
            }
        }
    }
}
