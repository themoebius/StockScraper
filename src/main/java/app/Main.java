package app;

import java.io.IOException;
import java.util.ArrayList;
import static app.Helper.createRunConfig;
import static app.Helper.saveAllToFile;


public class Main {

    public static void main (String[] args) throws IOException {

        //generating Stock-Objects from stockNames in run-config
        //and scraping information to fill Objects
        ArrayList<Stock> getData = createRunConfig(args);

        // converting and saving &| appending generated Objects
        // to .txt files in 'generatedData'- folder
        saveAllToFile(getData);
    }
}
