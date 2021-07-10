package helper;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import static readNwrite.JsonCRU.*;

public abstract class ScrapeTimeTracker {

    // get last DateTime from .txt file
    private static final String timeTrackingFilePath = ".\\generatedData\\ScrapeTimeTracker.txt";

    // compare last DateTime from file to DateTime.now()
    public static boolean isTimeToUpdate() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastRunDatetime = null;
        try {
            lastRunDatetime = readDateFromFile(timeTrackingFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (lastRunDatetime != null) {
            Duration duration = Duration.between(lastRunDatetime, now);
            if (duration.toDays() < 1) {
                System.out.println("You updated on: " + lastRunDatetime.getDayOfWeek() + " at " + lastRunDatetime.getHour() +":" +lastRunDatetime.getMinute() + " already. Do you want to run anyways ? (y/n)");
                Scanner scanner = new Scanner(System.in);
                boolean userChoice = scanner.nextLine().equalsIgnoreCase("y");
                scanner.close();
                if(userChoice){
                    writeDateToFile(createJsonStringFromDateObject(now));
                }
                return userChoice;
            }
        }
        else {
            System.out.println("Setting LocalDateTime for initial run");
            writeDateToFile(createJsonStringFromDateObject(now));
            return true;
        }
        return false;
    }
}
