package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    private static InputStreamReader streamReader = new InputStreamReader(System.in);
    private static BufferedReader bufferedReader = new BufferedReader(streamReader);

    public static String readUserInput(String message) {
        try {
            System.out.print(message +":\n>");
            return bufferedReader.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}
