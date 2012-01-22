package hasnaer.java.bytecode;

/**
 *
 * @author hasnae rehioui
 */
public class MethodInfo extends ClassMemberInfo {
    
    public MethodInfo(int access_flags,
            int name_index, int descriptor_index){
        super(access_flags, name_index, descriptor_index);
    }
}
