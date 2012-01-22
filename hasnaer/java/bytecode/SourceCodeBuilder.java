package hasnaer.java.bytecode;

import hasnaer.java.bytecode.cp.ConstantPool;
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
        
    public static String visitClass(ClassFile class_file){
        StringBuilder builder = new StringBuilder();
        
        if(AccessFlags.isPrivate(class_file.getAccess_flags())){
            builder.append("private ");
        } else if(AccessFlags.isPublic(class_file.getAccess_flags())){
            builder.append("public ");
        }
        
        if(class_file.isInterface()){
            builder.append("interface ");
        } else{
            builder.append("class ");
        }
        
        builder.append(class_file.getClassName());
        builder.append(" ");
        
        String super_class_name = class_file.getSuperClassName();
        if(!super_class_name.equals(OBJECT_CLASS)){
            builder.append("extends ");
            builder.append(super_class_name);
            builder.append(" ");
        }
        
        List<String> interfaces = class_file.getInterfaces();
        if(!interfaces.isEmpty()){
            builder.append("implements ");
            builder.append(interfaces.get(0));
            for(int i = 1; i < interfaces.size(); i++){
                builder.append(", ");
                builder.append(interfaces.get(i));
            }
            builder.append(" ");
        }
        
        builder.append("{\n");
        
        builder.append(visitFields(class_file.getFields(), class_file.getConstant_pool()));
        
        builder.append(visitMethods(class_file.getMethods(), class_file.getConstant_pool()));
        
        builder.append("}");
        
        
        return builder.toString();
    }
    
    public static String visitFields(List<FieldInfo> fields, 
            ConstantPool constant_pool){
        
        StringBuilder builder = new StringBuilder("\n");
        
        for(FieldInfo field : fields){
            
            builder.append(INDENT);
            builder.append(AccessFlags.fieldAccess(field.getAccess_flags()));
            builder.append(Descriptor.fieldDataType(field.getDescriptor()));
            builder.append(field.getName());
            builder.append(";\n");
            
        }
        
        return builder.toString();
    }
    
    public static String visitMethods(List<MethodInfo> methods, 
            ConstantPool constant_pool){
        
        StringBuilder builder = new StringBuilder("\n");
        
        for(MethodInfo method  : methods){
            
            builder.append(INDENT);
            builder.append(AccessFlags.methodAccess(method.getAccess_flags()));
            builder.append(Descriptor.getReturnDescriptor(method.getDescriptor()));
            builder.append(method.getName());
            builder.append("(){\n");
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
//            System.err.println("this_class=" + c_file.getName());
            System.out.println(SourceCodeBuilder.visitClass(c_file));
        }

    }
}