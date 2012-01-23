package hasnaer.java.bytecode;

import hasnaer.java.bytecode.attribute.AttributeInfo;
import hasnaer.java.bytecode.attribute.Code;

/**
 *
 * @author hasnae rehioui
 */
public class MethodInfo extends ClassMemberInfo {
    
    public MethodInfo(int access_flags,
            int name_index, int descriptor_index){
        super(access_flags, name_index, descriptor_index);
    }
    
    public Code getCodeAttribute(){
        for(AttributeInfo attribute : this.getAttributes()){
            if(attribute instanceof Code){
                return (Code)  attribute;
            }
        }
        return null;
    }
}
