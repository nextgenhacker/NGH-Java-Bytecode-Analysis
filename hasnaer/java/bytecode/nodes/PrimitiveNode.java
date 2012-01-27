package hasnaer.java.bytecode.nodes;

/**
 *
 * @author hasnae rehioui
 */
public class PrimitiveNode extends ValueNode {

    public PrimitiveNode(String type, String name) {
        super(type, name);
    }

    public PrimitiveNode(Type type, String name) {
        super(type.value(), name);
    }

    public String toJava(String indent) {
        return indent + toString();
    }

    public enum Type {

        BOOLEAN("boolean", 4),
        CHAR("char", 5),
        FLOAT("float", 6),
        DOUBLE("double", 7),
        BYTE("byte", 8),
        SHORT("short", 9),
        INT("int", 10),
        LONG("long", 11);

        public static Type fromCode(int atype) {
            for(Type type : values()){
                if(type.code == atype){
                    return type;
                }
            }
            return null;
        }
        String value;
        int code;

        Type(String value, int code) {
            this.value = value;
            this.code = code;
        }

        public String value() {
            return this.value;
        }
        
    }

    @Override
    public String toString() {
        Type t = Type.valueOf(type.trim().toUpperCase());

        switch (t) {
            case BOOLEAN:
                if (name.equals("0")) {
                    return "false";
                } else if (name.equals("1")) {
                    return "true";
                }

            case CHAR:
                try {
                    char c = (char) Integer.parseInt(name);
                    return "'" + String.valueOf(c) + "'";
                } catch (Exception ex) {
                }

            default:
                return name;

        }

    }

    public static void main(String[] args) {
        Type t = Type.valueOf("DOUBLE");

        System.err.println(t);
    }
}
