public class ConnectHandler extends ChannelInboundAdapter {
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("Client " + ctx.channel().remoteAddress() + " connected");
  }

  public scratchPad() {
    Channel channel = null;
    // Does not block
    ChannelFuture future = channel.connect(new InetSocketAddress("192.168.0.1", 25));

    Channel channel = null; 
    // Does not block
    ChannelFuture future = channel.connect(new InetSocketAddress("192.168.0.1", 25));
    future.addListener(new ChannelFutureListener() {
      @Override
      public void operationComplete(ChannelFuture future) {
        if (future.isSuccess()) {
          ByteBuf buffer = Unpooled.copiedBuffer(
            "Hello", Charset.defaultCharset());
          ChannelFuture wf = future.channel().writeAndFlush(buffer);
        } else {
          Throwable cause = future.cause();
          cause.printStackTrace();
        }
      }
    });
  }
}
