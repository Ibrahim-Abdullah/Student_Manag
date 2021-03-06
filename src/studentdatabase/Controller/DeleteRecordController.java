/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import studentdatabase.Model.Student;
import studentdatabase.Model.StudentTableModel;
import studentdatabase.View.TableFrame;
import studentdatabase.View.UpdatedialoguePopUp;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class DeleteRecordController implements ActionListener{
    UpdatedialoguePopUp view;
    StudentTableModel model;
    TableFrame tf;
    
    public DeleteRecordController(StudentTableModel studentTableModel,UpdatedialoguePopUp updatePopUp, TableFrame tf ){
        view = updatePopUp;
        model = studentTableModel;
        this.tf = tf;
    }
    
    public void control(){
        view.getOkButton().addActionListener(this);
        view.getCloseButton().addActionListener(this);
    }
   public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==view.getCloseButton() ){               
            view.dispose();
        }
        if (ae.getActionCommand().equalsIgnoreCase("OK")){ 
            String studentID = view.getStudentID();
            ArrayList<Student> studList = model.getStudentArrayList();
            Boolean studentIDExist = false;
            
            for(Student stud: studList){
                if(stud.getStudentID().equalsIgnoreCase(studentID)){
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Confirm Record Delete");
                    if(dialogResult == JOptionPane.YES_OPTION){
                        model.deleteRecord(studentID);
                        view.setVisible(false);
                        tf.setVisible(false);
                        StudentTableModel m= new StudentTableModel();
                        TableFrame v= new TableFrame();
                        TableFrameController tvc= new TableFrameController(m,v);
                        tvc.control();      
                    }
                    studentIDExist = true;
                    break;
                }
            }
            if(!studentIDExist){
                JOptionPane.showMessageDialog(null,"Student ID does not EXIST");
            }
        }
   }
}
