package hasnaer.java.bytecode.attribute;

/**
 *
 * @author hasnae rehioui
 */
public class LineNumberTable extends AttributeInfo {
    
    private int[][] line_number_table;
    
    public LineNumberTable(int attribute_name_index,
            int attribute_length, int line_number_table_length){
        super(attribute_name_index, attribute_length);
        this.line_number_table = new int[line_number_table_length][2];
    }
    
    public void addEntry(int index, int... values){
        for(int i = 0; i < values.length; i++){
            
        }
    }
    
}