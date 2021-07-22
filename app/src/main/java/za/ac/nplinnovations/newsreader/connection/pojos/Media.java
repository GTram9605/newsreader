package za.ac.nplinnovations.newsreader.connection.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media {
    private String type;
    private String subtype;
    private String caption;
    private String copyright;
    private Integer approved_for_syndication;
    @SerializedName("media-metadata")
    private List<Image> media_metadata;
    private Integer eta_id;

    public Media() {
    }

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public Integer getApproved_for_syndication() {
        return approved_for_syndication;
    }

    public List<Image> getMedia_metadata() {
        return media_metadata;
    }

    public Integer getEta_id() {
        return eta_id;
    }
}
