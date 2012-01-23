package hasnaer.java.bytecode;

/**
 *
 * @author hasnae rehioui
 */
public class Descriptor {

    public static String fieldDataType(String descriptor) {
        if (descriptor.equals("B")) {
            return "byte ";
        }

        if (descriptor.equals("C")) {
            return "char ";
        }
        
        if(descriptor.equals("D")){
            return "double ";
        }
        
        if(descriptor.equals("F")){
            return "float ";
        }
        if(descriptor.equals("I")){
            return "int ";
        }
        if(descriptor.equals("J")){
            return "long ";
        }
        if(descriptor.equals("S")){
            return "short ";
        }
        if(descriptor.equals("Z")){
            return "boolean ";
        }
        if(descriptor.equals("V")){
            return "void ";
        }
        
        if(descriptor.startsWith("L")){
            String classname = descriptor.replace(';', ' ');
            
            return classname.replace('/', '.').substring(1);
        }
        
        return fieldDataType(descriptor.substring(1)) + "[]";
    }
    
    public static String getReturnDescriptor(String descriptor){
        int index = descriptor.indexOf(")") + 1;
        return fieldDataType(descriptor.substring(index));
    }

    public static int getParamCount(String descriptor) {
        int start_index = descriptor.indexOf("(");
        int end_index = descriptor.indexOf(")");
        
        String params = descriptor.substring(start_index + 1, end_index);
        
        if(params.length() == 0){
            return 0;
        }
        
        return params.split(";").length;
    }
}