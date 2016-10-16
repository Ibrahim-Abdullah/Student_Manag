/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import studentdatabase.View.StudentDetails;
import studentdatabase.Model.StudentTableModel;  
import studentdatabase.Model.Student;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class AddStudentController implements ActionListener{
    
    StudentDetails view; 
    StudentTableModel model;

    public AddStudentController( StudentTableModel studentTableModel,StudentDetails addStudentForm) {
        view =addStudentForm;
        model= studentTableModel;        
    }
    public void control(){
        view.getAddButton().addActionListener(this);
        view.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {            
        if (ae.getSource()==view.getCloseButton() )
        {               
            view.dispose();
        }
        if (ae.getActionCommand().equalsIgnoreCase("Add"))
        {     
        boolean success = fieldValidation();
        if(success){
            String sID = view.getStudentID();
            String fName = view.getFirstname();
            String sName = view.getSurname();
            int yAdmission = Integer.parseInt(view.getAdmissionYear());
            float gpA = Float.parseFloat(view.getGPA());
            String programOfStudy = view.getMajor();
            
            Student newStudent = new Student();
            
            newStudent.setFirstname(fName);
            newStudent.setStudentID(sID);
            newStudent.setSurname(sName);
            newStudent.setProgram(programOfStudy);
            newStudent.setgpa(gpA);
            newStudent.setadmissionYear(yAdmission);
            
            //model.addToModel(newStudent);  
            JOptionPane.showMessageDialog(null,"Student record succesfully inserted");
            view.resetField();
        }
        else{
            JOptionPane.showMessageDialog(null,"Student Record NOT inserted");
        }
    }
 }
    
 private Boolean fieldValidation(){
        boolean success = false;
        try{
            //long id = new Long(studentId).longValue();
            long id = Long.parseLong(view.getStudentID());
            success = true;
            //Check if Year of Admission is of the format
            try{
                int yAdmission = Integer.parseInt(view.getAdmissionYear());
                success = true;
                try{
                    float _gpa  = Float.parseFloat(view.getGPA());
                    success = true;
                    
                }
                catch(NumberFormatException e){
                    success = false;
                    JOptionPane.showMessageDialog(null,"Incorrect GPA Format");
                }
            }
            catch(NumberFormatException e){
                success = false;
            JOptionPane.showMessageDialog(null,"Incorrect Year of Admission Format");
            }
            if(view.getMajor().toString().equalsIgnoreCase("Select Program of Study")){
                success = false;
                JOptionPane.showMessageDialog(null,"Select Program of Study");
            }
            else{
                success = true;
            }
        }
        catch(NumberFormatException e){
            success = false;
            JOptionPane.showMessageDialog(null,"Incorrect Student ID format"); 
        }
        return success;
    }
}
