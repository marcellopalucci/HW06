/**
 * Class which holds the QuestNotFoundException.
 * @author mpalucci3
 * @version 1.3
 */
public class QuestNotFoundException extends Exception {
    /**
     * Constructor which takes in a custom string and sends its super constructor.
     * @param m String representing the message to be printed
     */
    public QuestNotFoundException(String m) {
        super(m);
    }

    /**
     * No arg constructor which sends predefined message to its super constructor.
     */
    public QuestNotFoundException() {
        super("Selected Quest Not Found");
    }
}
