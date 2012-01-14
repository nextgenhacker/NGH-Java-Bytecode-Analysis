package hasnaer.java.bytecode.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hasnae rehioui
 */
public class Code extends AttributeInfo {
 
    private int max_stack;
    private int max_locals;
    private byte[] code;
    private List<ExceptionTableEntry> exception_table;
    private List<AttributeInfo> attributes;
    
    public Code(int name_index, int attribute_length,
            int max_stack, int max_locals){
        super(name_index, attribute_length);
        this.exception_table = new ArrayList<ExceptionTableEntry>();
        this.max_locals = max_locals;
        this.max_stack = max_stack;        
    }
    
    public void addExceptionTableEntry(ExceptionTableEntry entry){
        this.getException_table().add(entry);
    }

    /**
     * @return the max_stack
     */
    public int getMax_stack() {
        return max_stack;
    }

    /**
     * @param max_stack the max_stack to set
     */
    public void setMax_stack(int max_stack) {
        this.max_stack = max_stack;
    }

    /**
     * @return the max_locals
     */
    public int getMax_locals() {
        return max_locals;
    }

    /**
     * @param max_locals the max_locals to set
     */
    public void setMax_locals(int max_locals) {
        this.max_locals = max_locals;
    }

    /**
     * @return the code
     */
    public byte[] getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(byte[] code) {
        this.code = code;
    }

    /**
     * @return the exception_table
     */
    public List<ExceptionTableEntry> getException_table() {
        return exception_table;
    }

    /**
     * @param exception_table the exception_table to set
     */
    public void setException_table(List<ExceptionTableEntry> exception_table) {
        this.exception_table = exception_table;
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