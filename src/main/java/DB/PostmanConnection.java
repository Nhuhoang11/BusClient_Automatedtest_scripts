package DB;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PostmanConnection {

    public static void main(String[] args) {
        try {
            // Path to your Postman collection JSON file
            String collectionFilePath = "/Users/nhu/Desktop/Project/BUSCLIENT/busnoti.postman_collection.json";

            // Create a ProcessBuilder to run Newman with the collection file
            ProcessBuilder processBuilder = new ProcessBuilder("newman", "run", collectionFilePath);

            // Start the process
            Process process = processBuilder.start();

            // Capture the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
