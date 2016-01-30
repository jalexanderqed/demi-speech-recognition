/**
 * Created by jalexander on 1/29/2016.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();

        configuration
                .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
                .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration
                .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        System.out.println("We are now actually doing something");

        try {
            LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
// Start recognition process pruning previously cached data.
            recognizer.startRecognition(true);

            System.out.println("Inside try");
            SpeechResult result;

            System.out.println("Finished startRecognition()");

            while ((result = recognizer.getResult()) != null) {
                System.out.println("Inside loop");
                System.out.format("Hypothesis: %s\n", result.getHypothesis());
            }
            recognizer.stopRecognition();
        } catch (IOException e) {
            System.out.println("Main loop block threw exception.");
            System.out.println(e.getMessage());
        }
    }
}
