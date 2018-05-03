package com.mycompany.app;

import io.netty.*;

public class ResponseDataEncoder extends MessageToByteEncoder<ResponseData> {

  @Override
  protected void encode(ChannelHandlerContext ctx, ResponseData msg, ByteBuf out) throws Exception {
    out.writeInt(msg.getIntValue());
  }
}
