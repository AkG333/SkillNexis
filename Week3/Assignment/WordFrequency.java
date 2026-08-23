import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordFrequency {

    public static void main(String[] args) {

        String inputFile = "input.txt";
        String outputFile = "output.txt";

        HashMap<String, Integer> wordCount = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(inputFile)
            );

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.toLowerCase();

                // Remove punctuation
                line = line.replaceAll("[^a-zA-Z0-9 ]", "");

                String[] words = line.split("\\s+");

                for (String word : words) {

                    if (!word.isEmpty()) {
                        wordCount.put(
                            word,
                            wordCount.getOrDefault(word, 0) + 1
                        );
                    }
                }
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(outputFile)
            );

            writer.write("Word Frequency");
            writer.newLine();
            writer.write("----------------");
            writer.newLine();

            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                writer.write(
                    entry.getKey() + " : " + entry.getValue()
                );
                writer.newLine();
            }

            writer.close();

            System.out.println("Word frequency calculated successfully.");
            System.out.println("Output saved to " + outputFile);

        } catch (FileNotFoundException e) {

            System.out.println("Error: input.txt not found.");

        } catch (IOException e) {

            System.out.println("Error while reading or writing file.");
        }
    }
}