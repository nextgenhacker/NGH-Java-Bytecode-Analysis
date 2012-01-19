package hasnaer.java.bytecode.attribute;

/**
 *
 * @author hasnae rehioui
 */
public class SourceFile extends AttributeInfo {
 
    private int sourcefile_index;
    public SourceFile(int attribute_name_index, int attribute_length
            , int sourcefile_index){
        super(attribute_name_index, attribute_length);
        this.sourcefile_index = sourcefile_index;
    }
    
}