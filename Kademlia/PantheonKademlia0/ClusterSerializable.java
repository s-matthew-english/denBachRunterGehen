public interface ClusterSerializable {

  void writeToBuffer(Buffer buffer);

  int readFromBuffer(int pos, Buffer buffer);
}
