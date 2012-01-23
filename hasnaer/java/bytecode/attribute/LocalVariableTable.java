package hasnaer.java.bytecode.attribute;

import hasnaer.java.bytecode.Descriptor;
import hasnaer.java.bytecode.cp.ConstantPool;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hasnae rehioui
 */
public class LocalVariableTable extends AttributeInfo {
 
    private List<Entry> table;
    private ConstantPool constant_pool;
    
    public int THIS_INDEX = -1;
    
    public LocalVariableTable(int attribute_name_index, 
            int attribute_length, int table_length, ConstantPool constant_pool){
        super(attribute_name_index, attribute_length);
        this.table = new ArrayList<Entry>(table_length);
        this.constant_pool = constant_pool;
    }
    
    public void init(){
        for(Entry entry : table){
            if(getVariable(entry.index)[1].equals("this")){
                this.THIS_INDEX = entry.index;
                break;
            }
        }
    }
    
    public void addEntry(Entry entry){
        this.table.add(entry);
    }
    
    public List<Entry> getTable(){
        return table;
    }
    
    public String[] getVariable(int index){
        String[] type_and_name = new String[2];
        
        type_and_name[0] = Descriptor.fieldDataType(constant_pool.getUTF8_Info(table.get(index).descriptor_index).getValue());
        type_and_name[1] = constant_pool.getUTF8_Info(table.get(index).name_index).getValue();
                
        
        return type_and_name;
    }
    
    
    public static class Entry implements Comparable {
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

        @Override
        public int compareTo(Object o) {
            Entry e = (Entry) o;
            if(e.index < this.index){
                return 1;
            } else if(e.index == this.index){
                return 0;
            }
            return -1;
        }
    }
}