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
import nguyennst.tblQuestion.TblQuestionDAO;
import nguyennst.tblQuestion.TblQuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class GetQuizController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(GetQuizController.class);
    
    private static final String QUIZ = "student.jsp";
    
    
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
        
        String url = QUIZ;
        
        int index = Integer.parseInt(request.getParameter("txtIndex"));
        
        String correct = request.getParameter("txtCorrect");        
        String answer = request.getParameter("answer");    
        int score = Integer.parseInt(request.getParameter("txtScore"));
        String time = request.getParameter("txtTimee");
        String subID = request.getParameter("subIDquiz");

        try  {
            HttpSession session = request.getSession();
            TblQuestionDAO dao = new TblQuestionDAO();
            TblQuestionDTO dto = new TblQuestionDTO();                 
            
            List<TblQuestionDTO> list = (List<TblQuestionDTO>) session.getAttribute("LISTQUIZ");
            if(list != null && !list.isEmpty()) {
                dto = list.get(index);
                request.setAttribute("DETAIL", dto);
            }
            
            if(correct.equalsIgnoreCase(answer)){
                score++;
            }
            request.setAttribute("SCORE", score);
            url = QUIZ;
                    
            session.setAttribute("TIME", time);
            
        } catch (Exception ex){
            logger.error("GetQuizController: " + ex.getMessage());
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
