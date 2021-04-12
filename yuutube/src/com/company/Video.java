package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Video {

    public  void upload(){
        try{
            Scanner in = new Scanner(System.in);  //to take the path and the name of the video from the user
            PrintWriter importToFile = new PrintWriter(new FileOutputStream("Videos.txt",true));  //create a file that will has the videos and their information
            System.out.println("To UPLOAD THE VIDEO: Can you please write the path of the video with the extension, (E.g:C:\\\\Users\\\\hp\\\\Desktop\\\\path.mp4)(Without any spaces): ");
            String path = in.nextLine();
            System.out.print("Can you please write the name of the video: ");
            String name = in.nextLine();
            String views = "0";
            String like="0";
            String dislike="0";
            String subscribe="0";
            importToFile.println(path+" , "+name+" , "+views+" , "+like+" , "+dislike+" , "+subscribe); //the structure of each video line in the Videos text file.
            importToFile.close();
        }catch (IOException e){
            System.out.println("Sorry! The path is wrong!");
        }
    }

    public void delete() { //to choose the video you want to delete
        Scanner in = new Scanner(System.in);
        System.out.print("Can you please write the name of the video to delete it: ");
        String name = in.nextLine();
        delete(name); //to go to the delete method which has the function of deleting a video
    }

    public void delete(String name){ //delete the video
        try{
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            PrintWriter temp = new PrintWriter(new FileOutputStream("temp.txt")); //create temp file to transfer all the videos to it, except the chosen video to delete it
            boolean cheak = false;
            while(in.hasNextLine()){ //here this while loop is to catch each value of each line from Videos text file
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
                if(name.equalsIgnoreCase(findName)){  //if the loop find the chosen name then make the check variable true, in order to not transfer it to the temp file
                    cheak=true;
                }
                else if (cheak==false){ //if check is false then transfer the line to the temp file
                    temp.println(findPath+" , "+findName+ " , "+findViwes+ " , "+findLikes+ " , "+findDisLikes+ " , "+findSubscribe);
                }
                cheak=false; //make the check variable false before looping again.
            }
            in.close();
            temp.close();

            Scanner intemp = new Scanner(new FileInputStream("temp.txt"));
            PrintWriter renew = new PrintWriter(new FileOutputStream("Videos.txt"));
            while(intemp.hasNextLine()){ //now take all the videos from temp file to videos file
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
                renew.println(findPath+" , "+findName+ " , "+findViwes+ " , "+findLikes+ " , "+findDisLikes+ " , "+findSubscribe);
            }
            intemp.close();
            renew.close();
        } catch (IOException e){
            System.out.println("Problem with the delete!");
        }
        File deletetemp= new File("temp.txt"); //delete the temp file
        if(deletetemp.exists()){
            deletetemp.delete();
        }
    }
}