package za.ac.nplinnovations.newsreader.connection.pojos;

import java.util.ArrayList;

public class MainResponse {
    private String status;
    private String copyright;
    private Integer num_results;
    private ArrayList<Article> results;

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

    public ArrayList<Article> getResults() {
        return results;
    }
}
