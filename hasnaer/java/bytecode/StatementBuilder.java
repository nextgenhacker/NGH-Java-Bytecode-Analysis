package hasnaer.java.bytecode;

import hasnaer.java.bytecode.attribute.Code;
import hasnaer.java.bytecode.attribute.LocalVariableTable;
import hasnaer.java.bytecode.cp.CP_Info;
import hasnaer.java.bytecode.cp.ConstantPool;
import hasnaer.java.bytecode.cp.FMIref_Info;
import hasnaer.java.bytecode.cp.Float_Info;
import hasnaer.java.bytecode.cp.Integer_Info;
import hasnaer.java.bytecode.cp.String_Info;
import hasnaer.java.bytecode.nodes.AssignNode;
import hasnaer.java.bytecode.nodes.InvocationNode;
import hasnaer.java.bytecode.nodes.JVMNode;
import hasnaer.java.bytecode.nodes.MethodNode;
import hasnaer.java.bytecode.nodes.PrimitiveNode;
import hasnaer.java.bytecode.nodes.ReferenceNode;
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
            int end, LocalVariableTable lvt_attribute) {
        this.start = start;
        this.end = end;
        this.code_attribute = code_attribute;
        this.lvt_attribute = lvt_attribute;
        this.statements = new ArrayList<JVMNode>();
        this.stack = new Stack<JVMNode>();
        this.executed = false;
        this.variables = new HashMap<Integer, Integer>();

        if (lvt_attribute.THIS_INDEX != -1) {
            variables.put(lvt_attribute.THIS_INDEX, 0);
        }
        lastIndex = 0;
    }

    private byte[] code() {
        return code_attribute.getCode();
    }

    private ConstantPool pool() {
        return lvt_attribute.getConstantPool();
    }

    public List<JVMNode> build() {

        if (!executed) {

            int i = start;

            System.err.println("end=" + end);
            while (i < end) {
                int opcode = code()[i] & 0xFF;

                System.err.println("opcode= " + opcode + " pos=" + i);
                switch (opcode) {

                    case Opcode.BIPUSH:
                        System.err.println("consumeBIPUSH");
                        i = consumeBIPUSH(i);
                        break;

                    case Opcode.ISTORE_0:
                    case Opcode.ISTORE_1:
                    case Opcode.ISTORE_2:
                    case Opcode.ISTORE_3:
                        System.err.println("consumeSTOREVARIABLE");
                        i = consumeSTOREVARIABLE(i, opcode - Opcode.ISTORE_0);
                        break;

                    case Opcode.DSTORE_0:
                    case Opcode.DSTORE_1:
                    case Opcode.DSTORE_2:
                    case Opcode.DSTORE_3:
                        System.err.println("consumeSTOREVARIABLE");
                        i = consumeSTOREVARIABLE(i, opcode - Opcode.DSTORE_0);
                        break;

                    case Opcode.FSTORE_0:
                    case Opcode.FSTORE_1:
                    case Opcode.FSTORE_2:
                    case Opcode.FSTORE_3:
                        System.err.println("consumeSTOREVARIABLE");
                        i = consumeSTOREVARIABLE(i, opcode - Opcode.FSTORE_0);
                        break;

                    case Opcode.LSTORE_0:
                    case Opcode.LSTORE_1:
                    case Opcode.LSTORE_2:
                    case Opcode.LSTORE_3:
                        System.err.println("consumeSTOREVARIABLE");
                        i = consumeSTOREVARIABLE(i, opcode - Opcode.LSTORE_0);
                        break;

                    case Opcode.ASTORE_0:
                    case Opcode.ASTORE_1:
                    case Opcode.ASTORE_2:
                    case Opcode.ASTORE_3:
                        System.err.println("consumeSTOREVARIABLE");
                        i = consumeSTOREVARIABLE(i, opcode - Opcode.ASTORE_0);
                        break;

                    case Opcode.RETURN:
                        System.err.println("consumeRETURN");
                        i = consumeRETURN(i);
                        break;

                    case Opcode.NEW:
                        System.err.println("consumeNEW");
                        i = consumeNEW(i);
                        break;

                    case Opcode.DUP:
                        System.err.println("consumeDUP");
                        i = consumeDUP(i);
                        break;

                    case Opcode.LDC:
                        System.err.println("consumeLDC");
                        i = consumeLDC(i, code()[i + 1]);
                        break;

                    case Opcode.INVOKESPECIAL:
                        System.err.println("consumeINVOKESPECIAL");
                        i = consumeINVOKEMETHOD(i);
                        break;
                }
            }

            executed = true;
        }

        return statements;
    }

    private int consumeINVOKEMETHOD(int pos) {

        int index = unsignedShortAt(pos + 1);
//        FMIref_Info ref_info = pool().getFMIref_Info(index);
        String class_name = pool().getFMIref_ClassName(index);
        String[] type_name = pool().getFMIref_Name_And_Type(index);

        MethodNode method = new MethodNode(class_name.replace("/", "."), type_name[1], type_name[0]);

        int params_ln = Descriptor.getParamCount(method.getType());

        System.err.println("params ln = " + params_ln);

        for (int i = 0; i < params_ln; i++) {
            method.addParam(0, stack.pop());
        }

        InvocationNode invocation = new InvocationNode(stack.pop(), method);

        stack.push(invocation);

        return pos + 3;
    }

    private int consumeLDC(int pos, int index) {



        CP_Info cp_info = pool().getCP_Info(index);
        System.err.println("index= " + index);
        System.err.println("tag= " + cp_info.getTag());

        switch (cp_info.getTag()) {
            case INTEGER:
                Integer_Info int_info = (Integer_Info) cp_info;
                stack.push(new PrimitiveNode(PrimitiveNode.Type.INT, int_info.getStringValue()));
                break;
            case FLOAT:
                Float_Info float_info = (Float_Info) cp_info;
                stack.push(new PrimitiveNode(PrimitiveNode.Type.FLOAT, float_info.getStringValue()));
                break;
            case STRING:
                String_Info str_info = (String_Info) cp_info;
                stack.push(new ReferenceNode("java.lang.String", escapeSTR(pool().getUTF8_Info(str_info.getString_Index()).getValue())));
                break;
        }

        return pos + 2;
    }

    private String escapeSTR(String str) {
        StringBuilder builder = new StringBuilder("\"");
        builder.append(str.replaceAll("\n", "\\\\n"));
        builder.append("\"");
        return builder.toString();
    }

    private int consumeDUP(int pos) {
        return pos + 1;
    }

    private int unsignedShortAt(int pos) {
        return (code()[pos] << 8) + code()[pos + 1];
    }

    private int consumeNEW(int pos) {

        int index = unsignedShortAt(pos + 1);
        String type = pool().getUTF8_Info(pool().getClass_Info(index).getName_index()).getValue();

        stack.push(new ReferenceNode(type, "new "));

        return pos + 3;
    }

    private int consumeRETURN(int pos) {
        return pos + 1;
    }

    private int consumeSTOREVARIABLE(int pos, int index) {

        AssignNode.Type atype = AssignNode.Type.NONFIRST;

        if (!variables.containsKey(index)) {
            atype = AssignNode.Type.FIRST;
            lastIndex++;
            variables.put(index, lastIndex);
        }

        String[] type_name = lvt_attribute.getVariable(variables.get(index));

        ValueNode value = (ValueNode) stack.pop();
        ValueNode variable = null;

        if (value instanceof PrimitiveNode) {
            variable = new PrimitiveNode(type_name[0], type_name[1]);
        }
        else{
            variable = new ReferenceNode(type_name[0], type_name[1]);
        }
        
        AssignNode assign = new AssignNode(variable, value, atype);
        statements.add(assign);

        return pos + 1;
    }

    private int consumeBIPUSH(int pos) {
        int value = code()[pos + 1];
        stack.push(new PrimitiveNode(PrimitiveNode.Type.INT, Integer.toString(value)));
        return pos + 2;
    }

    public List<JVMNode> getStatements() {
        return this.statements;
    }
}
