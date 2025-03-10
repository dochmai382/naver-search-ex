package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import model.dto.APIClientParam;
import model.dto.NaverSearchResult;
import model.dto.NaverSearchResultItem;
import util.api.APIClient;
import util.logger.MyLogger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class NaverSearchService implements SearchService {
    private final String clientID;
    private final String clientSecret;
    private final APIClient apiClient;
    private final MyLogger logger;


    public NaverSearchService() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        this.clientID = dotenv.get("NAVER_CLIENT_ID");
        this.clientSecret = dotenv.get("NAVER_CLIENT_SECRET");

        if ( clientID == null || clientSecret == null) {
            throw new RuntimeException("NaverSerchService: clientID or clientSecret are missing");
        }

        this.logger = new MyLogger(NaverSearchService.class);
        this.apiClient = new APIClient();

        logger.info("NaverSearchService initialized");
    }

    @Override
    public List<NaverSearchResultItem> searchByKeyword(String keyword) throws IOException, InterruptedException {
        logger.info("searchByKeyword: " + keyword);
        HashMap<String, String> body = new HashMap<>();
        APIClientParam param = new APIClientParam("https://openapi.naver.com/v1/search/news.json?query=%s".formatted(keyword), "GET", body
                , "X-Naver-Client-Id", clientID, "X-Naver-Client-Secret", clientSecret);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(apiClient.callAPI(param), NaverSearchResult.class).items();
    }
}
