package hasnaer.java.bytecode.cp;

/**
 *
 * @author hasnae rehioui
 */
public class Double_Info extends CP_Info {

    private double value;
    
    public Double_Info(Tag ctag, double value) {
        super(ctag);
        this.value = value;
    }
    
}
