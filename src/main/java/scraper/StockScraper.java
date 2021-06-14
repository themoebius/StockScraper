package scraper;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;



public final class StockScraper {

    private final String searchTerm;

    private final URL googleSearchUrl;

    private final URL redditSearchUrl;

    private final String requestProp = "\"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)\"";


    public String getHigh() {
        try {
            return pullDayHighFromUrl();
        } catch (IOException e) {
            return "Could not fetch daily high";
        }
    }

    public String getLow () {
        try {
            return pullDayLowFromUrl();
        } catch (IOException e) {
            return "Could not fetch daily low";
        }
    }

    public String getVolume () {
        try {
            return pullVolumeFromUrl();
        } catch (IOException e) {
            return "Could not fetch today's volume";
        }
    }

    public String get60DayAvg () {
        try {
            return pullAvgFromUrl();
        } catch (IOException e) {
            return "Could not fetch 60 day average volume";
        }
    }

    public String getMentions() {
        try {
            return getMentionsFromReddit();

        } catch(IOException e) {
            return "Could not fetch 60 day average volume";
        }
    }

    public StockScraper(String searchTerm) throws IOException{
        this.searchTerm = searchTerm;
        String googleSearchString = "https://www.google.com/search?q=" + this.searchTerm + "+stock+volume&oq=" + this.searchTerm + "+stock+volume&sourceid=chrome&ie=UTF-8";
        this.googleSearchUrl = new URL(googleSearchString);
        String redditSearchStringOLD = "https://api.pushshift.io/reddit/search/submission/?q=" + this.searchTerm;
        String redditSearchString = "https://api.pushshift.io/reddit/search/comment/?q=" + this.searchTerm +"&aggs=link_id";

        String redditSearchString2 = "https://api.pushshift.io/reddit/comment/search/?subreddit=wallstreetbets,superstonk&aggs=subreddit&q=" + this.searchTerm;
        this.redditSearchUrl = new URL(redditSearchString);
    }




    private String getMentionsFromReddit() throws IOException {
        URLConnection connection = redditSearchUrl.openConnection();
        //connection.addRequestProperty("User-Agent", requestProp);
        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader buffReader = new BufferedReader(streamReader);

        String[] tickerVariations = new String[] {searchTerm.toLowerCase(), searchTerm.toUpperCase(), "$"+searchTerm.toUpperCase(), "$"+searchTerm.toUpperCase()};



        String line = buffReader.readLine();
        int mentions = 0;

        while (line != null) {
            //System.out.println(line);
            if((line.contains(tickerVariations[0]) || line.contains(tickerVariations[1]) || line.contains(tickerVariations[2]) || line.contains(tickerVariations[3])) & !line.contains("permalink") & !line.contains("subreddit") & !line.contains("author")) {
                mentions++;
                System.out.println(line);
            }

            line= buffReader.readLine();
        }

        System.out.println("Mentions of " + searchTerm + " during the last 3 days until "+ LocalDate.now() + ": " + mentions + "...");
        return String.valueOf(mentions);
    }


    private String pullVolumeFromUrl() throws IOException {
        URLConnection connection = googleSearchUrl.openConnection();
        connection.addRequestProperty("User-Agent", requestProp);
        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader buffReader = new BufferedReader(streamReader);

        String volume = "";
        String line = buffReader.readLine();

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

        System.out.println("Volume for " + searchTerm + " on "+ LocalDate.now() + ": " + volume + " Million");
        return volume;
    }


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



}