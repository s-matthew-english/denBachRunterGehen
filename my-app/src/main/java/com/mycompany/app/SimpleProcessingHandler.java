package com.mycompany.app;

import io.netty.*;

public class SimpleProcessingHandler extends ChannelInboundHandlerAdapter {
  private ByteBuf tmp;

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) {
    System.out.println("Handler added");
    tmp = ctx.alloc().buffer(4);
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) {
    System.out.println("Handler removed");
    tmp.release();
    tmp = null;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    ByteBuf m = (ByteBuf)msg;
    tmp.writeBytes(m);
    m.release();
    if (tmp.readableBytes() >= 4) {
      // request processing
      RequestData requestData = new RequestData();
      requestData.setIntValue(tmp.readInt());
      ResponseData responseData = new ResponseData();
      responseData.setIntValue(requestData.getIntValue() * 2);
      ChannelFuture future = ctx.writeAndFlush(responseData);
      future.addListner(ChannelFutureListener.CLOSE);
    }
  }
}
