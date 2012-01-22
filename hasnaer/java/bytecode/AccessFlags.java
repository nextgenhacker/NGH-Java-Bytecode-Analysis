package hasnaer.java.bytecode;

/**
 *
 * @author hasnae rehioui
 */
public class AccessFlags {

    public static int PUBLIC = 0x0001;
    public static int PRIVATE = 0x0002;
    public static int PROTECTED = 0x0004;
    public static int STATIC = 0x0008;
    public static int FINAL = 0x0010;
    public static int ABSTRACT = 0x0400;
    
    public static int INTERFACE = 0x0200;
    
    public static int TRANSIENT = 0x0080;
    public static int VOLATIlE = 0x0040;
    
    public static int SYNCHRONIZED = 0x0020;
    public static int NATIVE = 0x0100;
    
    public static int STRICT = 0x0800;
    
    public static boolean isPublic(int flags){
        return (flags & PUBLIC) != 0 ;
    }
    
    public static boolean isPrivate(int flags){
        return (flags & PRIVATE) != 0;
    }
    
    public static boolean isInterface(int flags){
        return (flags & INTERFACE) != 0;
    }
    
    public static boolean isProtected(int flags){
        return (flags & PROTECTED) != 0;
    }
    
    public static boolean isStatic(int flags){
        return (flags & STATIC) != 0;
    }
    
    public static boolean isFinal(int flags){
        return (flags & FINAL) != 0;
    }
    
    public static boolean isAbstract(int flags){
        return (flags & ABSTRACT) != 0;
    }
    
    public static boolean isTransient(int flags){
        return (flags & TRANSIENT) != 0;
    }
    
    public static boolean isVolatile(int flags){
        return (flags & VOLATIlE) != 0;
    }
    
    public static boolean isSynchronized(int flags){
        return (flags & SYNCHRONIZED) != 0;
    }
    
    public static boolean isNative(int flags){
        return (flags & NATIVE) != 0;
    }
    
    public static boolean isStrict(int flags){
        return (flags & STRICT) != 0;
    }
    
    public static String fieldAccess(int flags){
        
        StringBuilder builder = new StringBuilder();
        if(isPrivate(flags)){
            builder.append("private ");
        } else if(isProtected(flags)){
            builder.append("protected ");
        } else if(isPublic(flags)){
            builder.append("private ");
        }
        
        if(isFinal(flags)){
            builder.append("final ");
        }
        if(isStatic(flags)){
            builder.append("static ");
        }
        
        if(isVolatile(flags)){
            builder.append("volatile ");
        }
        if(isTransient(flags)){
            builder.append("transient ");
        }
        
        return builder.toString();
    }
    
    public static String methodAccess(int flags){
        StringBuilder builder = new StringBuilder();
        
        if(isPrivate(flags)){
            builder.append("private ");
        } else if(isPublic(flags)){
            builder.append("public ");
        } else if(isProtected(flags)){
            builder.append("protected ");
        }
    
        if(isFinal(flags)){
            builder.append("final ");
        }
        if(isStatic(flags)){
            builder.append("static ");
        }
        
        if(isSynchronized(flags)){
            builder.append("synchronized ");
        }
        
        if(isNative(flags)){
            builder.append("native ");
        }
        
        if(isAbstract(flags)){
            builder.append("abstract ");
        }
        
        if(isStrict(flags)){
            builder.append("strictfp ");
        }
        
        return builder.toString();
    }
}