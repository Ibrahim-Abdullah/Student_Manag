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
    private String studentID;
    private String surname;
    private String firstName;
    private int admissionYear;
    private float GPA;
    private Major program;
    
    public Student(){
    this.studentID = null;
    this.surname = null;
    this.firstName = null;
    }
    public String getSurname(){
        return this.surname;
    }
    
    public void setSurname(String sureName){
        this.surname = sureName;
    }
    
    public String getStudentID(){
        return this.studentID;
    }
    
    public void setStudentID(String studentID){
        this.studentID = studentID;
    }
    public String getFirstname(){
        return this.firstName;
    }
    
    public void setFirstname(String firstName){
        this.firstName = firstName;
    }
    public int getadmissionYear(){
        return this.admissionYear;
    }
    
    /**
     *
     * @param admissionYear
     */
    public void setadmissionYear(int admissionYear){
        this.admissionYear = admissionYear;
    }
    public float getGPA(){
        return this.GPA;
    }
    
    public void setgpa(float gpa){
        this.GPA = gpa;
    }
    public String getProgram(){
        return this.program.toString();
    }
    
    public void setProgram(String program){
        this.program = Major.valueOf(program);
    }
    public String toString() {
     String data = this.studentID + " " + this.firstName + " " + this.surname + " " + this.admissionYear + " " + this.GPA + " " + this.program + "\n";
            return data ;
        }
 
    
    
}
enum Major{BA,CS,MIS,EE,CE,ME}
