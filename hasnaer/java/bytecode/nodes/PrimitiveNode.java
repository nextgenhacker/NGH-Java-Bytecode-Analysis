package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class PrimitiveNode extends ValueNode {
 
    public PrimitiveNode(String type, String name){
        super(type, name);
    }
    
    
    public PrimitiveNode(Type type, String name){
        super(type.value(), name);
    }
    
    
    public enum Type{
        INT("int"),
        FLOAT("float"),
        DOUBLE("double"),
        SHORT("short"),
        LONG("long"),
        BYTE("byte"),
        CHAR("char"),
        BOOLEAN("boolean");

        String value;
        Type(String value){
            this.value = value;
        }
        
        public String value(){
            return this.value;
        }
    }
    
    @Override
    public String toString(){
        return name;
    }
}