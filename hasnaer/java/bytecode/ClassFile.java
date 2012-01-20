package hasnaer.java.bytecode;

import hasnaer.java.bytecode.cp.*;
import hasnaer.java.bytecode.attribute.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hasnae rehioui
 */
public class ClassFile extends DataInputStream {

    private static int magic = 0xCAFEBABE;
    private int minor_version;
    private int major_version;
    private List<CP_Info> constant_pool;
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
        if (test_magic == magic) {

            this.minor_version = this.readUnsignedShort();
            this.major_version = this.readUnsignedShort();

            int constant_pool_count = this.readUnsignedShort();
            this.constant_pool = new ArrayList<CP_Info>();
            loadConstantPool(constant_pool_count); // DONE

            this.access_flags = this.readUnsignedShort();
            this.this_class = this.readUnsignedShort();
            this.super_class = this.readUnsignedShort();

            int interfaces_count = this.readUnsignedShort();
            this.interfaces = new ArrayList<String>();
            loadInterfaces(interfaces_count); // DONE

            int fields_count = this.readUnsignedShort();
            this.fields = new ArrayList<FieldInfo>();
            loadFields(fields_count);

            int methods_count = this.readUnsignedShort();
            this.methods = new ArrayList<MethodInfo>();
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
        AttributeInfo attribute = null;
        for (int i = 0; i < attributes_count; i++) {
            int attribute_name_index = this.readUnsignedShort();
            int attribute_length = this.readInt();
            AttributeInfo.Type att_type = AttributeInfo.Type.valueOf(((UTF8_Info) this.constant_pool.get(attribute_name_index - 1)).getValue());

            switch (att_type) {
                case ConstantValue:
                    attribute = new ConstantValue(attribute_name_index,
                            attribute_length, this.readUnsignedShort());
                    
                    break;
                case Code:
                    attribute = new Code(attribute_name_index,
                            attribute_length, this.readUnsignedShort(),
                            this.readUnsignedShort());
                    int code_length = this.readInt();
                    byte[] code = new byte[code_length];
                    this.readFully(code);
                    ((Code)attribute).setCode(code);
                    int exception_table_length = this.readUnsignedShort();

                    for (int j = 0; j < exception_table_length; j++) {
                        ((Code)attribute).addExceptionTableEntry(
                                new ExceptionTableEntry(this.readUnsignedShort(), 
                                        this.readUnsignedShort(), 
                                        this.readUnsignedShort(), 
                                        this.readUnsignedShort()));
                    }

                    int att_count = this.readUnsignedShort();
                    ((Code)attribute).setAttributes(loadAttributes(att_count));
                    break;

                case Exceptions:
                    int number_of_exceptions = this.readUnsignedShort();
                    attribute = new Exceptions(attribute_name_index,
                            attribute_length, number_of_exceptions);
                    

                    for (int j = 0; j < number_of_exceptions; j++) {
                        ((Exceptions)attribute).addExceptionIndex(j, this.readUnsignedShort());                        
                    }
                    
                    break;
                    
                case InnerClasses:
                    attribute = new InnerClasses(attribute_name_index,
                            attribute_length);
                    int number_of_classes = this.readUnsignedShort();
                    for(int j = 0; j < number_of_classes; j++){
                        ((InnerClasses)attribute).addInnerClass(
                                new InnerClasses.Entry(this.readUnsignedShort(), 
                                        this.readUnsignedShort(), 
                                        this.readUnsignedShort(), 
                                        this.readUnsignedShort()));
                    }
                    break;
                case Synthetic:
                    attribute = new Synthetic(attribute_name_index, attribute_length);
                    break;
                case SourceFile:
                    attribute = new SourceFile(attribute_name_index, attribute_length, this.readUnsignedShort());
                    break;
                case LineNumberTable:
                    int line_number_table_length = this.readUnsignedShort();
                    attribute = new LineNumberTable(attribute_name_index,
                            attribute_length, line_number_table_length);
                    for(int j = 0; j < line_number_table_length; j++){
                        ((LineNumberTable)attribute).addEntry(j, 
                                this.readUnsignedShort(), 
                                this.readUnsignedShort());
                    }
                    break;
                case LocalVariableTable:
                    int local_variable_table_length = this.readUnsignedShort();
                    attribute = new LocalVariableTable(attribute_name_index, 
                            attribute_length, local_variable_table_length);
                    for(int j = 0; j < local_variable_table_length; j++){
                        ((LocalVariableTable)attribute).addEntry(
                                new LocalVariableTable.Entry(
                                        this.readUnsignedShort(), 
                                        this.readUnsignedShort(),
                                        this.readUnsignedShort(), 
                                        this.readUnsignedShort(), 
                                        this.readUnsignedShort()));
                    }
                    break;
                case Deprecated:
                    attribute = new hasnaer.java.bytecode.attribute.Deprecated(attribute_name_index, 
                            attribute_length);
                    break;                    
            }
            
            att_list.add(attribute);
        }
        return att_list;
    }

    private void loadMethods(int methods_count) throws IOException {
        for (int i = 0; i < methods_count; i++) {
            MethodInfo method = new MethodInfo(this.readUnsignedShort(),
                    this.readUnsignedShort(), this.readUnsignedShort());
            int attribute_count = this.readUnsignedShort();
            method.setAttributes(this.loadAttributes(attribute_count));
            this.methods.add(method);
        }
    }

    private void loadFields(int fields_count) throws IOException {
        for (int i = 0; i < fields_count; i++) {
            FieldInfo field = new FieldInfo(this.readUnsignedShort(),
                    this.readUnsignedShort(), this.readUnsignedShort());

            int attributes_count = this.readUnsignedShort();
            field.setAttributes(this.loadAttributes(attributes_count));
            this.fields.add(field);
        }
    }

    private void loadInterfaces(int interfaces_count) throws IOException {
        for (int i = 0; i < interfaces_count; i++) {
            int index = this.readUnsignedShort();
            this.interfaces.add(getInterface(index));
        }
    }

    private String getInterface(int index) {

        int name_index = ((Class_Info) this.constant_pool.get(index - 1)).getName_index();
        return ((UTF8_Info) this.constant_pool.get(name_index - 1)).getValue();

    }

    private void loadConstantPool(int constant_pool_count) throws IOException {
        for (int i = 1; i < constant_pool_count; i++) {
            int tag = this.readUnsignedByte();
            CP_Info.Tag ctag = CP_Info.Tag.fromValue(tag);

            switch (ctag) {
                case CLASS:
                    this.constant_pool.add(new Class_Info(ctag,
                            this.readUnsignedShort()));
                    break;

                case FIELDREF:
                case METHODREF:
                case INTERFACEMETHODREF:
                    this.constant_pool.add(new FMIref_Info(ctag,
                            this.readUnsignedShort(), this.readUnsignedShort()));
                    break;

                case STRING:
                    this.constant_pool.add(new String_Info(ctag,
                            this.readUnsignedShort()));
                    break;

                case INTEGER:
                    this.constant_pool.add(new Integer_Info(ctag,
                            this.readInt()));
                    break;
                case FLOAT:
                    this.constant_pool.add(new Float_Info(ctag,
                            this.readFloat()));
                    break;
                case LONG:
                    this.constant_pool.add(new Long_Info(ctag,
                            this.readLong()));
                    break;
                case DOUBLE:
                    this.constant_pool.add(new Double_Info(ctag,
                            this.readDouble()));
                    break;
                case NAME_AND_TYPE:
                    this.constant_pool.add(new NameAndType_Info(ctag,
                            this.readUnsignedShort(), this.readUnsignedShort()));
                    break;
                case UTF8:
                    this.constant_pool.add(new UTF8_Info(ctag, this.readUTF()));
                    break;
            }
        }
    }
}
