package model.dto;

import java.util.HashMap;

public record APIClientParam(
        String url, String method, HashMap<String, String> body, String... headers
) {
}
