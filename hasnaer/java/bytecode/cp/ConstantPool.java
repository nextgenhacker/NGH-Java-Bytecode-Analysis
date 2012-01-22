package hasnaer.java.bytecode.cp;

import java.util.ArrayList;

/**
 *
 * @author hasnae rehioui
 */
public class ConstantPool extends ArrayList<CP_Info> {
    
    public ConstantPool(int size){
        super(size);
    }
    
    public Class_Info getClass_Info(int index){
        return (Class_Info) this.get(index - 1);
    }
    
    public Double_Info getDouble_Info(int index){
        return (Double_Info) this.get(index - 1);
    }
    
    public FMIref_Info getFMIref_Info(int index){
        return (FMIref_Info) this.get(index - 1);
    }
    
    public Float_Info getFloat_Info(int index){
        return (Float_Info) this.get(index - 1);
    }
    
    public Integer_Info getInteger_Info(int index){
        return (Integer_Info) this.get(index - 1);
    }
    
    public Long_Info getLong_Info(int index){
        return (Long_Info) this.get(index - 1);
    }
    
    public NameAndType_Info getNameAndType_Info(int index){
        return (NameAndType_Info) this.get(index - 1);
    }
    
    public String_Info getString_Info(int index){
        return (String_Info) this.get(index - 1);
    }
    
    public UTF8_Info getUTF8_Info(int index){
        return (UTF8_Info) this.get(index - 1);
    }
}