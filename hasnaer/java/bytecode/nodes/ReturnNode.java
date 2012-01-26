package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class ReturnNode implements JVMNode {

    private JVMNode return_value;

    public ReturnNode(JVMNode return_value){
        this.return_value = return_value;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("return ");
        builder.append(this.return_value.toString());
        return builder.toString();
    }
}