package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class FieldNode extends InvocableNode {

    private boolean isStatic;
    public FieldNode(String class_name, String name, String type, 
            boolean isStatic){
        super(class_name, name, type);
        this.isStatic = isStatic;
    }

    @Override
    public String toString(){
        if(isStatic){
            return class_name + "." + name;
        }
        return "." + name;
    }
}