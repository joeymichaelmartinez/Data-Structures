package sentinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sentinal implements SentinalInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    
    private PhraseHash posHash, negHash;

    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    
    Sentinal (String posFile, String negFile) throws FileNotFoundException {
        File posPhrasesFile = new File(posFile);
        File negPhrasesFile = new File(negFile);
        Scanner posInput = new Scanner(posPhrasesFile);
        Scanner negInput = new Scanner(negPhrasesFile);
        posHash = new PhraseHash();
        negHash = new PhraseHash();
        while(posInput.hasNext()){
            String posPhrase = posInput.nextLine();
            posHash.put(posPhrase);
        }
        while(negInput.hasNext()){
            String negPhrase = negInput.nextLine();
            negHash.put(negPhrase);
        }
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    
    public void loadSentiment (String phrase, boolean positive) {
        if(positive){
            posHash.put(phrase);
        }
        else{
            negHash.put(phrase);
        }
    }
    
    public void loadSentimentFile (String filename, boolean positive) throws FileNotFoundException {
        File phrasesFile = new File(filename);
        Scanner input = new Scanner(phrasesFile);
        
        if(positive){
            while(input.hasNext()){
                String posPhrase = input.nextLine();
                posHash.put(posPhrase);
            }    
        }
        
        else{
            while(input.hasNext()){
                String negPhrase = input.nextLine();
                negHash.put(negPhrase);
            }
        }
        
    }
    
    public String sentinalyze (String filename) throws FileNotFoundException {
        int positivity=0;
        File phrasesFile = new File(filename);
        Scanner input = new Scanner(phrasesFile);
        String collectedSentences=input.nextLine();
        while(input.hasNext()){
            collectedSentences = collectedSentences + " " + input.nextLine();
        }
        
        positivity=search(collectedSentences);
            
        if(positivity > 0){
            return "positive";
        }
        if(positivity == 0){
            return "neutral";
        }
        if(positivity < 0){
            return "negative";
        }
        return "neutral";
    }
    
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    private int search(String input){
        int positivity=0;
        String[] words = input.split(" ");
        for(int k=0; k< posHash.longestLength() || k < negHash.longestLength(); k++){
            for(int i=0; i<words.length-k;i++){
                if(posHash.get(words[i])!=null){
                    positivity++;
                }
                if(negHash.get(words[i])!=null){
                    positivity--;
                }    
            }
            phraseLonger(words, input, k+1);
        }
        return positivity;
    }
    
    private String[] phraseLonger(String[] phrase, String input, int size){
        String[] originalWords = input.split(" ");
        for(int i=0; i < originalWords.length-size; i++){
            phrase[i] = phrase[i] + " " + originalWords[i+size];
            }
        return phrase;
    }
}

