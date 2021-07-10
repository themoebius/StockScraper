package readNwrite;

import app.Stock;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Arrays;

import static java.nio.file.Files.exists;

public class JsonCRU {

    public static String createJsonStringFromObject(Stock stock) {
        Gson gson = new Gson();
        //creates String with '!' as a separator between stockName and jsonString
        return stock.getTickerName() + "!" + gson.toJson(stock);
    }

    public static Stock createObjectFromJsonString(String jsonStr) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, Stock.class);
    }

    public static void writeJsonStringToFile(String singleJsonStr) {
        // find position of separator '!' to split stockName from jsonString
        int separatorPosition = singleJsonStr.indexOf('!');

        String stockName = singleJsonStr.substring(0, separatorPosition);
        String cleanJsonStr = singleJsonStr.substring(separatorPosition + 1, singleJsonStr.length() - 1);

        int success = 0;

        // save jsonString to file: if file exists, jsonString will be appended,
        // if no file exists, file will be created.
        try {
            final Path path = Paths.get(".\\generatedData\\" + stockName + ".txt");
            Files.write(path, Arrays.asList(cleanJsonStr), StandardCharsets.UTF_8, exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            success = 1;
        } catch (IOException e) {
            System.out.println("Something went wrong here..." + e);
        }
        if (success != 0) {
            System.out.println("saving data for " + stockName + " to file successful");
        }
    }

    public static String createJsonStringFromDateObject(LocalDateTime dateTime) {
        Gson gson = new Gson();
        return gson.toJson(dateTime);
    }

    public static LocalDateTime createDateObjectFromJsonString(String jsonStr) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, LocalDateTime.class);
    }


    public static LocalDateTime readDateFromFile(String filePath) throws IOException {
        final Path path = Paths.get(filePath);
        boolean saveFileExists = Files.exists(path);
        if (saveFileExists) {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            Gson gson = new Gson();
            return gson.fromJson(reader, LocalDateTime.class);
        } else {
            System.out.println("a new file will be created to keep track of the time-periods between runs");
            return null;
        }
    }

    public static void writeDateToFile(String DateAsJsonString) {
        int success = 0;
        // save jsonString to file: if file exists, it will be cleared,
        // if no file exists, file will be created.- the file will ever only hold one LocalDateTime Json.
        try {
            final Path path = Paths.get(".\\generatedData\\" + "ScrapeTimeTracker" + ".txt");
            Files.write(path, Arrays.asList(DateAsJsonString), StandardCharsets.UTF_8, exists(path) ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.CREATE);
            success =1;
        } catch (IOException e) {
            System.out.println("Something went wrong here..." + e);
        }
        if (success != 0) {
            System.out.println("saving DateTime of current run to file successful");
        }
    }
}