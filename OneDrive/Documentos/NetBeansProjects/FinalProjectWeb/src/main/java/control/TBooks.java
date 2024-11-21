/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import entities.Books;
import entities.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.BooksJpaController;
import jpa.UsersJpaController;

/**
 *
 * @author Alexis
 */
@WebServlet(name = "TestBooksServlet", urlPatterns = {"/TestBooksServlet"})
public class TBooks extends HttpServlet {
    private BooksJpaController booksController;

    @Override
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_FinalProjectWeb_war_1.0-SNAPSHOTPU");
        booksController = new BooksJpaController(emf);
        
        System.out.println("Hello man");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Books> books = booksController.findBooksEntities();
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if (books.isEmpty()) {
            out.println("No se encontraron libros.");
        } else {
            for (Books book : books) {
                out.println("Título: " + book.getTitle() + ", Año: " + book.getYearPublished());
            }
        }
    }
}

