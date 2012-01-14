package hasnaer.java.bytecode.attribute;

/**
 *
 * @author hasnae rehioui
 */
public class ExceptionTableEntry {
    
    private int start_pc;
    private int end_pc;
    private int handler_pc;
    private int catch_type;
    
    public ExceptionTableEntry(int start_pc, int end_pc, int handler_pc,
            int catch_type){
        this.start_pc = start_pc;
        this.end_pc = end_pc;
        this.handler_pc = handler_pc;
        this.catch_type = catch_type;
    }
}