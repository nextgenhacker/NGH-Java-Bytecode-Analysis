package hasnaer.java.bytecode;

import hasnaer.java.bytecode.cp.*;
import hasnaer.java.bytecode.attribute.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author hasnae rehioui
 */
public class ClassFile extends DataInputStream {

    private static int magic = 0xCAFEBABE;

    /**
     * @return the magic
     */
    public static int getMagic() {
        return magic;
    }
    private int minor_version;
    private int major_version;
    private ConstantPool constant_pool;
    private int access_flags;
    private int this_class;
    private int super_class;
    private List<String> interfaces;
    private List<FieldInfo> fields;
    private List<MethodInfo> methods;
    private List<AttributeInfo> attributes;

    public ClassFile(InputStream stream) throws IOException,
            InvalidBytecodeException {
        super(stream);
        loadClass();
    }

    private void loadClass() throws IOException,
            InvalidBytecodeException {

        int test_magic = this.readInt();
        if (test_magic == getMagic()) {

            this.minor_version = this.readUnsignedShort();
            this.major_version = this.readUnsignedShort();

            int constant_pool_count = this.readUnsignedShort();
            this.constant_pool = new ConstantPool(constant_pool_count);
//            System.err.println("load constant pool");
            loadConstantPool(constant_pool_count); // DONE


            this.access_flags = this.readUnsignedShort();
            this.this_class = this.readUnsignedShort();
            this.super_class = this.readUnsignedShort();

            int interfaces_count = this.readUnsignedShort();
            this.interfaces = new ArrayList<String>();
//            System.err.println("load interfaces");
            loadInterfaces(interfaces_count); // DONE

            int fields_count = this.readUnsignedShort();
            this.fields = new ArrayList<FieldInfo>();
//            System.err.println("load fields");
            loadFields(fields_count);

            int methods_count = this.readUnsignedShort();
            this.methods = new ArrayList<MethodInfo>();
//            System.err.println("load methods");
            loadMethods(methods_count);

            int attributes_count = this.readUnsignedShort();
            this.attributes = loadAttributes(attributes_count);

        } else {
            throw new InvalidBytecodeException();
        }
    }

    private List<AttributeInfo> loadAttributes(int attributes_count)
            throws IOException {
        List<AttributeInfo> att_list = new ArrayList<AttributeInfo>();

        for (int i = 0; i < attributes_count; i++) {
            int attribute_name_index = this.readUnsignedShort();
            int attribute_length = this.readInt();
            AttributeInfo.Type att_type = null;
            try {
                att_type = AttributeInfo.Type.valueOf(getConstant_pool().getUTF8_Info(attribute_name_index).getValue());
            } catch (Exception ex) {
                att_type = AttributeInfo.Type.IGNORE;
            }
            AttributeInfo attribute = null;
            switch (att_type) {
                case ConstantValue:
//                    System.err.println("ConstantValue attribute");
                    attribute = new ConstantValue(attribute_name_index,
                            attribute_length, this.readUnsignedShort(), constant_pool);

                    break;
                case Code:
//                    System.err.println("Code attribute");
                    attribute = new Code(attribute_name_index,
                            attribute_length, this.readUnsignedShort(),
                            this.readUnsignedShort(), constant_pool);
                    int code_length = this.readInt();
                    byte[] code = new byte[code_length];
                    this.readFully(code);
                    ((Code) attribute).setCode(code);
                    int exception_table_length = this.readUnsignedShort();

                    for (int j = 0; j < exception_table_length; j++) {
                        ((Code) attribute).addExceptionTableEntry(
                                new Exceptions.Entry(this.readUnsignedShort(),
                                this.readUnsignedShort(),
                                this.readUnsignedShort(),
                                this.readUnsignedShort()));
                    }

                    int att_count = this.readUnsignedShort();
                    ((Code) attribute).setAttributes(loadAttributes(att_count));
                    break;

                case Exceptions:
//                    System.err.println("Exceptions attribute");
                    int number_of_exceptions = this.readUnsignedShort();
                    attribute = new Exceptions(attribute_name_index,
                            attribute_length, number_of_exceptions, constant_pool);


                    for (int j = 0; j < number_of_exceptions; j++) {
                        ((Exceptions) attribute).addExceptionIndex(j,
                                this.readUnsignedShort());
                    }

                    break;

                case InnerClasses:
//                    System.err.println("InnerClasses attribute");
                    int number_of_classes = this.readUnsignedShort();
                    attribute = new InnerClasses(attribute_name_index,
                            attribute_length, number_of_classes, constant_pool);
                    for (int j = 0; j < number_of_classes; j++) {
                        ((InnerClasses) attribute).addInnerClass(
                                new InnerClasses.Entry(this.readUnsignedShort(),
                                this.readUnsignedShort(),
                                this.readUnsignedShort(),
                                this.readUnsignedShort()));
                    }
                    break;
                case Synthetic:
//                    System.err.println("Synthetic attribute");
                    attribute = new Synthetic(attribute_name_index, attribute_length, constant_pool);
                    break;

                case SourceFile:
//                    System.err.println("SourceFile attribute");
                    attribute = new SourceFile(attribute_name_index, attribute_length,
                            this.readUnsignedShort(), constant_pool);
                    break;

                case LineNumberTable:
//                    System.err.println("LineNumberTable attribute");
                    int line_number_table_length = this.readUnsignedShort();
                    attribute = new LineNumberTable(attribute_name_index,
                            attribute_length, line_number_table_length, constant_pool);
                    for (int j = 0; j < line_number_table_length; j++) {
                        ((LineNumberTable) attribute).addEntry(j,
                                this.readUnsignedShort(),
                                this.readUnsignedShort());
                    }
                    break;

                case LocalVariableTable:
//                    System.err.println("LocalVariableTable attribute");
                    int local_variable_table_length = this.readUnsignedShort();
                    attribute = new LocalVariableTable(attribute_name_index,
                            attribute_length, local_variable_table_length,
                            constant_pool);
                    for (int j = 0; j < local_variable_table_length; j++) {
                        ((LocalVariableTable) attribute).addEntry(
                                new LocalVariableTable.Entry(
                                this.readUnsignedShort(),
                                this.readUnsignedShort(),
                                this.readUnsignedShort(),
                                this.readUnsignedShort(),
                                this.readUnsignedShort()));
                    }
                    Collections.sort(((LocalVariableTable) attribute).getTable());

                    break;
                case Deprecated:
//                    System.err.println("Deprecated attribute");
                    attribute = new hasnaer.java.bytecode.attribute.Deprecated(attribute_name_index,
                            attribute_length, constant_pool);
                    break;

                case IGNORE:
                default:
//                    System.err.println("IGNORE attribute");
                    attribute = new DefaultAttribute(attribute_name_index, attribute_length, constant_pool);
                    this.readFully(((DefaultAttribute) attribute).getData());
                    break;
            }
            att_list.add(attribute);

        }
        return att_list;
    }

    private void loadMethods(int methods_count) throws IOException {
        for (int i = 0; i < methods_count; i++) {
            MethodInfo method = new MethodInfo(this.readUnsignedShort(),
                    this.readUnsignedShort(), this.readUnsignedShort(), constant_pool);

            method.setName(constant_pool.getUTF8_Info(method.getName_index()).getValue());
            method.setDescriptor(constant_pool.getUTF8_Info(method.getDescriptor_index()).getValue());
            int attribute_count = this.readUnsignedShort();
            method.setAttributes(this.loadAttributes(attribute_count));
            this.getMethods().add(method);
        }
    }

    private void loadFields(int fields_count) throws IOException {
        for (int i = 0; i < fields_count; i++) {
            FieldInfo field = new FieldInfo(this.readUnsignedShort(),
                    this.readUnsignedShort(), this.readUnsignedShort(), constant_pool);

            field.setName(constant_pool.getUTF8_Info(field.getName_index()).getValue());
            field.setDescriptor(constant_pool.getUTF8_Info(field.getDescriptor_index()).getValue());
            int attributes_count = this.readUnsignedShort();
            field.setAttributes(this.loadAttributes(attributes_count));
            this.getFields().add(field);
        }
    }

    private void loadInterfaces(int interfaces_count) throws IOException {
        for (int i = 0; i < interfaces_count; i++) {
            int index = this.readUnsignedShort();
            this.getInterfaces().add(getInterface(index));
        }
    }

    private String getInterface(int index) {

        int name_index = ((Class_Info) this.getConstant_pool().get(index - 1)).getName_index();
        return ((UTF8_Info) this.getConstant_pool().get(name_index - 1)).getValue();

    }

    private void loadConstantPool(int constant_pool_count) throws IOException {
        System.err.println("load constant_pool size: " + constant_pool_count);
        for (int i = 1; i < constant_pool_count; i++) {
            int tag = this.readUnsignedByte();
            CP_Info.Tag ctag = CP_Info.Tag.fromValue(tag);

            System.err.print(i + "  tag= " + tag + " ");
            
            switch (ctag) {
                case CLASS:
                    System.err.println("CLASS_Info");
                    this.getConstant_pool().add(new Class_Info(ctag,
                            this.readUnsignedShort()));
                    break;

                case FIELDREF:
                case METHODREF:
                case INTERFACEMETHODREF:
                    System.err.println("FMIref_Info");
                    this.getConstant_pool().add(new FMIref_Info(ctag,
                            this.readUnsignedShort(), this.readUnsignedShort()));
                    break;

                case STRING:
                    System.err.println("STRING_Info");
                    this.getConstant_pool().add(new String_Info(ctag,
                            this.readUnsignedShort()));
                    break;

                case INTEGER:
                    System.err.println("INTEGER_Info");
                    this.getConstant_pool().add(new Integer_Info(ctag,
                            this.readInt()));
                    break;
                case FLOAT:
                    System.err.println("FLOAT_Info");
                    this.getConstant_pool().add(new Float_Info(ctag,
                            this.readFloat()));
                    break;
                case LONG:
                    System.err.println("LONG_Info");
                    this.getConstant_pool().add(new Long_Info(ctag,
                            this.readLong()));
                    // add padding
                    this.getConstant_pool().add(new Padding());
                    i++;
                    break;
                case DOUBLE:
                    System.err.println("DOUBLE_Info");
                    this.getConstant_pool().add(new Double_Info(ctag,
                            this.readDouble()));
                    this.getConstant_pool().add(new Padding());
                    i++;
                    break;
                case NAME_AND_TYPE:
                    System.err.println("NameAndType_Info");
                    this.getConstant_pool().add(new NameAndType_Info(ctag,
                            this.readUnsignedShort(), this.readUnsignedShort()));
                    break;
                case UTF8:
                    System.err.println("UTF8_Info");
                    this.getConstant_pool().add(new UTF8_Info(ctag, this.readUTF()));
                    break;
            }
        }
    }

    public String getClassName() {
        int utf = getConstant_pool().getClass_Info(getThis_class()).getName_index();
        return getConstant_pool().getUTF8_Info(utf).getValue().replace("/", ".");
    }

    public String getSuperClassName() {
        int utf = getConstant_pool().getClass_Info(getSuper_class()).getName_index();
        return getConstant_pool().getUTF8_Info(utf).getValue().replace("/", ".");
    }

    public MethodInfo getMethod(String name) {
        for (MethodInfo method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    public FieldInfo getField(String name) {
        for (FieldInfo field : fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        JFileChooser browser = new JFileChooser();
        int result = browser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            ClassFile c_file = new ClassFile(new FileInputStream(browser.getSelectedFile()));
//            System.err.println("this_class=" + c_file.getName());
        }

    }

    /**
     * @return the minor_version
     */
    public int getMinor_version() {
        return minor_version;
    }

    /**
     * @return the major_version
     */
    public int getMajor_version() {
        return major_version;
    }

    /**
     * @return the constant_pool
     */
    public ConstantPool getConstant_pool() {
        return constant_pool;
    }

    /**
     * @return the access_flags
     */
    public int getAccess_flags() {
        return access_flags;
    }

    /**
     * @return the this_class
     */
    public int getThis_class() {
        return this_class;
    }

    /**
     * @return the super_class
     */
    public int getSuper_class() {
        return super_class;
    }

    /**
     * @return the interfaces
     */
    public List<String> getInterfaces() {
        return interfaces;
    }

    /**
     * @return the fields
     */
    public List<FieldInfo> getFields() {
        return fields;
    }

    /**
     * @return the methods
     */
    public List<MethodInfo> getMethods() {
        return methods;
    }

    /**
     * @return the attributes
     */
    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    public boolean isInterface() {
        return AccessFlags.isInterface(this.access_flags);
    }
}