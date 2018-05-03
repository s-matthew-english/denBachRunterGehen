package com.mycompany.app;

import io.netty.*;

public class NettyServer {

  private int port;

  public NettyServer(int port) {
    this.port = port;
  }

  public static void main(String... args) throws Exception {

    int port = args.length > 0
             ? Integer.parseInt(args[0])
             : 8080;

    new NettyServer(port).run();
  }

  public void run() throws Exception {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workerGroup)
       .channel(NioServerSocketChannel.class)
       .childHandler(new ChannelInitializer<SocketChannel>() {
        @Override
        public void initChannel(SocketChannel ch) throws Exception {
          /**
           * THIS IS THE MOST INTERESTING PART...
           **/
          ch.pipeline().addLast(new RequestDecoder(),
                                new ResponseDataEncoder(),
                                new ProcessingHandler()
                                );
          /**
           * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
           **/
        }
       }).option(ChannelOption.SO_BACKLOG, 128)
         .childOption(ChannelOption.SO_KEEPALIVE, true);

         ChannelFuture f = b.bind(port).sync();
         f.channel().closeFuture().sync();
    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }
}
