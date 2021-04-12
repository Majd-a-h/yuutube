package com.company;

import java.io.*;
import java.util.Scanner;

public class WatchList {

    public static void watchListMain(){
        System.out.println("(Enter the number):\n1-Show WatchList\n2-Add to WatchList\n3-Delete From WatchList\n");
        Search search = new Search();
        WatchList watchList = new WatchList();
        Scanner in = new Scanner(System.in);
        int choiceWatchList = in.nextInt();
        if(choiceWatchList==1){
            System.out.println("Here is your WatchList: ");
            watchList.showWatchList();
        }
        else if(choiceWatchList==2){
            System.out.println("Here is the list of videos in the YuuTube: ");
            String[] showVids = search.showVideos();
            System.out.println("The Videos : ");
            for(int i=0; i< showVids.length;i++){
                System.out.println((i+1)+": "+showVids[i]);
            }
            System.out.println();
            System.out.println("Type the name of the video to add in the WatchList: ");
            String name= in.next();
            in.nextLine();
            watchList.watchlist(name);
            System.out.println();
            System.out.println("The video has been added to the WatchList");
            System.out.println("");
            System.out.println("Here is your WatchList: ");
            watchList.showWatchList();
            System.out.println();
        }
        else if(choiceWatchList==3){
            watchList.chooseNameforDelete();
        }
    }

    public void watchlist(String name){  //add a video to watchlist
        try{
//            File createInCase = new File("UserInfo.txt");
//            if (!createInCase.exists()) {
//                PrintWriter putInWatchList = new PrintWriter(new FileOutputStream("WatchList.txt",true));
//                putInWatchList.print(name);
//                putInWatchList.close();
//            }
//            else{
                PrintWriter putInWatchList = new PrintWriter(new FileOutputStream("WatchList.txt",true));
                putInWatchList.println(name);
                putInWatchList.close();
            //}
        }catch(IOException e){
            System.out.println("The WatchList file could not be made");
        }
    }

    public void showWatchList(){ //to show all the videos in watchlist
        try{
            PlayVideo playVideo = new PlayVideo();
            Scanner showWatchList = new Scanner(new FileInputStream("WatchList.txt"));
            int i=1;
            while (showWatchList.hasNextLine()){
                System.out.println(i+"- "+showWatchList.nextLine());
                i++;
            }
            showWatchList.close();
            System.out.println();
            playVideo.play();
        }catch (FileNotFoundException e){
            System.out.println("WatchList is empty");
        }
    }

    public void chooseNameforDelete(){ //choose the name of the video you want to delete from the watchlist
        try{
            System.out.println("Here is your WatchList: ");
            Scanner showWatchList = new Scanner(new FileInputStream("WatchList.txt"));
            int count=1;
            while (showWatchList.hasNextLine()){
                System.out.println(count+"- "+showWatchList.nextLine());
                count++;
            }
            showWatchList.close();
            System.out.println("\nEnter the name of the video: ");
            Scanner in = new Scanner(System.in);
            String name = in.nextLine();
            deleteFromWatchList(name);
        }
        catch (IOException e){
        }
    }

    public void deleteFromWatchList(String name) {  //delete a video name from the watchlist
        try {
            Scanner showWatchList = new Scanner(new FileInputStream("WatchList.txt"));
            PrintWriter tempWatchList = new PrintWriter(new FileOutputStream("tempWatchList.txt"));
            while (showWatchList.hasNextLine()) {
                String line = "";
                line = showWatchList.nextLine();
                if (!line.equalsIgnoreCase(name)) {
                    tempWatchList.println(line);
                }
            }
                showWatchList.close();
                tempWatchList.close();
                Scanner intemp = new Scanner(new FileInputStream("tempWatchList.txt"));
                PrintWriter renew = new PrintWriter(new FileOutputStream("WatchList.txt"));
                while (intemp.hasNextLine()) {
                    renew.println(intemp.nextLine());
                }
                intemp.close();
                renew.close();
                File deletetemp = new File("tempWatchList.txt");
                if (deletetemp.exists()) {
                    deletetemp.delete();
                }
            }catch(IOException e){
                System.out.println("Problem inDeleteFromWatchList");
            }
    }

}
