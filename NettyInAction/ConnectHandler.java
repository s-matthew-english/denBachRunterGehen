public class ConnectHandler extends ChannelInboundAdapter {
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("Client " + ctx.channel().remoteAddress() + " connected");
  }
}
