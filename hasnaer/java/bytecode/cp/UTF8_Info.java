package hasnaer.java.bytecode.cp;

/**
 *
 * @author hasnae rehioui
 */
public class UTF8_Info extends CP_Info {

    private String value;
    
    public UTF8_Info(Tag ctag, String value) {
        super(ctag);
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    
}
