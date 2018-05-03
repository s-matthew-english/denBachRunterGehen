package com.mycompany.app;

import io.netty.*;

public class RequestDataEncoder extends MessageToByteEncoder<RequestData> {

  private final Charset charset = Charset.forName("UTF-8");

  @Override
  protected void encode(ChannelHandlerContext ctx, RequestData msg, ByteBuf out) throws Exception {

    out.writeInt(msg.getIntValue());
    out.writeInt(msg.getStringValue().length());
    out.writeCharSequence(msg.getStringValue(), charset);
  }
}
