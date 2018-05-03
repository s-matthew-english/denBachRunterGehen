package com.mycompany.app;

import io.netty.*;

public class RequestDecoder extends ReplayingDecoder<RequestData> {

  private final Charset charset = Charset.forName("UTF-8");

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    RequestData data = new RequestData();
    data.setIntValue(in.readInt());
    int strLen = in.readInt();
    data.setStringValue(
      in.readCharSequence(strLen, charset).toString()
    );
    out.add(data);
  }
}
