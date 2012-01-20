package hasnaer.java.bytecode.attribute;

import java.util.List;

/**
 *
 * @author hasnae rehioui
 */
public class InnerClasses extends AttributeInfo {
 
    private List<Entry> classes;
    
    public InnerClasses(int name_index, int attribute_length){
        super(name_index, attribute_length);
    }
    
    public void addInnerClass(Entry inner_class){
        this.classes.add(inner_class);
    }
    
    
    public static class Entry{
        
        private int inner_info_index;
        private int outer_info_index;
        private int inner_name_index;
        private int inner_access_flags;
        
        public Entry(int inner_info_index, int outer_info_index,
                int inner_name_index, int inner_access_flags){
            this.inner_info_index = inner_info_index;
            this.outer_info_index = outer_info_index;
            this.inner_name_index = inner_name_index;
            this.inner_access_flags = inner_access_flags;
            
        }
    }
    
}