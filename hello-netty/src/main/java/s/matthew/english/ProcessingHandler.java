package s.matthew.english;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.buffer.ByteBuf; 
import io.netty.channel.ChannelInboundHandlerAdapter; 
import io.netty.channel.ChannelFuture; 
import io.netty.channel.ChannelFutureListener; 

public class ProcessingHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    RequestData requestData = (RequestData)msg;
    ResponseData responseData = new ResponseData();
    responseData.setIntValue(requestData.getIntValue() * 2);
    ChannelFuture future = ctx.writeAndFlush(responseData);
    future.addListener(ChannelFutureListener.CLOSE);
    System.out.println(requestData);
  }
}
