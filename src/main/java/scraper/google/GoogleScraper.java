package scraper.google;

import scraper.Scraper;

import java.io.IOException;
import java.net.URL;

public class GoogleScraper extends Scraper {


    String searchString;
    URL searchURL;

    public GoogleScraper(String searchTerm) throws IOException {
        super(searchTerm);
        this.searchString = "https://www.google.com/search?q=" + searchTerm + "+stock+volume&oq=" + searchTerm + "+stock+volume&aqs=chrome..69i57j0l5j69i65l2.4320j1j7&sourceid=chrome&ie=UTF-8";
        this.searchURL = new URL(searchString);
    }

    @Override
    public URL getSearchURL(){
        return this.searchURL;
    }

}
