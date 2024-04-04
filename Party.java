import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * The Party class holds various public static that will let you read and write to the database.
 * @author mpalucci3
 * @version 1.1
 */
public class Party {
    /**
     * Main method, creates 3 Warrior objects and 3 Mage classes,
     * objects written to the TestParty.csv file. A quest is searched and the total party's quest level
     * is compared to the quest level.
     * @param args String [] args
     * @throws FileNotFoundException exception which is thrown if a file is erroneous
     * @throws QuestNotFoundException exception which is thrown if a quest is not found
     */
    public static void main(String[] args) throws FileNotFoundException, QuestNotFoundException {
        String partyFilePath = "TestParty.csv";

        ArrayList<PartyMember> partyMembers = new ArrayList<PartyMember>();

        Warrior warrior1 = new Warrior("John", 100, 9, "M4A1", 5);
        Warrior warrior2 = new Warrior("Josh", 4, 3, "Dagger", 9);
        Warrior warrior3 = new Warrior("Almond", 57, 10, "Tree", 3);
        Mage mage1 = new Mage("Borat", 100, 50, 60, 1);
        Mage mage2 = new Mage("Yoda", 50, 2, 3, 1);
        Mage mage3 = new Mage("Azamat", 100, 30, 49, 50);

        partyMembers.add(warrior1);
        partyMembers.add(warrior2);
        partyMembers.add(warrior3);
        partyMembers.add(mage1);
        partyMembers.add(mage2);
        partyMembers.add(mage3);

        partyMembers.addAll(recruitParty(partyFilePath));

        partyRoster(partyFilePath, partyMembers);

        for (PartyMember i : partyMembers) {
            System.out.println(i);
        }
        getQuest("End Game", partyMembers);
    }

    /**
     * Method that takes in a CSV file and parses each party member from each line and creates a Warrior or Mage object.
     * @param filePath String representing the file name to read from
     * @return ArrayList of type PartyMember representing the recruited party members
     * @throws FileNotFoundException exception which is thrown if the file is erroneous
     */
    public static ArrayList<PartyMember> recruitParty(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<PartyMember> partyArr = new ArrayList<>();
        try {
            Scanner readCSV = new Scanner(file);
            while (readCSV.hasNextLine()) {
                String lineParser = readCSV.nextLine();
                partyArr.add(processInfo(lineParser));
            }
            return partyArr;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }

    }

    private static PartyMember processInfo(String characterToProcess) throws InvalidPartyMemberException {
        //where to put exceptions
        String[] splitString = characterToProcess.split(", ");
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

    /**
     * Method which writes the roster to a CSV file.
     * @param filePath String representing the file to write to
     * @param partyMembers ArrayList of type PartyMember representing that will write to the file
     * @return boolean representing whether the write was successful
     * @throws FileNotFoundException exception which is thrown if the file is erroneous
     */
    public static boolean partyRoster(String filePath, ArrayList<PartyMember> partyMembers)
            throws FileNotFoundException {
        File file = new File(filePath);

        try {
            PrintWriter output = new PrintWriter(filePath);
            for (PartyMember i : partyMembers) {
                output.println(i.toString());
            }
            output.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method which sends out the assembled party on a quest.
     * @param questToSelect String representing the name of the quest to select
     * @param partyMembers ArrayList of type PartyMember representing the roster to send to the quest
     * @return boolean representing whether the party has succeeded
     * @throws FileNotFoundException exception which is thrown if the file is not quest.csv
     * @throws QuestNotFoundException exception which is thrown if the quest is not found
     */
    public static boolean getQuest(String questToSelect, ArrayList<PartyMember> partyMembers)
            throws FileNotFoundException, QuestNotFoundException {

        int partyQuestLevel = 0;
        int questIndex = 0;
        File file = new File("quest.csv");

        try {
            Scanner scanLine = new Scanner(file);
            while (scanLine.hasNextLine()) {
                String line = scanLine.nextLine();
                if (line.contains(questToSelect)) {
                    partyQuestLevel = partyQuestLevel(partyMembers);
                    String[] splitStringColon = line.split(": ");
                    String questName = splitStringColon[0];
                    String[] splitStringComma = splitStringColon[1].split(", ");
                    int questLevel = Integer.parseInt(splitStringComma[0]);
                    int reward = Integer.parseInt(splitStringComma[1]);
                    if (questLevel < partyQuestLevel) {
                        System.out.println("Success! Your party gained "
                                + reward + " coins. This calls for a trip to the Tavern!");
                        return removedQuest(questName);
                    } else if (questLevel > partyQuestLevel) {
                        System.out.println("Failure... Your party was defeated. Better Luck Next Time!");
                        return false;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }


        return true;
    }
    private static int partyQuestLevel(ArrayList<PartyMember> partyMembers) {
        if (partyMembers == null) {
            return -1;
        }
        int totalQuestLevel = 0;
        for (PartyMember i :partyMembers) {
            totalQuestLevel += i.questLevel();
        }
        return totalQuestLevel;
    }

    private static boolean removedQuest(String deleteQuest) throws QuestNotFoundException, FileNotFoundException {
        String tempFile = "temp.txt";
        File oldFile = new File("quest.csv");
        File newFile = new File(tempFile);

        ArrayList<String> quests = new ArrayList<>();

        try {
            Scanner lineScanner = new Scanner(oldFile);
            while (lineScanner.hasNextLine()) {
                String questLine = lineScanner.nextLine();
                if (!questLine.contains(deleteQuest)) {
                    quests.add(questLine);
                }
            }
            PrintWriter output = new PrintWriter(oldFile);
            for (String i : quests) {
                output.println(i);
            }
            output.close();
            return true;
        } catch (FileNotFoundException F) {
            throw new FileNotFoundException("File not found.");
        }
    }
}
