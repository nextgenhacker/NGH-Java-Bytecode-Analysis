package hasnaer.java.bytecode;

/**
 *
 * @author hasnae rehioui
 */
public class Opcode {
 
    public static int AALOAD = 0x32;
    public static int AASTORE = 0x53;
    
    public static final int BIPUSH = 0x10;
    
    public static final int I_LOAD_1 = 0x1b;
    
    public static final int I_STORE_1 = 0x3c;
    public static final int I_STORE_2 = 0x3d;
    
    public static final int RETURN = 0xb1;
    
    
    public static void main(String[] args){
        System.err.println(RETURN);
    }
    
}