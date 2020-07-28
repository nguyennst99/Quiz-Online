/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyennst.tblQuizTest.TblQuizTestDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class FinishQuizController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FinishQuizController.class);

    private static final String FINISH = "finish.jsp";

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
        String url = FINISH;

        String correct = request.getParameter("txtCorrect");
        String answer = request.getParameter("answer");
        int numCorrect = 0;
        String num = request.getParameter("txtScore");
        if (num.equals("")) {
            numCorrect = 0;
        } else {
            numCorrect = Integer.parseInt(num);
        }

        try {
            HttpSession session = request.getSession();
            if (correct.equals(answer)) {
                numCorrect++;
            }
            int numQue = (int) session.getAttribute("NUMBERQUESTION");
            float scoree = (float) numCorrect / numQue * 10;
            System.out.println(scoree);
            request.setAttribute("NUMBERCORRECT", numCorrect);
            request.setAttribute("SCOREEE", scoree);

            TblQuizTestDAO quizDAO = new TblQuizTestDAO();
            String email = (String) session.getAttribute("EMAIL");
            String subID = (String) session.getAttribute("SUBID");
            boolean result = quizDAO.insertQuiz(email, subID, scoree);
            if (result) {
                int idSoS = quizDAO.getidQuiz(subID, email);
                session.setAttribute("IDQUIZ", idSoS);              
                    url = FINISH;
                
            }

        } catch (NamingException ex) {
            logger.error("FinishController_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("FinishController_SQLE: " + ex.getMessage());
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
