package app;

import connector.APIconnector;
import scraper.google.GoogleScraper;
import scraper.reddit.RedditScraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

import static connector.APIconnector.buffReader;
import static connector.APIconnector.getBuffReader;

public class StockX {
    public  String tickerName;
    public  String volume;
    /*
    public final String dailyHigh;
    public final String dailyLow;
    public final String volume;
    public final String sixtyDayAverageVolume;
    */

    public final LocalDate date = LocalDate.now();

    public String socialMediaMentions;

    public StockX(String tickerName) throws IOException {
        this.tickerName = tickerName;

        /*
        this.dailyHigh = ticker.getHigh();
        this.dailyLow = ticker.getLow();
        this.volume = ticker.getVolume();
        this.sixtyDayAverageVolume = ticker.get60DayAvg();
        */
        this.socialMediaMentions = getMentionsFromReddit();
        this.volume = pullVolumeFromUrl();

    }


    public String getMentionsFromReddit() throws IOException {
        URL searchURL = new RedditScraper(this.tickerName).getSearchURL();
        System.out.println(searchURL);
        APIconnector apiConnector = new APIconnector(searchURL);

        String[] tickerVariations = new String[] {tickerName.toLowerCase(), tickerName.toUpperCase(), "$"+tickerName.toUpperCase(), "$"+tickerName.toUpperCase()};

        String line = getBuffReader().readLine();
        int mentions = 0;

        while (line != null) {
            if((line.contains(tickerVariations[0]) || line.contains(tickerVariations[1]) || line.contains(tickerVariations[2]) || line.contains(tickerVariations[3])) & !line.contains("permalink") & !line.contains("subreddit") & !line.contains("author")) {
                mentions++;
                System.out.println(line);
            }

            line= buffReader.readLine();
        }

        System.out.println("Mentions of " + tickerName + " during the last 3 days until "+ LocalDate.now() + ": " + mentions + "...");
        return String.valueOf(mentions);
    }


    public String pullVolumeFromUrl() throws IOException {
        URL searchURL = new GoogleScraper(this.tickerName).getSearchURL();
        APIconnector apiConnector = new APIconnector(searchURL);


        String volume = "";
        String line = getBuffReader().readLine();
        while (line != null) {
            if(line.contains("Volume: ")) {
                int targetLine = line.indexOf("Volume: ");
                int decimalPointPosition = line.indexOf(".", targetLine);

                int start = decimalPointPosition;

                while(line.charAt(start) != ' ') {
                    start--;
                }

                volume = line.substring(start + 1, decimalPointPosition +3);

            }

            line= buffReader.readLine();
        }

        System.out.println("Volume for " + tickerName + " on "+ LocalDate.now() + ": " + volume + " Million");
        return volume;
    }

/*
    private String pullAvgFromUrl() throws IOException {

        URLConnection connection = googleSearchUrl.openConnection();

        connection.addRequestProperty("User-Agent", requestProp);

        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader buffReader = new BufferedReader(streamReader);

        String average = "";
        String line = buffReader.readLine();

        while (line != null) {
            if(line.contains("Day Avg: ")) {
                int targetLine = line.indexOf("Day Avg: ");
                int decimalPointPosition = line.indexOf(".", targetLine);

                int start = decimalPointPosition;

                while(line.charAt(start) != ' ') {
                    start--;
                }

                average = line.substring(start + 1, decimalPointPosition +3);

            }

            line= buffReader.readLine();
        }

        System.out.println("60 Day Average Volume for " + searchTerm + " on "+ LocalDate.now() + ": " + average + " Million");

        return average;
    }

    private String pullDayLowFromUrl() throws IOException {

        URLConnection connection = googleSearchUrl.openConnection();

        connection.addRequestProperty("User-Agent", requestProp);

        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader buffReader = new BufferedReader(streamReader);


        String dayLow = "";
        String line = buffReader.readLine();

        while (line != null) {
            if(line.contains("vs Avg. ")) {
                int targetLine = line.indexOf("vs Avg. ");
                int decimalPointPosition = line.indexOf("g. ", targetLine);

                int start = decimalPointPosition;

                while(line.charAt(start) != ' ') {
                    start--;
                }

                dayLow = line.substring(start +6, decimalPointPosition +9);

            }

            line= buffReader.readLine();
        }

        System.out.println("Daily low for " + searchTerm + " on "+ LocalDate.now() + ": " + dayLow + " USD");
        return dayLow;
    }


    private String pullDayHighFromUrl() throws IOException {

        URLConnection connection = googleSearchUrl.openConnection();

        connection.addRequestProperty("User-Agent", requestProp);

        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader buffReader = new BufferedReader(streamReader);

        String dayHigh = "";
        String line = buffReader.readLine();

        while (line != null) {
            if(line.contains("Day Range ")) {
                int targetLine = line.indexOf("Day Range ");
                int decimalPointPosition = line.indexOf(".", targetLine);

                int start = decimalPointPosition;

                while(line.charAt(start) != ' ') {
                    start--;
                }

                dayHigh = line.substring(start +1, decimalPointPosition +3);

            }

            line= buffReader.readLine();
        }

        System.out.println("Daily high for " + searchTerm + " on "+ LocalDate.now() + ": " + dayHigh + " USD");
        return dayHigh;
    }

   */







}
