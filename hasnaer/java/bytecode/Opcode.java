package hasnaer.java.bytecode;

/**
 *
 * @author hasnae rehioui
 */
public class Opcode {

    public static int AALOAD = 0x32;
    public static int AASTORE = 0x53;

    public static final int BIPUSH = 0x10;

    public static final int LDC = 0x12;

    public static final int ILOAD_1 = 0x1b;

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
    public static final int RETURN = 0xb1;
    public static final int INVOKESPECIAL = 0xb7;
    public static final int NEW = 0xbb;

    public static void main(String[] args) {
        System.err.println(((byte) -79) & 0xFF);
    }
}
