package hasnaer.java.bytecode.cp;

/**
 *
 * @author hasnae rehioui
 */
public abstract class CP_Info {

    protected Tag tag;
    
    public CP_Info(Tag tag){
        this.tag = tag;
    }

    public Tag getTag(){
        return tag;
    }
    
    public enum Tag {
        CLASS(7),
        FIELDREF(9),
        METHODREF(10),
        INTERFACEMETHODREF(11),
        STRING(8),
        INTEGER(3),
        FLOAT(4),
        LONG(5),
        DOUBLE(6),
        NAME_AND_TYPE(12),
        UTF8(1),
        PADDING(0);
        
        public int value;
        
        Tag(int value){
            this.value = value;
        }
        
        public static Tag fromValue(int value){
            for(Tag type : values()){
                if(type.value == value)
                    return type;
            }
            return null;
        }
    }

    public String toString(){
        return tag.toString();
    }
}