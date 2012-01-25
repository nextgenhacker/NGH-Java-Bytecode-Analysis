package hasnaer.java.bytecode.cp;

/**
 *
 * @author hasnae rehioui
 */
public class String_Info extends CP_Info {

    private int string_index;
    
    public String_Info(Tag ctag, int string_index) {
        super(ctag);
        this.string_index = string_index;
    }

    public int getString_Index(){
        return string_index;
    }
}