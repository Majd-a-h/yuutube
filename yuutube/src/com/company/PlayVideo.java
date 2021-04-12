package com.company;

import java.io.*;
import java.util.Scanner;

public class PlayVideo {

    public void play() { //to play the video
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Can you please write the name of the video to play it (without any spaces), OR press enter to exit: ");
            String name = in.nextLine(); //take from the user the video name he/she wants to play
            if(checkNamePlay(name)==true){ //go to checkNamePlay method and check if the video name is exits in the program or not
                String path = play(name); //to get the path of the video from the Videos file and to add to the video views 1
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path); //to run the video on the computer
                System.out.println(
                        " Video Views: "+ showViwes(name)+
                        "\n Likes: "+ showLikes(name)+
                        "\n Dislikes: "+ showDislikes(name)+
                        "\n Comments: "
                ); //print the video information after the user has watched the video
                readComments(name);  //method to print the comments of the video
                String subscribe=null;
                String choiceSub=null;
                if(showSubscribe(name).equalsIgnoreCase("0")){ //if the user is not subscribed
                    subscribe= " You are unsubscribed";
                    choiceSub= "subscribe"; //choice to give it to the user
                }
                if(showSubscribe(name).equalsIgnoreCase("1")){ //if the user is subscribed
                    subscribe= " You are subscribed";
                    choiceSub= "unsubscribe"; //choice to give it to the user
                }
                System.out.println(subscribe + " do you want to "+choiceSub+"? [1/2]"); //ask the user to subscribe or not
                System.out.println("  \n1- Yes\n2- No Exit");
                int choice = in.nextInt();
                if (choice == 1) {
                    if(choiceSub.equalsIgnoreCase("subscribe")){
                        addSubscribe(name);
                    }
                    if(choiceSub.equalsIgnoreCase("unsubscribe")){
                        removeSubsribe(name);
                    }
                }

                System.out.println("Type the number to:\n1- Like\n2- Remove Like\n3- Dislike\n4- Remove Dislike\n5- Exit");
                int choiceLD = in.nextInt();
                if (choiceLD == 1) {
                    addlike(name);
                }
                if (choiceLD == 2) {
                    removelike(name);
                }
                if (choiceLD == 3) {
                    addDislike(name);
                }
                if (choiceLD == 4) {
                    removeDislike(name);
                }

                System.out.println("Type the number to:\n1- Add a Comment\n2- Exit to the menu");
                int choiceComment = in.nextInt();
                if (choiceComment==1) {
                    addComment(name);
                }
            }
            if(checkNamePlay(name)==false){ //if the name does not exits then show a message that it does not exits
                System.out.println("");
                System.out.println("                     SORRY! THE VIDEO NAME DOES NOT EXISTS!");
            }
        } catch (IOException e) {
        }
    }

    public boolean checkNamePlay(String name){ //to check if the name entered from the user is exists or not
        boolean nameExists = false;
        try {
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            while (in.hasNextLine()) {
                String findPath = in.next();
                String commaIgnore = in.next();
                String findName = in.next();
                in.nextLine();
                if (name.equalsIgnoreCase(findName)) { //if the name does exits then make the nameExists variable true (the return variable).
                    nameExists=true;
                    break;
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("There is a problem with checkNamePlay method in the PlayVideo class");
        }
        return nameExists;
    }

    public String play(String name) { //play method to search for the path of the video name that the user wants to play.
        String path = "";
        try {
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            PrintWriter temp = new PrintWriter(new FileOutputStream("temp.txt")); //this uses to add one view after the video is played
            boolean cheak = false; //to check if the views will be more 1 or not
            while (in.hasNextLine()) { //to read each line from the Videos text file
                String findPath = in.next();
                String commaIgnore = in.next();
                String findName = in.next();
                String commaIgnoreAgain = in.next();
                String findViwes = in.next();
                String commaIgnoreAgain1 = in.next();
                String findLikes = in.next();
                String commaIgnoreAgain2 = in.next();
                String findDisLikes = in.next();
                String commaIgnoreAgain3 = in.next();
                String findSubscribe = in.next();
                in.nextLine();
                if (name.equalsIgnoreCase(findName)) { //if you find the name in the file:
                    path = findPath; //give the return variable (path) the path of the name that the user has enterd
                    cheak = true; //make the check variable is true
                    int viewsTemp = Integer.parseInt(findViwes); //invert the views from string to int type, just to add 1 to the views.
                    viewsTemp += 1; //add one to the views
                    findViwes = Integer.toString(viewsTemp); //convert it back to String type
                    temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe); //move the whole line to temp file
                } else if (cheak == false) {
                    temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe); //move the whole line to temp file without any change
                }
                cheak = false;
            }
            in.close();
            temp.close();

            Scanner intemp = new Scanner(new FileInputStream("temp.txt"));
            PrintWriter renew = new PrintWriter(new FileOutputStream("Videos.txt"));
            while (intemp.hasNextLine()) { //transfer all the lines from temp file to Videos file back after you have uploaded the views of the video that has played.
                String findPath = intemp.next();
                String commaIgnore = intemp.next();
                String findName = intemp.next();
                String commaIgnoreAgain = intemp.next();
                String findViwes = intemp.next();
                String commaIgnoreAgain1 = intemp.next();
                String findLikes = intemp.next();
                String commaIgnoreAgain2 = intemp.next();
                String findDisLikes = intemp.next();
                String commaIgnoreAgain3 = intemp.next();
                String findSubscribe = intemp.next();
                intemp.nextLine();
                renew.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
            }
            intemp.close();
            renew.close();
        } catch (IOException e) {
            System.out.println("Problem with the delete!");
        }
        File deletetemp = new File("temp.txt"); //delete the temp file
        if (deletetemp.exists()) {
            deletetemp.delete();
        }
        return path;
    }

    public String showViwes(String name) { //to get the views number of a video
        String views = ""; //return variable
        try {
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            while (in.hasNextLine()) { //to read all the lines from the videos text file till it reach the played video name and assign the views number to the return variable
                String findPath = in.next();
                String commaIgnore = in.next();
                String findName = in.next();
                String commaIgnoreAgain = in.next();
                String findViwes = in.next();
                String commaIgnoreAgain1 = in.next();
                String findLikes = in.next();
                String commaIgnoreAgain2 = in.next();
                String findDisLikes = in.next();
                String commaIgnoreAgain3 = in.next();
                String findSubscribe = in.next();
                if (name.equalsIgnoreCase(findName)) {
                    views = findViwes;
                    break;
                }
                in.nextLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry! The name does not exits!");
        }
        return views;
    }

    public String addlike(String name) { //to add 1 like to the video
        String likes = "0"; //the return variable return the number of likes
        try {
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            PrintWriter temp = new PrintWriter(new FileOutputStream("temp.txt")); //make a temp file, just to edit the likes number and then the information will be return to the Videos file
            boolean cheak = false;
            while (in.hasNextLine()) { //to read all the lines from the videos text file till it reach the played video name
                String findPath = in.next();
                String commaIgnore = in.next();
                String findName = in.next();
                String commaIgnoreAgain = in.next();
                String findViwes = in.next();
                String commaIgnoreAgain1 = in.next();
                String findLikes = in.next();
                String commaIgnoreAgain2 = in.next();
                String findDisLikes = in.next();
                String commaIgnoreAgain3 = in.next();
                String findSubscribe = in.next();
                in.nextLine();
                if (name.equalsIgnoreCase(findName)) {
                    boolean seelikes = false;
                    likes = findLikes;
                    if (!findLikes.equals("1")) { //if the likes is not 1 then add a like
                        cheak = true;
                        int likesTemp = Integer.parseInt(findLikes);
                        likesTemp += 1;
                        findLikes = Integer.toString(likesTemp);
                        seelikes = true;
                    }
                    if (!findDisLikes.equals("0")) { //if the dislike is not 0 then make it zero (the user cannot like and dislike the video in the same time)
                        cheak = true;
                        int dislikesTemp = Integer.parseInt(findDisLikes);
                        dislikesTemp -= 1;
                        findDisLikes = Integer.toString(dislikesTemp);
                        seelikes = true;
                    }
                    if(seelikes==true){  //write the video information line to the temp file
                        temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                    }
                }
                if (cheak == false){ //write the video information line to the temp file if the name is not the video played name
                        temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                    }
                    cheak = false;
                }
                in.close();
                temp.close();
                Scanner intemp = new Scanner(new FileInputStream("temp.txt"));
                PrintWriter renew = new PrintWriter(new FileOutputStream("Videos.txt"));
                while (intemp.hasNextLine()) { //return all the information to the Videos file
                    String findPath = intemp.next();
                    String commaIgnore = intemp.next();
                    String findName = intemp.next();
                    String commaIgnoreAgain = intemp.next();
                    String findViwes = intemp.next();
                    String commaIgnoreAgain1 = intemp.next();
                    String findLikes = intemp.next();
                    String commaIgnoreAgain2 = intemp.next();
                    String findDisLikes = intemp.next();
                    String commaIgnoreAgain3 = intemp.next();
                    String findSubscribe = intemp.next();
                    intemp.nextLine();
                    renew.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                }
                intemp.close();
                renew.close();
            } catch(FileNotFoundException e){
                System.out.println("Sorry! The name does not exits!");
            } catch(IOException e){
                System.out.println("Problem with the delete!");
            }
            File deletetemp = new File("temp.txt");
            if (deletetemp.exists()) {
                deletetemp.delete();
            }
            return likes;
        }

        public String removelike (String name){
            String likes = "0";
            try {
                Scanner in = new Scanner(new FileInputStream("Videos.txt"));
                PrintWriter temp = new PrintWriter(new FileOutputStream("temp.txt")); //make a temp file, just to edit the likes number and then the information will be return to the Videos file
                boolean cheak = false;
                while (in.hasNextLine()) { //check the Videos file
                    String findPath = in.next();
                    String commaIgnore = in.next();
                    String findName = in.next();
                    String commaIgnoreAgain = in.next();
                    String findViwes = in.next();
                    String commaIgnoreAgain1 = in.next();
                    String findLikes = in.next();
                    String commaIgnoreAgain2 = in.next();
                    String findDisLikes = in.next();
                    String commaIgnoreAgain3 = in.next();
                    String findSubscribe = in.next();
                    in.nextLine();
                    if (name.equalsIgnoreCase(findName)) { //if you find the played video name
                        likes = findLikes;
                        if (!findLikes.equals("0")) { //if the like is not zero then make it a zero
                            cheak = true;
                            int likesTemp = Integer.parseInt(findLikes);
                            likesTemp -= 1;
                            findLikes = Integer.toString(likesTemp);
                        }
                        temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                    } else if (cheak == false) { //if the video name not the played video also transfer all the information to the temp file without editing anything
                        temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                    }
                    cheak = false;
                }
                in.close();
                temp.close();
                Scanner intemp = new Scanner(new FileInputStream("temp.txt"));
                PrintWriter renew = new PrintWriter(new FileOutputStream("Videos.txt"));
                while (intemp.hasNextLine()) { //return all the information to the Videos file
                    String findPath = intemp.next();
                    String commaIgnore = intemp.next();
                    String findName = intemp.next();
                    String commaIgnoreAgain = intemp.next();
                    String findViwes = intemp.next();
                    String commaIgnoreAgain1 = intemp.next();
                    String findLikes = intemp.next();
                    String commaIgnoreAgain2 = intemp.next();
                    String findDisLikes = intemp.next();
                    String commaIgnoreAgain3 = intemp.next();
                    String findSubscribe = intemp.next();
                    intemp.nextLine();
                    renew.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                }
                intemp.close();
                renew.close();
            } catch (FileNotFoundException e) {
                System.out.println("Sorry! The name does not exits!");
            } catch (IOException e) {
                System.out.println("Problem with the delete!");
            }
            File deletetemp = new File("temp.txt");
            if (deletetemp.exists()) {
                deletetemp.delete();
            }
            return likes;
        }

        public String showLikes (String name){ //to show the likes number to the user
            String likes = "";
            try {
                Scanner in = new Scanner(new FileInputStream("Videos.txt"));
                while (in.hasNextLine()) { //to find the played video name
                    String findPath = in.next();
                    String commaIgnore = in.next();
                    String findName = in.next();
                    String commaIgnoreAgain = in.next();
                    String findViwes = in.next();
                    String commaIgnoreAgain1 = in.next();
                    String findLikes = in.next();
                    String commaIgnoreAgain2 = in.next();
                    String findDisLikes = in.next();
                    String commaIgnoreAgain3 = in.next();
                    String findSubscribe = in.next();
                    if (name.equalsIgnoreCase(findName)) {
                        likes = findLikes; //to assign the return variable to the likes number of the video played
                        break;
                    }
                    in.nextLine();
                }
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("Sorry! The name does not exits!");
            }
            return likes;
        }


        public String addDislike (String name){ //to add dislike
            String dislikes = "0";
            try {
                Scanner in = new Scanner(new FileInputStream("Videos.txt"));
                PrintWriter temp = new PrintWriter(new FileOutputStream("temp.txt")); //make a temp file, just to edit the dislikes number and then the information will be return to the Videos file
                boolean cheak = false;
                while (in.hasNextLine()) {
                    String findPath = in.next();
                    String commaIgnore = in.next();
                    String findName = in.next();
                    String commaIgnoreAgain = in.next();
                    String findViwes = in.next();
                    String commaIgnoreAgain1 = in.next();
                    String findLikes = in.next();
                    String commaIgnoreAgain2 = in.next();
                    String findDisLikes = in.next();
                    String commaIgnoreAgain3 = in.next();
                    String findSubscribe = in.next();
                    in.nextLine();
                    if (name.equalsIgnoreCase(findName)) { //if find the played video name then:
                        boolean seelikes = false;
                        dislikes = findDisLikes;
                        if (!findDisLikes.equals("1")) { //if the dislikes not equal 1 then add 1
                            cheak = true;
                            int dislikesTemp = Integer.parseInt(findDisLikes);
                            dislikesTemp += 1;
                            findDisLikes = Integer.toString(dislikesTemp);
                            seelikes=true;
                        }
                        if (!findLikes.equals("0")) { //if the likes not equal 0 then make it 0
                            cheak = true;
                            int likesTemp = Integer.parseInt(findLikes);
                            likesTemp -= 1;
                            findLikes = Integer.toString(likesTemp);
                            seelikes=true;
                        }
                        if(seelikes==true){
                            temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                        }
                    }
                    if (cheak == false) {
                        temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                    }
                    cheak = false;
                }
                in.close();
                temp.close();

                Scanner intemp = new Scanner(new FileInputStream("temp.txt"));
                PrintWriter renew = new PrintWriter(new FileOutputStream("Videos.txt"));
                while (intemp.hasNextLine()) { //return all the information to the video file after you have taken all to the temp file
                    String findPath = intemp.next();
                    String commaIgnore = intemp.next();
                    String findName = intemp.next();
                    String commaIgnoreAgain = intemp.next();
                    String findViwes = intemp.next();
                    String commaIgnoreAgain1 = intemp.next();
                    String findLikes = intemp.next();
                    String commaIgnoreAgain2 = intemp.next();
                    String findDisLikes = intemp.next();
                    String commaIgnoreAgain3 = intemp.next();
                    String findSubscribe = intemp.next();
                    intemp.nextLine();
                    renew.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                }
                intemp.close();
                renew.close();
            } catch (IOException e) {
                System.out.println("Problem with the delete!");
            }
            File deletetemp = new File("temp.txt");
            if (deletetemp.exists()) {
                deletetemp.delete();
            }
            return dislikes;
        }

        public String removeDislike (String name){
            String dislikes = "0";
            try {
                Scanner in = new Scanner(new FileInputStream("Videos.txt"));
                PrintWriter temp = new PrintWriter(new FileOutputStream("temp.txt")); //make a temp file, just to edit the dislikes number and then the information will be return to the Videos file
                boolean cheak = false;
                while (in.hasNextLine()) {
                    String findPath = in.next();
                    String commaIgnore = in.next();
                    String findName = in.next();
                    String commaIgnoreAgain = in.next();
                    String findViwes = in.next();
                    String commaIgnoreAgain1 = in.next();
                    String findLikes = in.next();
                    String commaIgnoreAgain2 = in.next();
                    String findDisLikes = in.next();
                    String commaIgnoreAgain3 = in.next();
                    String findSubscribe = in.next();
                    in.nextLine();
                    if (name.equalsIgnoreCase(findName)) { //if find the played video name then:
                        dislikes = findDisLikes;
                        if (!findDisLikes.equals("0")) { //if the dislike is not zero then make it zero
                            cheak = true;
                            int dislikesTemp = Integer.parseInt(findDisLikes);
                            dislikesTemp -= 1;
                            findDisLikes = Integer.toString(dislikesTemp);
                        }
                        temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                    } else if (cheak == false) {
                        temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                    }
                    cheak = false;
                }
                in.close();
                temp.close();
                Scanner intemp = new Scanner(new FileInputStream("temp.txt"));
                PrintWriter renew = new PrintWriter(new FileOutputStream("Videos.txt"));
                while (intemp.hasNextLine()) {  //return all the information to the videoo file
                    String findPath = intemp.next();
                    String commaIgnore = intemp.next();
                    String findName = intemp.next();
                    String commaIgnoreAgain = intemp.next();
                    String findViwes = intemp.next();
                    String commaIgnoreAgain1 = intemp.next();
                    String findLikes = intemp.next();
                    String commaIgnoreAgain2 = intemp.next();
                    String findDisLikes = intemp.next();
                    String commaIgnoreAgain3 = intemp.next();
                    String findSubscribe = intemp.next();
                    intemp.nextLine();
                    renew.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                }
                intemp.close();
                renew.close();
            } catch (IOException e) {
                System.out.println("Problem with the delete!");
            }
            File deletetemp = new File("temp.txt");
            if (deletetemp.exists()) {
                deletetemp.delete();
            }
            return dislikes;
        }

        public String showDislikes (String name){ //to show the number of dislikes
            String dislikes = "";
            try {
                Scanner in = new Scanner(new FileInputStream("Videos.txt"));
                while (in.hasNextLine()) {
                    String findPath = in.next();
                    String commaIgnore = in.next();
                    String findName = in.next();
                    String commaIgnoreAgain = in.next();
                    String findViwes = in.next();
                    String commaIgnoreAgain1 = in.next();
                    String findLikes = in.next();
                    String commaIgnoreAgain2 = in.next();
                    String findDisLikes = in.next();
                    String commaIgnoreAgain3 = in.next();
                    String findSubscribe = in.next();
                    if (name.equalsIgnoreCase(findName)) {
                        dislikes = findDisLikes;
                        break;
                    }
                    in.nextLine();
                }
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("Sorry! The name does not exits!");
            }
            return dislikes;
        }

    public String addSubscribe (String name){
        String subscribe = "0";
        try {
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            PrintWriter temp = new PrintWriter(new FileOutputStream("temp.txt")); //make a temp file, just to edit the dislikes number and then the information will be return to the Videos file
            boolean cheak = false;
            while (in.hasNextLine()) {
                String findPath = in.next();
                String commaIgnore = in.next();
                String findName = in.next();
                String commaIgnoreAgain = in.next();
                String findViwes = in.next();
                String commaIgnoreAgain1 = in.next();
                String findLikes = in.next();
                String commaIgnoreAgain2 = in.next();
                String findDisLikes = in.next();
                String commaIgnoreAgain3 = in.next();
                String findSubscribe = in.next();
                in.nextLine();
                if (name.equalsIgnoreCase(findName)) { //if the name of the played video is found then:
                    subscribe = findSubscribe;
                    if (!findSubscribe.equals("1")) { //if the subscribers not 1 then add one
                        cheak = true;
                        int subscribeTemp = Integer.parseInt(findSubscribe);
                        subscribeTemp += 1;
                        findSubscribe = Integer.toString(subscribeTemp);
                    }
                    temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                }
                if (cheak == false) {
                    temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                }
                cheak = false;
            }
            in.close();
            temp.close();

            Scanner intemp = new Scanner(new FileInputStream("temp.txt"));
            PrintWriter renew = new PrintWriter(new FileOutputStream("Videos.txt"));
            while (intemp.hasNextLine()) { //return all information to the videos file
                String findPath = intemp.next();
                String commaIgnore = intemp.next();
                String findName = intemp.next();
                String commaIgnoreAgain = intemp.next();
                String findViwes = intemp.next();
                String commaIgnoreAgain1 = intemp.next();
                String findLikes = intemp.next();
                String commaIgnoreAgain2 = intemp.next();
                String findDisLikes = intemp.next();
                String commaIgnoreAgain3 = intemp.next();
                String findSubscribe = intemp.next();
                intemp.nextLine();
                renew.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
            }
            intemp.close();
            renew.close();
        } catch (IOException e) {
            System.out.println("Problem with the delete!");
        }
        File deletetemp = new File("temp.txt");
        if (deletetemp.exists()) {
            deletetemp.delete();
        }
        return subscribe;
    }

    public String removeSubsribe (String name){
        String subscribe = "0";
        try {
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            PrintWriter temp = new PrintWriter(new FileOutputStream("temp.txt")); //make a temp file, just to edit the dislikes number and then the information will be return to the Videos file
            boolean cheak = false;
            while (in.hasNextLine()) {
                String findPath = in.next();
                String commaIgnore = in.next();
                String findName = in.next();
                String commaIgnoreAgain = in.next();
                String findViwes = in.next();
                String commaIgnoreAgain1 = in.next();
                String findLikes = in.next();
                String commaIgnoreAgain2 = in.next();
                String findDisLikes = in.next();
                String commaIgnoreAgain3 = in.next();
                String findSubscribe = in.next();
                in.nextLine();
                if (name.equalsIgnoreCase(findName)) {
                    subscribe = findSubscribe;
                    if (!findSubscribe.equals("0")) { //if the dislikes of the playing video is not zero then make it zero
                        cheak = true;
                        int subscribeTemp = Integer.parseInt(findSubscribe);
                        subscribeTemp -= 1;
                        findSubscribe = Integer.toString(subscribeTemp);
                    }
                    temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                } else if (cheak == false) {
                    temp.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
                }
                cheak = false;
            }
            in.close();
            temp.close();
            Scanner intemp = new Scanner(new FileInputStream("temp.txt"));
            PrintWriter renew = new PrintWriter(new FileOutputStream("Videos.txt"));
            while (intemp.hasNextLine()) { //retrun all the information to the videos file
                String findPath = intemp.next();
                String commaIgnore = intemp.next();
                String findName = intemp.next();
                String commaIgnoreAgain = intemp.next();
                String findViwes = intemp.next();
                String commaIgnoreAgain1 = intemp.next();
                String findLikes = intemp.next();
                String commaIgnoreAgain2 = intemp.next();
                String findDisLikes = intemp.next();
                String commaIgnoreAgain3 = intemp.next();
                String findSubscribe = intemp.next();
                intemp.nextLine();
                renew.println(findPath + " , " + findName + " , " + findViwes + " , " + findLikes + " , " + findDisLikes + " , " + findSubscribe);
            }
            intemp.close();
            renew.close();
        } catch (IOException e) {
            System.out.println("Problem with the removeSubscribe!");
        }
        File deletetemp = new File("temp.txt");
        if (deletetemp.exists()) {
            deletetemp.delete();
        }
        return subscribe;
    }

    public String showSubscribe (String name){ //this method to show the Subscribe
        String subscribe = ""; //this is the return variable of the subscribers number
        try {
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            while (in.hasNextLine()) { //to read all the lines from the videos text file till it reach the played video name and assign the subcribers number to the return variable
                String findPath = in.next();
                String commaIgnore = in.next();
                String findName = in.next();
                String commaIgnoreAgain = in.next();
                String findViwes = in.next();
                String commaIgnoreAgain1 = in.next();
                String findLikes = in.next();
                String commaIgnoreAgain2 = in.next();
                String findDisLikes = in.next();
                String commaIgnoreAgain3 = in.next();
                String findSubscribe = in.next();
                if (name.equalsIgnoreCase(findName)) {
                    subscribe = findSubscribe;
                    break;
                }
                in.nextLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry! The name does not exits!");
        }
        return subscribe;
    }

    public void addComment(String name){
        try{
            File createInCase = new File("Comment.txt");
            if (!createInCase.exists()) {
                PrintWriter createUserInfo = new PrintWriter(new FileOutputStream("Comment.txt",true));
                createUserInfo.close();
            }
            PrintWriter outTempComment = new PrintWriter(new FileOutputStream("TempComment.txt"));
            Scanner scanComment = new Scanner(new FileInputStream("Comment.txt"));
            boolean check =false;
            String line ="";
            while (scanComment.hasNextLine()){   //to check if the video already has comments or not
                line=scanComment.nextLine();
                if(line.equals(name)){
                    check=true;
                    break;
                }
            }
            scanComment.close();
            Scanner scanCommentT = new Scanner(new FileInputStream("Comment.txt"));
            Scanner getInputComment = new Scanner(System.in);
            if(check==true) { //if the video already has comments
                String lineTrue ="";
                while (scanCommentT.hasNextLine()) {
                    lineTrue=scanCommentT.nextLine();
                    while (!lineTrue.equalsIgnoreCase(name)) {//to transfer all the lines before the video name
                        outTempComment.println(lineTrue);
                        lineTrue=scanCommentT.nextLine();
                    }
                    outTempComment.println(lineTrue); //to transfer the video name
                    System.out.println("Type the comment: ");
                    String inputCommetn = getInputComment.nextLine();
                    outTempComment.println(inputCommetn);
                    while (scanCommentT.hasNextLine()) { //to transfer all the lines after the video name
                        lineTrue=scanCommentT.nextLine();
                        outTempComment.println(lineTrue);
                    }
                }
            }
            scanCommentT.close();

            Scanner scanCommentf = new Scanner(new FileInputStream("Comment.txt"));
            if(check==false){
                outTempComment.println(name); //to transfer the video name
                System.out.println("Type the comment: ");
                String inputCommentf = getInputComment.nextLine();
                outTempComment.println(inputCommentf);
                while (scanCommentf.hasNextLine()) { //to transfer all the lines from comments file to temp
                    outTempComment.println(scanCommentf.nextLine());
                }
            }
            scanCommentf.close();
            outTempComment.close();
            File deleteCommentForNow = new File("Comment.txt");
            if (deleteCommentForNow.exists()) {
                deleteCommentForNow.delete();
            }
            Scanner scanTempComment = new Scanner(new FileInputStream("TempComment.txt"));
            PrintWriter outComment = new PrintWriter(new FileOutputStream("Comment.txt"));
            while (scanTempComment.hasNextLine()){
                outComment.println(scanTempComment.nextLine());
            }
            scanTempComment.close();
            outComment.close();
            File deleteTempComment = new File("TempComment.txt");
            if (deleteTempComment.exists()) {
                deleteTempComment.delete();
            }
        }catch (IOException e){
            System.out.println("In addComment method a file couldn't be made");
        }
    }

    public void readComments(String name){ //to show all the comments after the video played
        try{
            String array[] = new String[0]; //This is just temporary, it will be changed later and the array is for the videos names in file
            Scanner tempin = new Scanner(new FileInputStream("Videos.txt"));
            int size = 0;
            while (tempin.hasNextLine()) { //this while loop is to find how much the array size should be
                size++;
                tempin.nextLine();
            }
            tempin.close();
            array = new String[size]; //assign the array the size
            int i = 0;
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            while (in.hasNextLine()) { //to take each video name in the videos file and assign it to the array
                String findPath = in.next();
                String commaIgnore = in.next();
                String findName = in.next();
                String commaIgnoreAgain = in.next();
                String findViwes = in.next();
                String commaIgnoreAgain1 = in.next();
                String findLikes = in.next();
                String commaIgnoreAgain2 = in.next();
                String findDisLikes = in.next();
                String commaIgnoreAgain3 = in.next();
                String findSubscribe = in.next();
                in.nextLine();
                array[i] = findName;
                i++;
            }
            in.close();
            Scanner inComments = new Scanner(new FileInputStream("Comment.txt"));
            while (inComments.hasNextLine()){  //now to find the comments of the video
                String line ="";
                line=inComments.nextLine(); //to take each line of the comments file
                if(line.equalsIgnoreCase(name)){ //when the line = the video name you wan to show the comments of then:
                    boolean check = false;
                    label: while(inComments.hasNextLine()){
                    line=inComments.nextLine(); //go to the first comment
                    for(int y=0;y< array.length;y++){ //print every comment till the line is equal another video name then stop
                        if(!line.equalsIgnoreCase(array[y])){ //here we use the array that has all the videos names to help check
                            check=false;
                        }
                        else{
                            check=true; //if the check is true means that you have print each line of the comments
                            break label; //break the label which is the whole while loop
                        }
                    }
                    if(check==false){
                        System.out.println(line);  //to print the line of comment
                    }
                    }
                }
            }
            inComments.close();
        }catch (FileNotFoundException e){
            System.out.println("Could not read comments");
        }
    }
}