package hasnaer.java.bytecode.attribute;

import hasnaer.java.bytecode.cp.ConstantPool;

/**
 *
 * @author hasnae rehioui
 */
public class LineNumberTable extends AttributeInfo {
    
    private int[][] line_number_table;
    
    public LineNumberTable(int attribute_name_index,
            int attribute_length, int line_number_table_length,
            ConstantPool constant_pool){
        super(attribute_name_index, attribute_length, constant_pool);
        this.line_number_table = new int[line_number_table_length][2];
    }
    
    public void addEntry(int index, int... values){
        for(int i = 0; i < values.length; i++){
            
        }
    }
    
}