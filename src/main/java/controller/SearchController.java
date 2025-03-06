package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.NaverSearchResultItem;
import service.NaverSearchService;
import service.SearchService;
import util.logger.MyLogger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    private final MyLogger logger = new MyLogger(SearchController.class);
    private final SearchService searchService = new NaverSearchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("SearchController doGet()");

        String keyword = req.getParameter("keyword");
        String json = "";
        List<NaverSearchResultItem> searchResultItems = null;
        try {
             searchResultItems = searchService.searchByKeyword(keyword);
             ObjectMapper mapper = new ObjectMapper();
             json = mapper.writeValueAsString(searchResultItems);
        } catch (InterruptedException e) {
            resp.sendError(500);
            logger.severe(e.getMessage());
        }

        // 응답 설정
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // JSON 응답 전송
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
