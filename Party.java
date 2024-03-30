import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.io.File;

public class Party {
    public static ArrayList<PartyMember> recruitParty (String filePath) throws FileNotFoundException {
        File readFile = new File(filePath);
        if (readFile == null || readFile.exists()){
            throw new FileNotFoundException();
        }
        return null;
    }

    private PartyMember processInfo (String characterToProcess) throws InvalidPartyMemberException {
        PartyMember newCharacter;
        String name;
        int health;
        int baseAttack;

        if (characterToProcess.contains("Warrior")){

            newCharacter = new Warrior();
        } else if (characterToProcess.contains("Mage")){

        }
        return null;
    }

}
