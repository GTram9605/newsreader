package za.ac.nplinnovations.newsreader.connection.pojos;

import java.io.Serializable;
import java.util.List;

public class MainResponse implements Serializable {
    private String status;
    private String copyright;
    private Integer num_results;
    private List<Article> results;

    public MainResponse(String status, String copyright,
            Integer num_results, List<Article> results) {
        this.status = status;
        this.copyright = copyright;
        this.num_results = num_results;
        this.results = results;
    }

    public MainResponse() {
    }

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public Integer getNum_results() {
        return num_results;
    }

    public List<Article> getResults() {
        return results;
    }
}
