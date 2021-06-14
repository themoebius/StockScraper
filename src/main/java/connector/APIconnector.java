package connector;

import scraper.Scraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class APIconnector {
    public static InputStreamReader streamReader;

    public static BufferedReader buffReader;

    public static URL connectionURL;

    public static URLConnection connection;

    private final String requestProp = "\"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)\"";


    public APIconnector(URL connectionURL) throws IOException {
        this.connectionURL = connectionURL;
        this.connection = connectionURL.openConnection();
        this.streamReader = new InputStreamReader(connection.getInputStream());
        this.buffReader = new BufferedReader(streamReader);
    }

    public static BufferedReader getBuffReader() {
        return buffReader;
    }
}

    //connection.addRequestProperty("User-Agent", requestProp);

