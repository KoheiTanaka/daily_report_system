package controllers.bookmark;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Bookmark;
import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class bookmarkCreateServlet
 */
@WebServlet("/create")
public class bookmarkCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookmarkCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em =DBUtil.createEntityManager();

         Bookmark b = new Bookmark();

         b.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

         Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
         b.setReport(r);
         b.setBookmark_flag(0);

         em.getTransaction().begin();
         em.persist(b);
         em.getTransaction().commit();
         em.close();
         request.getSession().setAttribute("flush", "ブックマークしました。");

         response.sendRedirect(request.getContextPath() + "/reports/index");
    }

}
