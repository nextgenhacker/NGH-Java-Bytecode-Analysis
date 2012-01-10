package hasnaer.java.bytecode.cp;

/**
 *
 * @author hasnae rehioui
 */
public class NameAndType_Info extends CP_Info {

    private int name_index;
    private int descriptor_index;
    
    public NameAndType_Info(Tag ctag, int name_index, int descriptor_index) {
        super(ctag);
        this.name_index = name_index;
        this.descriptor_index = descriptor_index;
    }
    
}
