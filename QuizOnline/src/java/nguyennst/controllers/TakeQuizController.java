/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyennst.tblQuestion.TblQuestionDAO;
import nguyennst.tblQuestion.TblQuestionDTO;
import nguyennst.tblSubject.TblSubjectDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class TakeQuizController extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(TakeQuizController.class);
    
    private static final String MAKEQUIZ = "GetQuizController";
    
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
        String url = MAKEQUIZ;
        String subID = request.getParameter("subIDquiz");
        
        try {
            HttpSession session = request.getSession();
            TblSubjectDAO subDAO = new TblSubjectDAO();
            subDAO.getInfoSub(subID);
            int numberQuestion = subDAO.getNumberOfQuestion();
            int timee = subDAO.getTime();
            int time = timee*60;
            session.setAttribute("TIME", time);
            
            TblQuestionDAO quesDAO = new TblQuestionDAO();
            quesDAO.makeQuiz(numberQuestion, subID);
            List<TblQuestionDTO> listQuiz = quesDAO.getListQuiz();
            
            session.setAttribute("NUMBERQUESTION", numberQuestion);
            
            session.setAttribute("LISTQUIZ", listQuiz);
            
            session.setAttribute("SUBID", subID);
            
            url = MAKEQUIZ;
            
            
        } catch (NamingException ex) {
            logger.error("TakeQuizController_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("TakeQuizController_SQLE: " + ex.getMessage());
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
