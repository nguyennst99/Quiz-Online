/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblQuestion;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author nguyennst
 */
public class TblQuestionDTO implements Serializable{
    private String question_content, answerA, answerB, answerC, answerD, answer_correct, subID;
    private int id;
    private Date createDate;

    public TblQuestionDTO() {
    }

    public TblQuestionDTO(String question_content, String answerA, String answerB, String answerC, String answerD, String answer_correct, String subID, int id, Date createDate) {
        this.question_content = question_content;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answer_correct = answer_correct;
        this.subID = subID;
        this.id = id;
        this.createDate = createDate;
    }

    public TblQuestionDTO(String question_content, String answerA, String answerB, String answerC, String answerD, String answer_correct) {
        this.question_content = question_content;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answer_correct = answer_correct;
    }

    public TblQuestionDTO(String question_content, String answerA, String answerB, String answerC, String answerD, String answer_correct, int id) {
        this.question_content = question_content;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answer_correct = answer_correct;
        this.id = id;
    }

   

    public TblQuestionDTO(int id) {
        this.id = id;
    }

    
    


    /**
     * @return the question_content
     */
    public String getQuestion_content() {
        return question_content;
    }

    /**
     * @param question_content the question_content to set
     */
    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    /**
     * @return the answerA
     */
    public String getAnswerA() {
        return answerA;
    }

    /**
     * @param answerA the answerA to set
     */
    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    /**
     * @return the answerB
     */
    public String getAnswerB() {
        return answerB;
    }

    /**
     * @param answerB the answerB to set
     */
    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    /**
     * @return the answerC
     */
    public String getAnswerC() {
        return answerC;
    }

    /**
     * @param answerC the answerC to set
     */
    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    /**
     * @return the answerD
     */
    public String getAnswerD() {
        return answerD;
    }

    /**
     * @param answerD the answerD to set
     */
    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    /**
     * @return the answer_correct
     */
    public String getAnswer_correct() {
        return answer_correct;
    }

    /**
     * @param answer_correct the answer_correct to set
     */
    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the subID
     */
    public String getSubID() {
        return subID;
    }

    /**
     * @param subID the subID to set
     */
    public void setSubID(String subID) {
        this.subID = subID;
    }
    
    
}
