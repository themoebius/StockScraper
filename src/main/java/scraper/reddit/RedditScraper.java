package scraper.reddit;

import scraper.Scraper;

import java.io.IOException;
import java.net.URL;

public class RedditScraper extends Scraper {

    String searchString;
    URL searchURL;

    public RedditScraper (String searchTerm) throws IOException {
        super(searchTerm);
        this.searchString = "https://api.pushshift.io/reddit/search/?q=" + searchTerm +"&size=500&after=2d";
        this.searchURL = new URL(searchString);
    }

    @Override
    public URL getSearchURL(){
        return this.searchURL;
    }
}
