package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class InstanceOfNode extends ValueNode {

    private ReferenceNode ref;
    public InstanceOfNode(String instance_name, ReferenceNode ref){
        super(instance_name, null);
        this.ref = ref;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(ref.toString());
        builder.append(" instanceof ");
        builder.append(this.type);

        return builder.toString();
    }

    public String toJava(String indent) {
        return indent + toString();
    }
}
