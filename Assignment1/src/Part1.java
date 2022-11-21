// Einav Bar - 204552632
// Lior Poterman - 315368035

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        try {
            ArrayList<String> first = new ArrayList<String>();
            ArrayList<String> second = new ArrayList<String>();
            ArrayList<String> third = new ArrayList<String>();
            ArrayList<String> fourth = new ArrayList<String>();

            File teamsFile = new File("./teams.txt");
            Scanner reader = new Scanner(teamsFile);
            File newFile = new File("./ranks.txt");
            newFile.createNewFile();
            FileWriter writer = new FileWriter("./ranks.txt");

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
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
            writer.write("1 ");
            prepareText(first, writer);
            writer.write("\n2 ");
            prepareText(second, writer);
            writer.write("\n3 ");
            prepareText(third, writer);
            writer.write("\n4 ");
            prepareText(fourth, writer);

            reader.close();
            writer.close();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    public static void prepareText(ArrayList<String> list, Writer writer){
        list.forEach(team ->{
            try{
                writer.write(team + " ");
            }catch (Exception e){
                System.out.println("Error");
            }
        });
    }
}