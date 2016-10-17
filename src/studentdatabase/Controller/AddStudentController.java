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
import studentdatabase.View.TableFrame;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class AddStudentController implements ActionListener{
    
    StudentDetails view; 
    StudentTableModel model;
    //TableFrame tf;

    public AddStudentController( StudentTableModel studentTableModel,StudentDetails addStudentForm) {
        view =addStudentForm;
        model= studentTableModel;
        //this.tf = tf;        
    }
    public void control(){
        view.getAddButton().addActionListener(this);
        view.getCloseButton().addActionListener(this);
        view.getUpdateButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {            
        if (ae.getSource()==view.getCloseButton() )
        {               
        view.dispose();
        
        StudentTableModel m= new StudentTableModel();
        TableFrame v= new TableFrame();
        TableFrameController tvc= new TableFrameController(m,v);
        tvc.control();
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
            
            model.addRecord(sID,fName,sName, yAdmission, gpA, programOfStudy);
            JOptionPane.showMessageDialog(null,"Student record succesfully inserted");
            view.resetField();
        }
        else{
            JOptionPane.showMessageDialog(null,"Student Record NOT inserted");
        }
    }
        if (ae.getActionCommand().equalsIgnoreCase("Update")){
            boolean success = fieldValidation();
            if(success){
                String sID = view.getStudentID();
                String fName = view.getFirstname();
                String sName = view.getSurname();
                int yAdmission = Integer.parseInt(view.getAdmissionYear());
                float gpA = Float.parseFloat(view.getGPA());
                String programOfStudy = view.getMajor();
                model.updateRecord(sID, fName,sName,yAdmission,gpA, programOfStudy); 
            JOptionPane.showMessageDialog(null,"Student record has been Updated Succesfully");
            view.resetField();
            view.dispose();
            StudentTableModel m= new StudentTableModel();
            TableFrame v= new TableFrame();
            TableFrameController tvc= new TableFrameController(m,v);
            tvc.control();
        }
        else{
            JOptionPane.showMessageDialog(null,"Student record update was unsuccesful");
            }
    }
 }
    
 private Boolean fieldValidation(){
        boolean success = false;
        try{
            //long id = new Long(studentId).longValue();
            long id = Long.parseLong(view.getStudentID());
            if(id <= 0){
                    JOptionPane.showMessageDialog(null,"Incorrect Student ID format");
                }
            //Check if Year of Admission is of the format
            try{
                int yAdmission = Integer.parseInt(view.getAdmissionYear());
                if(yAdmission <= 0){
                    JOptionPane.showMessageDialog(null,"Incorrect Year of Admission Format");
                }
                
                try{
                    float _gpa  = Float.parseFloat(view.getGPA());
                    if(_gpa < 0.0){
                        success =false;
                        JOptionPane.showMessageDialog(null,"Incorrect GPA Format");
                    }
                    else{
                    success = true;
                    }
                    
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
        }
        catch(NumberFormatException e){
            success = false;
            JOptionPane.showMessageDialog(null,"Incorrect Student ID format"); 
        }
        return success;
    }
}
