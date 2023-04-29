package SearchEngine.parser;

import SearchEngine.dto.IndexDto;
import SearchEngine.model.Site;

import java.util.List;

public interface IndexParser {
    void run(Site site);
    List<IndexDto> getIndexList();
}
