package za.ac.nplinnovations.newsreader.connection.pojos;

import java.util.ArrayList;

public class Article {
    private String uri;
    private String url;
    private Integer id;
    private Integer asset_id;
    private String source;
    private String published_date;
    private String updated;
    private String section;
    private String subsection;
    private String nytdsection;
    private String adx_keywords;
    private String column;
    private String byline;
    private String type;
    private String title;
    private String Abstract;
    private ArrayList<String> des_facet;
    private ArrayList<String> org_facet;
    private ArrayList<String> per_facet;
    private ArrayList<String> geo_facet;
    private ArrayList<Media> media;
    private Integer eta_id;

    public Article() {
    }

    public String getUri() {
        return uri;
    }

    public String getUrl() {
        return url;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAsset_id() {
        return asset_id;
    }

    public String getSource() {
        return source;
    }

    public String getPublished_date() {
        return published_date;
    }

    public String getUpdated() {
        return updated;
    }

    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getNytdsection() {
        return nytdsection;
    }

    public String getAdx_keywords() {
        return adx_keywords;
    }

    public String getColumn() {
        return column;
    }

    public String getByline() {
        return byline;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstract() {
        return Abstract;
    }

    public ArrayList<String> getDes_facet() {
        return des_facet;
    }

    public ArrayList<String> getOrg_facet() {
        return org_facet;
    }

    public ArrayList<String> getPer_facet() {
        return per_facet;
    }

    public ArrayList<String> getGeo_facet() {
        return geo_facet;
    }

    public ArrayList<Media> getMedia() {
        return media;
    }

    public Integer getEta_id() {
        return eta_id;
    }
}
