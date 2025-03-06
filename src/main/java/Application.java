import io.github.cdimascio.dotenv.Dotenv;
import model.dto.NaverSearchResultItem;
import service.NaverSearchService;
import service.SearchService;
import util.ExcelWriter;
import util.logger.MyLogger;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        MyLogger logger = new MyLogger(Application.class);
        logger.info("main()");

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String searchKeyword = dotenv.get("SEARCH_KEYWORD");
        String mode = dotenv.get("MODE");
        if (mode.isBlank()) {
            throw new RuntimeException("mode is missing");
        }
        SearchService searchService = new NaverSearchService();

        List<NaverSearchResultItem> resultItems = null;
        try {
            resultItems = searchService.searchByKeyword(searchKeyword);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        ExcelWriter excelWriter = new ExcelWriter();
        String filepath = "%d %s";
        if (mode.equals("DEV")) {
            filepath += "_dev";
        }
        excelWriter.createExcel(filepath.formatted(System.currentTimeMillis(), searchKeyword) + ".xlsx", searchKeyword, resultItems);
    }
}
