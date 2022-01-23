import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RunBash {
    public static void main(String[] args) throws IOException {
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
       // String command = "/usr/bin/open -a Terminal " + currentDir +"/TicTacToe.sh " + currentDir;
        String command = "/usr/bin/open -a Terminal " + currentDir +"/TicTacToe.sh ";

        System.out.println(command);
        //Process process = Runtime.getRuntime().exec( "/usr/bin/open -a Terminal TicTacToe.sh");
      //  Process process = Runtime.getRuntime().exec( "/usr/bin/open -a Terminal dir");
        Process process = Runtime.getRuntime().exec(command);



        //  Process process = Runtime.getRuntime().exec(currentDir + "/open -a Terminal TicTacToe.sh");

        printResults(process);
    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
