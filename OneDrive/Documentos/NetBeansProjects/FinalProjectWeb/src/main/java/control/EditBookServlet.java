package control;

import entities.Books;
import jpa.BooksJpaController;
import jpa.exceptions.NonexistentEntityException;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditBookServlet", urlPatterns = {"/EditBookServlet"})
public class EditBookServlet extends HttpServlet {

    private BooksJpaController booksController;

    @Override
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_FinalProjectWeb_war_1.0-SNAPSHOTPU");
        booksController = new BooksJpaController(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene el ID del libro desde la URL
        String bookIdParam = request.getParameter("book_id");
        if (bookIdParam != null) {
            try {
                int bookId = Integer.parseInt(bookIdParam);
                Books book = booksController.findBook(bookId);

                // Si el libro existe, lo pasa al JSP
                if (book != null) {
                    request.setAttribute("book", book);
                    request.getRequestDispatcher("editBook.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Redirige al dashboard si no se encuentra el libro
        response.sendRedirect("DashboardServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Procesa la edición del libro
        String bookIdParam = request.getParameter("book_id");
        String title = request.getParameter("title");
        String yearPublishedParam = request.getParameter("year_published");

        if (bookIdParam != null && title != null && yearPublishedParam != null) {
            try {
                int bookId = Integer.parseInt(bookIdParam);
                int yearPublished = Integer.parseInt(yearPublishedParam);

                // Actualiza el libro
                Books book = booksController.findBook(bookId);
                if (book != null) {
                    book.setTitle(title);
                    book.setYearPublished(yearPublished);
                    booksController.edit(book);

                    // Redirige al dashboard después de guardar los cambios
                    response.sendRedirect("DashboardServlet");
                    return;
                }
            } catch (NumberFormatException | NonexistentEntityException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Redirige al dashboard en caso de error
        response.sendRedirect("DashboardServlet");
    }
}
