package app;
import java.io.IOException;


public class Main {

    public static void main (String[] args) throws IOException {


        Stock tsla = new Stock("tsla");

        System.out.println(tsla.date);

        System.out.println(tsla.volume);
        System.out.println(tsla.sixtyDayAverageVolume);

        System.out.println(tsla.dailyHigh);
        System.out.println(tsla.dailyLow);

        System.out.println(tsla.socialMediaMentions);


    }

}
