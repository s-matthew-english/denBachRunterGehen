package hello;



import org.joda.time.LocalTime;

import org.rocksdb.RocksDB;
import org.rocksdb.Options;
import org.rocksdb.RocksDBException;

public class HelloWorld {
  public static void main(String[] args) {
    LocalTime currentTime = new LocalTime();
    System.out.println("The current local time is: " + currentTime);
    Greeter greeter = new Greeter();
    System.out.println(greeter.sayHello());


// a static method that loads the RocksDB C++ library.
RocksDB.loadLibrary();


// the Options class contains a set of configurable DB options
// that determines the behaviour of the database.
try (final Options options = new Options().setCreateIfMissing(true)) {
  
  // a factory method that returns a RocksDB instance
  try (final RocksDB db = RocksDB.open(options, "/Users/s.matthew.english/ConsenSys/PegaSys/workbench/java/DenBachRunterGehen/RocksDB/rocksdj")) {
  
    // do something
    byte[] key1 = "key1".getBytes();
    byte[] key2 = "key2".getBytes();
    // some initialization for key1 and key2
    final byte[] value = db.get(key1);
    if (value != null) {  // value == null if key1 does not exist in db.
      db.put(key2, value);
    }
    //db.remove(key1);

    byte[] result = db.get(key1);
    String s = new String(result);

    System.out.println(s);

  }
} catch (RocksDBException e) {
  // do some error handling
}



  }
}
