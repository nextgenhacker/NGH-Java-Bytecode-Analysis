package hasnaer.java.bytecode.attribute;

import hasnaer.java.bytecode.cp.ConstantPool;

/**
 *
 * @author hasnae rehioui
 */
public class Synthetic extends AttributeInfo {
 
    public Synthetic(int name_index, int attribute_length, ConstantPool constant_pool){
        super(name_index, attribute_length, constant_pool);
    }
}