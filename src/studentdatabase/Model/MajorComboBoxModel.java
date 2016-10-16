/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase.Model;

import java.util.Vector;

/**
 *
 * @author derry
 */
public class MajorComboBoxModel {
    Vector v = new Vector();
    public MajorComboBoxModel(){
        
        v.add("MIS");
        v.add("CS");
        v.add("BA");
        v.add("EE");
        v.add("CE");
        v.add("ME");
        v.add("Econ");
    }
    public Vector getData(){
        return v;
    }
}
