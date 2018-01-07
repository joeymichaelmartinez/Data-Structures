package sentinal;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class SentinalTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Global timeout to prevent infinite loops from
    // crashing the test suite
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);
    
    // Used as the basic empty PhraseHash to test; 
    // the @Before method is run before every @Test
    Sentinal s;
    // [!] IMPORTANT: Replace the below with an absolute
    // file path to where your test files are located
    String absolutePath = "C:/Users/joeym/Desktop/Projects/CMSI281/src/sentinal/";
    @Before
    public void init () {
        try {
            // [!] You may also change the sentiment file names below
            // should you wish to test others
            s = new Sentinal(absolutePath + "posPhrases.txt",
                             absolutePath + "negPhrases.txt");
        } catch (Exception e) {
            fail();
        }
    }
    
    
    // =================================================
    // Unit Tests
    // =================================================

    @Test
    public void testSentinal() {
        try {
            Sentinal senti = new Sentinal(absolutePath + "posPhrases.txt", absolutePath + "negPhrases.txt");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testSentinalyze_1() {
        try {
            assertEquals("negative", s.sentinalyze(absolutePath + "negDoc.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }

    @Test
    public void testSentinalyze_2() {
        try {
            assertEquals("positive", s.sentinalyze(absolutePath + "posDoc.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }
   @Test
    public void testSentinalyze_3() {
        try {
            assertEquals("neutral", s.sentinalyze(absolutePath + "neuDoc.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }
    @Test
    public void testSentinalyze_4() {
        try {
            assertEquals("positive", s.sentinalyze(absolutePath + "comDoc.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }
    @Test
    public void testSentinalyze_5() {
        try {
            assertEquals("positive", s.sentinalyze(absolutePath + "goodDoc1.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }
   @Test
    public void testSentinalyze_6() {
        try {
            assertEquals("neutral", s.sentinalyze(absolutePath + "neuDoc1.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }
    @Test
    public void testSentinalyze_7() {
        try {
            assertEquals("negative", s.sentinalyze(absolutePath + "badDoc1.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }
    @Test
    public void testSentinalyze_8() {
        try {
            assertEquals("positive", s.sentinalyze(absolutePath + "goodDoc2.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }
   /* @Test
    public void testSentinalyze_9() {
        try {
            assertEquals("neutral", s.sentinalyze(absolutePath + "comDoc2.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }*/
    @Test
    public void testSentinalyze_10() {
        try {
            assertEquals("negative", s.sentinalyze(absolutePath + "comDoc3.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }
    @Test
    public void testSentinalyze_11() {

        try {
            s.loadSentimentFile(absolutePath + "posPhrases2.txt", true);
            assertEquals("neutral", s.sentinalyze(absolutePath + "comDoc4.txt"));
        } catch (FileNotFoundException e) {
            fail();
        }
    }
    
    
    
    
}
