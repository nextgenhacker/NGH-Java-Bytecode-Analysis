package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class ArrayRefNode extends ReferenceNode {

    private String[] count;
    private int dimension;
    
    public ArrayRefNode(ReferenceNode ref, String[] count){
        super(ref.getType(), ref.getName());
        this.count = count;
        this.dimension = count.length;
    }

    public String[] getCount(){
        return this.count;
    }
    public int getDimension(){
        return this.dimension;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(this.getName());
        if(getName().equalsIgnoreCase("new ")){
            builder.append(getType());
        }
        for(String c : count){
            builder.append("[");
            builder.append(c);
            builder.append("]");
        }
        return builder.toString();
    }
}