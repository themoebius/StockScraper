package helper;

public class RedditConnectionStringBuilder {
    private final static String leadString = "https://api.pushshift.io/reddit/search/comment/?q=";
    private final static String midString = "&subreddit=";
    private final static String endString = "&size=499";

    public RedditConnectionStringBuilder() {
        super();
    }

    public static String getConnectionString(String searchTerm, String[] subSelectorChoice) {
        StringBuilder subredditStringBuilder = new StringBuilder();

        for(String subredditString : subSelectorChoice) {
            subredditStringBuilder.append(subredditString).append(",");
        }
        String subRedditString = subredditStringBuilder.deleteCharAt(subredditStringBuilder.lastIndexOf(",")).toString();
        return leadString + searchTerm + midString + subRedditString + endString;
    }
}
