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
        Type t = Type.valueOf(type.trim().toUpperCase());
        
        switch(t){
            case BOOLEAN:
                if(name.equals("0")){
                    return "false";
                }
                else if(name.equals("1")){
                    return "true";
                }
                
            case CHAR:
                try{
                    char c = (char) Integer.parseInt(name);
                    return "'" + String.valueOf(c) + "'";
                } catch(Exception ex){
                    
                }
                
            default:
                return name;
        
        }
        
    }
    
    public static void main(String[] args){
        Type t = Type.valueOf("DOUBLE");
        
        System.err.println(t);
    }
}