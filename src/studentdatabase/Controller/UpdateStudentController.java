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
import studentdatabase.View.StudentDetails;
import studentdatabase.View.UpdatedialoguePopUp;
import studentdatabase.Controller.AddStudentController;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class UpdateStudentController implements ActionListener{
    
    UpdatedialoguePopUp view;
    StudentDetails view2; 
    StudentTableModel model;
    //I have not been consistent in view & model naming. 
    //should be addNewForm etc.
    public UpdateStudentController(StudentTableModel studentTabelModel, UpdatedialoguePopUp updateForm,StudentDetails addStudentForm){
        view =updateForm;
        model= studentTabelModel; 
        view2 = addStudentForm;
    }
    
    public void control(){
        view.getOkButton().addActionListener(this);
        view.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==view.getCloseButton() ){               
            view.dispose();
        }
        if (ae.getActionCommand().equalsIgnoreCase("OK")){ 
            String studentID = view.getStudentID();
            ArrayList<Student> studList = model.getStudentArrayList();
            Boolean studentIDExist = false;
            
            for(Student stud: studList){
                if(stud.getStudentID().equalsIgnoreCase(studentID)){
                    validateUpdateRecord(stud);
                    studentIDExist = true;
                    break;
                }
            }
            if(!studentIDExist){
                JOptionPane.showMessageDialog(null,"Student ID does not EXIST");
            }
        }
    }
    private void validateUpdateRecord(Student Studentid){
        StudentDetails v= new StudentDetails(null, true);
        AddStudentController asc =  new AddStudentController(model,v);
        //v.getUpdateButton().addActionListener(asc);
        //v.getCloseButton().addActionListener(asc);
        asc.control();
        v.setStudentID(Studentid.getStudentID());
        v.setSurname(Studentid.getSurname());
        v.setFirstname(Studentid.getFirstname());
        v.setGPA(Studentid.getGPA());
        v.setMajor(Studentid.getProgram());
        v.setAdmissionYear(Studentid.getadmissionYear());
        view.setVisible(false);
        v.setVisible(true);
    }
}
