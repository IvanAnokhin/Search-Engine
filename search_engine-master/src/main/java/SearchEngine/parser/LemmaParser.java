package SearchEngine.parser;

import SearchEngine.dto.LemmaDto;
import SearchEngine.model.Site;

import java.util.List;

public interface LemmaParser {
    void run(Site site);
    List<LemmaDto> getLemmaDtoList();
}
