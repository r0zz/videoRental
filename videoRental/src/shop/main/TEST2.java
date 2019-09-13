package shop.main;

import junit.framework.Assert;
import junit.framework.TestCase;
import shop.command.RerunnableCommand;
import shop.command.UndoableCommand;
import shop.data.Data;
import shop.data.Video;
import shop.data.Inventory;
import java.util.Iterator;

public class TEST2 extends TestCase {
  public TEST2(String name) {
    super(name);
  }
  public void test1() {
    final Inventory inventory = Data.newInventory();
    final Video v1 = Data.newVideo("K", 2003, "S");
    final Video v2 = Data.newVideo("S", 2002, "K");
    final RerunnableCommand UNDO = Data.newUndoCmd(inventory);
    final RerunnableCommand REDO = Data.newRedoCmd(inventory);
	
    UndoableCommand c = Data.newAddCmd(inventory, v1, 2);
     assertTrue  ( c.run() );
     assertEquals( 1, inventory.size() );
     assertTrue  (!c.run() );     // cannot run an undoable command twice
     assertTrue  (!Data.newAddCmd(inventory, null, 3).run()); // can't add null!
     assertTrue  (!Data.newAddCmd(inventory, v2, 0).run());   // can't add zero copies!
     assertEquals( 1, inventory.size() );
     assertTrue  ( UNDO.run() );
     assertEquals( 0, inventory.size() );
     assertTrue  (!UNDO.run() );  // nothing to undo!
     assertEquals( 0, inventory.size() );
     assertTrue  ( REDO.run() );
     assertEquals( 1, inventory.size() );
     assertTrue  (!REDO.run() );  // nothing to redo!
     assertEquals( 1, inventory.size() );
     assertTrue  ( Data.newAddCmd(inventory, v1, -2).run());   // delete!
     assertEquals( 0, inventory.size() );
     assertTrue  (!Data.newOutCmd(inventory, v1).run());       // can't check out
     assertEquals( 0, inventory.size() );
     assertTrue  ( UNDO.run() );  // should undo the AddCmd, not the OutCmd
     assertEquals( 1, inventory.size() ); 
     assertTrue  (!Data.newAddCmd(inventory, v1, -3).run());   // can't delete 3
     assertTrue  ( Data.newAddCmd(inventory, v1, -2).run());   // delete 2
     assertEquals( 0, inventory.size() );
     assertTrue  ( UNDO.run() ); 
     assertEquals( 1, inventory.size() ); 

     assertEquals( "K (2003) : S [2,0,0]", inventory.get(v1).toString() );	
     assertTrue  ( Data.newAddCmd(inventory, v1, 2).run());
     assertEquals( "K (2003) : S [4,0,0]", inventory.get(v1).toString() );	
     assertTrue  ( Data.newAddCmd(inventory, v1, 2).run());
     assertEquals( "K (2003) : S [6,0,0]", inventory.get(v1).toString() );	
     assertTrue  ( UNDO.run() );
     assertEquals( "K (2003) : S [4,0,0]", inventory.get(v1).toString() );	
     assertTrue  ( UNDO.run() );
     assertEquals( "K (2003) : S [2,0,0]", inventory.get(v1).toString() );	

     assertTrue  ( Data.newOutCmd(inventory, v1).run());
     assertEquals( "K (2003) : S [2,1,0]", inventory.get(v1).toString() );
     assertTrue  ( Data.newOutCmd(inventory, v1).run());
     assertEquals( "K (2003) : S [2,2,0]", inventory.get(v1).toString() );
     assertTrue  (!Data.newOutCmd(inventory, v1).run());
     assertEquals( "K (2003) : S [2,2,0]", inventory.get(v1).toString() );
     assertTrue  ( UNDO.run() );
     assertEquals( "K (2003) : S [2,1,0]", inventory.get(v1).toString() );
     assertTrue  ( UNDO.run() );
     assertEquals( "K (2003) : S [2,0,0]", inventory.get(v1).toString() );
     assertTrue  ( REDO.run() );
     assertEquals( "K (2003) : S [2,1,0]", inventory.get(v1).toString() );
     assertTrue  ( REDO.run() );
     assertEquals( "K (2003) : S [2,2,0]", inventory.get(v1).toString() );
    
     assertTrue  ( Data.newInCmd(inventory, v1).run() );
     assertEquals( "K (2003) : S [2,1,0]", inventory.get(v1).toString() );	
     assertTrue  ( Data.newInCmd(inventory, v1).run() );
     assertEquals( "K (2003) : S [2,0,0]", inventory.get(v1).toString() );
     assertTrue  (!Data.newInCmd(inventory, v1).run() );
     assertEquals( "K (2003) : S [2,0,0]", inventory.get(v1).toString() );
     assertTrue  ( UNDO.run() );
     assertEquals( "K (2003) : S [2,1,0]", inventory.get(v1).toString() );
     assertTrue  ( UNDO.run() );
     assertEquals( "K (2003) : S [2,2,0]", inventory.get(v1).toString() );
     assertTrue  ( REDO.run() );
     assertEquals( "K (2003) : S [2,1,0]", inventory.get(v1).toString() );
     assertTrue  ( REDO.run() );
     assertEquals( "K (2003) : S [2,0,0]", inventory.get(v1).toString() );

     assertTrue  ( Data.newAddCmd(inventory, v2, 4).run());
     assertEquals( 2, inventory.size() );
     assertTrue  ( Data.newClearCmd(inventory).run());
     assertEquals( 0, inventory.size() );
     assertTrue  ( UNDO.run() );
     assertEquals( 2, inventory.size() );
     assertTrue  ( REDO.run() );
     assertEquals( 0, inventory.size() );
  }

  public void test2() {
    final Inventory inventory = Data.newInventory();
    final Video v1 = Data.newVideo("K", 2003, "S");
    final RerunnableCommand UNDO = Data.newUndoCmd(inventory);
    final RerunnableCommand REDO = Data.newRedoCmd(inventory);
     assertTrue  ( Data.newAddCmd(inventory, v1,2).run());
     assertEquals( "K (2003) : S [2,0,0]", inventory.get(v1).toString() );
     assertTrue  ( Data.newOutCmd(inventory, v1).run());
     assertEquals( "K (2003) : S [2,1,0]", inventory.get(v1).toString() );
     assertTrue  ( UNDO.run() );
     assertEquals( "K (2003) : S [2,0,0]", inventory.get(v1).toString() );
     assertTrue  ( REDO.run() );
     assertEquals( "K (2003) : S [2,1,0]", inventory.get(v1).toString() );
     assertTrue  ( Data.newOutCmd(inventory, v1).run());
     assertEquals( "K (2003) : S [2,2,0]", inventory.get(v1).toString() );
     assertTrue  ( UNDO.run() );
     assertEquals( "K (2003) : S [2,1,0]", inventory.get(v1).toString() );
     assertTrue  ( UNDO.run() );
     assertEquals( "K (2003) : S [2,0,0]", inventory.get(v1).toString() );
  }
}
