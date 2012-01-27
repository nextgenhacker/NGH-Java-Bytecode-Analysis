package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class ArrayLengthNode extends ValueNode {

    private ReferenceNode ref;
    
    public ArrayLengthNode(ReferenceNode ref){
        super(ref.type, ref.name);
        this.ref = ref;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(ref.toString());
        builder.append(".length");
        return builder.toString();
    }

    public String toJava(String indent) {
        return indent + toString();
    }
}