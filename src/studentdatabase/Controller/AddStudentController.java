/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import studentdatabase.View.AddStudent;
/**
 *
 * @author Ibrahim-Abdullah
 */
public class AddStudentController implements ActionListener{
    
    AddStudent view; 
    //MyTableModel model;
    //I have not been consistent in view & model naming. 
    //should be addNewForm etc.
    public AddNewFormController(MyTableModel myTableModel, AddNewForm addStudent) {
        view =addStudent;
        //model= myTableModel;        
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
            /*
            //fetch the data into a student.
            //I can have a separate fxn in the model to simply receive
            //the individual elemnets instead of a stuent object
            Student s= new Student();
            s.Surname= view.getFullName();
            s.Major=view.getMajor();
            s.WASSCE=Integer.parseInt(view.getWASCE());
            s.age=Integer.parseInt(view.getAge());
            model.addToModel(s);  
            */
        }
    }
}
