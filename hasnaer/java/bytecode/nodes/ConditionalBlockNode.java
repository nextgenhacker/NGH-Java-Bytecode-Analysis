package hasnaer.java.bytecode.nodes;

import hasnaer.java.bytecode.SourceCodeBuilder;
import java.util.List;

/**
 *
 * @author hasnae rehioui
 */
public class ConditionalBlockNode implements JVMNode {

    private OperationNode condition;
    private List<JVMNode> thenBlock;
    private List<JVMNode> elseBlock;


    public ConditionalBlockNode(OperationNode condition){
        this.condition = condition;
    }

    public void setThenBlock(List<JVMNode> thenBlock){
        this.thenBlock = thenBlock;
    }

    public void setElseBlock(List<JVMNode> elseBlock){
        this.elseBlock = elseBlock;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        return builder.toString();
    }

    public String toJava(String indent) {
        StringBuilder builder = new StringBuilder();

        builder.append(indent);
        builder.append("if (");
        builder.append(condition.toString());
        builder.append(") {\n");

        for (JVMNode statement : thenBlock) {
            builder.append(statement.toJava(indent + SourceCodeBuilder.INDENT));
            if (!(statement instanceof ConditionalBlockNode)) {
                builder.append(";");
            }
            builder.append("\n");
        }

        builder.append(indent);
        builder.append("} ");

        if (elseBlock != null && !elseBlock.isEmpty()) {
            builder.append("else {\n");

            for (JVMNode statement : elseBlock) {
                builder.append(statement.toJava(indent + SourceCodeBuilder.INDENT));

                if (!(statement instanceof ConditionalBlockNode)) {
                    builder.append(";");
                }
                builder.append("\n");
            }

            builder.append(indent);
            builder.append("} ");
        }

        return builder.toString();
    }
}