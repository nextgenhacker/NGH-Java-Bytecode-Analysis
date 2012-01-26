package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class OperationNode extends ValueNode {
 
    private ValueNode left;
    private ValueNode right;
    private Type op;
    
    public OperationNode(ValueNode left, ValueNode right, Type op){
        super(null, null);
        this.left = left;
        this.right = right;
        this.op = op;
    }
    
    public enum Type{
        ADD(" + "), 
        SUB(" - "),
        MUL(" * "),
        DIV(" / "),
        REM(" % "),
        
        NE(" != "),
        EQ(" == "),
        LT(" < "),
        GE(" >= "),
        GT(" > "),
        LE(" <= "),

        AND(" & "),
        OR(" | "),
        XOR(" ^ "),
        
        LSH(" >> "),
        RSH(" << ");
        String value;
        Type(String value){
            this.value = value;
        }
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(left.toString());
        builder.append(op.value);
        builder.append(right.toString());

        return builder.toString();
    }
}