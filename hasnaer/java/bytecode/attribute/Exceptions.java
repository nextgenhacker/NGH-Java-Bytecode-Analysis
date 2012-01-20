package hasnaer.java.bytecode.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hasnae rehioui
 */
public class Exceptions extends AttributeInfo {
 
    private List<Integer> exception_index_table;
    private int table[];
    
    public Exceptions(int name_index, int attribute_length,
            int number_of_exceptions){
        super(name_index, attribute_length);
        this.exception_index_table = new ArrayList<Integer>();
        this.table = new int[number_of_exceptions];
    }

    public void addExceptionIndex(int key, int exception_index){
        this.table[key] = exception_index;
    }
    public void addExceptionIndex(int exception_index){
        this.exception_index_table.add(exception_index);
    }
    
}