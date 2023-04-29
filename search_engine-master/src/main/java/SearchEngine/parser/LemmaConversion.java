package SearchEngine.parser;

import SearchEngine.utils.ClearHtmlCode;
import SearchEngine.dto.LemmaDto;
import SearchEngine.model.Field;
import SearchEngine.model.Page;
import SearchEngine.model.Site;
import SearchEngine.morphology.Morphology;
import SearchEngine.repository.FieldRepository;
import SearchEngine.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class LemmaConversion implements LemmaParser {
    private final PageRepository pageRepository;
    private final FieldRepository fieldRepository;
    private final Morphology morphology;
    private List<LemmaDto> lemmaDtoList;

    @Override
    public List<LemmaDto> getLemmaDtoList() {
        return lemmaDtoList;
    }

    @Override
    public void run(Site site) {
        lemmaDtoList = new ArrayList<>();
        List<Page> pageList = pageRepository.findBySite(site);
        List<Field> fieldList = fieldRepository.findAll();
        HashMap<String, Integer> lemmaList = new HashMap<>();
        for (Page page : pageList) {
            var content = page.getContent();
            var title = ClearHtmlCode.clear(content, fieldList.get(0).getSelector());
            var body = ClearHtmlCode.clear(content, fieldList.get(1).getSelector());
            var titleList = morphology.getLemmaList(title);
            var bodyList = morphology.getLemmaList(body);

            Set<String> allWords = new HashSet<>();
            allWords.addAll(titleList.keySet());
            allWords.addAll(bodyList.keySet());
            for (String word : allWords) {
                int frequency = lemmaList.getOrDefault(word, 0);
                lemmaList.put(word, frequency + 1);
            }
        }

        for (String lemma : lemmaList.keySet()) {
            var frequency = lemmaList.get(lemma);
            lemmaDtoList.add(new LemmaDto(lemma, frequency));
        }

    }
}
