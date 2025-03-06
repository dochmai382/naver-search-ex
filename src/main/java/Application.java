import io.github.cdimascio.dotenv.Dotenv;
import service.NaverSearchService;
import service.SearchService;
import util.logger.MyLogger;

public class Application {
    public static void main(String[] args) {
        MyLogger logger = new MyLogger(Application.class);
        logger.info("main()");

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String searchKeyword = dotenv.get("SEARCH_KEYWORD");
        SearchService searchService = new NaverSearchService();
        String result = searchService.searchByKeyword(searchKeyword);

        logger.info(result);
    }
}
