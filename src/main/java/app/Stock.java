package app;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import static helper.MarketStackConnectionStringBuilder.getConnectionString;
import static helper.RedditConnectionStringBuilder.getConnectionString;
import static java.lang.String.valueOf;
import static scraper.MarketStackScraper.getStockData;
import static scraper.RedditScraper.getRedditComments;

public class Stock {
    private String tickerName;
    private String dailyHigh;
    private String dailyLow;
    private String dailyVolume;
    private String open;
    private String close;
    private LocalDateTime datetime;
    private String socialMediaMentionsCount;
    private ArrayList<String> redditComments;

    public Stock() {
        super();
    }

    public Stock(String tickerName, String[] subRedditChoice) throws IOException {
        this.tickerName = tickerName;

        String redditString = getConnectionString(tickerName, subRedditChoice);
        String financeString = getConnectionString(tickerName);

        HashMap<String, String> stockValues = getStockData(financeString);

        this.dailyHigh = stockValues.get("high");
        this.dailyLow = stockValues.get("low");
        this.dailyVolume = stockValues.get("volume");
        this.open = stockValues.get("open");
        this.close = stockValues.get("close");
        this.datetime = LocalDateTime.now();
        this.redditComments = getRedditComments(redditString);
        this.tickerName = stockValues.get("symbol");

        try {
            this.socialMediaMentionsCount = valueOf(redditComments.size());
        }
        catch (NullPointerException e) {
            System.out.println("could not retrieve social media data./n Setting mentions to 0");
            if (this.socialMediaMentionsCount == null) {
                this.socialMediaMentionsCount = "0";
            }
        }
    }

    public String getTickerName() {
        return tickerName;
    }

    @Override
    public String toString()
    {
        return "[tickerName="
                + tickerName
                + ", date="
                + datetime
                + ", dailyHigh="
                + dailyHigh
                + ", dailyLow="
                + dailyLow
                + ", volume="
                + dailyVolume
                + ", open="
                + open
                + ", close="
                + close
                + ", socialMediaMentionsCount="
                + socialMediaMentionsCount
                + ", redditComments="
                + redditComments
                + "]";
    }
}
