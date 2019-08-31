package controllers.bookmark;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Bookmark;
import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class bookmarkIndexServlet
 */
@WebServlet("/bookmark/index")
public class bookmarkIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookmarkIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       EntityManager em = DBUtil.createEntityManager();

       Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

       int page;
       try{
           page=Integer.parseInt(request.getParameter("page"));
       }catch(Exception e){
           page=1;
       }
       List<Bookmark> bookmark=em.createNamedQuery("getMyAllBookmark",Bookmark.class)
               .setParameter("employee", login_employee)
               .setFirstResult(15 * (page-1))
               .setMaxResults(15)
               .getResultList();

       long bookmark_count =(long)em.createNamedQuery("getMyBookmarkCount",Long.class)
               .setParameter("employee", login_employee)
                           .getSingleResult();

       em.close();

       request.setAttribute("bookmark",bookmark);
       request.setAttribute("bookmark_count", bookmark_count);
       request.setAttribute("page", page);

       RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/bookmark/index.jsp");
       rd.forward(request, response);

    }

}
