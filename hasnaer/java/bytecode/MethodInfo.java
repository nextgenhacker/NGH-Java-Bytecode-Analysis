package hasnaer.java.bytecode;

import hasnaer.java.bytecode.attribute.AttributeInfo;
import hasnaer.java.bytecode.attribute.Code;
import hasnaer.java.bytecode.attribute.Exceptions;
import hasnaer.java.bytecode.cp.ConstantPool;

/**
 *
 * @author hasnae rehioui
 */
public class MethodInfo extends ClassMemberInfo {
    
    public MethodInfo(int access_flags,
            int name_index, int descriptor_index, ConstantPool constant_pool){
        super(access_flags, name_index, descriptor_index, constant_pool);
    }
    
    public Code getCodeAttribute(){
        for(AttributeInfo attribute : this.getAttributes()){
            if(attribute instanceof Code){
                return (Code)  attribute;
            }
        }
        return null;
    }

    public Exceptions getExceptionsAttibute(){
        for(AttributeInfo attribute : getAttributes()){
            if(attribute instanceof Exceptions){
                return (Exceptions) attribute;
            }
        }
        return null;
    }
}
