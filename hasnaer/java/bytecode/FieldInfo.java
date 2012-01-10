package hasnaer.java.bytecode;

/**
 *
 * @author hasnae rehioui
 */
public class FieldInfo extends ClassMemberInfo {
 
    
    public FieldInfo(int access_flags, int name_index, 
            int descriptor_index){
        super(access_flags, name_index, descriptor_index);
    }
    
}