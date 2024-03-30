import java.io.*;
import java.util.Scanner;
public class Driver {
    public static void main(String[] args) throws Exception{

        java.io.File file = new java.io.File("src\\HW06\\TestMembersClean.csv");

        Scanner readCSV = new Scanner(file);
        readCSV.useDelimiter(",");
        while(readCSV.hasNext()){
            String parser = readCSV.next();
//            if (parser.contains("Warrior")){
//                System.out.println("Warrior");
//            } else if (parser.contains("Mage")){
//                System.out.println("Mage");
//            }
            //System.out.println(readCSV.nextLine());
           if(parser.contains("Name: ")){
               System.out.println(parser.substring(7));
           }

        }
        readCSV.close();
    }
}
