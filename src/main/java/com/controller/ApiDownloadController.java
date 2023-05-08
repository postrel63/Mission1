package com.controller;

import com.dto.bookMarkGroupDTO;
import com.dto.historyDTO;
import com.dto.wifiDTO;
import com.service.ApiExplorerService;
import com.service.bookmarkService;
import com.service.wifiService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/apiDownload", "/myLocate", "/history", "/deleteHistory", "/wifiDetail"})
public class ApiDownloadController extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        wifiService wifiservice = new wifiService();

        switch (request.getServletPath()) {

            case "/apiDownload":
                System.out.println("wifiapi controller");
                ApiExplorerService apiservice = new ApiExplorerService();
                int totalNum = apiservice.downloadWifi();
                request.setAttribute("totalNum", totalNum);
                request.getRequestDispatcher("/WEB-INF/view/wifi_save.jsp").forward(request, response);
                break;

            case "/myLocate":
                System.out.println("mylocate");
                float LAT = Float.parseFloat(request.getParameter("LAT"));
                float LNT = Float.parseFloat(request.getParameter("LNT"));
                wifiservice.SaveHistory(LAT, LNT);

                List<wifiDTO> wifilist = wifiservice.locateWifi(LAT, LNT);
                System.out.println(wifilist);


                request.setAttribute("wifilist", wifilist);
//                request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
                RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
                rs.forward(request, response);
                System.out.println("??");
                break;

            case "/history":
                System.out.println("history");

                List<historyDTO> historylist = wifiservice.showHistory();
                System.out.println(historylist);
                request.setAttribute("list", historylist);
                request.getRequestDispatcher("/WEB-INF/view/historyView.jsp").forward(request, response);
                break;

            case "/deleteHistory":
                int id = Integer.parseInt(request.getParameter("dNum"));
                int result = wifiservice.deleteHistory(id);
                request.getRequestDispatcher("/history").forward(request, response);
                break;

            case "/wifiDetail":
                String wId = request.getParameter("wId");
                wifiDTO dto = wifiservice.wifiDetail(wId);
                bookmarkService bservice = new bookmarkService();
                List<bookMarkGroupDTO> bookmarkGroupList = bservice.bookmarkGroupList();
                System.out.println(bookmarkGroupList);
                request.setAttribute("bookmarkGroupList",bookmarkGroupList);
                request.setAttribute("list", dto);
                request.getRequestDispatcher("/WEB-INF/view/wifiDetail.jsp").forward(request, response);


                break;

        }


    }
}
