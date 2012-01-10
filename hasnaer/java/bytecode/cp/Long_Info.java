package hasnaer.java.bytecode.cp;

/**
 *
 * @author hasnae rehioui
 */
public class Long_Info extends CP_Info {

    private long value;
    
    public Long_Info(Tag ctag, long value) {
        super(ctag);
        this.value = value;
    }
    
}
