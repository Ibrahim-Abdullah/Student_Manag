/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase.Controller;

import studentdatabase.View.TableFrame;
import studentdatabase.Model.StudentTableModel;
import studentdatabase.View.StudentDetails;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import studentdatabase.Model.Student;
import studentdatabase.View.UpdatedialoguePopUp;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class TableFrameController implements ActionListener{
    TableFrame tableView;
    StudentTableModel studentTableModel;
    ActionListener actionListener;
    
    public TableFrameController(StudentTableModel studentTableModel, TableFrame tableView){
        this.tableView= tableView;
        this.studentTableModel=studentTableModel;  
        tableView.getTable().setModel((TableModel) studentTableModel);
    }
    
    //this illustrates how both approaches for event handling are accomplished
    public void control(){ 
        tableView.getAddButton().addActionListener(this);
        tableView.getEditButton().addActionListener(this);
        tableView.getListButton().addActionListener(this);
        tableView.getEditButton().addActionListener(this);
        tableView.getDeleteButton().addActionListener(this);
                
        //this uses an anonymous class to handle one of the buttons
        actionListener = (ActionEvent actionEvent) -> {
            System.exit(0);
        };                
        tableView.getExitButton().addActionListener(actionListener);   
        
        //make the table use a combo in the view
        
        tableView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //following method wont work if btn text is changed.
        if (ae.getActionCommand().equals("Add")){
            StudentTableModel m=StudentTableModel.getInstance();
            StudentDetails v= new StudentDetails(null, true);            
            AddStudentController cntroller= new AddStudentController(m,v);            
            cntroller.control();            
            v.setVisible(true);
            
        }
        if (ae.getActionCommand().equals("List")){
            
            
            //Dereck should provid getter method for getting a reference to 
            //the list of student in the table model
            ArrayList<Student> tmp =studentTableModel.getStudentArrayList();
            for(Student stud: tmp){
                System.out.println(stud.toString());
            }
        }
        if(ae.getActionCommand().equals("Edit")){
            StudentTableModel m=StudentTableModel.getInstance();
            UpdatedialoguePopUp v= new UpdatedialoguePopUp(null, true);
            StudentDetails sd = new StudentDetails(null,true);
            UpdateStudentController cntroller= new UpdateStudentController(m,v,sd);            
            cntroller.control();            
            v.setVisible(true);
        }
        if (ae.getActionCommand().equals("Delete")){
            StudentTableModel m=StudentTableModel.getInstance();
            UpdatedialoguePopUp v= new UpdatedialoguePopUp(null, true);
            DeleteRecordController cntroller= new DeleteRecordController(m,v); 
            cntroller.control();
            v.setVisible(true);
        }
        
    }
}