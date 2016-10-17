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
        
        //Add the actionListener to StudentDetails form
        view2.getUpdateButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {            
        if (ae.getSource()==view.getCloseButton() )
        {               
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
        if (ae.getActionCommand().equalsIgnoreCase("Update")){
            Boolean success = fieldValidation();
            if(success){
                String sID = view2.getStudentID();
                String fName = view2.getFirstname();
                String sName = view2.getSurname();
                int yAdmission = Integer.parseInt(view2.getAdmissionYear());
                float gpA = Float.parseFloat(view2.getGPA());
                String programOfStudy = view2.getMajor();
                 model.updateRecord(sID, fName,sName,yAdmission,gpA, programOfStudy);
//                Student newStudent = new Student();
//            
//                newStudent.setFirstname(fName);
//                newStudent.setStudentID(sID);
//                newStudent.setSurname(sName);
//                newStudent.setProgram(programOfStudy);
//                newStudent.setgpa(gpA);
//                newStudent.setadmissionYear(yAdmission);
//            
//            //model.addToModel(newStudent);  
            JOptionPane.showMessageDialog(null,"Student record has been Updated Succesfully");
            view2.resetField();
        }
        }
    }
     private Boolean fieldValidation(){
        boolean success = false;
        try{
            //long id = new Long(studentId).longValue();
            long id = Long.parseLong(view2.getStudentID());
            //success = true;
            //Check if Year of Admission is of the format
            try{
                int yAdmission = Integer.parseInt(view2.getAdmissionYear());
                //success = true;
                try{
                    float _gpa  = Float.parseFloat(view2.getGPA());
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
            if(view2.getMajor().equalsIgnoreCase("Select Program of Study")){
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
    
    private void validateUpdateRecord(Student Studentid){
        StudentDetails v= new StudentDetails(null, true);
        v.setStudentID(Studentid.getStudentID());
        v.setSurname(Studentid.getSurname());
        v.setFirstname(Studentid.getFirstname());
        v.setGPA(Studentid.getGPA());
        v.setMajor(Studentid.getProgram());
        v.setAdmissionYear(Studentid.getadmissionYear());
        v.setVisible(true);
    }
}
