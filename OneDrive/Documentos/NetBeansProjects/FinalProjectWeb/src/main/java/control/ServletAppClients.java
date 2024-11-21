/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import entities.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.UsersJpaController;

/**
 *
 * @author Alexis
 */
public class ServletAppClients extends HttpServlet {
    private UsersJpaController usersJPA;
    private final static String PU = "com.mycompany_FinalProjectWeb_war_1.0-SNAPSHOTPU";

    @Override
    public void init() throws ServletException {
        super.init();
        // Inicializa el controlador JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        usersJPA = new UsersJpaController(emf);
    }

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
            // Consulta todos los usuarios de la base de datos
            List<Users> usersList = usersJPA.findUsersEntities();

            // Genera la página HTML
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Usuarios Registrados</title>");
            out.println("<style>");
            out.println("table { border-collapse: collapse; width: 50%; margin: auto; }");
            out.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 style='text-align: center;'>Lista de Usuarios</h1>");

            if (usersList.isEmpty()) {
                out.println("<p style='text-align: center;'>No hay usuarios registrados.</p>");
            } else {
                out.println("<table>");
                out.println("<tr><th>Username</th><th>Password</th></tr>");
                for (Users user : usersList) {
                    out.println("<tr>");
                    out.println("<td>" + user.getUsername() + "</td>");
                    out.println("<td>" + user.getPassword() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet que muestra una lista de usuarios registrados.";
    }
}

