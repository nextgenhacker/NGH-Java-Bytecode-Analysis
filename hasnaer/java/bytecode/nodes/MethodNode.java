package hasnaer.java.bytecode.nodes;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author hasnae rehioui
 */
public class MethodNode extends InvocableNode {

    private List<JVMNode> params;
    private boolean isStatic;
    
    public MethodNode(String class_name, String name, String type, boolean isStatic){
        super(class_name, name, type);
        this.params = new LinkedList<JVMNode>();
        this.isStatic = isStatic;
    }

    public void addParam(JVMNode param){
        this.params.add(param);
    }

    public void addParam(int index, JVMNode param){
        this.params.add(index, param);
    }

    public boolean isConstructor(){
        return name.equals("<init>");
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        if(isConstructor()){
            builder.append(" ");
            builder.append(class_name);
        }
        else if(isStatic){
            builder.append(class_name);
        }
        else{
            builder.append(".");
            builder.append(name);
        }

        builder.append("(");

        int ln = params.size();
        if(ln > 0){
            builder.append(params.get(0).toString());

            for(int i = 1; i < ln; i++){
                builder.append(", ");
                builder.append(params.get(i).toString());
            }
        }

        builder.append(")");
        return builder.toString();
    }
}