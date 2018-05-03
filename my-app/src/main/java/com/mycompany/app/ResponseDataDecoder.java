package com.mycompany.app;

import io.netty.*;

public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    ResponseData data = new ResponseData();
    data.setIntValue(in.readInt());
    out.add(data);
  }
}
