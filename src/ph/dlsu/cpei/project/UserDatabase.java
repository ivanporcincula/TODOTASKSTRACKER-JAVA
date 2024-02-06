package ph.dlsu.cpei.project;

import java.io.*;
import java.util.Scanner;

public class UserDatabase {

    private int counter=0;
    private int limit;
    private final String fileName = "assets/accountDatabase.txt";
    private String oldCount;
    private String newCount;

    public void create(String user, String password){

        File accountDatabase = new File (fileName);

       // boolean exists = accountDatabase.exists();
        if(accountDatabase.exists()){

            try{
                Scanner intRead = new Scanner(accountDatabase);
                counter = intRead.nextInt();
                intRead.close();
                oldCount = Integer.toString(counter);
                counter+=1;
                newCount = Integer.toString(counter);
            }catch(IOException e){
                e.printStackTrace();
            }
            try{
                StringBuilder oldAccount= new StringBuilder();
                StringBuilder newAccount = new StringBuilder();
                BufferedReader readFile = new BufferedReader(new FileReader(accountDatabase));
                String wholeContent = readFile.readLine();
                System.out.println(wholeContent);

                while(wholeContent !=null){
                    oldAccount.append(wholeContent).append("\n");
                    wholeContent=readFile.readLine();
                }
                readFile.close();
                String oldContent = oldAccount.toString();
                newAccount.append(oldContent).append(user).append('|').append(password).append("\n");
                String newContent = newAccount.toString();
                newContent = newContent.replaceFirst(oldCount,newCount);
                FileWriter addNew= new FileWriter(fileName);

                addNew.write(newContent);

                addNew.close();
            } catch(IOException e){
                e.printStackTrace();
            }

        }

        else{
            try{
                FileWriter createFile= new FileWriter(fileName);
                counter+=1;
                createFile.write(Integer.toString(counter));
                createFile.write("\n"+user+'|');
                createFile.write(password+"\n");
                createFile.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }


    }



    public String login(String user, String password){
        int i=0;

        String enteredData = user +"|"+password;

        File accountDatabase = new File (fileName);
        try{
            Scanner intRead = new Scanner(accountDatabase);
            counter = intRead.nextInt();
            intRead.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            String[] accountData;
            BufferedReader readFile = new BufferedReader(new FileReader(accountDatabase));
            String wholeContent = readFile.readLine();
            accountData = new String[counter];

            while(wholeContent != null){

                wholeContent = readFile.readLine();
                if(wholeContent == null){
                    break;
                }
                accountData[i] = wholeContent;
                i++;
            }
            readFile.close();
            i=0;
            for(String data : accountData){
                i++;
                if(data.equals(enteredData)){
                    this.limit = i;
                    return "Login successful!";
                }

            }

        } catch(IOException e){
            e.printStackTrace();
        }

        return "Login failed. Invalid username or password. Try again.";

    }
    public int success(){
        return limit;
    }





}
