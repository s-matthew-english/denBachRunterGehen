package s.matthew.english;

import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.socket.SocketChannel; 
import io.netty.channel.ChannelFuture; 
import java.nio.charset.Charset;
import io.netty.channel.ChannelHandlerContext;
import io.netty.buffer.ByteBuf; 
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.socket.nio.NioSocketChannel;

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
