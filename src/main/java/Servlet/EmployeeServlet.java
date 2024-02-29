/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import EJB.EmpEJBModuleLocal;
import Entity.Department;
import Entity.Employee;
import Entity.Project;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = {"/EmployeeServlet"})
public class EmployeeServlet extends HttpServlet {

    @EJB EmpEJBModuleLocal eeml;
    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Neel Employee Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>15 - Neel Gajjar | Assignment-3 : Question 3</h1>");
 
            Collection<Department> depts = eeml.getAllDepartment();
            Collection<Employee> emps = eeml.getAllEmployee();
            Collection<Project> projs = eeml.getAllProject();
            
            out.println("<h2>Department</h2>");
            out.println("<table border=2><tr><th>ID</th><th>Name</th></tr>");
            
            for(Department dept : depts)
            {
                out.println("<tr><td>"+dept.getDeptId()+"</td><td>"+dept.getDeptName()+"</td></tr>");
            }
            
            out.println("</table>");
            out.println("<br/>");
            
            out.println("<h2>Employee</h2>");
            out.println("<table border=2><tr><th>ID</th><th>Name</th><th>Salary</th></tr>");
            
            for(Employee emp : emps)
            {
                out.println("<tr><td>"+emp.getEmpID()+"</td><td>"+emp.getEmpName()+"</td><td>"+emp.getEmpSalary()+"</td></tr>");
            }
            
            out.println("</table>");
            out.println("<br/>");
            
            out.println("<h2>Project</h2>");
            out.println("<table border=2><tr><th>ID</th><th>Name</th><th>Deadline</th><th>Cost</th></tr>");
            
            for(Project proj : projs)
            {
                out.println("<tr><td>"+proj.getProjID()+"</td><td>"+proj.getProjName()+"</td><td>"+proj.getPorjDeadline()+"</td><td>"+proj.getProjCost()+"</td></tr>");
            }
            
            out.println("</table>");
                        
            out.println("</body>");
            out.println("</html>");
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
