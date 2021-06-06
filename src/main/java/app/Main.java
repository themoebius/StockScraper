package app;
import java.io.IOException;


public class Main {

    public static void main (String[] args) throws IOException {


        Stock gme = new Stock("gme");

        System.out.println(gme.date);

        System.out.println(gme.volume);
        System.out.println(gme.sixtyDayAverageVolume);

        System.out.println(gme.dailyHigh);
        System.out.println(gme.dailyLow);

        System.out.println(gme.socialMediaMentions);


    }

}
