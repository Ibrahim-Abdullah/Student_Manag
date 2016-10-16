/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase;

import studentdatabase.Controller.TableFrameController;
import studentdatabase.Model.StudentTableModel;
import studentdatabase.View.TableFrame;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class StudentDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudentTableModel m=StudentTableModel.getInstance();
        TableFrame v= new TableFrame();
        TableFrameController tvc= new TableFrameController(m,v);
        tvc.control(); 
    }
    
}
