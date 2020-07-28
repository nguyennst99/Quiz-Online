/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblQuestion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyennst.utils.DBUtils;

/**
 *
 * @author nguyennst
 */
public class TblQuestionDAO implements Serializable{
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
    
    private List<TblQuestionDTO> listQuestion;

    public List<TblQuestionDTO> getListQuestion() {
        return listQuestion;
    }
    
    
    public int getNumberofQuestion(String searchValue, int statusID, String subID) throws NamingException, SQLException{
        int number = 0;
        try{
            con = DBUtils.makeConnection();
            String url = "Select COUNT(*) as Total From tbl_Question Where question_content LIKE ? and statusID = ? and subjectID = ?";
            stm = con.prepareStatement(url);
            stm.setString(1, "%" + searchValue + "%");
            stm.setInt(2, statusID);
            stm.setString(3, subID);
            rs = stm.executeQuery();
            if(rs.next()){
                number  = rs.getInt("Total");
            }
        } finally{
            closeConnection();
        }
        return number;
    }
    
    public void searchQuestion(String searchValue, String subject, int statusID, int offset, int next) throws NamingException, SQLException{
        try{
            con = DBUtils.makeConnection();
            String url = "Select questionID, question_content, answerA, answerB, answerC, answerD, answer_correct, createDate, subjectID "
                    + "From tbl_Question "
                    + "Where subjectID = ? and question_content LIKE ? and statusID = ? "
                    + "ORDER BY questionID "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            stm = con.prepareStatement(url);
            stm.setString(1, subject);
            stm.setString(2, "%" + searchValue + "%");
            stm.setInt(3, statusID);
            stm.setInt(4, offset);
            stm.setInt(5, next);
            rs = stm.executeQuery();
            while(rs.next()){
                int id = rs.getInt("questionID");
                String question_content = rs.getString("question_content");
                String answerA = rs.getString("answerA");
                String answerB = rs.getString("answerB");
                String answerC = rs.getString("answerC");
                String answerD = rs.getString("answerD");
                String answer_correct = rs.getString("answer_correct");
                Date createDate = rs.getDate("createDate");
                String subID = rs.getString("subjectID");
                
                TblQuestionDTO dto = new TblQuestionDTO(question_content, answerA, answerB, answerC, answerD, answer_correct, subID, id, createDate);
                if(this.listQuestion==null){
                    this.listQuestion = new ArrayList<>();
                }
                this.listQuestion.add(dto);
            }
        } finally{
            closeConnection();
        }
    }
    
    public boolean insertQuestion(String content, String ansA, String ansB, String ansC, 
            String ansD, String correct, Date createDate, String subID, int statusID) throws NamingException, SQLException{
        boolean result = false;
        try{
            con = DBUtils.makeConnection();
            String url = "Insert Into tbl_Question(question_content, answerA, answerB, answerC, answerD,"
                    + " answer_correct, createDate, subjectID, statusID) values (?,?,?,?,?,?,?,?,?)";
            stm = con.prepareStatement(url);
            stm.setString(1, content);
            stm.setString(2, ansA);
            stm.setString(3, ansB);
            stm.setString(4, ansC);
            stm.setString(5, ansD);
            stm.setString(6, correct);
            stm.setDate(7, createDate);
            stm.setString(8, subID);
            stm.setInt(9, statusID);
            int row = stm.executeUpdate();
            if(row>0){
                return true;
            }
        } finally{
            closeConnection();
        }
        return result;
    }
    
    public boolean updateQuestion(String content, String ansA, String ansB, String ansC, 
            String ansD, String correct, String subj, int id) throws NamingException, SQLException{
        boolean result = false;
        try{
            con = DBUtils.makeConnection();
            String url = "Update Tbl_Question Set question_content = ?, answerA = ?, answerB = ?, answerC = ?, answerD = ?, "
                    + "answer_correct = ?, subjectID = ? Where questionID = ?";
            stm = con.prepareStatement(url);
            stm.setString(1, content);
            stm.setString(2, ansA);
            stm.setString(3, ansB);
            stm.setString(4, ansC);
            stm.setString(5, ansD);
            stm.setString(6, correct);
            stm.setString(7, subj);
            stm.setInt(8, id);
            int row = stm.executeUpdate();
            if(row>0){
                return true;
            }
        } finally{
            closeConnection();
        }
        return result;
    }
    
    public boolean deleteQuestion(int idQuestion) throws NamingException, SQLException{
        boolean result = false;
        try{
            con = DBUtils.makeConnection();
            String url = "Update Tbl_Question Set statusID = 3 Where questionID = ?";
            stm = con.prepareStatement(url);
            stm.setInt(1, idQuestion);
            int row = stm.executeUpdate();
            if(row>0){
                return true;
            }
        } finally{
            closeConnection();
        }
        return result;
    }
    
    
    private List<TblQuestionDTO> listQuiz;

    public List<TblQuestionDTO> getListQuiz() {
        return listQuiz;
    }
    
    public void makeQuiz(int numberQuestion, String subID) throws NamingException, SQLException{
        try{
            con = DBUtils.makeConnection();
            String url = "Select TOP (?) questionID, question_content, answerA, answerB, answerC, answerD, answer_correct "
                    + "From tbl_Question Where subjectID = ? "
                    + "ORDER BY NEWID()";
            stm = con.prepareStatement(url);
            stm.setInt(1, numberQuestion);
            stm.setString(2, subID);
            rs = stm.executeQuery();
            while(rs.next()){
                int questionid = rs.getInt("questionID");
                String content = rs.getString("question_content");
                String ansA = rs.getString("answerA");
                String ansB = rs.getString("answerB");
                String ansC = rs.getString("answerC");
                String ansD = rs.getString("answerD");
                String correct = rs.getString("answer_correct");
                TblQuestionDTO dto = new TblQuestionDTO(content, ansA, ansB, ansC, ansD, correct, questionid);
                if(this.listQuiz==null){
                    this.listQuiz= new ArrayList<>();
                }
                this.listQuiz.add(dto);
            }
        } finally {
            closeConnection();
        }
    }
    
//    public void makeQuiz(int numberQuestion, String subID) throws NamingException, SQLException{
//        try{
//            con = DBUtils.makeConnection();
//            String url = "Select TOP (?) questionID "
//                    + "From tbl_Question Where subjectID = ? "
//                    + "ORDER BY NEWID()";
//            stm = con.prepareStatement(url);
//            stm.setInt(1, numberQuestion);
//            stm.setString(2, subID);
//            rs = stm.executeQuery();
//            while(rs.next()){
//                int questionid = rs.getInt("questionID");
//                TblQuestionDTO dto = new TblQuestionDTO(questionid);
//                if(this.listQuiz==null){
//                    this.listQuiz= new ArrayList<>();
//                }
//                this.listQuiz.add(dto);
//            }
//        } finally {
//            closeConnection();
//        }
//    }
//    
//    public TblQuestionDTO getDetailQuestion(int id) throws NamingException, SQLException{
//        TblQuestionDTO dto = null;
//        try{
//            con = DBUtils.makeConnection();
//            String url = "Select question_content, answerA, answerB, answerC, answerD, answer_correct from tbl_Question Where questionID = ?";
//            stm = con.prepareStatement(url);
//            stm.setInt(1, id);
//            rs = stm.executeQuery();
//            if(rs.next()){
//                String content = rs.getString("question_content");
//                String ansA = rs.getString("answerA");
//                String ansB = rs.getString("answerB");
//                String ansC = rs.getString("answerC");
//                String ansD = rs.getString("answerD");
//                String correct = rs.getString("answer_correct");
//                dto = new TblQuestionDTO(content, ansA, ansB, ansC, ansD, correct);
//            }
//        } finally{
//            closeConnection();
//        }
//        return dto;
//    }
//    
    
}
