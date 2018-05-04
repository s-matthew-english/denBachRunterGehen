package s.matthew.english;

import io.netty.handler.codec.ReplayingDecoder;
import io.netty.channel.ChannelHandlerContext; 
import io.netty.buffer.ByteBuf; 
import java.util.List;

public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    ResponseData data = new ResponseData();
    data.setIntValue(in.readInt());
    out.add(data);
  }
}
