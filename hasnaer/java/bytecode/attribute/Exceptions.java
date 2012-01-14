package hasnaer.java.bytecode.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hasnae rehioui
 */
public class Exceptions extends AttributeInfo {
 
    private List<Integer> exception_index_table;
    
    public Exceptions(int name_index, int attribute_length){
        super(name_index, attribute_length);
        this.exception_index_table = new ArrayList<Integer>();
    }

    public void addExceptionIndex(int exception_index){
        this.exception_index_table.add(exception_index);
    }
    
}