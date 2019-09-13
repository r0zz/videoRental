package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;

// TODO:  complete the tests
public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  public void testEquals() { 
    // TODO
	// creating two objects and testing equals method

      VideoObj v1 = new VideoObj("Hello", 1994, "James");

      VideoObj v2 = new VideoObj("Hello", 1994, "James");

      // should be equal

      assertTrue(v1.equals(v2));

      // re initializing second object to use different title

      v2 = new VideoObj("Hey", 1994, "James");

      assertFalse(v1.equals(v2));
  }

  public void testCompareTo() { 
    // TODO
	  VideoObj v1 = new VideoObj("Hello", 1994, "James");

      VideoObj v2 = new VideoObj("Hello", 1994, "James");

      // comparison result should be 0 since both objects are same

      assertEquals(v1.compareTo(v2), 0);

      //differing titles

      v2 = new VideoObj("I am", 1994, "James");

      assertTrue(v1.compareTo(v2) < 0);

      v2 = new VideoObj("Amazo", 1994, "James");

      assertTrue(v1.compareTo(v2) > 0);

      //differing years

      v2 = new VideoObj("Hello", 1995, "James");

      assertTrue(v1.compareTo(v2) < 0);

      v2 = new VideoObj("Hello", 1993, "James");

      assertTrue(v1.compareTo(v2) > 0);

      //differing directors

      v2 = new VideoObj("Hello", 1994, "Imran");

      assertTrue(v1.compareTo(v2) > 0);

      v2 = new VideoObj("Hello", 1994, "Kevin");

      assertTrue(v1.compareTo(v2) < 0);
  }

  public void testToString() { 
    // TODO
	//creating object and testing toString()

      VideoObj v1 = new VideoObj("Hello", 1994, "James");

      assertEquals(v1.toString(), "Hello (1994) : James");
  }

  public void testHashCode() {
    assertEquals
      (-875826552,
       new VideoObj("None", 2009, "Zebra").hashCode());
    assertEquals
      (-1391078111,
       new VideoObj("Blah", 1954, "Cante").hashCode());
  }

}
