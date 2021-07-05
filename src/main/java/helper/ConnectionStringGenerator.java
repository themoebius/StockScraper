package helper;

public class ConnectionStringGenerator {

    public static String getRedditConnectionString(String searchTerm, String[] subSelectorChoice) {
        return RedditConnectionStringBuilder.getConnectionString(searchTerm, subSelectorChoice);
    }

    public static String getMarketStackConnectionString(String searchTerm) {
        return MarketStackConnectionStringBuilder.getConnectionString(searchTerm);
    }
}