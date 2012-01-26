package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public abstract class ValueNode implements JVMNode {
 
    protected String type;
    protected String name;
    
    public ValueNode(String type, String name){
        this.type = type;
        this.name = name;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getName(){
        return this.name;
    }
}