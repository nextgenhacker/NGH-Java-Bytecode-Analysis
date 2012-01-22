
package hasnaer.java.bytecode.attribute;

/**
 *
 * @author hasnae rehioui
 */
public class DefaultAttribute extends AttributeInfo {
 
    private byte[] data;
    public DefaultAttribute(int attribute_name_index, int attribute_length){
        super(attribute_name_index, attribute_length);
        data = new byte[attribute_length];
    }
    
    public byte[] getData(){
        return data;
    }
}