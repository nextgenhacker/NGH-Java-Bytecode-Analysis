package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class CastNode extends ValueNode {

    private ValueNode value;

    public CastNode(String cast_name, ValueNode value){
        super(cast_name, null);
        this.value = value;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("(");
        builder.append(this.type);
        builder.append(") ");
        builder.append(value.toString());
        
        return builder.toString();

    }

    public String toJava(String indent) {
        return indent + toString();
    }
}