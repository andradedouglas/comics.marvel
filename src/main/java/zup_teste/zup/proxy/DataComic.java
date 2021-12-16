package zup_teste.zup.proxy;

import java.util.List;

public class DataComic {

    private List<ResultsComic> results;
    
    public DataComic() {
    }


    public DataComic(List<ResultsComic> results) {
        this.results = results;
    }

    public List<ResultsComic> getResults() {
        return this.results;
    }

    public void setResults(List<ResultsComic> results) {
        this.results = results;
    }
   
}
