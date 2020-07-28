/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblQuestion;

import java.io.Serializable;

/**
 *
 * @author nguyennst
 */
public class TblQuestionError implements Serializable{
    private String contentError, answerAError, answerBError, answerCError, answerDError, correctError;

    public TblQuestionError() {
    }

    public TblQuestionError(String contentError, String answerAError, String answerBError, String answerCError, String answerDError, String correctError) {
        this.contentError = contentError;
        this.answerAError = answerAError;
        this.answerBError = answerBError;
        this.answerCError = answerCError;
        this.answerDError = answerDError;
        this.correctError = correctError;
    }

    /**
     * @return the contentError
     */
    public String getContentError() {
        return contentError;
    }

    /**
     * @param contentError the contentError to set
     */
    public void setContentError(String contentError) {
        this.contentError = contentError;
    }

    /**
     * @return the answerAError
     */
    public String getAnswerAError() {
        return answerAError;
    }

    /**
     * @param answerAError the answerAError to set
     */
    public void setAnswerAError(String answerAError) {
        this.answerAError = answerAError;
    }

    /**
     * @return the answerBError
     */
    public String getAnswerBError() {
        return answerBError;
    }

    /**
     * @param answerBError the answerBError to set
     */
    public void setAnswerBError(String answerBError) {
        this.answerBError = answerBError;
    }

    /**
     * @return the answerCError
     */
    public String getAnswerCError() {
        return answerCError;
    }

    /**
     * @param answerCError the answerCError to set
     */
    public void setAnswerCError(String answerCError) {
        this.answerCError = answerCError;
    }

    /**
     * @return the answerDError
     */
    public String getAnswerDError() {
        return answerDError;
    }

    /**
     * @param answerDError the answerDError to set
     */
    public void setAnswerDError(String answerDError) {
        this.answerDError = answerDError;
    }

    /**
     * @return the correctError
     */
    public String getCorrectError() {
        return correctError;
    }

    /**
     * @param correctError the correctError to set
     */
    public void setCorrectError(String correctError) {
        this.correctError = correctError;
    }
    
    
}
