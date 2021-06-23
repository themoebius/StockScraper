package app;

import java.io.IOException;
import java.time.LocalDateTime;
import scraper.StockScraper;
import static java.lang.String.valueOf;

public class Stock {
    public String tickerName;
    public String dailyHigh;
    public String dailyLow;
    public String volume;
    public String sixtyDayAverageVolume;
    public LocalDateTime datetime;
    public String socialMediaMentionsCount;
    public String[] redditComments;

    public Stock(String tickerName) throws IOException {
        StockScraper ticker = new StockScraper(tickerName);
        this.tickerName = tickerName;
        this.dailyHigh = ticker.getHigh();
        this.dailyLow = ticker.getLow();
        this.volume = ticker.getVolume();
        this.sixtyDayAverageVolume = ticker.get60DayAvg();
        this.datetime = LocalDateTime.now();
        this.redditComments = ticker.getMentions();

        try {
            this.socialMediaMentionsCount = valueOf(ticker.getMentions().length);
        }
        catch (NullPointerException e) {
            System.out.println("could not retrieve social media data./n Setting mentions to 0");
            if (this.socialMediaMentionsCount == null) {
                this.socialMediaMentionsCount = "0";
            }
        }
    }

    // creates Json-string ...not utilized but maybe good to have
    @Override
    public String toString()
    {
        return "[tickerName="
                + tickerName
                + ", dailyHigh="
                + dailyHigh
                + ", dailyLow="
                + dailyLow
                + ", volume="
                + volume
                + ", sixtyDayAverageVolume="
                + sixtyDayAverageVolume
                + ", redditComments="
                + redditComments
                + ", socialMediaMentionsCount="
                + socialMediaMentionsCount
                + ", datetime="
                + datetime
                + ", socialMediaComments="
                + redditComments
                + "]";
    }
}
