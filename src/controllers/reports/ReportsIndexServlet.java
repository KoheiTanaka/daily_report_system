package controllers.reports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Bookmark;
import models.BookmarkCheck;
import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsIndexServlet
 */
@WebServlet("/reports/index")
public class ReportsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsIndexServlet() {
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
        List<Report> reports=em.createNamedQuery("getAllReports",Report.class)
                .setFirstResult(15 * (page-1))
                .setMaxResults(15)
                .getResultList();

        long reports_count =(long)em.createNamedQuery("getReportsCount",Long.class)
                            .getSingleResult();

        List<Bookmark> bookmark = em.createNamedQuery("getMyAllBookmark",Bookmark.class)
                .setParameter("employee", login_employee)
                .getResultList();

        List<BookmarkCheck> bookmarkChecks = new ArrayList<BookmarkCheck>();
        for(Report r : reports){
            BookmarkCheck bmc = new BookmarkCheck();
            bmc.setReport(r);

            for(Bookmark bm : bookmark){
                if(r.getId() == bm.getReport().getId()){
                    bmc.setCheck(true);
                }
            }
            bookmarkChecks.add(bmc);
        }






        em.close();


        request.setAttribute("bookmarkChecks", bookmarkChecks);
        request.setAttribute("reports_count",reports_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush",request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
        rd.forward(request, response);
    }

}
