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
import nguyennst.tblUser.TblUserDAO;
import nguyennst.tblUser.TblUserError;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class SignUpController extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(SignUpController.class);
    
    private static final String FAIL = "createNewAccount.jsp";
    private static final String SUCCESS = "login.html";

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
        
        try  {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String name = request.getParameter("txtName");
            
            TblUserDAO userDao = new TblUserDAO();
            TblUserError userErrors = new TblUserError();
            boolean errros = false;
            
            if(email.isEmpty()){
                userErrors.setEmailError("Email can't be blank!!!");
                errros = true;
            } else if(!email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?") || email.length() > 50){
                userErrors.setEmailError("Email must be valid and <= 50 characters.");
                errros = true;
            } else if (userDao.checkEmailExist(email) != null){
                userErrors.setEmailError("Email is existed");
                errros = true;
            }
            
            if(password.isEmpty()){
                userErrors.setPasswordError("Password can't be blank!!!");
                errros = true;
            } else if (password.length()>50){
                userErrors.setPasswordError("Password must be <= 50 characters..");
                errros = true;
            }
            
            if(confirm.isEmpty()){
                userErrors.setConfirmError("Confirm must match Password!!");
                errros = true;
            }
            
            if(name.isEmpty()){
                userErrors.setNameError("Name can't be blank!!!");
                errros = true;
            } else if (name.length()>50){
                userErrors.setNameError("Name must be <= 50 characters.");
            }
            
            if(errros){
                request.setAttribute("ERROR", userErrors);
            } else {
                String password1 = DigestUtils.sha256Hex(password);
                boolean result = userDao.insertAccount(email, password1, name);
                if(result){
                    url = SUCCESS;
                }
            }
            
        } catch (NamingException ex) {
            logger.error("SignUpController_Naming : " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("SignUpController_SQL: " + ex.getMessage());
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
