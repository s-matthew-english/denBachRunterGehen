package com.mycompany.app;

import io.netty.*;

public class NettyClient {
  public static void main(String... args) throws Exception {

    String host = "localhost";
    int port = 8080;
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      Bootstrap b = new Bootstrap();
      b.group(workerGroup);
      b.channel(NioSocketChannel.class);
      b.option(ChannelOption.SO_KEEPALIVE, true);
      b.handler(new ChannelInitializer<SocketChannel>() {

        @Override
        public void initChannel(SocketChannel ch) throws Exception {
          ch.pipeline().addLast(new RequestDataEncoder(),
                                new ResponseDataDecoder(),
                                new ClientHandler()
                                );
        }
      });

      ChannelFuture f = b.connect(host, port).sync();

      f.channel().closeFuture().sync();
    } finally {
      workerGroup.shutdownGracefully();
    }
  }
}
