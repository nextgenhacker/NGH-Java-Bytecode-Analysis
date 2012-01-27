package hasnaer.java.bytecode.attribute;

import hasnaer.java.bytecode.cp.ConstantPool;

/**
 *
 * @author hasnae rehioui
 */
public class ConstantValue extends AttributeInfo {
    
    private int value_index;
    
    public ConstantValue(int name_index, int attribute_length, 
            int constantvalue_index, ConstantPool constant_pool){
        super(name_index, attribute_length, constant_pool);
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
