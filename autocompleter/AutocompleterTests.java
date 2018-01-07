package autocompleter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class AutocompleterTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Global timeout to prevent infinite loops from
    // crashing the test suite
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);
    
    // Used as the basic empty Autocompleter to test; 
    // the @Before method is run before every @Test
    Autocompleter ac;
    @Before
    public void init () {
        ac = new Autocompleter();
    }
    
    
    // =================================================
    // Unit Tests
    // =================================================
    
    // Initialization Tests
    // -------------------------------------------------
    @Test
    public void testAutocompleter() {
        assertTrue(ac.isEmpty());
    }
    
    public void testAutocompleter2() {
        ac.addTerm("Yo");
        assertFalse(ac.isEmpty());
    }

    // Basic Tests
    // -------------------------------------------------
  @Test
    public void testAddTerm() {
        ac.addTerm("is");
        ac.addTerm("it");
        ac.addTerm("as");
        ac.addTerm("ass");
        ac.addTerm("at");
        ac.addTerm("bat");
        ac.addTerm("batty");
        ac.addTerm("butt");
        ac.addTerm("Company");
        ac.addTerm("companion");
        //System.out.println(ac.root.letter);
        //System.out.println(ac.root.mid.letter);
        //System.out.println(ac.root.mid.right.letter);
        //System.out.println(ac.root.left.letter);
        //System.out.println(ac.root.left.mid.letter);
    }

  @Test
  public void testAddTerm2() {
      ac.addTerm("i");
      ac.addTerm("tt");
      ac.addTerm("as");
      ac.addTerm("bss");
      ac.addTerm("ct");
      ac.addTerm("vat");
      ac.addTerm("natty");
      ac.addTerm("mutt");
      ac.addTerm("wompany");
      ac.addTerm("dompanion");
  }
  
  @Test
  public void testAddTerm3() {
      ac.addTerm("i");
      ac.addTerm("ii");
      ac.addTerm("ii");
      ac.addTerm("item");
      ac.addTerm("ite");
  }
  
    @Test
    public void testHasTerm() {
        ac.addTerm("is");
        ac.addTerm("it");
        ac.addTerm("as");
        ac.addTerm("ass");
        ac.addTerm("at");
        ac.addTerm("bat");
        ac.addTerm("ite");
        assertTrue(ac.hasTerm("is"));
        assertTrue(ac.hasTerm("it"));
        assertTrue(ac.hasTerm("as"));
        assertTrue(ac.hasTerm("ass"));
        assertTrue(ac.hasTerm("at"));
        assertTrue(ac.hasTerm("bat"));
        assertFalse(ac.hasTerm("ii"));
        assertFalse(ac.hasTerm("i"));
        assertFalse(ac.hasTerm("zoo"));
        assertFalse(ac.hasTerm("batty"));
        assertTrue(ac.hasTerm("ite"));
    }

    @Test
    public void testHasTerm2() {
        ac.addTerm("is");
        ac.addTerm("i");
        ac.addTerm("it");
        ac.addTerm("as");
        ac.addTerm("ass");
        ac.addTerm("at");
        ac.addTerm("bat");
        ac.addTerm("ba");
        ac.addTerm("ite");
        ac.addTerm("item");
        //assertTrue(ac.hasTerm("i"));
        assertTrue(ac.hasTerm("it"));
        assertTrue(ac.hasTerm("as"));
        assertTrue(ac.hasTerm("ass"));
        assertTrue(ac.hasTerm("at"));
        assertTrue(ac.hasTerm("bat"));
        assertTrue(ac.hasTerm("ba"));
        assertFalse(ac.hasTerm("b"));
        assertFalse(ac.hasTerm("ii"));
        assertTrue(ac.hasTerm("i"));
        assertFalse(ac.hasTerm("zoo"));
        assertFalse(ac.hasTerm("batty"));
        assertTrue(ac.hasTerm("ite"));
        assertTrue(ac.hasTerm("item"));
    }
    
    @Test
    public void testHasTerm3() {
        ac.addTerm("is");
        ac.addTerm("i");
    }
    
    
    @Test
    public void getSuggestedTerm() {
        ac.addTerm("is");
        ac.addTerm("it");
        ac.addTerm("as");
        ac.addTerm("at");
        ac.addTerm("item");
        ac.addTerm("ass");
        ac.addTerm("bat");
        ac.addTerm("bother");
        ac.addTerm("goat");
        ac.addTerm("goad");
        //System.out.println(ac.getSuggestedTerm("is"));
        assertEquals("is", ac.getSuggestedTerm("is"));
        assertEquals("it", ac.getSuggestedTerm("it"));
        assertEquals("item", ac.getSuggestedTerm("ite"));
        assertEquals("as", ac.getSuggestedTerm("as"));
        assertEquals("bat", ac.getSuggestedTerm("ba"));
        assertEquals("bother", ac.getSuggestedTerm("bo"));
        assertEquals(null, ac.getSuggestedTerm("bad"));
        assertEquals(null, ac.getSuggestedTerm("zoo"));
        String result = ac.getSuggestedTerm("go");
        assertTrue(result.equals("goat") || result.equals("goad"));
    }
   
    @Test
    public void getSuggestedTerm2() {
        ac.addTerm("is");
        ac.addTerm("i");
        ac.addTerm("item");
        assertEquals("i", ac.getSuggestedTerm("i"));
        assertEquals("is", ac.getSuggestedTerm("is"));
        assertEquals("item", ac.getSuggestedTerm("it"));
    }
    
    @Test
    public void getSortedTerms() {
        ac.addTerm("is");
        ac.addTerm("it");
        ac.addTerm("as");
        ac.addTerm("jes");
        ac.addTerm("itenerary");
        ac.addTerm("ass");
        ac.addTerm("at");
        ac.addTerm("zoo");
        ac.addTerm("bat");
        ac.addTerm("bother");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList(
           "as", "ass", "at", "bat", "bother", "is", "it", "itenerary","jes", "zoo"
        ));
        assertEquals(solution, ac.getSortedTerms());
    }
    
    @Test
    public void getSortedTerms2() {
        ac.addTerm("Juice");
        ac.addTerm("korn");
        ac.addTerm("lemon");
        ac.addTerm("melon");
        ac.addTerm("apple");
        ac.addTerm("banana");
        ac.addTerm("corn");;
        ac.addTerm("grapes");
        ac.addTerm("horseradish");
        ac.addTerm("icecream");
        ac.addTerm("nut");
        ac.addTerm("urchins");
        ac.addTerm("veal");
        ac.addTerm("whale");
        ac.addTerm("xidk");
        ac.addTerm("yam");
        ac.addTerm("zebra");
        ac.addTerm("date");
        ac.addTerm("edamame");
        ac.addTerm("fudge");
        ac.addTerm("orange");
        ac.addTerm("popcorn");
        ac.addTerm("quail");
        ac.addTerm("rice");
        ac.addTerm("salmon");
        ac.addTerm("tea");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList(
           "apple", "banana", "corn", "date", "edamame", "fudge", "grapes", "horseradish","icecream", "juice",
           "korn", "lemon", "melon", "nut", "orange", "popcorn", "quail", "rice","salmon", "tea", "urchins", 
           "veal", "whale", "xidk", "yam", "zebra"
        ));
        assertEquals(solution, ac.getSortedTerms());
    }
    
}
