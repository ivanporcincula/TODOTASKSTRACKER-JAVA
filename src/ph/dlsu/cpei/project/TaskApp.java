package ph.dlsu.cpei.project;

import acm.program.ConsoleProgram;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class TaskApp extends ConsoleProgram {
    private int fileNameNumber;
    private Update sort;

    public void run(){
        try {
            menuDisplay1();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private void menuDisplay1() throws ParseException, IOException {
        int choice;
        String username, password;
        UserDatabase account = new UserDatabase();

        do{
        print(" _____________________________________________\n");
        print("|             TO-DO LIST TRACKER              |\n");
        print("|_____________________________________________|\n");
        print("|           [1] Create a new account          |\n");
        print("|     [2] Login with an existing account      |\n");
        print("|                [3]EXIT PROGRAM              |\n");
        print("-----------------------------------------------\n");
        choice = readInt("Choice: ");
        switch (choice) {
            case 1:
                username = readLine("Enter desired username: ");
                password = readLine("Enter desired password: ");
                account.create(username,password);
                break;
            case 2:
                username = readLine("Enter your username: ");
                password = readLine("Enter your password: ");
                println(account.login(username, password));
                fileNameNumber = account.success();
                if(fileNameNumber != 0) menuDisplay2();
                break;
            case 3:
                exit();

        }
    } while (choice != 4);


    }

    private void menuDisplay2() throws ParseException, IOException {
        int choice;
        String taskToRemove;
        sort = new Update();
        String[] name;
        Date[] date;
        long[] sec,hr,mins,days,yrs;
        do {
            print(" _________________________________________________\n");
            print("|               TO-DO LIST TRACKER                |\n");
            print("|_________________________________________________|\n");
            print("|             [1] Add new Tasks to do             |\n");
            print("|            [2] Update finished tasks            |\n");
            print("|    [3] List of all tasks needed to accomplish   |\n");
            print("|           [4] List all accomplished tasks       |\n");
            print("|                  [5] LOGOUT                     |\n");
            print("---------------------------------------------------\n");

            choice = readInt("Choice: ");

            switch (choice) {
                case 1:
                    menuDisplay3();
                    break;
                case 2:
                    if(sort.timeAndDate(fileNameNumber)){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();

                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else print("Past the deadline.");
                            println();
                            println();
                        }

                        taskToRemove = readLine("Please choose the task you have accomplished: ");
                        sort.remove(taskToRemove,fileNameNumber);
                        println("Task '" +taskToRemove+"' has been successfully finished!");

                    }
                    else println("There's nothing in here! Add some and organize them now!");
                    break;
                case 3:
                    menuDisplay5();
                    break;
                case 4:
                    File file = new File("assets/Accomplished"+fileNameNumber+".txt");
                    if (file.exists()){
                        Scanner sc = new Scanner(file);
                        println();
                        println("Here are your accomplish tasks: ");
                        println();
                        while(sc.hasNextLine()){
                            String read = sc.nextLine();
                            println(read);
                        }
                        sc.close();
                        println();
                    }
                    break;
                case 5:
                    menuDisplay1();
                    return;
            }
        } while (choice != 6);


    }

    private void menuDisplay3() throws ParseException, IOException {
        int choice;
        do {
            print(" __________________________\n");
            print("|         CATEGORY        |\n");
            print("|_________________________|\n");
            print("|        [1] Home         |\n");
            print("|     [2] Education       |\n");
            print("|       [3] Games         |\n");
            print("|      [4] Meeting        |\n");
            print("|        [5] Back         |\n");
            print("---------------------------\n");
            choice = readInt("Choice: ");


            switch (choice) {
                case 1:
                    menuDisplay4(new Home());
                    break;
                case 2:
                    menuDisplay4(new Education());
                    break;
                case 3:
                    menuDisplay4(new Games());
                    break;
                case 4:
                    menuDisplay4(new Meeting());
                    break;
                case 5:
                    menuDisplay2();
                    return;
            }
        } while (choice != 6);

    }

    private void menuDisplay4(Category tasks){
        int choice;
        String name, date,time;
    do {
        print(" _____________________________________________\n");
        print("|                  PRIORITY                   |\n");
        print("|_____________________________________________|\n");
        print("|               [1] High Priority             |\n");
        print("|               [2] Medium Priority           |\n");
        print("|               [3] Low Priority              |\n");
        print("|               [4] Back                      |\n");
        print("----------------------------------------------\n");

        choice = readInt("Choice: ");



        switch (choice) {
            case 1:
                name = readLine("Assign a task name: ");
                date = readLine("Please enter the deadline date in format MM/DD/YYYY : ");
                time = readLine("Please enter the time of deadline in 24-hour format HH:MM:SS : ");
                tasks.addHigh(fileNameNumber, name, date, time);
                break;
            case 2:
                name = readLine("Assign a task name: ");
                date = readLine("Please enter the deadline date in format MM/DD/YYYY : ");
                time = readLine("Please enter the time of deadline in 24-hour format HH:MM:SS : ");
                tasks.addMed(fileNameNumber, name, date, time);
                break;
            case 3:
                name = readLine("Assign a task name: ");
                date = readLine("Please enter the deadline date in format MM/DD/YYYY : ");
                time = readLine("Please enter the time of deadline in 24-hour format HH:MM:SS : ");
                tasks.addLow(fileNameNumber, name, date, time);
                break;
            case 4:
                return;

        }
    } while (choice != 5);

    }

    private void menuDisplay5() throws ParseException {
        int choice;
        sort = new Update();
        String[] name;
        Date[] date;
        long[] sec,hr,mins,days,yrs;

        do {
            print(" _________________________________________________\n");
            print("|              GENERATE A SCHEDULE!               |\n");
            print("|_________________________________________________|\n");
            print("|        [1] Sort according to Date & Time        |\n");
            print("|        [2] Sort according to Priority           |\n");
            print("|        [3] Sort according to Category           |\n");
            print("|   [4] Suggested order on accomplishing tasks    |\n");
            print("|                  [5] Back                       |\n");
            print("---------------------------------------------------\n");

            choice = readInt("Choice: ");
            println();
            println();


            switch (choice) {
                case 1:
                    if(sort.timeAndDate(fileNameNumber)){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("We sorted out your tasks according to time and date! ");
                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }
                            println();
                            println();
                        }
                    }
                    else println("There's nothing in here! Add some and organize them now!");

                    break;
                case 2:
                    if(!sort.priority(fileNameNumber,1) && !sort.priority(fileNameNumber,1.0)
                            && !sort.priority(fileNameNumber,'1')){
                        println("No plans added yet. Add some now!");
                    }
                    else{
                        println("We sorted out your tasks according to priority! ");
                        println();
                    }
                    if(sort.priority(fileNameNumber,1)){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("HIGH PRIORITY");
                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }
                            println();
                            println();
                        }
                    }

                    if(sort.priority(fileNameNumber,2.0)){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("MEDIUM PRIORITY");
                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }
                            println();
                            println();
                        }
                    }

                    if(sort.priority(fileNameNumber,'3')){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("LOW PRIORITY");
                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }
                            println();
                            println();
                        }
                    }


                    break;
                case 3:
                    if(!sort.category(fileNameNumber,1) && !sort.category(fileNameNumber, 1.0)
                            && !sort.category(fileNameNumber, '1')
                            && !sort.category(fileNameNumber, "1")){
                        println("No plans added yet. Add some now!");
                    }
                    else{
                        println("We sorted out your tasks according to category! ");
                        println();
                    }
                    if(sort.category(fileNameNumber,1)){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("HOME CATEGORY");
                        for(int i = 0 ; i<name.length ; i++){

                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }
                            println();
                            println();
                        }
                    }

                    if(sort.category(fileNameNumber, 1.0)){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("EDUCATION CATEGORY");
                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }
                            println();
                            println();
                        }

                    }

                    if(sort.category(fileNameNumber, '1')){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("GAMES CATEGORY");
                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }
                            println();
                            println();
                        }
                    }

                    if(sort.category(fileNameNumber, "1")){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("MEETING CATEGORY");
                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }
                            println();
                            println();
                        }

                    }
                    break;
                case 4:
                    if(!sort.timePriority(fileNameNumber,1) && !sort.timePriority(fileNameNumber,1.0)
                            && !sort.timePriority(fileNameNumber,'1')){
                        println("No plans added yet. Add some now!");
                    }
                    else{
                        println("We suggest you to do your tasks in this way order:  ");
                    }
                    if(sort.timePriority(fileNameNumber,1)){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("HIGH PRIORITY");
                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }
                            println();
                            println();
                        }
                    }

                    if(sort.timePriority(fileNameNumber,2.0)){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("MEDIUM PRIORITY");
                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }

                            println();
                            println();
                        }
                    }

                    if(sort.timePriority(fileNameNumber,'3')){
                        name = sort.getTaskName();
                        date = sort.getDates();
                        sec = sort.getSeconds();
                        mins = sort.getMinutes();
                        days = sort.getDays();
                        hr = sort.getHours();
                        yrs = sort.getYears();
                        println("LOW PRIORITY");
                        for(int i = 0 ; i<name.length ; i++){
                            println("Name of task: "+name[i]);
                            println("Due on: "+date[i]);
                            print("Remaining time: ");
                            if(yrs[i] > 0) print(yrs[i]+" years, ");
                            if(days[i] > 0)  print(days[i]+" days, ");
                            if(hr[i] > 0 ) print(hr[i]+" hours, ");
                            if(mins[i] > 0) print(mins[i]+" minutes, and ");
                            if(sec[i]> 0)  print(sec[i]+ " seconds.");
                            else if(yrs[i]<0 && days[i] < 0 && hr[i] < 0 && mins[i] < 0 &&sec[i]< 0 ) {
                                print("Past the deadline.");
                            }
                            println();
                            println();
                        }
                    }

                    break;
                case 5:
                    return;
            }
        } while (choice != '5');

    }

    public static void main(String[] args) {new TaskApp().start(args);}
}
