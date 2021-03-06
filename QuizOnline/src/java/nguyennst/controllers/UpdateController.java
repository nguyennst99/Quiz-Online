/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nguyennst.tblQuestion.TblQuestionDAO;
import nguyennst.tblQuestion.TblQuestionError;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class UpdateController extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(UpdateController.class);
    
    private static final String ERROR = "error.html";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR;
        String content = request.getParameter("question_content");
        String ansA = request.getParameter("txtAnsA");
        String ansB = request.getParameter("txtAnsB");
        String ansC = request.getParameter("txtAnsC");
        String ansD = request.getParameter("txtAnsD");
        String correct = request.getParameter("txtAnsCorrect");
        String subj = request.getParameter("cboSubjectUpdate");
        int idQuestion = Integer.parseInt(request.getParameter("txtQuestionID"));
        
        String lastSearch = request.getParameter("txtLastSearch");
        String lastPage = request.getParameter("txtLastPage");
        String lastSubject = request.getParameter("txtLastSub");
        int lastStatus = Integer.parseInt(request.getParameter("txtLastStatus"));
        
        try {
            TblQuestionError QuestionError = new TblQuestionError();
            boolean errors = false;
            
            if (content.isEmpty()) {
                QuestionError.setContentError("Content Question can't be blank!!!");
                errors = true;
            }
            if (ansA.isEmpty()) {
                QuestionError.setAnswerAError("Answer can't be blank!!!");
                errors = true;
            }
            if (ansB.isEmpty()) {
                QuestionError.setAnswerBError("Answer can't be blank!!!");
                errors = true;
            }
            if (ansC.isEmpty()) {
                QuestionError.setAnswerCError("Answer can't be blank!!!");
                errors = true;
            }
            if (ansD.isEmpty()) {
                QuestionError.setAnswerDError("Answer can't be blank!!!");
                errors = true;
            }
            if (correct.isEmpty()) {
                QuestionError.setCorrectError("Answer Correct can't be blank!!!");
                errors = true;
            }
            if(errors){
                request.setAttribute("UPDATEERROR", QuestionError);
                url = "MainController?btAction=Search&txtSearch="+lastSearch+"&txtPage="+lastPage+"&cboSubject="+lastSubject+"&cboStatus="+lastStatus;
            } else {
                TblQuestionDAO dao = new TblQuestionDAO();
                boolean result = dao.updateQuestion(content, ansA, ansB, ansC, ansD, correct, subj, idQuestion);
                if(result){
                    url = "MainController?btAction=Search&txtSearch="+lastSearch+"&txtPage="+lastPage+"&cboSubject="+lastSubject+"&cboStatus="+lastStatus;
                }
            }
                
        } catch (NamingException ex) {
            logger.error("UpdateController_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("UpdateController_SQLE: " + ex.getMessage());
        } finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
