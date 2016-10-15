/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase.Model;

/**
 *
 * @author derry
 */
public class Student {
 String studentID;
    String surname;
    String firstName;
    int admissionYear;
    float GPA;
    Major program;
    
    public String toStrings() {
     String data = studentID + " " + firstName + " " + surname + " " + admissionYear + " " + GPA + " " + program + "\n";
            return data ;
        }
 
    
    
}
enum Major{BA,CS,MIS,EE,CE,ME}
