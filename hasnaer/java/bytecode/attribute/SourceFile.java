package hasnaer.java.bytecode.attribute;

import hasnaer.java.bytecode.cp.ConstantPool;

/**
 *
 * @author hasnae rehioui
 */
public class SourceFile extends AttributeInfo {
 
    private int sourcefile_index;
    public SourceFile(int attribute_name_index, int attribute_length
            , int sourcefile_index, ConstantPool constant_pool){
        super(attribute_name_index, attribute_length, constant_pool);
        this.sourcefile_index = sourcefile_index;
    }
    
}