package com.mycompany.app;

import io.netty.*;

public class ClientHandler extends ChannelInboundHandlerAdopter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {

    RequestData msg = new RequestData();
    msg.setIntValue(123);
    msg.setStringValue("all work and no play makes jack a dull boy");
    ChannelFuture future = ctx.writeAndFlush(msg);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Objext msg) throws Exception {
    System.out.println((ResponseData)msg);
    ctx.close();
  }
}
