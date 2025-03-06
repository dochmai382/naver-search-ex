import io.github.cdimascio.dotenv.Dotenv;
import model.dto.NaverSearchResultItem;
import service.NaverSearchService;
import service.SearchService;
import util.logger.MyLogger;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        MyLogger logger = new MyLogger(Application.class);
        logger.info("main()");

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String searchKeyword = dotenv.get("SEARCH_KEYWORD");
        SearchService searchService = new NaverSearchService();

        try {
            List<NaverSearchResultItem> resultItems = searchService.searchByKeyword(searchKeyword);
            logger.info(resultItems.toString());
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }
}
