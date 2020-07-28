/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblQuizTest;

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
public class TblQuizTestDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean insertQuiz(String email, String subID, float score) throws NamingException, SQLException {
        boolean result = false;
        try {
            con = DBUtils.makeConnection();
            String url = "Insert Into tbl_QuizTest(subjectID, email, Score) values(?,?,?)";
            stm = con.prepareStatement(url);
            stm.setString(1, subID);
            stm.setString(2, email);
            stm.setFloat(3, score);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public int getidQuiz(String subID, String email) throws NamingException, SQLException{
        int idQuiz = 0;
        try{
            con = DBUtils.makeConnection();
            String url = "Select MAX(idSoS) as IDQUIZ From tbl_QuizTest Where subjectID = ? and email = ?";
            stm = con.prepareStatement(url);
            stm.setString(1, subID);
            stm.setString(2, email);
            rs = stm.executeQuery();
            if(rs.next()){
                idQuiz = rs.getInt("IDQUIZ");
            }
        } finally{
            closeConnection();
        }
        return idQuiz;
    }
    
    List<TblQuizTestDTO> listHistory;

    public List<TblQuizTestDTO> getListHistory() {
        return listHistory;
    }
    
    public void getHistory(String subID, int offset, int next) throws NamingException, SQLException{
        try{
            con = DBUtils.makeConnection();
            String url = "Select subjectID, email, Score "
                    + "From tbl_QuizTest "
                    + "Where subjectID = ? "
                    + "ORDER BY idSoS "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            stm = con.prepareStatement(url);
            stm.setString(1, subID);
            stm.setInt(2, offset);
            stm.setInt(3, next);
            rs = stm.executeQuery();
            while(rs.next()){
                String subject = rs.getString("subjectID");
                String email = rs.getString("email");
                float score = rs.getFloat("Score");
                TblQuizTestDTO dto = new TblQuizTestDTO(subID, email, score);
                if(this.listHistory == null){
                    this.listHistory = new ArrayList<>();
                }
                this.listHistory.add(dto);
            }
        } finally{
            closeConnection();
        }
    }
    
    public int getNumberofQuiz(String subID) throws NamingException, SQLException{
        int number = 0;
        try{
            con = DBUtils.makeConnection();
            String url = "Select COUNT(*) as Total From tbl_QuizTest Where subjectID = ?";
            stm = con.prepareStatement(url);
            stm.setString(1, subID);
            rs = stm.executeQuery();
            if(rs.next()){
                number  = rs.getInt("Total");
            }
        } finally{
            closeConnection();
        }
        return number;
    }

}
