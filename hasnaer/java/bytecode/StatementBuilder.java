package hasnaer.java.bytecode;

import hasnaer.java.bytecode.attribute.Code;
import hasnaer.java.bytecode.attribute.LocalVariableTable;
import hasnaer.java.bytecode.cp.CP_Info;
import hasnaer.java.bytecode.cp.ConstantPool;
import hasnaer.java.bytecode.cp.Double_Info;
import hasnaer.java.bytecode.cp.Float_Info;
import hasnaer.java.bytecode.cp.Integer_Info;
import hasnaer.java.bytecode.cp.Long_Info;
import hasnaer.java.bytecode.cp.String_Info;
import hasnaer.java.bytecode.nodes.AssignNode;
import hasnaer.java.bytecode.nodes.FieldNode;
import hasnaer.java.bytecode.nodes.InvocationNode;
import hasnaer.java.bytecode.nodes.JVMNode;
import hasnaer.java.bytecode.nodes.MethodNode;
import hasnaer.java.bytecode.nodes.OperationNode;
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

                    case Opcode.LDC_W:
                    case Opcode.LDC2_W:
                        System.err.println("consumeLDC");
                        i = consumeLDC(i + 1, unsignedShortAt(i + 1));
                        break;

                    case Opcode.INVOKESPECIAL:
                        System.err.println("consumeINVOKESPECIAL");
                        i = consumeINVOKEMETHOD(i);
                        break;

                    case Opcode.ISTORE:
                    case Opcode.LSTORE:
                    case Opcode.FSTORE:
                    case Opcode.DSTORE:
                    case Opcode.ASTORE:
                        System.err.println("consumeSTOREVARIABLE");
                        i = consumeSTOREVARIABLE(i + 1, code()[i + 1]);
                        break;

                    case Opcode.ILOAD_0:
                    case Opcode.ILOAD_1:
                    case Opcode.ILOAD_2:
                    case Opcode.ILOAD_3:
                        System.err.println("consumeLOADPRIMITIVE");
                        i = consumeLOADPRIMITIVE(i, opcode - Opcode.ILOAD_0);
                        break;

                    case Opcode.FLOAD_0:
                    case Opcode.FLOAD_1:
                    case Opcode.FLOAD_2:
                    case Opcode.FLOAD_3:
                        System.err.println("consumeLOADPRIMITIVE");
                        i = consumeLOADPRIMITIVE(i, opcode - Opcode.FLOAD_0);
                        break;

                    case Opcode.DLOAD_0:
                    case Opcode.DLOAD_1:
                    case Opcode.DLOAD_2:
                    case Opcode.DLOAD_3:
                        System.err.println("consumeLOADPRIMITIVE");
                        i = consumeLOADPRIMITIVE(i, opcode - Opcode.DLOAD_0);
                        break;

                    case Opcode.LLOAD_0:
                    case Opcode.LLOAD_1:
                    case Opcode.LLOAD_2:
                    case Opcode.LLOAD_3:
                        System.err.println("consumeLOADPRIMITIVE");
                        i = consumeLOADPRIMITIVE(i, opcode - Opcode.LLOAD_0);
                        break;

                    case Opcode.ALOAD_0:
                    case Opcode.ALOAD_1:
                    case Opcode.ALOAD_2:
                    case Opcode.ALOAD_3:
                        System.err.println("consumeLOADREFERENCE");
                        i = consumeLOADREFERENCE(i, opcode - Opcode.ALOAD_0);
                        break;

                    case Opcode.ILOAD:
                    case Opcode.LLOAD:
                    case Opcode.DLOAD:
                    case Opcode.FLOAD:
                        System.err.println("consumeLOADPRIMITIVE");
                        i = consumeLOADPRIMITIVE(i + 1, code()[i + 1]);
                        break;

                    case Opcode.ALOAD:
                        System.err.println("consumeLOADREFERENCE");
                        i = consumeLOADREFERENCE(i + 1, code()[i + 1]);
                        break;

                    case Opcode.INVOKEVIRTUAL:
                        System.err.println("consumeINVOKEMETHOD");
                        i = consumeINVOKEMETHOD(i);
                        break;

                    case Opcode.GETSTATIC:
                        System.err.println("consumeGETSTATIC");
                        i = consumeGETSTATIC(i);
                        break;

                    case Opcode.PUTFIELD:
                        System.err.println("consumePUTFIELD");
                        i = consumePUTFIELD(i);
                        break;

                    case Opcode.GETFIELD:
                        System.err.println("consumeGETFIELD");
                        i = consumeGETFIELD(i);
                        break;

                    case Opcode.ICONST_0:
                    case Opcode.ICONST_1:
                    case Opcode.ICONST_2:
                    case Opcode.ICONST_3:
                    case Opcode.ICONST_4:
                    case Opcode.ICONST_5:
                    case Opcode.ICONST_M1:
                        System.err.println("consumeLOADCONST");
                        i = consumeLOADCONST(i, opcode - Opcode.ICONST_0);
                        break;

                    case Opcode.LCONST_0:
                    case Opcode.LCONST_1:
                        System.err.println("consumeLOADCONST");
                        i = consumeLOADCONST(i, (long) (opcode - Opcode.LCONST_0));
                        break;

                    case Opcode.ACONST_NULL:
                        System.err.println("consumeLOADNULLCONST");
                        i = consumeLOADNULLCONST(i);
                        break;

                    case Opcode.IADD:
                    case Opcode.FADD:
                    case Opcode.DADD:
                    case Opcode.LADD:
                        System.err.println("consumeOPERATION");
                        i = consumeOPERATION(i, OperationNode.Type.ADD);
                        break;

                    case Opcode.ISUB:
                    case Opcode.LSUB:
                    case Opcode.DSUB:
                    case Opcode.FSUB:
                        System.err.println("consumeOPERATION");
                        i = consumeOPERATION(i, OperationNode.Type.SUB);
                        break;

                    case Opcode.IDIV:
                    case Opcode.LDIV:
                    case Opcode.DDIV:
                    case Opcode.FDIV:
                        System.err.println("consumeOPERATION");
                        i = consumeOPERATION(i, OperationNode.Type.DIV);
                        break;

                    case Opcode.IMUL:
                    case Opcode.FMUL:
                    case Opcode.DMUL:
                    case Opcode.LMUL:
                        System.err.println("consumeOPERATION");
                        i = consumeOPERATION(i, OperationNode.Type.MUL);
                        break;

                    case Opcode.IREM:
                    case Opcode.FREM:
                    case Opcode.DREM:
                    case Opcode.LREM:
                        System.err.println("consumeOPERATION");
                        i = consumeOPERATION(i, OperationNode.Type.REM);
                        break;

                    case Opcode.IOR:
                    case Opcode.LOR:
                        System.err.println("consumeOPERATION");
                        i = consumeOPERATION(i, OperationNode.Type.OR);
                        break;

                    case Opcode.IXOR:
                    case Opcode.LXOR:
                        System.err.println("consumeOPERATION");
                        i = consumeOPERATION(i, OperationNode.Type.XOR);
                        break;
                        
                    case Opcode.IAND:
                    case Opcode.LAND:
                        System.err.println("consumeOPERATION");
                        i = consumeOPERATION(i, OperationNode.Type.AND);
                        break;
                        
                    case Opcode.ISHL:
                    case Opcode.LSHL:
                        System.err.println("consumeOPERATION");
                        i = consumeOPERATION(i, OperationNode.Type.LSH);
                        break;
                        
                    case Opcode.ISHR:
                    case Opcode.LSHR:
                        System.err.println("consumeOPERATION");
                        i = consumeOPERATION(i, OperationNode.Type.RSH);
                        break;
                        
                    case Opcode.SIPUSH:
                        System.err.println("consumeSIPUSH");
                        i = consumeSIPUSH(i);
                        break;
                    case Opcode.POP:
                        System.err.println("consumePOP");
                        i = consumePOP(i);
                        break;
                }
            }

            executed = true;
        }

        return statements;
    }
    
    private int consumePOP(int pos){
        if(!stack.isEmpty()){
            statements.add(stack.pop());
        }
        return pos + 1;
    }

    private int consumeSIPUSH(int pos){
        short value = (short) signedShortAt(pos + 1);
        stack.push(new PrimitiveNode(PrimitiveNode.Type.SHORT, Short.toString(value)));
        return pos + 3;
    }
    
    private int consumeOPERATION(int pos, OperationNode.Type type) {
        ValueNode right = (ValueNode) stack.pop();
        ValueNode left = (ValueNode) stack.pop();

        stack.push(new OperationNode(left, right, type));

        return pos + 1;
    }

    private int consumeLOADNULLCONST(int pos) {
        stack.push(new ReferenceNode("null", "null"));
        return pos + 1;
    }

    private int consumeLOADCONST(int pos, Object value) {
        stack.push(new PrimitiveNode(PrimitiveNode.Type.INT, value.toString()));
        return pos + 1;
    }

    private int consumeGETFIELD(int pos) {

        int index = unsignedShortAt(pos + 1);

        String class_name = pool().getFMIref_ClassName(index);
        String[] type_name = pool().getFMIref_Name_And_Type(index);

        FieldNode field = new FieldNode(class_name.replace('/', '.'), type_name[1],
                type_name[0], false);

        JVMNode node = stack.pop();

        stack.push(new InvocationNode(node, field));

        return pos + 3;
    }

    private int consumePUTFIELD(int pos) {

        int index = unsignedShortAt(pos + 1);

        String class_name = pool().getFMIref_ClassName(index);
        String[] type_name = pool().getFMIref_Name_And_Type(index);

        FieldNode field = new FieldNode(class_name.replace('/', '.'), type_name[1],
                type_name[0], false);

        ValueNode value = (ValueNode) stack.pop();
        JVMNode node = stack.pop();

        statements.add(new AssignNode(new InvocationNode(node, field), value, AssignNode.Type.NONFIRST));


        return pos + 3;
    }

    private int consumeGETSTATIC(int pos) {

        int index = unsignedShortAt(pos + 1);
        String class_name = pool().getFMIref_ClassName(index);
        String[] type_name = pool().getFMIref_Name_And_Type(index);

        FieldNode field = new FieldNode(class_name.replace('/', '.'), type_name[1],
                type_name[0], true);

        stack.push(field);


        return pos + 3;
    }

    private int consumeLOADREFERENCE(int pos, int index) {
        String[] type_name = lvt_attribute.getVariable(index);
        stack.push(new ReferenceNode(type_name[0], type_name[1]));
        return pos + 1;
    }

    private int consumeLOADPRIMITIVE(int pos, int index) {

        String[] type_name = lvt_attribute.getVariable(index);
        stack.push(new PrimitiveNode(type_name[0], type_name[1]));
        return pos + 1;
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
            case DOUBLE:
                Double_Info double_info = (Double_Info) cp_info;
                stack.push(new PrimitiveNode(PrimitiveNode.Type.DOUBLE, double_info.getStringValue()));
                break;
            case LONG:
                Long_Info long_info = (Long_Info) cp_info;
                stack.push(new PrimitiveNode(PrimitiveNode.Type.LONG, long_info.getStringValue()));
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
        return ((code()[pos] & 0xFF) << 8) + (code()[pos + 1] & 0xFF);
    }

    private int signedShortAt(int pos){
        return (code()[pos] << 8) + (code()[pos + 1] & 0xFF);
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
            value = new PrimitiveNode(type_name[0], value.getName());
        } else {
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
