/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblSubject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyennst.utils.DBUtils;

/**
 *
 * @author nguyennst
 */
public class TblSubjectDAO implements Serializable{
    private Connection con;
    private PreparedStatement stm;;
    private ResultSet rs;
    
    private void closeConnection() throws SQLException{
        if(rs!=null){
            rs.close();
        }
        if(stm!=null){
            stm.close();
        }
        if(con!=null){
            con.close();
        }
    }
    
    List<TblSubjectDTO> listSubject;

    public List<TblSubjectDTO> getListSubject() {
        return listSubject;
    }
    
    
    public void getAllSubject() throws SQLException, NamingException {
        try{
            con = DBUtils.makeConnection();
            String url = "Select subjectID, subjectName From tbl_Subject";
            stm = con.prepareStatement(url);
            rs = stm.executeQuery();
            while(rs.next()){
                String subID = rs.getString("subjectID");
                String subName = rs.getString("subjectName");
                TblSubjectDTO dto = new TblSubjectDTO(subID,subName);
                if(this.listSubject==null){
                    this.listSubject = new ArrayList<>();
                }
                this.listSubject.add(dto);
            }
        } finally{
            closeConnection();
        }
    }
    
    public String getSubjName(String subID) throws NamingException, SQLException{
        String subName = null;
        try{
            con = DBUtils.makeConnection();
            String url = "Select subjectName From tbl_Subject Where subjectID = ?";
            stm = con.prepareStatement(url);
            stm.setString(1, subID);
            rs = stm.executeQuery();
            if(rs.next()){
                subName = rs.getString("subjectName");
            }
        } finally {
            closeConnection();
        }
        return subName;
    }   
    
    private int numberOfQuestion;
    private int time;

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public int getTime() {
        return time;
    }        
    
    public void getInfoSub(String subID) throws NamingException, SQLException{
        try{
            con = DBUtils.makeConnection();
            String url = "Select numberOfQuestion, time From tbl_Subject Where subjectID = ?";
            stm = con.prepareStatement(url);
            stm.setString(1, subID);
            rs = stm.executeQuery();
            if(rs.next()){
                this.numberOfQuestion = rs.getInt("numberOfQuestion");
                this.time = rs.getInt("time");
            }
        } finally{
            closeConnection();
        }
    }
    
    
}
