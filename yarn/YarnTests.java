package yarn;

import static org.junit.Assert.*;

import org.junit.Test;

public class YarnTests {

    @Test
    public void testYarn() {
        Yarn ball = new Yarn();
    }

    @Test
    public void testIsEmpty() {
        Yarn ball = new Yarn();
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());
        ball.insert("Another");
        assertFalse(ball.isEmpty());
    }

    @Test
    public void testGetSize() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);
        ball.insert("unique");
        ball.insert("cool");
        assertEquals(ball.getSize(), 5);
        ball.remove("unique");
        assertEquals(ball.getSize(), 4);
    }

    @Test
    public void testGetUniqueSize() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getUniqueSize(), 1);
        ball.insert("unique");
        assertEquals(ball.getUniqueSize(), 2);
        ball.insert("unique");
        assertEquals(ball.getUniqueSize(), 2);
        ball.insert("cool");
        assertEquals(ball.getUniqueSize(), 3);
        ball.remove("unique");
        assertEquals(ball.getUniqueSize(), 3);
        ball.remove("unique");
        assertEquals(ball.getUniqueSize(), 2);
    }

    @Test
    public void testInsert() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("");
        ball.insert(" ");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertTrue(ball.contains(""));
        assertTrue(ball.contains(" "));
    }

    @Test
    public void testRemove() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 1);
        ball.remove("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 3);
        ball.remove("dup");
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 2);
        ball.remove("");
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 2);
    }

    @Test
    public void testRemoveAll() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals(ball.getSize(), 4);
        assertEquals(ball.getUniqueSize(), 3);
        ball.removeAll("dup");
        assertFalse(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertTrue(ball.contains("cool"));
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 2);
        ball.removeAll("unique");
        assertFalse(ball.contains("unique"));
        assertTrue(ball.contains("cool"));
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
        ball.removeAll("cool");
        assertFalse(ball.contains("cool"));
        assertEquals(ball.getSize(), 0);
        assertEquals(ball.getUniqueSize(), 0);
        ball.removeAll("");
        assertEquals(ball.getSize(), 0);
        assertEquals(ball.getUniqueSize(), 0);
    }

    @Test
    public void testCount() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("");
        ball.insert("");
        ball.insert("");
        ball.insert("");
        assertEquals(ball.count("dup"), 2);
        assertEquals(ball.count("unique"), 1);
        assertEquals(ball.count("forneymon"), 0);
        assertEquals(ball.count(""), 4);
        ball.removeAll("");
        assertEquals(ball.count(""), 0);
    }

    @Test
    public void testContains() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));
        ball.remove("dup");
        assertTrue(ball.contains("dup"));
        ball.remove("dup");
        assertFalse(ball.contains("dup"));
        ball.insert("");
        assertTrue(ball.contains(""));
        
    }

    @Test
    public void testGetNth() {
        Yarn ball = new Yarn();
        Yarn ball2 = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        Yarn comparison = ball.clone();
        Yarn comparison2 = ball2.clone();

        for (int i = 0; i < ball.getSize(); i++) {
            String gotten = ball.getNth(i);
            assertTrue(comparison.contains(gotten));
            comparison.remove(gotten);
        }
        
        for (int i = 0; i < ball2.getSize(); i++) {
            String gotten = ball2.getNth(i);
            assertTrue(comparison2.contains(gotten));
            comparison2.remove(gotten);
        }
    }

    @Test
    public void testGetMostCommon() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        ball.insert("another");
        ball.insert("one");
        assertEquals(ball.getMostCommon(), "dup");
        ball.insert("cool");
        ball.insert("cool");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("cool"));
    }

    @Test
    public void testClone() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        Yarn dolly = ball.clone();
        assertEquals(dolly.count("dup"), 2);
        assertEquals(dolly.count("unique"), 1);
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));
    }

    @Test
    public void testSwap() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("yo");
        y2.insert("sup");
        y1.swap(y2);
        assertTrue(y1.contains("yo"));
        assertTrue(y1.contains("sup"));
        assertTrue(y2.contains("dup"));
        assertTrue(y2.contains("unique"));
        assertFalse(y1.contains("dup"));
        y1.swap(y2);
        assertTrue(y2.contains("yo"));
        assertTrue(y2.contains("sup"));
        assertTrue(y1.contains("dup"));
        assertTrue(y1.contains("unique"));
        assertFalse(y2.contains("dup"));
    }

    @Test
    public void testKnit() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        y2.insert("");
        Yarn y3 = Yarn.knit(y1, y2);
        assertEquals(y3.count("dup"), 3);
        assertEquals(y3.count("unique"), 1);
        assertEquals(y3.count("cool"), 1);
        assertEquals(y3.count(""), 1);
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
        
    }

    @Test
    public void testTear() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.tear(y1, y2);
        assertEquals(y3.count("dup"), 1);
        assertEquals(y3.count("unique"), 1);
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
        y2.insert("unique");
        y3 = Yarn.tear(y1, y2);
        assertEquals(y3.count("dup"), 1);
        assertFalse(y3.contains("unique"));
    }

    @Test
    public void testSameYarn() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(Yarn.sameYarn(y1, y2));
        assertTrue(Yarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(Yarn.sameYarn(y1, y2));
        Yarn y3 = new Yarn();
        y3.insert("dup");
        Yarn y4 = new Yarn();
        assertFalse(Yarn.sameYarn(y3, y4));
    }

}
