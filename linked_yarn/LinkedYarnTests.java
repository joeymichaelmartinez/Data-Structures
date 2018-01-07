package linked_yarn;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.Timeout;

import yarn.Yarn;

public class LinkedYarnTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Global timeout to prevent infinite loops from
    // crashing the test suite
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);
    
    // Used as the basic empty LinkedYarn to test; the @Before
    // method is run before every @Test
    LinkedYarn ball;
    @Before
    public void init () {
        ball = new LinkedYarn();
    }
    
    
    // =================================================
    // Unit Tests
    // =================================================
    
    // Initialization Tests
    // -------------------------------------------------
    @Test
    public void testInit() {
        assertTrue(ball.isEmpty());
        assertEquals(0, ball.getSize());
    }

    // Basic Tests
    // -------------------------------------------------
    @Test
    public void testIsEmpty() {
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());
        ball.remove("not_empty");
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        ball.insert("not_empty");
        ball.remove("not_empty");
        assertFalse(ball.isEmpty());
        ball.insert("not_empty");
        ball.removeAll("not_empty");
        assertTrue(ball.isEmpty());
    }
    
    @Test
    public void testIsEmpty_t1() {
        ball.insert("a");
        ball.insert("a");
        ball.removeAll("a");
        assertTrue(ball.isEmpty());
    }
    @Test
    public void testIsEmpty_t2() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("a");
        ball.removeAll("a");
        assertFalse(ball.isEmpty());
        ball.removeAll("b");
        assertTrue(ball.isEmpty());
    }

    @Test
    public void testGetSize() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(2, ball.getSize());
        ball.insert("unique");
        assertEquals(3, ball.getSize());
        ball.remove("dup");
        assertEquals(2, ball.getSize());
        ball.remove("unique");
        assertEquals(1, ball.getSize());
        ball.remove("dup");
        assertEquals(0, ball.getSize());
    }

    @Test
    public void testGetSize_t1() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 3);
    }
    @Test
    public void testGetSize_t2() {
        ball.insert("u1");
        ball.insert("u2");
        ball.insert("u3");
        assertEquals(ball.getSize(), 3);
    }

    public void testGetUniqueSize_t1() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getUniqueSize(), 1);
    }
    @Test
    public void testGetUniqueSize_t2() {
        ball.insert("u1");
        ball.insert("u2");
        ball.insert("u3");
        assertEquals(ball.getUniqueSize(), 3);
    }
    @Test
    public void testGetUniqueSize_t3() {
        ball.insert("u1");
        ball.insert("u2");
        ball.remove("u1");
        assertEquals(ball.getUniqueSize(), 1);
        ball.remove("u2");
        assertEquals(ball.getUniqueSize(), 0);
    }
    
    @Test
    public void testGetUniqueSize() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(1, ball.getUniqueSize());
        ball.insert("unique");
        assertEquals(2, ball.getUniqueSize());
        ball.remove("dup");
        assertEquals(2, ball.getUniqueSize());
        ball.remove("dup");
        assertEquals(1, ball.getUniqueSize());
        ball.remove("unique");
        assertEquals(0, ball.getUniqueSize());
    }

    // LinkedYarn Manipulation Tests
    // -------------------------------------------------
    @Test
    public void testInsert() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("another");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertTrue(ball.contains("another"));
        LinkedYarn ball2 = new LinkedYarn();
        ball2.insert("dup");
    }
    
    @Test
    public void testInsert2() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup2");
        ball.insert("dup3");
        ball.insert("dup3");
        ball.remove("dup2");
        assertEquals(ball.getSize(), 4);
        assertEquals(ball.getUniqueSize(), 2);
    }

    @Test
    public void testInsert_t1() {
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("dup");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 2);
    }
    @Test
    public void testInsert_t2() {
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("dup");
        ball.remove("unique");
        assertTrue(ball.contains("dup"));
        assertFalse(ball.contains("unique"));
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 1);
    }
    @Test
    public void testInsert_t3() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup2");
        ball.insert("dup3");
        ball.insert("dup3");
        ball.remove("dup2");
        assertEquals(ball.getSize(), 4);
        assertEquals(ball.getUniqueSize(), 2);
    }
    @Test
    public void testInsert_t5() {
        for (int i = 0; i < 101; i++) {
            ball.insert("SAMESIES");
        }
        assertEquals(ball.getSize(), 101);
        assertEquals(ball.getUniqueSize(), 1);
    }
    
    @Test
    public void testRemove() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(2, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        int dups = ball.remove("dup");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        assertEquals(1, dups);
        ball.remove("dup");
        assertFalse(ball.contains("dup"));
    }
    
    @Test
    public void testRemove_t1() {
        ball.insert("uni1");
        int dups = ball.remove("uni1");
        assertEquals(ball.getSize(), 0);
        assertEquals(dups, 0);
        assertFalse(ball.contains("uni1"));
    }
    @Test
    public void testRemove_t2() {
        ball.remove("uni1");
        ball.insert("uni1");
        ball.remove("uni");
        assertEquals(ball.getSize(), 1);
        assertTrue(ball.contains("uni1"));
        ball.insert("uni2");
        ball.insert("uni3");
        ball.remove("uni1");
        assertEquals(ball.getSize(), 2);
        assertFalse(ball.contains("uni1"));
    }
    @Test
    public void testRemove_t3() {
        ball.insert("dup1");
        ball.insert("dup1");
        ball.insert("dup2");
        ball.insert("dup2");
        ball.insert("uni1");
        ball.remove("uni1");
        assertEquals(ball.getSize(), 4);
        assertEquals(ball.getUniqueSize(), 2);
        ball.insert("uni2");
        ball.insert("uni3");
        int dups = ball.remove("dup1");
        assertEquals(dups, 1);
        dups = ball.remove("dup1");
        assertEquals(dups, 0);
        assertEquals(ball.getSize(), 4);
        assertEquals(ball.getUniqueSize(), 3);
        assertFalse(ball.contains("dup1"));
    }
    
    @Test
    public void testRemove2() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("another");
        ball.remove("dup");
        assertTrue(ball.contains("dup"));
        ball.remove("unique");
        assertFalse(ball.contains("unique"));
        ball.remove("another");
        assertFalse(ball.contains("another"));
        ball.remove("dup");
        assertFalse(ball.contains("dup"));
        assertTrue(ball.isEmpty());
    }

    @Test
    public void testRemove3() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("another");
        ball.insert("one");
        ball.remove("another");
        ball.remove("unique");
    }
    
    @Test
    public void testRemove4(){
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.remove("dup");
        ball.remove("dup");
        ball.remove("unique");
        assertFalse(ball.contains("dup"));
        assertFalse(ball.contains("unique"));
    }
    
    @Test
    public void testRemoveAll() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(3, ball.getSize());
        assertEquals(2, ball.getUniqueSize());
        ball.removeAll("dup");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        ball.removeAll("dup");
        assertFalse(ball.contains("dup"));
    }
    
    @Test
    public void testRemoveAll2() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("unique");
        ball.remove("dup");
        assertTrue(ball.contains("dup"));
        ball.removeAll("unique");
        assertFalse(ball.contains("unique"));
        ball.removeAll("dup");
        assertFalse(ball.contains("dup"));
        assertTrue(ball.isEmpty());
    }

    @Test
    public void testRemoveAll3() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("another");
        ball.insert("one");
        ball.remove("another");
        ball.removeAll("unique");
        ball.removeAll("unique");
    }
    
    @Test
    public void testRemoveAll4(){
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.removeAll("dup");
        ball.removeAll("dup");
        ball.removeAll("unique");
        assertFalse(ball.contains("dup"));
        assertFalse(ball.contains("unique"));
    }

    @Test
    public void testRemoveAll_t1() {
        ball.removeAll("uni1");
        ball.insert("uni1");
        ball.removeAll("uni1");
        assertEquals(ball.getSize(), 0);
        assertFalse(ball.contains("uni1"));
    }
    @Test
    public void testRemoveAll_t2() {
        ball.insert("uni1");
        ball.insert("uni2");
        ball.insert("uni3");
        ball.removeAll("uni1");
        ball.removeAll("uni2");
        assertEquals(ball.getSize(), 1);
        assertFalse(ball.contains("uni1"));
    }
    @Test
    public void testRemoveAll_t3() {
        ball.insert("dup1");
        ball.insert("dup1");
        ball.insert("dup2");
        ball.insert("dup2");
        ball.insert("uni1");
        ball.removeAll("dup1");
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 2);
        assertFalse(ball.contains("dup1"));
        ball.removeAll("dup2");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
        ball.removeAll("uni1");
        assertEquals(ball.getSize(), 0);
        assertEquals(ball.getUniqueSize(), 0);
    }
    
    @Test
    public void testCount() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(2, ball.count("dup"));
        assertEquals(1, ball.count("unique"));
        assertEquals(0, ball.count("forneymon"));
        ball.remove("unique");
        assertEquals(0, ball.count("unique"));
        ball.remove("unique");
        assertEquals(0, ball.count("unique"));
        ball.remove("dup");
        assertEquals(1, ball.count("dup"));
    }

    @Test
    public void testCount_t1() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.count("dup"), 3);
        ball.removeAll("dup");
        assertEquals(ball.count("dup"), 0);
    }
    @Test
    public void testCount_t2() {
        ball.insert("dup");
        ball.insert("dup2");
        ball.insert("dup");
        ball.insert("dup2");
        assertEquals(ball.count("dup"), 2);
        assertEquals(ball.count("dup2"), 2);
        ball.removeAll("dup");
        assertEquals(ball.count("dup"), 0);
        ball.remove("dup2");
        assertEquals(ball.count("dup2"), 1);
    }
    
    @Test
    public void testContains() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));
    }
    // This is tested pretty much everywhere so...


    @Test
    public void testGetMostCommon() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals("dup", ball.getMostCommon());
        ball.insert("cool");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("dup") || mc.equals("cool"));
    }
    
    @Test
    public void testGetMostCommon2(){
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        ball.insert("cool");
        ball.insert("cool");
        assertEquals("cool", ball.getMostCommon());
        ball.remove("cool");
        assertEquals("cool", ball.getMostCommon());
    }
    
    @Test
    public void testGetMostCommon3(){
        assertEquals(ball.getMostCommon(), null);
    }
    
    @Test
    public void testGetMostCommon4(){
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        String mc = ball.getMostCommon();
        assertEquals(mc, "cool");
    }
    
        

    // Iterator Tests
    // -------------------------------------------------
    @Test
    public void testIteratorBasics() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        LinkedYarn.Iterator it = ball.getIterator();

        // Test next()
        LinkedYarn dolly = ball.clone();
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasNext()) {it.next();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasNext());
        
        // Test prev()
        dolly = ball.clone();
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasPrev()) {it.prev();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasPrev());
        
        int countOfReplaced = ball.count(it.getString());
        it.replaceAll("replaced!");
        assertEquals(countOfReplaced, ball.count("replaced!"));
        assertTrue(it.isValid());
        
        ball.insert("c");
        assertFalse(it.isValid());
    }
    
    @Test
    public void testIteratorBasics2() {
        //test next
        ball.insert("a");
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        LinkedYarn.Iterator it = ball.getIterator();
        assertEquals(it.getString(), "b");
        it.next();
        assertEquals(it.getString(), "a");
        it.next();
        assertEquals(it.getString(), "a");
        it.next();
        assertEquals(it.getString(), "a");
        
        //prev
        it.prev();
    }
        
    @Test
    public void testIteratorBasics3() {   
        //isValid
        ball.insert("a");
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        LinkedYarn.Iterator it = ball.getIterator();
        ball.insert("a");
        assertFalse(it.isValid());
        ball.insert("a");
        assertFalse(it.isValid());
        LinkedYarn.Iterator it2 = ball.getIterator();
        assertTrue(it2.isValid());
        ball.remove("b");
        assertFalse(it2.isValid());
        
    }
    
    @Test
    public void testIteratorBasics4() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        LinkedYarn.Iterator it = ball.getIterator();
        it.next();
        it.replaceAll("c");
        assertEquals(ball.count("c"), 3);
        assertTrue(it.isValid());
    }
    
    // Inter-LinkedYarn Tests
    // -------------------------------------------------
    @Test
    public void testClone() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        LinkedYarn dolly = ball.clone();
        assertEquals(2, dolly.count("dup"));
        assertEquals(1, dolly.count("unique"));
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));
    }
    
    @Test
    public void testClone1() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        LinkedYarn dolly = ball.clone();
        assertEquals(4, dolly.count("dup"));
        assertEquals(2, dolly.count("unique"));
        dolly.insert("cool");
        assertEquals(dolly.count("cool"), 1);
    }
    
    @Test
    public void testClone2() {
        LinkedYarn dolly = ball.clone();
        ball.insert("a");
        assertFalse(dolly.contains("a"));
    }
    
    @Test
    public void testClone3() {
        ball.insert("a");
        LinkedYarn dolly = ball.clone();
        dolly.insert("b");
        LinkedYarn superDolly = dolly.clone();
        superDolly.insert("c");
        assertTrue(superDolly.contains("a"));
        assertTrue(superDolly.contains("b"));
        assertFalse(dolly.contains("c"));
    }

    /*
    @Test
    public void testClone4() {
        LinkedYarn ball2 = new LinkedYarn();
        ball.insert("dup");
        ball.insert("another");
        ball2.insert("unique");
        ball2.insert("one");
        LinkedYarn ball3 = ball.clone();
        //ball3 = ball2.clone();
        assertTrue(ball3.contains("dup"));
        assertTrue(ball3.contains("another"));
        assertTrue(ball3.contains("unique"));
        assertTrue(ball3.contains("one"));
    }
    */

    
    @Test
    public void testSwap() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("yo");
        y2.insert("sup");
        y1.swap(y2);
        assertTrue(y1.contains("yo"));
        assertTrue(y1.contains("sup"));
        assertTrue(y2.contains("dup"));
        assertTrue(y2.contains("unique"));
        assertFalse(y1.contains("dup"));
    }

    @Test
    public void testSwap2() {
        LinkedYarn y2 = new LinkedYarn();
        ball.insert("a");
        y2.swap(ball);
        assertTrue(ball.isEmpty());
        assertFalse(y2.isEmpty());
    }
    
    @Test
    public void testSwap3() {
        LinkedYarn y2 = new LinkedYarn();
        LinkedYarn y3 = new LinkedYarn();
        y2.insert("a");
        ball.insert("b");
        y2.swap(ball);
        y3.swap(y2);
        assertTrue(y2.isEmpty());
        assertTrue(ball.contains("a"));
        assertTrue(y3.contains("b"));
        ball.insert("c");
        assertFalse(y2.contains("c"));
    }
    
    @Test
    public void testSwap4() {
        ball.insert("a");
        ball.swap(ball);
        assertTrue(ball.contains("a"));
        assertEquals(ball.getSize(), 1);
    }
    
    // Static Method Tests
    // -------------------------------------------------
    @Test
    public void testKnit() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("dup");
        y2.insert("cool");
        LinkedYarn y3 = LinkedYarn.knit(y1, y2);
        assertEquals(3, y3.count("dup"));
        assertEquals(1, y3.count("unique"));
        assertEquals(1, y3.count("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
    }
    
    @Test
    public void testKnit2() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("a");
        y1.insert("b");
        LinkedYarn y3 = LinkedYarn.knit(ball, y1);
        assertEquals(y3.getSize(), 2);
        assertEquals(y3.getUniqueSize(), 2);
    }
    
    @Test
    public void testKnit3() {
        ball.insert("a");
        ball.insert("a");
        ball = LinkedYarn.knit(ball, ball);
        assertEquals(ball.getSize(), 4);
        assertEquals(ball.getUniqueSize(), 1);
    }

    @Test
    public void testTear() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("dup");
        y2.insert("cool");
        LinkedYarn y3 = LinkedYarn.tear(y1, y2);
        assertEquals(1, y3.count("dup"));
        assertEquals(1, y3.count("unique"));
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
    }

    @Test
    public void testTear2() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        LinkedYarn y3 = LinkedYarn.tear(y1, y2);
        assertEquals(0, y3.count("dup"));
        //System.out.println(y3.contains("unique"));
        assertEquals(0, y3.count("unique"));
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
    }
    
    @Test
    public void testTear_t1() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("a");
        y1.insert("b");
        LinkedYarn y2 = LinkedYarn.tear(ball, y1);
        assertEquals(y2.getSize(), 0);
        assertFalse(y2.contains("a"));
    }
    @Test
    public void testTear_t2() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("a");
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("a");
        y1.insert("a");
        LinkedYarn y2 = LinkedYarn.tear(ball, y1);
        assertEquals(y2.getSize(), 1);
        assertFalse(y2.contains("a"));
    }
    
    @Test
    public void testSameYarn() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(LinkedYarn.sameYarn(y1, y2));
        assertTrue(LinkedYarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(LinkedYarn.sameYarn(y1, y2));
    }
    
    @Test
    public void testSameYarn_t1() {
        LinkedYarn y1 = new LinkedYarn();
        assertTrue(LinkedYarn.sameYarn(ball, y1));
        assertTrue(LinkedYarn.sameYarn(y1, y1));
        y1.insert("a");
        assertTrue(LinkedYarn.sameYarn(y1, y1));
    }
    @Test
    public void testSameYarn_t2() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("b");
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("b");
        y1.insert("a");
        assertFalse(LinkedYarn.sameYarn(ball, y1));
        ball.removeAll("b");
        y1.removeAll("b");
        assertTrue(LinkedYarn.sameYarn(ball, y1));
    }
    
    @Test
    public void testSameYarn2() {
        LinkedYarn y1 = new LinkedYarn();
        LinkedYarn y2 = new LinkedYarn();
        assertTrue(LinkedYarn.sameYarn(y1, y2));
        assertTrue(LinkedYarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(LinkedYarn.sameYarn(y1, y2));
    }

}
