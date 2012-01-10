package hasnaer.java.bytecode.cp;

/**
 *
 * @author hasnae rehioui
 */
public class Class_Info extends CP_Info {
    
    private int name_index;
    
    public Class_Info(Tag tag, int name_index){
        super(tag);
        this.name_index = name_index;
    }

    /**
     * @return the name_index
     */
    public int getName_index() {
        return name_index;
    }

    /**
     * @param name_index the name_index to set
     */
    public void setName_index(int name_index) {
        this.name_index = name_index;
    }
}
