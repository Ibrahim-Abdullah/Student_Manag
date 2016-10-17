/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author derry
 */
public class StudentTableModel extends AbstractTableModel {

    
    String[] colHeader = null;
    private ArrayList<Student> sList;
    static StudentTableModel sModel = null;

    public static StudentTableModel getInstance() {     //I am using the Singleton pattern here
        if (sModel == null) {
            sModel = new StudentTableModel();
        }
        return sModel;
    }

    public StudentTableModel() {
        super();
        sList = new ArrayList<>();
        fetchTableData();
        
    }

    public ArrayList getStudentArrayList() {
        return sList;
    }

    @Override
    public int getColumnCount() {
        return colHeader.length;
    }

    @Override
    public int getRowCount() {
        return sList.size();
    }

    public Object getValueAt(int row, int col) {
        Student ss = sList.get(row);

        if (col == 0) {
            return ss.getStudentID();
        } else if (col == 1) {
            return ss.getSurname();
        } else if (col == 2) {
            return ss.getFirstname();
        } else if (col == 3) {
            return ss.getadmissionYear();
        } else if (col == 4) {
            return ss.getGPA();
        } else if (col == 5) {
            return ss.getProgram();
        }
        return ss;
    }
    
    /**
     *
     * @param col
     * @return
     */
    @Override
    public String getColumnName(int col) {
        return colHeader[col];//otherwise returns A, B, C etc
    }

    public void setValueAt(Object value, int row, int col) {
        Student s;
        s = sList.get(row);
        switch (col) {
            case 0:
                s.setStudentID((String) value);
                break;
            case 1:
                s.setSurname(String.valueOf(value));
                ;
                break;
            case 2:
                s.setFirstname((String) value);
                break;
            case 3:
                s.setadmissionYear(Integer.valueOf((Integer) value));
                break;
            case 4:
                s.setgpa(Float.valueOf((String) value));
                break;
            case 5:
                s.setProgram((String) value);
        }

        fireTableCellUpdated(row, col); //*works best with this
    }

    public boolean addRecord(String id, String fName, String sName, int adYear, float gpa, String major) {
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/studentdatabase?user=root&password=0030104018profib");

            PreparedStatement ps = con.prepareStatement("Insert into studentdata set StudentID=?, firstName=?, Surname=?, AdmissionYear=?, "
                    + "GPA=?, Program=?");
            ps.setString(1, id);
            ps.setString(2, fName);
            ps.setString(3, sName);
            ps.setInt(4, adYear);
            ps.setFloat(5, gpa);
            ps.setString(6, major);
           boolean result = ps.execute();
            ps.close();
            con.close();
            return result;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return true;
        }
        
        //sModel.fireTableDataChanged();
    }

    public int updateRecord(String id, String fName, String sName, int adYear, float gpa, String major) {
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/studentdatabase?user=root&password=0030104018profib");
            PreparedStatement ps = con.prepareStatement("update studentdata  set firstName=?, Surname=?, AdmissionYear=?,"
                    + "GPA=?, Program=? where studentID="+ " '"+ id+" '");
            //ps.setString(1, id);
            ps.setString(1, fName);
            ps.setString(2, sName);
            ps.setInt(3, adYear);
            ps.setFloat(4, gpa);
            ps.setString(5,major);
            int rowsAffected = ps.executeUpdate();
            ps.close();
            con.close();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return 0;
        }
    }

    public void deleteRecord(String stId) {
        for (int i = 0; i < sList.size(); i++) {
            if (sList.get(i).getStudentID().equals(stId)) {
                try {
                    Connection con = null;
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = java.sql.DriverManager.getConnection(
                            "jdbc:mysql://localhost/studentdatabase?user=root&password=0030104018profib");
                    PreparedStatement ps = con.prepareStatement("delete from studentdata where StudentID=?");
                    ps.setString(1, stId);
                    System.out.println("Success: " + ps.execute());
                } catch (Exception e) {
                    System.out.println("Error " + e.toString());
                    return;
                }
            }
        }
    }

    void fetchTableData() {
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/studentdatabase?user=root&password=0030104018profib");

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT StudentID, FirstName, Surname, AdmissionYear, GPA, Program FROM studentdata");
            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            colHeader = new String[numberOfColumns];
            for (int i = 1; i <= numberOfColumns; i++) {
                colHeader[i - 1] = meta.getColumnName(i);
            }

            ArrayList<Object> rows = new ArrayList<Object>();
            //get actual row data

            while (rs.next()) {
//            ArrayList <String> tmp = new ArrayList<String>();
                Student st = new Student();
                st.setStudentID(rs.getString(1));
                st.setFirstname(rs.getString(2));
                st.setSurname(rs.getString(3));
                st.setadmissionYear(Integer.valueOf(rs.getString(4)));
                st.setgpa(Float.valueOf(rs.getString(5)));
                st.setProgram(rs.getString(6));

                sList.add(st);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            System.exit(0);
        }

    }

//    public static void main(String[] arg) {
//
//        StudentTableModel newModel = new StudentTableModel();
//        Scanner input = new Scanner(System.in);
//        //newModel.addRecord("122", "Miles", "Morales", 2000, (float)2.33, Major.CS);
//        System.out.println("Enter the student ID:");
//        String id = input.nextLine();
//        newModel.deleteRecord(id);
//
//    }
}
