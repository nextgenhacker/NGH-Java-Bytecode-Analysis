package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public abstract class InvocableNode extends ValueNode {

    protected String name;
    protected String class_name;
    protected String type;

    public InvocableNode(String class_name, String name, String type){
        super(type, name);
        this.class_name = class_name;
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public String getClass_Name(){
        return class_name;
    }

    public String getType(){
        return type;
    }
}