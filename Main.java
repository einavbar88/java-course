package school.java;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

class Main{
    public static void main(String[] args) {
        try {
            ArrayList<String> first = new ArrayList<String>();
            ArrayList<String> second = new ArrayList<String>();
            ArrayList<String> third = new ArrayList<String>();
            ArrayList<String> fourth = new ArrayList<String>();

            File teamsFile = new File("school/java/teams.txt");
            Scanner myReader = new Scanner(teamsFile);
            File newFile = new File("school/java/ranks.txt");
            newFile.createNewFile();
            FileWriter myWriter = new FileWriter("school/java/ranks.txt");

            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              String[] splittedData = data.split(" ");
              switch(splittedData[1]){
                case "1":
                    first.add(splittedData[0]);
                    break;
                case "2": 
                    second.add(splittedData[0]);
                    break;
                case "3":
                    third.add(splittedData[0]);
                    break;
                case "4":
                    fourth.add(splittedData[0]);
                    break;
                default:
                    break;
              }
            }
            myWriter.write("1 ");
            
            first.forEach(team ->{
                try{
                myWriter.write(team + " ");
                } catch (Exception e) {
                    System.out.println("Error");
                }
            });
            myWriter.write("\n2 ");
            second.forEach(team ->{
                try{
                myWriter.write(team + " ");
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }); 
            myWriter.write("\n3 ");
            third.forEach(team ->{
                try{
                myWriter.write(team + " ");
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }); 
            myWriter.write("\n4 ");
            fourth.forEach(team ->{
                try{
                myWriter.write(team + " ");
                } catch (Exception e) {
                    System.out.println("Error");
                }
            });

            myReader.close();
            myWriter.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}