import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.io.File;
import java.util.Collection;
import java.util.Scanner;

public class Party {
    public static void main(String[] args) throws FileNotFoundException, QuestNotFoundException {
        //recruitParty("src\\HW06\\TestMembersClean.csv");
        Warrior testWarrior1 = new Warrior("John", 100, 9, "m4a1", 5);
        Warrior warrior2 = new Warrior("Josh", 4, 3, "dager", 9);
        Warrior warrior3 = new Warrior("Almond", 57, 10, "Tree", 3);

        ArrayList<PartyMember> testArray = new ArrayList<>();
        testArray.add(testWarrior1);
        testArray.add(warrior2);
        testArray.add(warrior3);

        partyRoster("src\\HW06\\TestParty.csv", testArray);
       // partyQuestLevel(recruitParty("src\\HW06\\TestMembersClean.csv"));

        getQuest("Runethorn Shrine", recruitParty("src\\HW06\\TestMembersClean.csv"));

    }
    public static ArrayList<PartyMember> recruitParty (String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner readCSV = new Scanner(file);
        if (!file.exists()){
            throw new FileNotFoundException();
        }
        ArrayList<PartyMember> PARTY = new ArrayList<>();

        while(readCSV.hasNextLine()){
            String lineParser = readCSV.nextLine();
            PARTY.add(processInfo(lineParser));
        }
        return PARTY;
    }

    private static PartyMember processInfo (String characterToProcess) throws InvalidPartyMemberException {
        //where to put exceptions
        String [] splitString = characterToProcess.split(", ");
        String name = splitString[1].substring(6);
        int health = Integer.parseInt(splitString[2].substring(8));
        int baseAttack = Integer.parseInt(splitString[3].substring(13));
        if (splitString[0].contains("Warrior")) {
            String weapon = splitString[4].substring(8);
            int armorClass = Integer.parseInt(splitString[5].substring(13));
            return new Warrior(name, health, baseAttack, weapon, armorClass);
        } else if (splitString[0].contains("Mage")) {
            int spellAttack = Integer.parseInt(splitString[4].substring(14));
            int armorClass = Integer.parseInt(splitString[5].substring(13));
            return new Mage(name, health, baseAttack, spellAttack, armorClass);
        }
        throw new InvalidPartyMemberException();
    }

    public static boolean partyRoster(String filePath, ArrayList<PartyMember> partyMembers) throws FileNotFoundException {
        ArrayList<PartyMember> tempArrayList = new ArrayList<>();
        File file = new File(filePath);
        Scanner readCSV = new Scanner(file);
        if (!file.exists()){
            throw new FileNotFoundException();
        }
        try{
           // recruitParty("src\\HW06\\TestMembersClean.csv");
            tempArrayList = recruitParty("src\\HW06\\TestMembersClean.csv");
            tempArrayList.addAll(partyMembers);
            PrintWriter output = new PrintWriter(filePath);
            for (PartyMember i : tempArrayList){
                output.println(i.toString());
            }
            output.close();
            return true;
        } catch (FileNotFoundException exception){
            throw new FileNotFoundException("Try again, lol");
        }
    }
    public static boolean getQuest(String questToSelect, ArrayList<PartyMember> partyMembers) throws FileNotFoundException, QuestNotFoundException {
        int partyQuestLevel = 0;
        int questIndex = 0;
        File file = new File("src\\HW06\\quest.csv");
        if (file == null || !file.exists()){
            return false;
        }
        Scanner scanLine = new Scanner(file);
        while (scanLine.hasNextLine()){

            String line = scanLine.nextLine();

            if (line.contains(questToSelect)){
                partyQuestLevel = partyQuestLevel(partyMembers);
                String [] splitStringColon = line.split(": ");
                String questName = splitStringColon[0];
                String [] splitStringComma = splitStringColon[1].split(", ");
                int questLevel = Integer.parseInt(splitStringComma[0]);
                int reward = Integer.parseInt(splitStringComma[1]);
                if (questLevel < partyQuestLevel){
                    System.out.println("Success! Your party gained " + reward + " coins. This calls for a trip to the Tavern!");
                    return removedQuest(questName);
                } else if (questLevel > partyQuestLevel){
                    System.out.println("Failure... Your party was defeated. Better Luck Next Time!");
                    return false;
                }
            }
        }
        return true;
    }
    private static int partyQuestLevel(ArrayList<PartyMember> partyMembers){
        if (partyMembers == null) return -1;
        int totalQuestLevel = 0;
        for (PartyMember i :partyMembers){
            totalQuestLevel += i.questLevel();
        }
        return totalQuestLevel;
    }

    private static boolean removedQuest (String deleteQuest) throws QuestNotFoundException, FileNotFoundException {
        String tempFile = "temp.txt";
        File oldFile = new File("src\\HW06\\quest.csv");
        File newFile = new File(tempFile);

        ArrayList<String> quests = new ArrayList<>();

        try {
            Scanner lineScanner = new Scanner(oldFile);
            while (lineScanner.hasNextLine()){
                String questLine = lineScanner.nextLine();
                if (!questLine.contains(deleteQuest)){
                    quests.add(questLine);
                }

            }
            PrintWriter output = new PrintWriter(oldFile);
            for (String i : quests){
                output.println(i);
            }
            output.close();
            return true;
        } catch (FileNotFoundException F){
            throw new FileNotFoundException("File not found.");
        }
    }
}
