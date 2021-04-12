package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Trending {
    public void trending(){
        try{
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            Scanner tempin = new Scanner(new FileInputStream("Videos.txt"));
            int size = 0;
            while(tempin.hasNextLine()){ //to get the size of the array which will holds the views of all the videos
                size++;
                tempin.nextLine();
            }
            if (size>=5) {
                int[] array = new int[size];

                int i = 0;
                while (in.hasNextLine()) { //to assign the views of all the videos to the array
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
                    int viewsTemp = Integer.parseInt(findViwes);
                    array[i] = viewsTemp;
                    i++;
                }

                for (int pass = 0; pass < array.length - 1; pass++) { //binary search to arrange the array from the bigger to smaller
                    for (int j = 0; j < array.length - pass - 1; j++) {
                        if (array[j] > array[j + 1]) {
                            int hold = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = hold;
                        }
                    }
                }
                //to assign the highest 5 values to variables:
                int highestOne = array[array.length - 1];
                String first = Integer.toString(highestOne);
                int highestTwo = array[array.length - 2];
                String second = Integer.toString(highestTwo);
                int highestThree = array[array.length - 3];
                String third = Integer.toString(highestThree);
                int highestFour = array[array.length - 4];
                String fourth = Integer.toString(highestFour);
                int highestFive = array[array.length - 5];
                String fifth = Integer.toString(highestFive);

                //These if there is videos with the same views number
                if(first.equals(second)){
                    System.out.println("1- " + search(first)+"\n");
                }
                else if(second.equals(third)){
                    System.out.println(
                            "1- " + search(first) +
                                    "\n" + "2- " + search(second)+
                                    "\n"
                    );
                }
                else if(third.equals(fourth)){
                    System.out.println(
                            "1- " + search(first) +
                                    "\n" + "2- " + search(second) +
                                    "\n" + "3- " + search(third) +
                                    "\n"
                    );
                }
                else if(fourth.equals(fifth)){
                    System.out.println(
                            "1- " + search(first) +
                                    "\n" + "2- " + search(second) +
                                    "\n" + "3- " + search(third) +
                                    "\n" + "4- " + search(fourth) +
                                    "\n"
                    );
                }
                else{
                    System.out.println(
                            "1- " + search(first) +
                                    "\n" + "2- " + search(second) +
                                    "\n" + "3- " + search(third) +
                                    "\n" + "4- " + search(fourth) +
                                    "\n" + "5- " + search(fifth) +
                                    "\n"
                    );
                }
                in.close();
            }
            if(size<5){ //These if there is no five videos
                System.out.println("Only have less than 5 videos");
            }
        }catch (IOException e){
            System.out.println("The file Does not exits");
        }
    }

    public String search(String value){ //to search for the name of the video that has the views number
        String trendname="";
        try{
            Scanner in = new Scanner(new FileInputStream("Videos.txt"));
            while(in.hasNextLine()){
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
                if(value.equalsIgnoreCase(findViwes)){
                    trendname = findName;
                    break;
                }
                in.nextLine();
            }
            in.close();
        }catch (FileNotFoundException e){
            System.out.println("Sorry! The name does not exits!");
        }
        return trendname;
    }
}