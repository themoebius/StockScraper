package scraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Scraper {

    static String searchTerm;





    public Scraper(String searchTerm) throws IOException {
        this.searchTerm = searchTerm;

    }

    public URL getSearchURL(){
        return null;
    }
}
