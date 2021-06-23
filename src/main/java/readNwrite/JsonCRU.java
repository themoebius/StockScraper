package readNwrite;

import app.Stock;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class JsonCRU {

    public static String createJsonStringFromObject(Stock stock) {
        Gson gson = new Gson();
        //creates String with '!' as a separator between stockName and jsonString
        return stock.tickerName + "!" + gson.toJson(stock);
    }

    public static Stock createObjectFromJsonString(String jsonStr) {
        Gson gson = new Gson();
        Stock stockFromString = gson.fromJson(jsonStr, Stock.class);
        return stockFromString;
    }

    public static void writeJsonStringToFile(String singleJsonStr) {
        // find position of separator '!' to split stockName from jsonString
        int separatorPosition = singleJsonStr.indexOf('!');

        String stockName = singleJsonStr.substring(0 , separatorPosition);
        String cleanJsonStr = singleJsonStr.substring(separatorPosition + 1, singleJsonStr.length() - 1);

        int success = 0;

        // save jsonString to file: if file exists, jsonString will be appended,
        // if no file exists, file will be created.
        try {
            final Path path = Paths.get(".\\generatedData\\" + stockName + ".txt");
            Files.write(path, Arrays.asList(cleanJsonStr), StandardCharsets.UTF_8, Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            success =1;
        } catch (IOException e) {
            System.out.println("Something went wrong here..." + e);
        }
        if (success != 0) {
            System.out.println("saving data for " + stockName + " to file successful");
        }
    }
}