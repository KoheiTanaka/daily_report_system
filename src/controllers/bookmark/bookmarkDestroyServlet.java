package controllers.bookmark;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class bookmarkDestroyServlet
 */
@WebServlet("/bookmark/destroy")
public class bookmarkDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookmarkDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Employee e = (Employee)request.getSession().getAttribute("login_employee");
        Report r = em.find(Report.class,Integer.parseInt(request.getParameter("id")));

        List<Bookmark> bookmarkList = em.createNamedQuery("getBookMarkByEmpAndRep",Bookmark.class)
                                        .setParameter("employee",e)
                                        .setParameter("report",r)
                                        .getResultList();

        Bookmark b = bookmarkList.get(0);


        em.getTransaction().begin();
        em.remove(b);
        em.getTransaction().commit();
        em.close();
        request.getSession().setAttribute("flush", "ブックマークをはずしました。");




        response.sendRedirect(request.getContextPath() + "/reports/index");
    }

}
