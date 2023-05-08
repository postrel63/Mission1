package com.controller;

import com.dto.bookMarkDTO;
import com.dto.bookMarkGroupDTO;
import com.service.bookmarkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet({"/manageBookmark","/addBookmarkGroupPage","/addBookmarkGroup","/showBookmark","/deleteBookmarkGroup","/addBookmark","/deleteBookmark"})
public class bookmarkController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                bookmarkService bservice = new bookmarkService();

        switch (request.getServletPath()){

            case "/manageBookmark":
                System.out.println("manageBookmark");
               List<bookMarkGroupDTO> list =  bservice.showBookmarkgroup();
                System.out.println(list);
                request.setAttribute("list", list);
                request.getRequestDispatcher("/WEB-INF/view/bookmarkGroup.jsp").forward(request,response);
                break;

            case "/deleteBookmarkGroup":
                System.out.println("deleteBookmarkGroup");
                int Nid = Integer.parseInt(request.getParameter("Nid"));
                int result = bservice.deleteBookmarkGroup(Nid);
                request.getRequestDispatcher("/manageBookmark").forward(request,response);


                break;

            case "/addBookmarkGroupPage":
                System.out.println("addBookmarkGroupPage");
                request.getRequestDispatcher("/WEB-INF/view/addbookmarkGroup.jsp").forward(request,response);

                break;

            case "/addBookmarkGroup":
                System.out.println("addBookmarkGroup");
                int sequence = Integer.parseInt(request.getParameter("sequence"));
                String name = request.getParameter("name");
                bservice.addBookmarkGroup(name,sequence);
                request.getRequestDispatcher("/manageBookmark").forward(request,response);

                break;

            case "/addBookmark":
                int bId = Integer.parseInt(request.getParameter("bId"));
                String wName = request.getParameter("wName");

                int addResult = bservice.addBookmark(bId,wName);
                request.setAttribute("addResult",addResult);
                request.setAttribute("bId",bId);
                request.getRequestDispatcher("/wifiDetail").forward(request,response);

                break;

            case "/showBookmark":
                System.out.println("showBookmark");
                List<bookMarkDTO> blist =  bservice.showBookmark();
                request.setAttribute("blist",blist);
                request.getRequestDispatcher("/WEB-INF/view/bookmark.jsp").forward(request,response);
                break;


            case "/deleteBookmark":
                System.out.println("deleteBookmark");
                int dId = Integer.parseInt(request.getParameter("dId"));
                bservice.deleteBookmark(dId);
                request.getRequestDispatcher("/showBookmark").forward(request,response);



                break;
        }
    }
}
