/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kelompok22;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Evita Cindy S
 */
@WebServlet(name = "LoginSevlet", urlPatterns = {"/Login"})
public class LoginSevlet extends HttpServlet {

    @EJB
    private ValidasiSession validasiSession;

    LoginSessionLocal loginSession = lookupLoginSessionLocal();

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
        validasiSession = new ValidasiSession();
        
        request.setAttribute("status", "false");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginView.jsp");
        rd.forward(request, response);

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginSevlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginSevlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        if("Keluar".equals(request.getParameter("keluar"))){
            loginSession.setLoginStatus(false);
            request.setAttribute("nimEr", "");
            request.setAttribute("namaEr", "");
        }

        
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
        String nama = request.getParameter("nama");
String nim  = request.getParameter("nim");
boolean stNama, stNim = false;
stNama = validasiSession.nama(nama);
stNim  = validasiSession.nim(nim);
    if (stNim && stNama){
        if (loginSession.Login(nama, nim)) {
           loginSession.setLoginStatus(true);
        } else {
                request.setAttribute("userEr", "Mahasiswa tidak terdaftar");
            }
        }
        else{
            if (!stNim) request.setAttribute("nimEr", "Inputan Salah");
            if (!stNama) request.setAttribute("namaEr", "Inputan Salah");
        }
        
        if (loginSession.isLoginStatus()) {
            request.setAttribute("status", "true");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginView.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("status", "false");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginView.jsp");
            rd.forward(request, response);
        }

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

    private LoginSessionLocal lookupLoginSessionLocal() {
        try {
            Context c = new InitialContext();
            return (LoginSessionLocal) c.lookup("java:global/Modul2_Kelompok22/Modul2_Kelompok22-ejb/LoginSession!Kelompok22.LoginSessionLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
