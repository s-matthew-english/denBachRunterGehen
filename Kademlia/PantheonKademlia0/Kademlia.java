public class Kademlia {

  public static void main(String[] args) {
    System.out.println("Pantheon Kademlia Implementation");


    PeerTable table = new PeerTable(Peer.randomId(), 16);


    String[] idArray = new String[]{ "0x1B9816FC81487226045EA29618F2668E7FE21F0CF89F37DEF97722651E8ACA60B8207831433FAA56DCFDEF8B98CD1996A9FD96D86A4E839AE0C570C942BF38A1",
                                     "0xA2D244EC013ABCDF1627F7E5C26E592D4C044347A3906EFE7A2FAAECA220254FC470D572450E097789D000463055546B8A2ABEE4F170C71A5DA3E166B8877D3E",
                                     "0xDC1C00E522809B9367042C4F82B783DCDA0E0D990550C7EF068EA97894BD2C6D189873135799FA0FABBDF13504888F3B4428E287EFF3C08FB486B9ADCBEB37BC",
                                     "0x9F089857CD005D45708A4E22ECB41651BAAD6E5EB333E0662B110290608CAA4A0D9509F789CED6C6E07D5DA622B89404A2ABC39C0FD5EFD821E56C76123028EC",
                                     "0xB168722F79AFFBA62471BF25DEA99EFAF60EDAB384083A7587338BB8421B8DE95A94EDB6182F2D22D957C3A2ECA82F273E66EA96C6D4F14F9CBE50B62936202B"};
   //Endpoint{host='127.0.0.1', udpPort=1}, status=known}



    DiscoveryPeer[] peers = new DiscoveryPeer[5];
    for(int i = 0; i < idArray.length; i++) {
      peers[i] = new DefaultDiscoveryPeer(idArray[i], new Endpoint("127.0.0.1", 1, null));
    }


    for (DiscoveryPeer peer : peers) {
      System.out.println(peer.toString());
    }
  }
}
