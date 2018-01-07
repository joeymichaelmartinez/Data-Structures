package forneymon;

//import static org.junit.Assert.*

public abstract class ForneymonTests {
	@test
    public void testTakeDamageBurny() {
       Burnymon Burny = new Burnymon("burny");
       assertEquals(burny.getHealth(), 15);
       burny.takeDamage(5, "dampy");
        assertEquals(burny.getHealth(), 10);
    }
    
    public void testTakeDamageDampy() {
       Burnymon Burny = new Burnymon("burny");
       assertEquals(burny.getHealth(), 15);
       dampy.takeDamage(5, "burny");
        assertEquals(burny.getHealth(), 10);
    }

    @Test
    public void testToString() {
        Burnymon burny = new Burnymon("burny"):
        assertEquals(burny.toString(), "burny burny");
    }

}
