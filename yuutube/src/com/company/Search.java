package com.company;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.util.Scanner;
public class Search {

    public String[] showVideos() { //to show all the videos in the program
        String array[] = new String[0];
        try {
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            Scanner tempin = new Scanner(new FileInputStream("Videos.txt"));
            int size = 0;
            while (tempin.hasNextLine()) {
                size++;
                tempin.nextLine();
            }
            array = new String[size];
            int i = 0;
            while (in.hasNextLine()) { //assign all the videos names to the array
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

        } catch (IOException e) {
            System.out.println("The file Does not exits");
        }
        return array;
    }

    public void Similar(){ //to take the input from the user and then invoke the searchSimilar method
        Scanner in = new Scanner(System.in);
        System.out.println("Enter anything to search for similar titles and keywords (At least type three letters)");
        String anything =in.nextLine();
        searchSimilar(anything);
        try{
            Scanner inSimilar = new Scanner(new FileInputStream("similarNames.txt"));
            while(inSimilar.hasNextLine()){
                System.out.println(inSimilar.nextLine());
            }
            inSimilar.close();
        }catch (FileNotFoundException e){
            System.out.println("similarNames does not exits");
        }

    }


    public void searchSimilar(String anything){ //to see what videos names has Similar keywords or letters as the user input
        String similarName = "";
        File deletetemp = new File("similarNames.txt"); //delete the file if already exits
        if (deletetemp.exists()) {
            deletetemp.delete();
        }
        try {
            Scanner tempin = new Scanner(new FileInputStream("Videos.txt"));
            int size = 0;
            while (tempin.hasNextLine()) {
                size++;
                tempin.nextLine();
            }
            tempin.close();
            String[] array = new String[size];
            int i = 0;
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            while (in.hasNextLine()) { //to take all teh videos names and put in array to compare
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
            PrintWriter tempSimilar = new PrintWriter(new FileOutputStream("similarNames.txt"));
            System.out.println("\nThese are the similar videos with similar titles and keywords:\n");
            for(int j=0;j<array.length;j++){ //see each name from the array that has the names of the videos, if there is three letters are the same
                int k=0;
                for(int f=0;f<anything.length();f++){
                    if(array[j].charAt(f)==anything.charAt(f)){
                        k++;
                    }
                }
                if(k>=3){
                    tempSimilar.println(array[j]);
                }
            }
            in.close();
            tempSimilar.close();
        } catch (IOException e) {
            System.out.println("Problem with searchSimilar!");
        }
    }
}