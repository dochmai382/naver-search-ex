package model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NaverSearchResultItem(
        String title,
        String link,
        String description,
        String pubDate
){
}
