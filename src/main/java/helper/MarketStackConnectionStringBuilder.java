package helper;

public class MarketStackConnectionStringBuilder extends ConnectionStringGenerator{
    private final static String leadString = "http://api.marketstack.com/v1/";
    private final static String midString = "eod/latest?access_key=";
    private final static String endString = "&symbols=";
    private final static String accessToken = System.getenv("MARKETSTACK_KEY");

    MarketStackConnectionStringBuilder(){
        super();
    }

    public static String getConnectionString(String searchTerm){
        return leadString + midString + accessToken + endString + searchTerm;
    }
}
