package service;

import model.dto.NaverSearchResultItem;

import java.io.IOException;
import java.util.List;

public interface SearchService {
    List<NaverSearchResultItem> searchByKeyword(String searchKeyword) throws IOException, InterruptedException;
}
