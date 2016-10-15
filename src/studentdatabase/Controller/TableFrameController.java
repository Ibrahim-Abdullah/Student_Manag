/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase.Controller;

import studentdatabase.View.TableFrame;
import studentdatabase.View.AddStudent;
import studentdatabase.Controller.AddStudentController;
import studentdatabase.View.StudentDetails;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class TableFrameController implements ActionListener{
    TableFrame tableView;
    //StudentTableModel myTableModel;
    ActionListener actionListener;
    
    public TableFrameController(TableFrame tableView){
        /*MyTableModel StudentTableModel )*/
        this.tableView= tableView;
        //this.myTableModel=myTableModel;  
        //tableView.getTable().setModel(myTableModel);
    }
    
    //this illustrates how both approaches for event handling are accomplished
    public void control(){ 
        tableView.getAddButton().addActionListener(this);
        tableView.getEditButton().addActionListener(this);
        tableView.getListButton().addActionListener(this);
        tableView.getEditButton().addActionListener(this);
        tableView.getDeleteButton().addActionListener(this);
                
        //this uses an anonymous class to handle one of the buttons
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {                  
                  System.exit(0);
              }
        };                
        tableView.getExitButton().addActionListener(actionListener);   
        
        //make the table use a combo in the view
        
        tableView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //following method wont work if btn text is changed.
        if (ae.getActionCommand().equals("Add")){
            //MyTableModel m=MyTableModel.getInstance();
            AddStudent v= new AddStudent(null, true);            
            AddStudentController cntroller= new AddStudentController(v);            
            //cntroller.control();            
            //v.setVisible(true);
            
        }
        if (ae.getActionCommand().equals("Print")){
           /* //poorly written fxn. type conversions shd be in model
            Object [] tmp=myTableModel.getList().toArray();
            Student s;
            for (int i = 0; i < tmp.length; i++) {
                
                s=(Student)tmp[i];
                System.out.println(s.toString());                
            }
            */
        }
        //comparing with the actual btn
        //if (ae.getSource()== tableView.getDetailButton()){
            
        //}
        
    }
}