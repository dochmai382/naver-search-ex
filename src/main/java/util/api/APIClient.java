package util.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.dto.APIClientParam;
import util.logger.MyLogger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIClient {
    private final MyLogger logger;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public APIClient() {
        this.logger = new MyLogger(APIClient.class);
        logger.info("APIClient initialized");
    }

    public String callAPI(APIClientParam param) throws IOException, InterruptedException {
        logger.info("call API");
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(param.body());
        HttpResponse<String> response = httpClient.send(HttpRequest.newBuilder()
                .uri(URI.create(param.url()))
                .method(param.method(), HttpRequest.BodyPublishers.ofString(body))
                .headers(param.headers())
                .build(), HttpResponse.BodyHandlers.ofString());
        logger.info(Integer.toString(response.statusCode()));
        return response.body();
    }
}
