/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.controllers;

import java.io.IOException;
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
import nguyennst.tblSubject.TblSubjectDAO;
import nguyennst.tblSubject.TblSubjectDTO;
import nguyennst.tblUser.TblUserDAO;
import nguyennst.tblUser.TblUserError;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class LoginController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    private static final String INVALID = "login.jsp";
    private static final String STUDENT = "student.jsp";
    private static final String ADMIN = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = INVALID;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");

            TblUserError userError = new TblUserError();
            boolean error = false;

            if (email.isEmpty()) {
                userError.setEmailError("Email can't be blank!!!");
                error = true;
            } else if (!email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?")) {
                userError.setEmailError("Email not valid");
                error = true;
            }

            if (password.isEmpty()) {
                userError.setPasswordError("Password can't be blank!!!");
                error = true;
            }

            if (error) {
                request.setAttribute("LOGINERROR", userError);
            } else {
                TblUserDAO dao = new TblUserDAO();
                String password1 = DigestUtils.sha256Hex(password);
                int role = dao.checkLogin(email, password1);

                if (role == 0) {
                    url = INVALID;
                    request.setAttribute("INVALID", "Invalid email or password!!");
                } else {
                    HttpSession session = request.getSession();
                    int status = dao.getStatusID();
                    session.setAttribute("NAME", dao.getName());
                    session.setAttribute("EMAIL", email);

                    TblSubjectDAO subDao = new TblSubjectDAO();
                    subDao.getAllSubject();
                    List<TblSubjectDTO> listSubject = subDao.getListSubject();
                    session.setAttribute("LISTSUBJECT", listSubject);

                    if (status < 1 || status > 2) {
                        url = INVALID;
                        request.setAttribute("INVALID", "Invalid email or password!!");
                    } else {
                        if (role == 1) {
                            url = ADMIN;
                        }
                        if (role == 2) {
                            url = STUDENT;
                        }
                    }
                }
            }
        } catch (NamingException ex) {
            logger.error("LoginController_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("LoginController_SQLE: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
