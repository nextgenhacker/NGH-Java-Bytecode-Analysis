
package hasnaer.java.bytecode.attribute;

import hasnaer.java.bytecode.cp.ConstantPool;

/**
 *
 * @author hasnae rehioui
 */
public class DefaultAttribute extends AttributeInfo {
 
    private byte[] data;
    public DefaultAttribute(int attribute_name_index, int attribute_length
            , ConstantPool constant_pool){
        super(attribute_name_index, attribute_length, constant_pool);
        data = new byte[attribute_length];
    }
    
    public byte[] getData(){
        return data;
    }
}