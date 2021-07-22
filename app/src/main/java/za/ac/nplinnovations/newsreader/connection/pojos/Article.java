package za.ac.nplinnovations.newsreader.connection.pojos;

import java.util.List;

public class Article {
    private String uri;
    private String url;
    private Double id;
    private Double asset_id;
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

    public Double getId() {
        return id;
    }

    public Double getAsset_id() {
        return asset_id;
    }

    private List<String> des_facet;
    private List<String> org_facet;
    private List<String> per_facet;
    private List<String> geo_facet;
    private List<Media> media;
    private Integer eta_id;

    public Article() {
    }

    public String getUri() {
        return uri;
    }

    public String getUrl() {
        return url;
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

    public List<String> getDes_facet() {
        return des_facet;
    }

    public List<String> getOrg_facet() {
        return org_facet;
    }

    public List<String> getPer_facet() {
        return per_facet;
    }

    public List<String> getGeo_facet() {
        return geo_facet;
    }

    public List<Media> getMedia() {
        return media;
    }

    public Integer getEta_id() {
        return eta_id;
    }
}
