package app;

import java.io.IOException;
import static helper.Helper.run;

public class Main {

    public static void main (String[] args) {

        try {
            run(args);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
