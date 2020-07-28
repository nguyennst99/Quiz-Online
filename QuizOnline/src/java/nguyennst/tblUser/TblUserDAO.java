/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblUser;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import nguyennst.utils.DBUtils;

/**
 *
 * @author nguyennst
 */
public class TblUserDAO implements Serializable{
    
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;
    
    private String name;
    private int statusID;

    public String getName() {
        return name;
    }

    public int getStatusID() {
        return statusID;
    }
    
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
    
    public int checkLogin(String email, String password) throws NamingException, SQLException{
        int role = 0;
        try{
            con = DBUtils.makeConnection();
            String url = "Select roleID, name, statusID from tbl_User Where email = ? and password = ?";
            stm = con.prepareStatement(url);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if(rs.next()){
                this.name = rs.getString("name");
                this.statusID = rs.getInt("statusID");
                role = rs.getInt("roleID");
            }
        } finally {
            closeConnection();
        }
        return role;
    }
    
    public boolean insertAccount(String email, String password, String name) throws SQLException, NamingException {
        boolean result = false;
        try{
            con = DBUtils.makeConnection();
            String url = "Insert Into tbl_User(email, name, password, roleID, statusID) values(?,?,?,2,1)" ;
            stm = con.prepareStatement(url);
            stm.setString(1, email);
            stm.setString(2, name);
            stm.setString(3, password);
            int row = stm.executeUpdate();
            if(row>0){
                return true;
            }
            
        } finally{
            closeConnection();
        }
        return result;
    }
    
    public String checkEmailExist(String emailCheck) throws NamingException, SQLException{
        String email = null;
        try{
            con = DBUtils.makeConnection();
            String url = "Select email from tbl_User where email = ?";
            stm = con.prepareStatement(url);
            stm.setString(1, emailCheck);
            rs = stm.executeQuery();
            if(rs.next()){
                email = rs.getString("email");
            }
        } finally{
            closeConnection();
        }
        return email;
    }
    
}
