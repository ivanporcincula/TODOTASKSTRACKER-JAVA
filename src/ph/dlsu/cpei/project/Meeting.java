package ph.dlsu.cpei.project;

import java.io.*;
import java.util.Scanner;

public class Meeting extends Category{

    @Override
    public void addHigh(int filenum, String name, String date, String time) {
        File highFile = new File("assets/High"+filenum+".txt");
        File meetingFile = new File("assets/Meeting"+filenum+".txt");

        if(highFile.exists()){
            try{
                StringBuilder changeData= new StringBuilder();
                String newContent;
                Scanner sc = new Scanner(highFile);
                String getString = sc.nextLine();
                changeData.append(getString).append("|").append(name).append("|").append(date).append(" ").append(time);
                newContent = changeData.toString();
                FileWriter addNew= new FileWriter("assets/High"+filenum+".txt");
                addNew.write(newContent);
                addNew.close();
                sc.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        else{
            try {
                FileWriter createFile1 = new FileWriter("assets/High" + filenum + ".txt");
                createFile1.write(name+"|"+date+" "+time);
                createFile1.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        if(meetingFile.exists()){
            try{
                StringBuilder changeData= new StringBuilder();
                String newContent;
                Scanner sc = new Scanner(meetingFile);
                String getString = sc.nextLine();
                changeData.append(getString).append("|").append(name).append("|").append(date).append(" ").append(time);
                newContent = changeData.toString();
                FileWriter addNew= new FileWriter("assets/Meeting"+filenum+".txt");
                addNew.write(newContent);
                addNew.close();
                sc.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        else{
            try {
                FileWriter createFile2 = new FileWriter("assets/Meeting" + filenum + ".txt");
                createFile2.write(name+"|"+date+" "+time);
                createFile2.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public void addMed(int filenum, String name, String date, String time) {
        File mediumFile = new File("assets/Medium"+filenum+".txt");
        File meetingFile = new File("assets/Meeting"+filenum+".txt");

        if(mediumFile.exists()){
            try{
                StringBuilder changeData= new StringBuilder();
                String newContent;
                Scanner sc = new Scanner(mediumFile);
                String getString = sc.nextLine();
                changeData.append(getString).append("|").append(name).append("|").append(date).append(" ").append(time);
                newContent = changeData.toString();
                FileWriter addNew= new FileWriter("assets/Medium"+filenum+".txt");
                addNew.write(newContent);
                addNew.close();
                sc.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        else{
            try {
                FileWriter createFile1 = new FileWriter("assets/Medium" + filenum + ".txt");
                createFile1.write(name+"|"+date+" "+time);
                createFile1.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        if(meetingFile.exists()){
            try{
                StringBuilder changeData= new StringBuilder();
                String newContent;
                Scanner sc = new Scanner(meetingFile);
                String getString = sc.nextLine();
                changeData.append(getString).append("|").append(name).append("|").append(date).append(" ").append(time);
                newContent = changeData.toString();
                FileWriter addNew= new FileWriter("assets/Meeting"+filenum+".txt");
                addNew.write(newContent);
                addNew.close();
                sc.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        else{
            try {
                FileWriter createFile2 = new FileWriter("assets/Meeting" + filenum + ".txt");
                createFile2.write(name+"|"+date+" "+time);
                createFile2.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public void addLow(int filenum, String name, String date, String time) {
        File lowFile = new File("assets/Low"+filenum+".txt");
        File meetingFile = new File("assets/Meeting"+filenum+".txt");

        if(lowFile.exists()){
            try{
                StringBuilder changeData= new StringBuilder();
                String newContent;
                Scanner sc = new Scanner(lowFile);
                String getString = sc.nextLine();
                changeData.append(getString).append("|").append(name).append("|").append(date).append(" ").append(time);
                newContent = changeData.toString();
                FileWriter addNew= new FileWriter("assets/Low"+filenum+".txt");
                addNew.write(newContent);
                addNew.close();
                sc.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        else{
            try {
                FileWriter createFile1 = new FileWriter("assets/Low" + filenum + ".txt");
                createFile1.write(name+"|"+date+" "+time);
                createFile1.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }

        if(meetingFile.exists()){
            try{
                StringBuilder changeData= new StringBuilder();
                String newContent;
                Scanner sc = new Scanner(meetingFile);
                String getString = sc.nextLine();
                changeData.append(getString).append("|").append(name).append("|").append(date).append(" ").append(time);
                newContent = changeData.toString();
                FileWriter addNew= new FileWriter("assets/Meeting"+filenum+".txt");
                addNew.write(newContent);
                addNew.close();
                sc.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        else{
            try {
                FileWriter createFile2 = new FileWriter("assets/Meeting" + filenum + ".txt");
                createFile2.write(name+"|"+date+" "+time);
                createFile2.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
