package za.ac.nplinnovations.newsreader.connection;

public class Connection {
    private static String ADDRESS = "developer.nytimes.com/svc/mostpopular/v2/";
    private final static String PROTOCOL = "https://";
    private final static String API_KEY = "JGGKCKZJTTwd6NUWsQe3GhrQL7AjbIMh";

    public static String getUrl(String category, int period) {
        String url = PROTOCOL + ADDRESS + category + "/";
                //+ "api-key=" + API_KEY;

        return url;
    }

    public static String getApiKey() {
        return API_KEY;
    }
}
