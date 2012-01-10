package hasnaer.java.bytecode.cp;

/**
 *
 * @author hasnae rehioui
 */
public class Float_Info extends CP_Info {

    private float value;
    
    public Float_Info(Tag ctag, float value) {
        super(ctag);
        this.value = value;
    }
    
}
