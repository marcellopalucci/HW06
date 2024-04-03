/**
 * Class which holds the InvalidPartyMemberException which extend RuntimeException.
 * @author mpalucci3
 * @version 1.2
 */
public class InvalidPartyMemberException extends RuntimeException {
    /**
     * Constructor which calls RuntimeException.
     */
    public InvalidPartyMemberException() {
        super("Invalid party member!");
    }

    /**
     * Constructor which calls RunTimeException
     * @param m String which represents the message to be sent to its super constructor.
     */
    public InvalidPartyMemberException(String m){
        super(m);
    }
}
