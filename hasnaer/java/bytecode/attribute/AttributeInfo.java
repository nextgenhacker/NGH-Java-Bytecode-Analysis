package hasnaer.java.bytecode.attribute;

/**
 *
 * @author hasnae rehioui
 */
public abstract class AttributeInfo {
    
    private int name_index;
    private int attribute_length;
    
    public AttributeInfo(int name_index, int attribute_length){
        this.name_index = name_index;
        this.attribute_length = attribute_length;
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
     * @return the attribute_length
     */
    public int getAttribute_length() {
        return attribute_length;
    }

    /**
     * @param attribute_length the attribute_length to set
     */
    public void setAttribute_length(int attribute_length) {
        this.attribute_length = attribute_length;
    }
    
    public enum Type{
        SourceFile,
        ConstantValue,
        Code,
        Exceptions,
        InnerClasses,
        Synthetic,
        LineNumberTable,
        LocalVariableTable,
        Deprecated,
        IGNORE;
        
        
    }
    
}