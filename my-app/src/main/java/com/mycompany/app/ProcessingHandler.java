package com.mycompany.app;

import io.netty.*;

public class ProcessingHandler extends ChannelInboundAdapter {

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
