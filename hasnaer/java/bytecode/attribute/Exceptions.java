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
            int number_of_exceptions) {
        super(name_index, attribute_length);
        this.exception_index_table = new ArrayList<Integer>();
        this.table = new int[number_of_exceptions];
    }

    public void addExceptionIndex(int key, int exception_index) {
        this.table[key] = exception_index;
    }

    public void addExceptionIndex(int exception_index) {
        this.exception_index_table.add(exception_index);
    }

    public static class Entry {

        private int start_pc;
        private int end_pc;
        private int handler_pc;
        private int catch_type;

        public Entry(int start_pc, int end_pc, int handler_pc,
                int catch_type) {
            this.start_pc = start_pc;
            this.end_pc = end_pc;
            this.handler_pc = handler_pc;
            this.catch_type = catch_type;
        }
    }
}