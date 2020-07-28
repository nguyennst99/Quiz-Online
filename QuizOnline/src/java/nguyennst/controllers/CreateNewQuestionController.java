/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
public class CreateNewQuestionController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CreateNewQuestionController.class);

    private static final String FAIL = "createNewQuestion.jsp";
    private static final String SUCCESS = "admin.jsp";

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
        String url = FAIL;
        String content = request.getParameter("txtContent");
        String ansA = request.getParameter("txtAnswerA");
        String ansB = request.getParameter("txtAnswerB");
        String ansC = request.getParameter("txtAnswerC");
        String ansD = request.getParameter("txtAnswerD");
        String correct = request.getParameter("txtCorrect");
        String subject = request.getParameter("cboSubject");
        Date createDate = new Date(System.currentTimeMillis());
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

            if (errors) {
                request.setAttribute("QUESTIONERROR", QuestionError);
            } else {
                TblQuestionDAO dao = new TblQuestionDAO();
                boolean result = dao.insertQuestion(content, ansA, ansB, ansC, ansD, correct, createDate, subject, 2);
                if (result) {
                    url = SUCCESS;
                }
            }
        } catch (NamingException ex) {
            logger.error("CreateNewQuestionController_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("CreateNewQuestionController_SQLE: " + ex.getMessage());
        } finally {
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
