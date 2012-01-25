package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class InvocationNode extends ValueNode {

    private JVMNode node;
    private InvocableNode invoke;

    public InvocationNode(JVMNode node, InvocableNode invoke){
        super(null, null);
        this.node = node;
        this.invoke = invoke;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append(node.toString());
        builder.append(invoke.toString());

        return builder.toString();
    }
    
}