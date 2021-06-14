package app;
import java.io.IOException;
import java.time.LocalDate;
import scraper.StockScraper;

public class Stock {

    public final String tickerName;
    public final String dailyHigh;
    public final String dailyLow;
    public final String volume;
    public final String sixtyDayAverageVolume;
    public final LocalDate date = LocalDate.now();
    public final String socialMediaMentions;

    public Stock(String tickerName) throws IOException {
        StockScraper ticker = new StockScraper(tickerName);

        this.tickerName = tickerName;
        this.dailyHigh = ticker.getHigh();
        this.dailyLow = ticker.getLow();
        this.volume = ticker.getVolume();
        this.sixtyDayAverageVolume = ticker.get60DayAvg();
        this.socialMediaMentions = ticker.getMentions();
    }

}
