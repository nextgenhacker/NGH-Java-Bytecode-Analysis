package hasnaer.java.bytecode;

import hasnaer.java.bytecode.attribute.AttributeInfo;
import java.util.List;

/**
 *
 * @author hasnae rehioui
 */
public abstract class ClassMemberInfo {
    
    private int access_flags;
    private int name_index;
    private int descriptor_index;
    
    private List<AttributeInfo> attributes;
    
    public ClassMemberInfo(int access_flags, 
            int name_index, int descriptor_index){
        this.access_flags = access_flags;
        this.name_index = name_index;
        this.descriptor_index = descriptor_index;
        
    }

    /**
     * @return the access_flags
     */
    public int getAccess_flags() {
        return access_flags;
    }

    /**
     * @param access_flags the access_flags to set
     */
    public void setAccess_flags(int access_flags) {
        this.access_flags = access_flags;
    }

    /**
     * @return the name_index
     */
    public int getName_index() {
        return name_index;
    }

    /**
     * @param name_index the name_index to set
     */
    public void setName_index(int name_index) {
        this.name_index = name_index;
    }

    /**
     * @return the descriptor_index
     */
    public int getDescriptor_index() {
        return descriptor_index;
    }

    /**
     * @param descriptor_index the descriptor_index to set
     */
    public void setDescriptor_index(int descriptor_index) {
        this.descriptor_index = descriptor_index;
    }

    /**
     * @return the attributes
     */
    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(List<AttributeInfo> attributes) {
        this.attributes = attributes;
    }
    
    
}