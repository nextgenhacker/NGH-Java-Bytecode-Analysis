package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class ReferenceNode extends ValueNode {

    public ReferenceNode(String type, String name){
        super(type, name);
    }

    @Override
    public String toString(){
        return name;
    }
}