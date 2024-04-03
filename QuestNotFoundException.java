public class QuestNotFoundException extends Exception{
    public QuestNotFoundException(String m) {
        super(m);
    }
    public QuestNotFoundException() {
        super("Selected Quest Not Found");
    }
}
