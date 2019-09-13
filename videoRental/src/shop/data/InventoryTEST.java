package shop.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;
import junit.framework.TestCase;

// TODO:  complete the tests
public class InventoryTEST extends TestCase {
  public InventoryTEST(String name) {
    super(name);
  }
  // TODO
	  InventorySet s = new InventorySet();
	  final Video v1 = new VideoObj( "A", 2000, "B" );
	  final Video v1copy = new VideoObj( "A", 2000, "B" );
	  final Video v2 = new VideoObj( "B", 2000, "B" );
  
  public void testSize() {
    // TODO
	  s.addNumOwned(v1,  1); assertEquals( 1, s.size() );
      s.addNumOwned(v1,  2); assertEquals( 1, s.size() );
      s.addNumOwned(v2,  1); assertEquals( 2, s.size() );
      s.addNumOwned(v2, -1); assertEquals( 1, s.size() );
      s.addNumOwned(v1, -3); assertEquals( 0, s.size() );
      try { s.addNumOwned(v1, -3); fail(); 
      } catch ( IllegalArgumentException e ) {}
  }

  public void testAddNumOwned() {
    // TODO
	  s.addNumOwned(v1, 1);     assertEquals( v1, s.get(v1).video() );
      	assertEquals( 1, s.get(v1).numOwned());
      s.addNumOwned(v1, 2);     assertEquals( 3, s.get(v1).numOwned());
      s.addNumOwned(v1, -1);    assertEquals( 2, s.get(v1).numOwned());
      s.addNumOwned(v2, 1);     assertEquals( 2, s.get(v1).numOwned());
      s.addNumOwned(v1copy, 1); assertEquals( 3, s.get(v1).numOwned());
      s.addNumOwned(v1, -3);    assertEquals( null, s.get(v1) );
      try { s.addNumOwned(null, 1);   fail(); 
      } catch ( IllegalArgumentException e ) {}
  }

  public void testCheckOutCheckIn() {
    // TODO
	  	try { s.checkOut(null);     fail(); } catch ( IllegalArgumentException e ) {}
	    try { s.checkIn(null);      fail(); } catch ( IllegalArgumentException e ) {}
	          s.addNumOwned(v1, 2); assertTrue( s.get(v1).numOut() == 0 && s.get(v1).numRentals() == 0 );
	          s.checkOut(v1);       assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
	    try { s.addNumOwned(v1,-3); fail(); } catch ( IllegalArgumentException e ) {}
	    try { s.addNumOwned(v1,-2); fail(); } catch ( IllegalArgumentException e ) {}
	          s.addNumOwned(v1,-1); assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
	          s.addNumOwned(v1, 1); assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
	          s.checkOut(v1);       assertTrue( s.get(v1).numOut() == 2 && s.get(v1).numRentals() == 2 );
	          s.checkIn(v1);        assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 2 );
	          s.checkIn(v1copy);    assertTrue( s.get(v1).numOut() == 0 && s.get(v1).numRentals() == 2 );
	    try { s.checkIn(v1);        fail(); } catch ( IllegalArgumentException e ) {}
	    try { s.checkOut(v2);       fail(); } catch ( IllegalArgumentException e ) {}
	          s.checkOut(v1);       assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 3 );
  }

  public void testClear() {
    // TODO
	  s.addNumOwned(v1, 2); assertEquals( 1, s.size() );
      s.addNumOwned(v2, 2); assertEquals( 2, s.size() );
      s.clear();            assertEquals( 0, s.size() );
      try { s.checkOut(v2); fail(); 
      } catch ( IllegalArgumentException e ) {}
  }

  public void testGet() {
    // TODO
	  s.addNumOwned(v1, 1);
	  Record r1 = s.get(v1);
	  Record r2 = s.get(v1);
	  assertTrue( r1.equals(r2) );
	  assertTrue( r1 == r2 );
  }

  public void testIterator1() {
    // TODO
	  	Set<Video> expected = new HashSet<Video>();
	    InventorySet inv = new InventorySet();
	    Video v1 = new VideoObj("XX", 2004, "XX");
	    Video v2 = new VideoObj("XY", 2000, "XY");
	    inv.addNumOwned(v1,10);
	    inv.addNumOwned(v2,20);
	    expected.add(v1);
	    expected.add(v2);
	    
	    Iterator<Record> i = inv.iterator();
	    try { i.remove(); fail(); }
	    catch (UnsupportedOperationException e) { }
	    while(i.hasNext()) {
	      Record r = i.next(); 
	      assertTrue(expected.contains(r.video()));
	      expected.remove(r.video());
	    }
	    assertTrue(expected.isEmpty());
  }
  public void testIterator2() {
    // TODO
	  	List<Video> expected = new ArrayList<Video>();
	    InventorySet inv = new InventorySet();
	    Video v1 = new VideoObj("XX", 2004, "XX");
	    Video v2 = new VideoObj("XY", 2000, "XY");
	    expected.add(v2);
	    expected.add(v1);
	    inv.addNumOwned(v1,10);
	    inv.addNumOwned(v2,20);

	    Comparator<Record> c = new Comparator<Record>() {
	        public int compare(Record r1, Record r2) {
	          return r1.video().year() - r2.video().year();
	        }
	      };
	    Iterator<Record> i = inv.iterator(c);
	    try { i.remove();}catch (IllegalStateException e) { }
	    Iterator j = expected.iterator();
	    while (i.hasNext()) {
	      assertSame(j.next(), i.next().video());
	      j.remove();
	    }
	    assertTrue(expected.isEmpty());
  }

}
