package hasnaer.java.bytecode;

import hasnaer.java.bytecode.cp.ConstantPool;

/**
 *
 * @author hasnae rehioui
 */
public class FieldInfo extends ClassMemberInfo {
 
    
    public FieldInfo(int access_flags, int name_index, 
            int descriptor_index, ConstantPool constant_pool){
        super(access_flags, name_index, descriptor_index, constant_pool);
    }
    
}