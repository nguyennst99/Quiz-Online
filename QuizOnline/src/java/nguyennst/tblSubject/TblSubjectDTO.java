/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblSubject;

import java.io.Serializable;

/**
 *
 * @author nguyennst
 */
public class TblSubjectDTO implements Serializable{
    private String subjectID;
    private String subName;
    private int numberofQuestion;
    private int time;

    public TblSubjectDTO() {
    }

    public TblSubjectDTO(String subjectID, String subName) {
        this.subjectID = subjectID;
        this.subName = subName;
    }

    public TblSubjectDTO(int numberofQuestion, int time) {
        this.numberofQuestion = numberofQuestion;
        this.time = time;
    }

    

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the subName
     */
    public String getSubName() {
        return subName;
    }

    /**
     * @param subName the subName to set
     */
    public void setSubName(String subName) {
        this.subName = subName;
    }

    /**
     * @return the numberofQuestion
     */
    public int getNumberofQuestion() {
        return numberofQuestion;
    }

    /**
     * @param numberofQuestion the numberofQuestion to set
     */
    public void setNumberofQuestion(int numberofQuestion) {
        this.numberofQuestion = numberofQuestion;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }
    
}
