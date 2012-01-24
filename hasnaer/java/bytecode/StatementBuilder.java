package hasnaer.java.bytecode;

import hasnaer.java.bytecode.attribute.Code;
import hasnaer.java.bytecode.attribute.LocalVariableTable;
import hasnaer.java.bytecode.nodes.AssignNode;
import hasnaer.java.bytecode.nodes.JVMNode;
import hasnaer.java.bytecode.nodes.PrimitiveNode;
import hasnaer.java.bytecode.nodes.ValueNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author hasnae rehioui
 */
public class StatementBuilder {
 
    private int start;
    private int end;
    private Code code_attribute;
    private LocalVariableTable lvt_attribute;
           
    private List<JVMNode> statements;
    private Stack<JVMNode> stack;
    
    private boolean executed;
    
    private Map<Integer, Integer> variables;
    
    private int lastIndex;
    public StatementBuilder(Code code_attribute, int start, 
            int end, LocalVariableTable lvt_attribute){
        this.start = start;
        this.end = end;
        this.code_attribute = code_attribute;
        this.lvt_attribute = lvt_attribute;
        this.statements = new ArrayList<JVMNode>();
        this.stack = new Stack<JVMNode>();
        this.executed = false;
        this.variables = new HashMap<Integer, Integer>();
        
        if(lvt_attribute.THIS_INDEX != -1){
            variables.put(lvt_attribute.THIS_INDEX, 0);
        }
        lastIndex = 0;
    }
    
    private byte[] code(){
        return code_attribute.getCode();
    }
    
    public List<JVMNode> build(){
        
        
        if(!executed){
        
            int i = start;
            
            System.err.println("end=" + end);
            while(i < end){
                int opcode = code()[i];
                
                System.err.println("opcode= " + opcode + " pos=" + i);
                switch(opcode){
                    
                    case Opcode.BIPUSH:
                        System.err.println("consumeBIPUSH");
                        i = consumeBIPUSH(i);
                        break;
                        
                    case Opcode.I_STORE_1:
                        System.err.println("consumeISTORE");
                        i = consumeISTORE(i, 1);
                        break;
                        
                    case Opcode.RETURN:
                        i = consumeRETURN(i);
                        break;
                }
            }
            
            executed = true;
        } 
        
        return statements;
    }
    
    private int consumeRETURN(int pos){
        return pos + 2;
    }
    
    private int consumeISTORE(int pos, int index){
        
        AssignNode.Type atype = AssignNode.Type.NONFIRST;
        
        if(!variables.containsKey(index)){
            atype = AssignNode.Type.FIRST;
            lastIndex++;
            variables.put(index, lastIndex);
        }
        
        String[] type_name = lvt_attribute.getVariable(variables.get(index));
        
        ValueNode value = (ValueNode) stack.pop();
        ValueNode variable = null;
        
        if(value instanceof PrimitiveNode){
            variable = new PrimitiveNode(type_name[0], type_name[1]);
        }
        
        AssignNode assign = new AssignNode(variable, value, atype);
        statements.add(assign);
        
        return pos + 2;
    }
    private int consumeBIPUSH(int pos){
        int value = code()[pos + 1];
        stack.push(new PrimitiveNode(PrimitiveNode.Type.INT, Integer.toString(value)));
        return pos + 2;
    }
    
    public List<JVMNode> getStatements(){
        return this.statements;
    }
}