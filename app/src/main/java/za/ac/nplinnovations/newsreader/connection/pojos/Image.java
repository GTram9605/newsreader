package za.ac.nplinnovations.newsreader.connection.pojos;

import java.io.Serializable;

public class Image implements Serializable {
    private String url;
    private String format;
    private Integer height;
    private Integer width;

    public Image() {
    }

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}
