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

    private String class_name;
    
    public MethodInfo(int access_flags,
            int name_index, int descriptor_index, ConstantPool constant_pool,
            String class_name){
        super(access_flags, name_index, descriptor_index, constant_pool);
        this.class_name = class_name;
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

    @Override
    public String getName(){
        String m_name = super.getName();
        if(m_name.equals("<init>")){
            return class_name;
        }
        if(m_name.equals("<clinit>")){
            return "";
        }
        return m_name;
    }

    public boolean isStaticInit(){
        return super.getName().equals("<clinit>");
    }
    public boolean isConstructor(){
        return (super.getName().equals("<init>"));
    }

    public boolean isAbstract(){
        return AccessFlags.isAbstract(access_flags);
    }
}
