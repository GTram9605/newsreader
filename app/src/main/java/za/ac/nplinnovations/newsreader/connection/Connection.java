package za.ac.nplinnovations.newsreader.connection;

public class Connection {
    private static String ADDRESS = "api.nytimes.com/svc/mostpopular/v2/";
    private final static String PROTOCOL = "https://";
    private final static String API_KEY = "JGGKCKZJTTwd6NUWsQe3GhrQL7AjbIMh";

    public static String getUrl(String category, int period) {
        String url;

        if (!category.equalsIgnoreCase("facebook")) {
             url = PROTOCOL + ADDRESS + category + "/" + period + ".json?"
                    + "api-key=" + API_KEY;
        }else{
            url = PROTOCOL + ADDRESS  + "/shared/1/facebook.json?"
                    + "api-key=" + API_KEY;
        }

        return url;
    }

    public static String getApiKey() {
        return API_KEY;
    }
}
