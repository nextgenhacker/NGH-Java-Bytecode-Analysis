/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hasnaer.java.bytecode.cp;

/**
 *
 * @author hasnae rehioui
 */
public class FMIref_Info extends CP_Info {

    private int class_index;
    private int name_and_type_index;
    
    public FMIref_Info(Tag ctag, int class_index, 
            int name_and_type_index) {
        super(ctag);
        this.class_index = class_index;
        this.name_and_type_index = name_and_type_index;
    }
    
}
