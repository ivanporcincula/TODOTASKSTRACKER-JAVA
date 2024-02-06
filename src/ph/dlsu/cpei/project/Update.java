package ph.dlsu.cpei.project;


import java.io.*;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Update {

    private String[] taskName;
    private Date[] dates;
    private long[] time;
    private long[] seconds;
    private long[] minutes;
    private long[] hours;
    private long[] days;
    private long[] years;

    public String[] getTaskName() {
        return taskName;
    }

    public Date[] getDates() {
        return dates;
    }

    public long[] getSeconds() {
        return seconds;
    }

    public long[] getMinutes() {
        return minutes;
    }

    public long[] getHours() {
        return hours;
    }

    public long[] getDays() {
        return days;
    }

    public long[] getYears() {
        return years;
    }

    public boolean timeAndDate(int filenum) throws ParseException {
        File highFile = new File("assets/High"+filenum+".txt");
        File mediumFile = new File("assets/Medium"+filenum+".txt");
        File lowFile = new File("assets/Low"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date tempDate;
        String tempTask;
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();
        if(highFile.exists()){
            try{
                Scanner sc1 = new Scanner(highFile);
                sc1.useDelimiter("[|]");
                while(sc1.hasNext()){
                    String getString = sc1.next();
                    storeTaskDT.add(getString);

                }
                sc1.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }


        if(mediumFile.exists()){
            try{
                Scanner sc2 = new Scanner(mediumFile);
                sc2.useDelimiter("[|]");
                while(sc2.hasNext()){
                    String getString = sc2.next();
                    storeTaskDT.add(getString);
                }
                sc2.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }


        if(lowFile.exists()){
            try{
                Scanner sc3 = new Scanner(lowFile);
                sc3.useDelimiter("[|]");
                while(sc3.hasNext()){
                    String getString = sc3.next();
                    storeTaskDT.add(getString);
                }
                sc3.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }



        if(!highFile.exists() && !mediumFile.exists() && !lowFile.exists()){
            return false;
        }

        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++){
            for(int j=i+1; j < arrayStoreTaskDT.length / 2 ; j++){
                if(dates[i].after(dates[j])){
                    tempTask = taskName[i];
                    taskName[i] = taskName[j];
                    taskName[j] = tempTask;

                    tempDate = dates[i];
                    dates[i] = dates[j];
                    dates[j] = tempDate;
                }

            }
        }

        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }

        return true;

    }

    public boolean category(int filenum, int home) throws ParseException {
        File homeFile = new File("assets/Home"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date tempDate;
        String tempTask;
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();
        if(!homeFile.exists()){
            return false;
        }
        if(homeFile.exists()){
            try{
                Scanner sc = new Scanner(homeFile);
                sc.useDelimiter("[|]");
                while(sc.hasNext()){
                    String getString = sc.next();
                    storeTaskDT.add(getString);
                }
                sc.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }


        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++){
            for(int j=i+1; j < arrayStoreTaskDT.length / 2 ; j++){
                if(dates[i].after(dates[j])){
                    tempTask = taskName[i];
                    taskName[i] = taskName[j];
                    taskName[j] = tempTask;

                    tempDate = dates[i];
                    dates[i] = dates[j];
                    dates[j] = tempDate;
                }

            }
        }

        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }

        return true;

    }

    public boolean category(int filenum, double education) throws ParseException {
        File educationFile = new File("assets/Education"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date tempDate;
        String tempTask;
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();
        if(!educationFile.exists()){
            return false;
        }
        if(educationFile.exists()){
            try{
                Scanner sc = new Scanner(educationFile);
                sc.useDelimiter("[|]");
                while(sc.hasNext()){
                    String getString = sc.next();
                    storeTaskDT.add(getString);
                }
                sc.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }


        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++){
            for(int j=i+1; j < arrayStoreTaskDT.length / 2 ; j++){
                if(dates[i].after(dates[j])){
                    tempTask = taskName[i];
                    taskName[i] = taskName[j];
                    taskName[j] = tempTask;

                    tempDate = dates[i];
                    dates[i] = dates[j];
                    dates[j] = tempDate;
                }

            }
        }

        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }

        return true;


    }

    public boolean category(int filenum, char games) throws ParseException {
        File gamesFile = new File("assets/Games"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date tempDate;
        String tempTask;
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();

        if(!gamesFile.exists()){
            return false;
        }
        if(gamesFile.exists()){
            try{
                Scanner sc = new Scanner(gamesFile);
                sc.useDelimiter("[|]");
                while(sc.hasNext()){
                    String getString = sc.next();
                    storeTaskDT.add(getString);
                }
                sc.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }


        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++){
            for(int j=i+1; j < arrayStoreTaskDT.length / 2 ; j++){
                if(dates[i].after(dates[j])){
                    tempTask = taskName[i];
                    taskName[i] = taskName[j];
                    taskName[j] = tempTask;

                    tempDate = dates[i];
                    dates[i] = dates[j];
                    dates[j] = tempDate;
                }

            }
        }

        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }
        return true;

    }

    public boolean category(int filenum, String meeting) throws ParseException {

        File meetingFile = new File("assets/Meeting"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date tempDate;
        String tempTask;
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();

        if(!meetingFile.exists()){
            return false;
        }
        if(meetingFile.exists()){
            try{
                Scanner sc = new Scanner(meetingFile);
                sc.useDelimiter("[|]");
                while(sc.hasNext()){
                    String getString = sc.next();
                    storeTaskDT.add(getString);
                }
                sc.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }


        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++){
            for(int j=i+1; j < arrayStoreTaskDT.length / 2 ; j++){
                if(dates[i].after(dates[j])){
                    tempTask = taskName[i];
                    taskName[i] = taskName[j];
                    taskName[j] = tempTask;

                    tempDate = dates[i];
                    dates[i] = dates[j];
                    dates[j] = tempDate;
                }

            }
        }

        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }

        return true;

    }

    public boolean timePriority(int filenum, int high) throws ParseException{
        File highFile = new File("assets/High"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date tempDate;
        String tempTask;
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();

        if(!highFile.exists()){
            return false;
        }
        if(highFile.exists()){
            try{
                Scanner sc = new Scanner(highFile);
                sc.useDelimiter("[|]");
                while(sc.hasNext()){
                    String getString = sc.next();
                    storeTaskDT.add(getString);
                }
                sc.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }


        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++){
            for(int j=i+1; j < arrayStoreTaskDT.length / 2 ; j++){
                if(dates[i].after(dates[j])){
                    tempTask = taskName[i];
                    taskName[i] = taskName[j];
                    taskName[j] = tempTask;

                    tempDate = dates[i];
                    dates[i] = dates[j];
                    dates[j] = tempDate;
                }

            }
        }

        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }

        return true;
    }

    public boolean timePriority(int filenum, double medium) throws ParseException{
        File medFile = new File("assets/Medium"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date tempDate;
        String tempTask;
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();

        if(!medFile.exists()){
            return false;
        }
        if(medFile.exists()){
            try{
                Scanner sc = new Scanner(medFile);
                sc.useDelimiter("[|]");
                while(sc.hasNext()){
                    String getString = sc.next();
                    storeTaskDT.add(getString);
                }
                sc.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }


        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++){
            for(int j=i+1; j < arrayStoreTaskDT.length / 2 ; j++){
                if(dates[i].after(dates[j])){
                    tempTask = taskName[i];
                    taskName[i] = taskName[j];
                    taskName[j] = tempTask;

                    tempDate = dates[i];
                    dates[i] = dates[j];
                    dates[j] = tempDate;
                }

            }
        }

        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }

        return true;
    }

    public boolean timePriority(int filenum, char low) throws ParseException{
        File lowFile = new File("assets/Low"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date tempDate;
        String tempTask;
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();

        if(!lowFile.exists()){
            return false;
        }
        if(lowFile.exists()){
            try{
                Scanner sc = new Scanner(lowFile);
                sc.useDelimiter("[|]");
                while(sc.hasNext()){
                    String getString = sc.next();
                    storeTaskDT.add(getString);
                }
                sc.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }


        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++){
            for(int j=i+1; j < arrayStoreTaskDT.length / 2 ; j++){
                if(dates[i].after(dates[j])){
                    tempTask = taskName[i];
                    taskName[i] = taskName[j];
                    taskName[j] = tempTask;

                    tempDate = dates[i];
                    dates[i] = dates[j];
                    dates[j] = tempDate;
                }

            }
        }

        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }

        return true;
    }

    public boolean priority(int filenum, int high) throws ParseException {
        File highFile = new File("assets/High"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();

        if(!highFile.exists()){
            return false;
        }
        if(highFile.exists()){
            try{
                Scanner sc = new Scanner(highFile);
                sc.useDelimiter("[|]");
                while(sc.hasNext()){
                    String getString = sc.next();
                    storeTaskDT.add(getString);
                }
                sc.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }

        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);


        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }

        return true;

    }

    public boolean priority(int filenum, double medium) throws ParseException{
        File medFile = new File("assets/Medium"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();

        if(!medFile.exists()){
            return false;
        }
        if(medFile.exists()){
            try{
                Scanner sc = new Scanner(medFile);
                sc.useDelimiter("[|]");
                while(sc.hasNext()){
                    String getString = sc.next();
                    storeTaskDT.add(getString);
                }
                sc.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }

        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);


        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }

        return true;

    }

    public boolean priority(int filenum, char low) throws ParseException{
        File lowFile = new File("assets/Low"+filenum+".txt");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date dateNow = new Date();

        ArrayList<String> storeTaskDT = new ArrayList<>();

        if(!lowFile.exists()){
            return false;
        }
        if(lowFile.exists()){
            try{
                Scanner sc = new Scanner(lowFile);
                sc.useDelimiter("[|]");
                while(sc.hasNext()){
                    String getString = sc.next();
                    storeTaskDT.add(getString);
                }
                sc.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }

        String[] arrayStoreTaskDT =  storeTaskDT.toArray(new String[storeTaskDT.size()]);
        taskName = new String[arrayStoreTaskDT.length / 2];
        String [] deadline = new String[arrayStoreTaskDT.length / 2];
        dates = new Date[arrayStoreTaskDT.length / 2];
        seconds = new long[arrayStoreTaskDT.length / 2];
        minutes = new long[arrayStoreTaskDT.length / 2];
        hours = new long[arrayStoreTaskDT.length / 2];
        days = new long[arrayStoreTaskDT.length / 2];
        years = new long[arrayStoreTaskDT.length / 2];
        time = new long[arrayStoreTaskDT.length / 2];

        for(int i=0, j=0;j<arrayStoreTaskDT.length;j+=2){
            taskName[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0, j=1;j<arrayStoreTaskDT.length;j+=2){
            deadline[i] = arrayStoreTaskDT[j];
            i++;
        }

        for(int i=0; i < arrayStoreTaskDT.length / 2 ; i++) dates[i] = sdf.parse(deadline[i]);


        for(int i = 0 ; i < arrayStoreTaskDT.length/2; i++){
            time[i] = dates[i].getTime() - dateNow.getTime();
            seconds[i] = TimeUnit.MILLISECONDS.toSeconds(time[i]) % 60;
            minutes[i] = TimeUnit.MILLISECONDS.toMinutes(time[i]) % 60;
            hours[i] = TimeUnit.MILLISECONDS.toHours(time[i]) % 24;
            days[i] = TimeUnit.MILLISECONDS.toDays(time[i]) % 365;
            years[i] = TimeUnit.MILLISECONDS.toDays(time[i]) / 365L;
        }

        return true;

    }

    public void remove(String remove, int filenum) throws IOException {
        int i,size1,size2,size3,size4,size5,size6,size7;
        StringBuilder newContent;
        StringBuilder newContent1;
        String newCont;
        String newCont1;
        Date timeNow = new Date();

        File highFile = new File("assets/High"+filenum+".txt");
        File mediumFile = new File("assets/Medium"+filenum+".txt");
        File lowFile = new File("assets/Low"+filenum+".txt");

        File homeFile = new File("assets/Home"+filenum+".txt");
        File educFile = new File("assets/Education"+filenum+".txt");
        File gamesFile = new File("assets/Games"+filenum+".txt");
        File meetingFile = new File("assets/Meeting"+filenum+".txt");

        ArrayList<String> highPriorityTasksDT = new ArrayList<>();
        ArrayList<String> medPriorityTasksDT = new ArrayList<>();
        ArrayList<String> lowPriorityTasksDT = new ArrayList<>();

        ArrayList<String> homePriorityTasksDT = new ArrayList<>();
        ArrayList<String> educPriorityTasksDT = new ArrayList<>();
        ArrayList<String> gamesPriorityTasksDT = new ArrayList<>();
        ArrayList<String> meetingPriorityTasksDT = new ArrayList<>();

        if(highFile.exists()){
            try{
                Scanner sc1 = new Scanner(highFile);
                sc1.useDelimiter("[|]");
                while(sc1.hasNext()){
                    String getString = sc1.next();
                    highPriorityTasksDT.add(getString);

                }
                sc1.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if(mediumFile.exists()){
            try{
                Scanner sc2 = new Scanner(mediumFile);
                sc2.useDelimiter("[|]");
                while(sc2.hasNext()){
                    String getString = sc2.next();
                    medPriorityTasksDT.add(getString);
                }
                sc2.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if(lowFile.exists()){
            try{
                Scanner sc3 = new Scanner(lowFile);
                sc3.useDelimiter("[|]");
                while(sc3.hasNext()){
                    String getString = sc3.next();
                    lowPriorityTasksDT.add(getString);
                }
                sc3.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if(homeFile.exists()){
            try{
                Scanner sc4 = new Scanner(homeFile);
                sc4.useDelimiter("[|]");
                while(sc4.hasNext()){
                    String getString = sc4.next();
                    homePriorityTasksDT.add(getString);
                }
                sc4.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if(educFile.exists()){
            try{
                Scanner sc5 = new Scanner(educFile);
                sc5.useDelimiter("[|]");
                while(sc5.hasNext()){
                    String getString = sc5.next();
                    educPriorityTasksDT.add(getString);
                }
                sc5.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if(gamesFile.exists()){
            try{
                Scanner sc6 = new Scanner(gamesFile);
                sc6.useDelimiter("[|]");
                while(sc6.hasNext()){
                    String getString = sc6.next();
                    gamesPriorityTasksDT.add(getString);
                }
                sc6.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if(meetingFile.exists()){
            try{
                Scanner sc7 = new Scanner(meetingFile);
                sc7.useDelimiter("[|]");
                while(sc7.hasNext()){
                    String getString = sc7.next();
                    meetingPriorityTasksDT.add(getString);
                }
                sc7.close();

            } catch(IOException e){
                e.printStackTrace();
            }
        }

        size1 = highPriorityTasksDT.size();

        i=0;
        for(String f : highPriorityTasksDT){
            if(f.equals(remove)){
                highPriorityTasksDT.remove(i);
                if(i>0) highPriorityTasksDT.remove(i-1);
                if(i==0) highPriorityTasksDT.remove(i);
                break;
            }

            i++;
        }



        if(highPriorityTasksDT.size() < size1){
            newContent= new StringBuilder();
            i=0;
            for(String f : highPriorityTasksDT){
                newContent.append(f);
                if(i != highPriorityTasksDT.size()-1){ newContent.append("|"); }
                i++;
            }
            newCont=newContent.toString();
            FileWriter createFile = new FileWriter("assets/High"+filenum+".txt");
            createFile.write(newCont);
            createFile.close();
        }


        size2 = medPriorityTasksDT.size();

        i=0;
        for(String f : medPriorityTasksDT){
            if(f.equals(remove)){
                medPriorityTasksDT.remove(i);
                if(i>0) medPriorityTasksDT.remove(i-1);
                if(i==0) medPriorityTasksDT.remove(i);
                break;
            }
            i++;
        }



        if(medPriorityTasksDT.size() < size2){
            newContent= new StringBuilder();
            i=0;
            for(String f : medPriorityTasksDT){
                newContent.append(f);
                if(i != medPriorityTasksDT.size()-1){ newContent.append("|"); }
                i++;
            }
            newCont=newContent.toString();
            FileWriter createFile = new FileWriter("assets/Medium"+filenum+".txt");
            createFile.write(newCont);
            createFile.close();
        }

        size3 = lowPriorityTasksDT.size();
        i=0;
        for(String f : lowPriorityTasksDT){
            if(f.equals(remove)){
                lowPriorityTasksDT.remove(i);
                if(i>0) lowPriorityTasksDT.remove(i-1);
                if(i==0) lowPriorityTasksDT.remove(i);
                break;
            }
            i++;
        }



        if(lowPriorityTasksDT.size() < size3){
            newContent= new StringBuilder();
            i=0;
            for(String f : lowPriorityTasksDT){
                newContent.append(f);
                if(i != lowPriorityTasksDT.size()-1){ newContent.append("|"); }
                i++;
            }

            newCont=newContent.toString();
            FileWriter createFile = new FileWriter("assets/Low"+filenum+".txt");
            createFile.write(newCont);
            createFile.close();
        }



        size4 = homePriorityTasksDT.size();

        i=0;
        for(String f : homePriorityTasksDT){
            if(f.equals(remove)){
                homePriorityTasksDT.remove(i);
                if(i>0) homePriorityTasksDT.remove(i-1);
                if(i==0) homePriorityTasksDT.remove(i);
                break;
            }
            i++;
        }



        if(homePriorityTasksDT.size() < size4){
            newContent1= new StringBuilder();
            i=0;
            for(String f : homePriorityTasksDT){
                newContent1.append(f);
                if(i != homePriorityTasksDT.size()-1){ newContent1.append("|"); }
                i++;
            }

            newCont1=newContent1.toString();
            FileWriter createFile = new FileWriter("assets/Home"+filenum+".txt");
            createFile.write(newCont1);
            createFile.close();
        }

        size5 = educPriorityTasksDT.size();

        i=0;

        for(String f : educPriorityTasksDT){
            if(f.equals(remove)){
                educPriorityTasksDT.remove(i);
                if(i>0) educPriorityTasksDT.remove(i-1);
                if(i==0) educPriorityTasksDT.remove(i);
                break;
            }
            i++;

        }



        if(educPriorityTasksDT.size() < size5){
            newContent1= new StringBuilder();
            i=0;
            for(String f : educPriorityTasksDT){
                newContent1.append(f);
                if(i != educPriorityTasksDT.size()-1){ newContent1.append("|"); }
                i++;
            }

            newCont1=newContent1.toString();
            FileWriter createFile = new FileWriter("assets/Education"+filenum+".txt");
            createFile.write(newCont1);
            createFile.close();
        }

        size6 = gamesPriorityTasksDT.size();
        i=0;
        for(String f : gamesPriorityTasksDT){
            if(f.equals(remove)){
                gamesPriorityTasksDT.remove(i);
                if(i>0) gamesPriorityTasksDT.remove(i-1);
                if(i==0) gamesPriorityTasksDT.remove(i);
                break;
            }
            i++;
        }


        if(gamesPriorityTasksDT.size() < size6){
            newContent1= new StringBuilder();
            i=0;
            for(String f : gamesPriorityTasksDT){
                newContent1.append(f);
                if(i != gamesPriorityTasksDT.size()-1){ newContent1.append("|"); }
                i++;
            }
            newCont1=newContent1.toString();
            FileWriter createFile = new FileWriter("assets/Games"+filenum+".txt");
            createFile.write(newCont1);
            createFile.close();
        }



        size7 = meetingPriorityTasksDT.size();
        i=0;
        for(String f : meetingPriorityTasksDT){
            if(f.equals(remove)){
                meetingPriorityTasksDT.remove(i);
                if(i>0) meetingPriorityTasksDT.remove(i-1);
                if(i==0) meetingPriorityTasksDT.remove(i);
                break;
            }
            i++;
        }

        if(meetingPriorityTasksDT.size() < size7){
            newContent1= new StringBuilder();
            i=0;
            for(String f : meetingPriorityTasksDT){
                newContent1.append(f);
                if(i != meetingPriorityTasksDT.size()-1){ newContent1.append("|"); }
                i++;
            }
            newCont1=newContent1.toString();
            FileWriter createFile = new FileWriter("assets/Meeting"+filenum+".txt");
            createFile.write(newCont1);
            createFile.close();
        }

        FileWriter accomplish = new FileWriter("assets/Accomplished"+filenum+".txt", true);
        accomplish.write("Accomplished task name: " + remove+"\n");
        accomplish.write("Date and time accomplished: " + timeNow +"\n");
        accomplish.close();

    }

}
