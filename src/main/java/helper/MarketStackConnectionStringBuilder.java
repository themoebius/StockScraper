package helper;

public class MarketStackConnectionStringBuilder {

    final static String leadString = "http://api.marketstack.com/v1/";
    final static String midString = "eod/latest?access_key=";
    final static String endString = "&symbols=";
    final static String accessToken = System.getenv("MARKETSTACK_KEY");

    MarketStackConnectionStringBuilder(){
        super();
    }

    public static String getConnectionString(String searchTerm){
        return leadString + midString + accessToken + endString + searchTerm;
    }
}
