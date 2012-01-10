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

    private List<AttributeInfo> loadAttributes(int attributes_count) {
        List<AttributeInfo> att_list = new ArrayList<AttributeInfo>();
        return att_list;
    }

    private void loadMethods(int methods_count) {
        for (int i = 0; i < methods_count; i++) {
        }
    }

    private void loadFields(int fields_count) throws IOException {
        for (int i = 0; i < fields_count; i++) {
            FieldInfo field = new FieldInfo(this.readUnsignedShort(),
                    this.readUnsignedShort(), this.readUnsignedShort());
            
            int attributes_count = this.readUnsignedShort();
            field.setAttributes(this.loadAttributes(attributes_count));
        }
    }

    private void loadInterfaces(int interfaces_count) throws IOException {
        for (int i = 0; i < interfaces_count; i++) {
            int index = this.readUnsignedShort();
            this.interfaces.add(getInterface(index));
        }
    }

    private String getInterface(int index){
    
        int name_index = ((Class_Info)this.constant_pool.get(index - 1)).getName_index();
        return ((UTF8_Info)this.constant_pool.get(name_index - 1)).getValue();
        
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
