package hasnaer.java.bytecode.cp;

/**
 *
 * @author hasnae rehioui
 */
public class Integer_Info extends CP_Info {

    private int value;
    
    public Integer_Info(Tag ctag, int value) {
        super(ctag);
        this.value = value;
    }

    public int getIntValue(){
        return value;
    }

    public String getStringValue(){
        return Integer.toString(value);
    }
}
