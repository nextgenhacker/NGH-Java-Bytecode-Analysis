package hasnaer.java.bytecode.attribute;

/**
 *
 * @author hasnae rehioui
 */
public class ConstantValue extends AttributeInfo {
    
    private int value_index;
    
    public ConstantValue(int name_index, int attribute_length, 
            int constantvalue_index){
        super(name_index, attribute_length);
        this.value_index = constantvalue_index;
    }

    /**
     * @return the value_index
     */
    public int getValue_index() {
        return value_index;
    }

    /**
     * @param value_index the value_index to set
     */
    public void setValue_index(int value_index) {
        this.value_index = value_index;
    }
    
}
