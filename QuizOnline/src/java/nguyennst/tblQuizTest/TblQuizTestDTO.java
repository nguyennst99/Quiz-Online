/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblQuizTest;

import java.io.Serializable;

/**
 *
 * @author nguyennst
 */
public class TblQuizTestDTO implements Serializable{
    private String subID, email;
    private float score;

    public TblQuizTestDTO() {
    }

    public TblQuizTestDTO(String subID, String email, float score) {
        this.subID = subID;
        this.email = email;
        this.score = score;
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

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }
    
    
}
