package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;

// TODO:  complete the tests
public class DataTEST extends TestCase {
  public DataTEST(String name) {
    super(name);
  }
  public void testConstructorAndAttributes() {
    String title1 = "XX";
    String director1 = "XY";
    String title2 = " XX ";
    String director2 = " XY ";
    int year = 2002;

    Video v1 = Data.newVideo(title1, year, director1);
     assertSame(title1, v1.title());
     assertEquals(year, v1.year());
     assertSame(director1, v1.director());

    Video v2 = Data.newVideo(title2, year, director2);
     assertEquals(title1, v2.title());
     assertEquals(director1, v2.director());
  }

  public void testConstructorExceptionYear() {
    try {
      Data.newVideo("X", 1800, "Y");
       fail();
    } catch (IllegalArgumentException e) { }
    try {
      Data.newVideo("X", 5000, "Y");
       fail();
    } catch (IllegalArgumentException e) { }
    try {
      Data.newVideo("X", 1801, "Y");
      Data.newVideo("X", 4999, "Y");
    } catch (IllegalArgumentException e) {
       fail();
    }
  }

  public void testConstructorExceptionTitle() {
    try {
      Data.newVideo(null, 2002, "Y");
       fail();
    } catch (IllegalArgumentException e) { }
    try {
      Data.newVideo("", 2002, "Y");
       fail();
    } catch (IllegalArgumentException e) { }
    try {
      Data.newVideo(" ", 2002, "Y");
       fail();
    } catch (IllegalArgumentException e) { }
  }

  public void testConstructorExceptionDirector() {
    // TODO
	  try {
	      Data.newVideo("X", 2002, null);
	       fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      Data.newVideo("X", 2002, "");
	       fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      Data.newVideo("X", 2002, " ");
	       fail();
	    } catch (IllegalArgumentException e) { }
  }

}
