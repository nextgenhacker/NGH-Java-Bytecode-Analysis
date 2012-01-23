package hasnaer.java.bytecode.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hasnae rehioui
 */
public class LocalVariableTable extends AttributeInfo {
 
    private List<Entry> table;
    
    public LocalVariableTable(int attribute_name_index, 
            int attribute_length, int table_length){
        super(attribute_name_index, attribute_length);
        this.table = new ArrayList<Entry>(table_length);
    }
    
    public void addEntry(Entry entry){
        this.table.add(entry);
    }
    
    public List<Entry> getTable(){
        return table;
    }
    
    public static class Entry {
        private int start_pc;
        private int length;
        private int name_index;
        private int descriptor_index;
        private int index;
        
        public Entry(int start_pc, int length, 
                int name_index,
                int descriptor_index, int index){
            this.start_pc = start_pc;
            this.length = length;
            this.name_index = name_index;
            this.descriptor_index = descriptor_index;
            this.index = index;
        }
        
        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder();
            builder.append("start_pc= " + start_pc);
            builder.append("| length= " + length);
            builder.append("| name_index= " + name_index);
            builder.append("| des_index= " + descriptor_index);
            builder.append("| index= " + index);
            return builder.toString();
        }
    }
}