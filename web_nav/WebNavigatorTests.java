package web_nav;

public class WebNavigatorTests {
    @AfterClass
    public static void gradeReport () {
        System.out.println("Tests");
    }
    
    
    @Test
    public void testvisit() {
        WebNavigator navi = new WebNavigator();
        navi.visit("www.amazon.com");
        assertEquals(navi.getCurrent(), "www.amazon.com");
        navi.visit("www.reddit.com");
        assertEquals(navi.getCurrent(), "www.reddit.com");
        navi.visit("www.amazon.com");
        assertEquals(navi.getCurrent(), "www.amazon.com");
        navi.visit("www.amazon.com");
        assertEquals(navi.getCurrent(), "www.amazon.com");
        navi.visit("www.google.com");
        assertEquals(navi.getCurrent(), "www.google.com");
        navi.forw();
        assertEquals(navi.getCurrent(), "www.google.com");
        navi.back();
        assertEquals(navi.getCurrent(), "www.amazon.com");
        navi.back();
        assertEquals(navi.getCurrent(), "www.amazon.com");
        navi.visit("www.google.com");    
    }
    
    @Test
    public void testBack() {
        WebNavigator navi = new WebNavigator();
        navi.visit("www.reddit.com");
        navi.visit("www.amazon.com");
        navi.back();
        assertEquals(navi.getCurrent(), "www.reddit.com");
        navi.back();
        assertEquals(navi.getCurrent(), "www.reddit.com");
        navi.visit("www.amazon.com");
        navi.visit("www.google.com");
        navi.visit("www.facebook.com");
        navi.visit("www.youtube.com");
        navi.visit("www.ebay.com");
        navi.back();
        navi.back();
        navi.back();
        assertEquals(navi.getCurrent(), "www.google.com");
        navi.back();
        assertEquals(navi.getCurrent(),"www.amazon.com");
    }
    
    @Test
    public void tesForw() {
        WebNavigator navi = new WebNavigator();
        navi.visit("www.reddit.com");
        navi.visit("www.amazon.com");
        navi.visit("www.amazon.com");
        navi.visit("www.google.com");
        navi.visit("www.facebook.com");
        navi.visit("www.youtube.com");
        navi.visit("www.ebay.com");
        navi.back();
        navi.back();
        navi.back();
        assertEquals(navi.getCurrent(), "www.google.com");
        navi.forw();
        assertEquals(navi.getCurrent(), "www.facebook.com");
        navi.forw();
        assertEquals(navi.getCurrent(), "www.youtube.com");
        navi.forw();
        assertEquals(navi.getCurrent(), "www.ebay.com");
        navi.forw();
        assertEquals(navi.getCurrent(), "www.ebay.com");
    }
    

}