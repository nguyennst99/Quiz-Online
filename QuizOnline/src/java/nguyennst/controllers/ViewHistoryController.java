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
import nguyennst.tblQuizTest.TblQuizTestDAO;
import nguyennst.tblQuizTest.TblQuizTestDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class ViewHistoryController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ViewHistoryController.class);

    private static final String QUIZDETAIL = "history.jsp";

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
        String url = QUIZDETAIL;
        String subID = request.getParameter("cboSubjectt");
        int pageNum = Integer.parseInt(request.getParameter("txtPageee"));
        try {
            if (!subID.equals("")) {
                TblQuizTestDAO dao = new TblQuizTestDAO();

                int totalQuiz = dao.getNumberofQuiz(subID);
                int numPage = totalQuiz / 5;
                if (totalQuiz > numPage * 5) {
                    numPage += 1;
                }

                int offset = (pageNum - 1) * 5;
                dao.getHistory(subID, offset, 5);
                List<TblQuizTestDTO> listttQuiz = dao.getListHistory();

                request.setAttribute("LISTQUY", listttQuiz);

                request.setAttribute("TRANG", numPage);

                url = QUIZDETAIL;
            }
        } catch (NamingException ex) {
            logger.error("ViewHistoryController_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("ViewHistoryController_SQL: " + ex.getMessage());
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
