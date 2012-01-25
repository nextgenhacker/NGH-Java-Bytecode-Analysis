package hasnaer.java.bytecode;

import hasnaer.java.bytecode.attribute.Code;
import hasnaer.java.bytecode.attribute.LocalVariableTable;
import hasnaer.java.bytecode.nodes.JVMNode;
import java.io.FileInputStream;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author hasnae rehioui
 */
public class SourceCodeBuilder {

    private static String OBJECT_CLASS = "java.lang.Object";
    private static String INDENT = "\t";

    public static String visitClass(ClassFile class_file) {
        StringBuilder builder = new StringBuilder();

        if (AccessFlags.isPrivate(class_file.getAccess_flags())) {
            builder.append("private ");
        } else if (AccessFlags.isPublic(class_file.getAccess_flags())) {
            builder.append("public ");
        }

        if (class_file.isInterface()) {
            builder.append("interface ");
        } else {
            builder.append("class ");
        }

        builder.append(class_file.getClassName());
        builder.append(" ");

        String super_class_name = class_file.getSuperClassName();
        if (!super_class_name.equals(OBJECT_CLASS)) {
            builder.append("extends ");
            builder.append(super_class_name);
            builder.append(" ");
        }

        List<String> interfaces = class_file.getInterfaces();
        if (!interfaces.isEmpty()) {
            builder.append("implements ");
            builder.append(interfaces.get(0));
            for (int i = 1; i < interfaces.size(); i++) {
                builder.append(", ");
                builder.append(interfaces.get(i));
            }
            builder.append(" ");
        }
        builder.append("{\n");
        builder.append(visitFields(class_file.getFields()));
        builder.append(visitMethods(class_file.getMethods()));
        builder.append("}");


        return builder.toString();
    }

    public static String visitFields(List<FieldInfo> fields) {

        StringBuilder builder = new StringBuilder("\n");

        for (FieldInfo field : fields) {

            builder.append(INDENT);
            builder.append(AccessFlags.fieldAccess(field.getAccess_flags()));
            builder.append(Descriptor.fieldDataType(field.getDescriptor()));
            builder.append(field.getName());
            builder.append(";\n");

        }

        return builder.toString();
    }

    public static String visitMethods(List<MethodInfo> methods) {

        StringBuilder builder = new StringBuilder("\n");

        for (MethodInfo method : methods) {

            builder.append(visitMethod(method));

        }

        return builder.toString();
    }

    public static String visitMethod(MethodInfo method) {




        StringBuilder builder = new StringBuilder();

        Code code_attribute = method.getCodeAttribute();
        LocalVariableTable lvt_attribute = code_attribute.getLocalVariableTableAttribute();

        System.err.println("constant_pool= ");
        System.err.println(lvt_attribute.getConstantPool().toString());



        if (lvt_attribute != null) {

            lvt_attribute.init();

            builder.append(INDENT);
            builder.append(AccessFlags.methodAccess(method.getAccess_flags()));
            builder.append(Descriptor.getReturnDescriptor(method.getDescriptor()));
            builder.append(method.getName());
            int numOfParameters = Descriptor.getParamCount(method.getDescriptor());
            builder.append(" (");
            if (numOfParameters > 0) {
                String[] variable = lvt_attribute.getVariable(lvt_attribute.THIS_INDEX + 1);
                builder.append(variable[0]);
                builder.append(" ");
                builder.append(variable[1]);

                for (int i = 2; i <= numOfParameters; i++) {
                    builder.append(", ");
                    variable = lvt_attribute.getVariable(lvt_attribute.THIS_INDEX + i);
                    builder.append(variable[0]);
                    builder.append(" ");
                    builder.append(variable[1]);
                }
            }

            builder.append(") {\n");

            StatementBuilder st_builder = new StatementBuilder(code_attribute, 0,
                    code_attribute.getCode().length, lvt_attribute);
            st_builder.build();
            List<JVMNode> statements = st_builder.getStatements();

            for (JVMNode statement : statements) {
                builder.append(INDENT + INDENT);
                builder.append(statement.toString());
                builder.append(";\n");
            }


            builder.append(INDENT);
            builder.append("}\n\n");

        }


        return builder.toString();
    }

    public static void main(String[] args) throws Exception {
        JFileChooser browser = new JFileChooser();
        int result = browser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            ClassFile c_file = new ClassFile(new FileInputStream(browser.getSelectedFile()));


            System.err.println("\n\n" + SourceCodeBuilder.visitMethod(c_file.getMethod("test")));
//            System.out.println(SourceCodeBuilder.visitClass(c_file));

        }

    }
}
