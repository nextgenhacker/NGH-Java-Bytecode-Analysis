package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class AssignNode implements JVMNode {

    private ValueNode variable;
    private ValueNode value;
    private Type type;
    
    public AssignNode(ValueNode variable, ValueNode value, Type type) {
        this.variable = variable;
        this.value = value;
        this.type = type;
    }

    public enum Type {
        FIRST,NONFIRST;
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        
        if(type.compareTo(Type.FIRST) == 0){
            builder.append(variable.type);
            builder.append(" ");
        }
        
        builder.append(variable.toString());
        builder.append(" = ");
        builder.append(value.toString());
        
        return builder.toString();
    }
}