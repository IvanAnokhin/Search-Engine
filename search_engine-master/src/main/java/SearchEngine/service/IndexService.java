package SearchEngine.service;

public interface IndexService {
    boolean indexUrl(String url);
    boolean indexAll();
    boolean stopIndexing();
}
