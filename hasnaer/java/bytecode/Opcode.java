package hasnaer.java.bytecode;

/**
 *
 * @author hasnae rehioui
 */
public class Opcode {

    public static final int ACONST_NULL = 0x01;
    public static final int ICONST_M1 = 0x02;
    public static final int ICONST_0 = 0x03;
    public static final int ICONST_1 = 0x04;
    public static final int ICONST_2 = 0x05;
    public static final int ICONST_3 = 0x06;
    public static final int ICONST_4 = 0x07;
    public static final int ICONST_5 = 0x08;
    
    public static final int LCONST_0 = 0x09;
    public static final int LCONST_1 = 0x0a;
    
    public static final int BIPUSH = 0x10;

    public static final int LDC = 0x12;
    public static final int LDC_W = 0x13;
    public static final int LDC2_W = 0x14;
    
    public static final int ILOAD = 0x15;
    public static final int LLOAD = 0x16;
    public static final int FLOAD = 0x17;
    public static final int DLOAD = 0x18;
    public static final int ALOAD = 0x19;

    
    public static final int ILOAD_0 = 0x1a;
    public static final int ILOAD_1 = 0x1b;
    public static final int ILOAD_2 = 0x1c;
    public static final int ILOAD_3 = 0x1d;

    public static final int LLOAD_0 = 0x1e;
    public static final int LLOAD_1 = 0x1f;
    public static final int LLOAD_2 = 0x20;
    public static final int LLOAD_3 = 0x21;

    public static final int FLOAD_0 = 0x22;
    public static final int FLOAD_1 = 0x23;
    public static final int FLOAD_2 = 0x24;
    public static final int FLOAD_3 = 0x25;

    public static final int DLOAD_0 = 0x26;
    public static final int DLOAD_1 = 0x27;
    public static final int DLOAD_2 = 0x28;
    public static final int DLOAD_3 = 0x29;

    public static final int ALOAD_0 = 0x2a;
    public static final int ALOAD_1 = 0x2b;
    public static final int ALOAD_2 = 0x2c;
    public static final int ALOAD_3 = 0x2d;
        
    public static final int ISTORE = 0x36;
    public static final int LSTORE = 0x37;
    public static final int FSTORE = 0x38;
    public static final int DSTORE = 0x39;
    public static final int ASTORE = 0x3a;

    public static final int ISTORE_0 = 0x3b;
    public static final int ISTORE_1 = 0x3c;
    public static final int ISTORE_2 = 0x3d;
    public static final int ISTORE_3 = 0x3e;

    public static final int LSTORE_0 = 0x3f;
    public static final int LSTORE_1 = 0x40;
    public static final int LSTORE_2 = 0x41;
    public static final int LSTORE_3 = 0x42;

    public static final int FSTORE_0 = 0x43;
    public static final int FSTORE_1 = 0x44;
    public static final int FSTORE_2 = 0x45;
    public static final int FSTORE_3 = 0x46;

    public static final int DSTORE_0 = 0x47;
    public static final int DSTORE_1 = 0x48;
    public static final int DSTORE_2 = 0x49;
    public static final int DSTORE_3 = 0x4a;

    public static final int ASTORE_0 = 0x4b;
    public static final int ASTORE_1 = 0x4c;
    public static final int ASTORE_2 = 0x4d;
    public static final int ASTORE_3 = 0x4e;


    public static final int DUP = 0x59;
    
    public static final int IADD = 0x60;
    public static final int LADD = 0x61;
    public static final int FADD = 0x62;
    public static final int DADD = 0x63;
    
    public static final int ISUB = 0x64;
    public static final int LSUB = 0x65;
    public static final int FSUB = 0x66;
    public static final int DSUB = 0x67;
    
    public static final int IMUL = 0x68;
    public static final int LMUL = 0x69;
    public static final int FMUL = 0x6a;
    public static final int DMUL = 0x6b;
    
    public static final int IDIV = 0x6c;
    public static final int LDIV = 0x6d;
    public static final int FDIV = 0x6e;
    public static final int DDIV = 0x6f;
    
    public static final int IREM = 0x70;
    public static final int LREM = 0x71;
    public static final int FREM = 0x72;
    public static final int DREM = 0x73;
            
    
    public static final int RETURN = 0xb1;
    public static final int GETSTATIC = 0xb2;

    public static final int GETFIELD = 0xb4;
    public static final int PUTFIELD = 0xb5;
    
    public static final int INVOKEVIRTUAL = 0xb6;
    public static final int INVOKESPECIAL = 0xb7;
    public static final int NEW = 0xbb;

    
}
