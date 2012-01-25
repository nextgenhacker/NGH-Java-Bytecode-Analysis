package hasnaer.java.bytecode.cp;

import hasnaer.java.bytecode.Descriptor;
import java.util.ArrayList;

/**
 *
 * @author hasnae rehioui
 */
public class ConstantPool extends ArrayList<CP_Info> {
    
    public ConstantPool(int size){
        super(size);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for(CP_Info info : this){
            builder.append("index= " + i++);
            builder.append(" | ");
            builder.append(info.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    public CP_Info getCP_Info(int index){
        return this.get(index - 1);
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


    public String getString_Info_Value(int index){
        return getUTF8_Info(getString_Info(index).getString_Index()).getValue();
    }

    public String getFMIref_ClassName(int index){
        return getUTF8_Info(getClass_Info(getFMIref_Info(index).getClass_Index()).getName_index()).getValue();
    }

    public String[] getFMIref_Name_And_Type(int index){
        String[] retvalue = new String[2];

        NameAndType_Info nat = getNameAndType_Info(getFMIref_Info(index).getName_And_Type_Index());

        retvalue[0] = getUTF8_Info(nat.getDescriptor_Index()).getValue();
        retvalue[1] = getUTF8_Info(nat.getName_Index()).getValue();
        
        return retvalue;
    }
}