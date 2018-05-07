package server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

public class FixedLengthFrameDecoderTest {

  @Test
  public void testFramesDecoded() {
    ByteBuf buf = Unpooled.buffer();
    for (int i = 0; i < 9; i++) {
      buf.writeByte(i);
    }
    ByteBuf input = buf.duplicate();
    EmbeddedChannel channel = new EmbeddedChannel(
        new FixedLengthFrameDecoder(3));
    // write bytes
    assertTrue(channel.writeInbound(input.retain()));
    assertTrue(channel.finish());

    // read messages
    ByteBuf read = (ByteBuf) channel.readInbound();
    assertEquals(buf.readSlice(3), read);
    read.release();

    read = (ByteBuf) channel.readInbound();
    assertEquals(buf.readSlice(3), read);
    read.release();

    read = (ByteBuf) channel.readInbound();
    assertEquals(buf.readSlice(3), read);
    read.release();

    assertNull(channel.readInbound());
    buf.release();
  }

}
