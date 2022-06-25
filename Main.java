//**FILL OUT**Group Member Names
//1.Siyun Tan
//2.Lasya Erukulla

//Brief Description
  /*This is a program helps the user to create their new account. The program will read and record the original file firstly 
  and them prompt user to input their new account info to the datafile. Finally, the program will rearrange the file in alphabetically.
  */

//Begin pseudocode here:
/*
Part 1:
read file
if file exists, save file info as arrays 
  - string array for Names
  - long array for Followers
  - long array for Likes
  - long array for revenues
  then get user data and add to the txt file
  -Ask the user for num accounts to input
    1. user input of usernames
      - do{
          -input user name
          - catch it
          - check for conditions
              -length less than 20
              -has lower case letters, numbers,periods and underscore(Methods)
                -isValidEntry,hasLowerCase and hasValidChar
        }while(length>20 || validEntry==false) 
    2. user input of numFollowers
      - do{
          -input followers
          - catch it
          - check for negative input
              - method(hasNegativeNum)
        }while(!hasNegativeNum) 
    3. user input of numLikes
      - do{
          -input likes
          - catch it
          - check for negative input
              - method(hasNegativeNum)
        }while(!hasNegativeNum)
    4. user input of adRevenue
      - do{
          -input adRevenue
          - catch it
          - check for negative input
              - method(hasNegativeNum)
        }while(!hasNegativeNum)
  Copy the user inputs to the original arrays that contain the exitisng users
    - use java.util.Arrays library 
    - input them to the ending of the correspondent arrays(Names, Followers, Likes,Revenues) 
  Sort the names array alphabetically using a for loop (search sort method)
  - for(k;k<numUsers<k++){
      for(i=k+1;k<numUsers<k++){
        if(names[k].compareTo(names[i])>0){
          -swap names value
          -swap foolowers value
          -swap likes value
          -swap revenues value
        }
      }
    }
  - Use pw.write to dataFile.txt
  
Part 2
  - write to markdown
  - use try{
    //format the hearder with hashtag
    //format the num of users with hashtags + the numUsers(includes numUserAccounts)
    //format it into table
    //double totalRevenue
    for (int m = 0; m < numUsers; m++){
      pr.print(names[m]+ "|");
      if(followers[m]>=1000000000){
            //create double and then divide/1000000000;
            pr.print(df.format(temp)+"B"+"|");
          }else if(followers[m]>=1000000){
            //create double and then divide/1000000;
            pr.print(df.format(temp)+"M"+"|");
          }else if(followers[m]>=1000){
            //create double and then divide/1000;
            pr.print(df.format(temp)+"K"+"|");
          }else{
            pr.print(followers[m]+"|");
          }

          if(likes[m]>=1000000000){
            //create double and then divide/1000000000;
            pr.print(df.format(temp)+"B"+"|");
          }else if(likes[m]>=1000000){
            //create double and then divide/1000000;
            pr.print(df.format(temp)+"M"+"|");
          }else if(likes[m]>=1000){
            //create double and then divide/1000;
            pr.print(df.format(temp)+"K"+"|");
          }else{
            pr.print(likes[m]+"|");
          }
          
          pr.printf("%.2f",revenues[m]);
          //new line
          totalRevenue += revenues[m];
      }
    }
    pr.printf("`Total Ad Revenue: $%20.2f`",totalRevenue);
  }catch(exception)
  end with else statment: print Thank you!
*/

//Import java scanner, fliewriter and io from the lab
import java.util.Scanner;
import java.io.*;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Main {
  //write method for checking other symbols,lower case and numbers
  public static boolean isValidEntry(String name){
    boolean hasSymbol= true;
    if(hasLowerCase(name)||hasValidChar(name)){
      hasSymbol= true;
    }else{
      hasSymbol=false;
    }  
    return hasSymbol;
  }
  
  //This method is testing if there is a uppercase
  public static boolean hasLowerCase(String name){
    char currentChar = ' ';
    boolean hasLowerCase = true;
    String invalidChar= "`~!@#$%^&*()\\-=+\\\\|\\[{\\]};:'\",<>/?";
    for(int i=0;i<name.length();i++){
      currentChar=name.charAt(i);
      if(Character.isUpperCase(currentChar)|| invalidChar.contains(String.valueOf(currentChar))){
        hasLowerCase = false;
        break;
      }
    }
    return hasLowerCase;
  }

  //This method is testing if it has any symbols
  public static boolean hasValidChar(String name){
    Pattern pattern = Pattern.compile(".*[_.].*");
    Matcher matcher = pattern.matcher(name);
    boolean matchFound = matcher.find();
    if(matchFound){
      return true;
    }else{
      return false;
    }
  }

  //check for negative numbers
  public static boolean hasNegativeNum(double num){
    if (num<0) 
      return false;
    else 
      return true;
  }
  
  public static void main(String[] args) {
    //create and intialize variables
    DecimalFormat df = new DecimalFormat("##.#");
    int count = 0;//The lines in datafile.txt
    
    try{
      //Read the file
      FileReader fr = new FileReader("dataFile.txt");
      BufferedReader br = new BufferedReader(fr);
      String line = br.readLine();			
			//Count the number of lines in datafile
      while (line != null){
				count++;
				line = br.readLine();
			}
        //br.close();
    }catch(IOException e){
			System.out.println("Error reading from file.");
		}

    //number of already existing users
    int numUsers = count/4;

    //declare variables to store dataFile.txt contents
    String names[]= new String[numUsers];
    long followers[]=new long[numUsers];
    long likes[]= new long[numUsers];
    double revenues[]= new double[numUsers];

    try{
			FileReader fr = new FileReader("dataFile.txt");  
			BufferedReader br = new BufferedReader(fr);  
			int i;			
			for(i = 0; i <numUsers;i++){
        names[i] = br.readLine();			
				followers[i] = Long.parseLong(br.readLine());
       	likes[i] = Long.parseLong(br.readLine());
        revenues[i]= Double.parseDouble(br.readLine());
			}
		}catch(IOException e){
			System.out.println("Error reading from file.");
		}catch(NumberFormatException e){
			System.out.println("Error converting.");
		}
   
    //Prompt user to enter their account info, if "no", exit the program
    System.out.println("Welcome to the Filp-Flop!\nWould you like to create an account?");
    String userAns = In.getString();


    if(userAns.toUpperCase().equals("Y")|| userAns.toUpperCase().equals("YES")){
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      //Prompt user to input the number of accounts they want to create
      System.out.println("How many accounts do you want to create?");
      int userNumAccounts = In.getInt();
      
      //declare user inputed varibales
      String newUserName[]= new String[userNumAccounts];
      long numFollowers[] = new long[userNumAccounts];
      long numLikes[] = new long[userNumAccounts];
      double adRevenue[] = new double[userNumAccounts];
      
      try{
        FileWriter wr = new FileWriter("dataFile.txt");
        PrintWriter pw= new PrintWriter(wr);
        //get user info
        for(int j = 0; j < userNumAccounts;j++){
          boolean validEntry;
          do{
            System.out.println("Enter a username: ");
            newUserName[j] = br.readLine();
            //check for length + valid entry
            validEntry = isValidEntry(newUserName[j]);
            if(newUserName[j].length()>20 || validEntry == false){
              System.out.println("The user name does not meet requirements.");
            }
          }while(newUserName[j].length()>20 || validEntry == false);  
          //Copy USER NAME to NAMES ARRAY
          names = Arrays.copyOf(names, names.length + 1);
          names[names.length - 1] = newUserName[j];

          //enter followers + check for negative input
          do{
            System.out.println("Enter the number of followers:");
            numFollowers[j]=Long.parseLong(br.readLine());
            if (!hasNegativeNum(numFollowers[j])){
              System.out.println("Invalid input...RE-ENTER");
            }
          }while(!hasNegativeNum(numFollowers[j]));
          //copy to NUM FOLLOWERS to FOLLOWERS array
          followers = Arrays.copyOf(followers,followers.length+1);
          followers[followers.length-1]=numFollowers[j];
            
          //enter likes + check for negative input
          do{
            System.out.println("Enter the number of likes:");
            numLikes[j]=Long.parseLong(br.readLine());
            if(!hasNegativeNum(numLikes[j])){
              System.out.println("Invalid input...RE-ENTER");
            }
          }while(!hasNegativeNum(numLikes[j]));
          //copy to NUM LIKES to LIKES array 
          likes=Arrays.copyOf(likes,likes.length+1);
          likes[likes.length-1]=numLikes[j];

          //enter revenue + check for negative input
          do{
            System.out.println("Enter the ad revenue: ");
            adRevenue[j]=Double.parseDouble(br.readLine());
            if(!hasNegativeNum(adRevenue[j])){
              System.out.println("Invalid input...RE-ENTER");
            }
          }while(!hasNegativeNum(adRevenue[j]));     
          //copy to AD REVENUE to REVENUES array
          revenues = Arrays.copyOf(revenues, revenues.length+1);
          revenues[revenues.length-1]=adRevenue[j];
          
        }//end of for loop  
            
        //existing numUsers length + userNumAccounts length 
        numUsers += userNumAccounts;

        //alphabetically sort users and write to dataFile.txt  
        for (int k = 0;k < numUsers;k++){
          for(int i=k+1;i<numUsers;i++){
            if(names[k].compareTo(names[i])>0){
              //swap user names alphabetically
              String temp = names[k];
              names[k] = names[i];
              names[i] = temp;
              //swap followers along with usernames    
              long temp1= followers[k];
              followers[k] = followers[i];
              followers[i] = temp1;
              //swap likes along with usernames 
              long temp2= likes[k];
              likes[k] = likes[i];
              likes[i] = temp2;
              //swap revenue along with usernames 
              double temp3= revenues[k];
              revenues[k]= revenues[i];
              revenues[i]= temp3;
            }
          }
          //Write the arrays to dataFile.txt
          pw.println(names[k]);
          pw.println(followers[k]);
          pw.println(likes[k]);
          pw.println(revenues[k]);
        }
          //Close the file writer
          pw.close();      
      }catch(IOException e){
        System.out.println("Error...Please input correctly.");
      }
      
      //Format the markdown file
      try{
        FileWriter fw= new FileWriter("userStats.md");
        PrintWriter pr= new PrintWriter(fw);

        pr.println("# "+"Flip-Flop");
        pr.println("## Total Number of Flip-Flop users: "+numUsers);
        pr.println("User Names|Followers|Likes|Ad Revenue($)");
        pr.println("----------|---------|-----|-------------");
        double totalRevenue=0;
        //for each user print out table formatted output
        for (int m = 0; m < numUsers; m++){
          pr.print(names[m]+ "|");
          //followers size and formatted output
          if(followers[m]>=1000000000){
            double temp=(double)followers[m]/1000000000;
            pr.print(df.format(temp)+"B"+"|");
          }else if(followers[m]>=1000000){
            double temp=(double)followers[m]/1000000;
            pr.print(df.format(temp)+"M"+"|");
          }else if(followers[m]>=1000){
            double temp=(double)followers[m]/1000;
            pr.print(df.format(temp)+"K"+"|");
          }else{
            pr.print(followers[m]+"|");
          }

          //likes size and formatted output
          if(likes[m]>=1000000000){
            double temp= (double) likes[m]/1000000000;
            pr.print(df.format(temp)+"B"+"|");
          }else if(likes[m]>=1000000){
            double temp= (double) likes[m]/1000000;
            pr.print(df.format(temp)+"M"+"|");
          }else if(likes[m]>=1000){
            double temp= (double) likes[m]/1000;
            pr.print(df.format(temp)+"K"+"|");
          }else{
            pr.print(likes[m]+"|");
          }
          //output revenue
          pr.printf("%.2f",revenues[m]);
          pr.println();
          totalRevenue += revenues[m];//increase total value
        }
        //print formatted total revenue
        pr.printf("`Total Ad Revenue: $%20.2f`",totalRevenue);
        pr.close();
      }catch(IOException e){
        System.out.println("Error...Please input correctly.");
      }
    }else{
      System.out.println("Thank you for visiting Flip-Flop!");
    } 

  }//end public static void main
}//end class Main