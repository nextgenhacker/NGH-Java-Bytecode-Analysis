package hasnaer.java.bytecode.attribute;

import hasnaer.java.bytecode.cp.ConstantPool;

/**
 *
 * @author hasnae rehioui
 */
public class Deprecated extends AttributeInfo {
 
    public Deprecated(int attribute_name_index,
            int attribute_length, ConstantPool constant_pool){
        super(attribute_name_index, attribute_length, constant_pool);
    }
    
}