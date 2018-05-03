package com.mycompany.app;

import io.netty.buffer.Unpooled;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

/**
 * Hello world!
 **/
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        ByteBuf buf = Unpooled.buffer(16);
        buf.writeInt(0);

        String output = ByteBufUtil.hexDump(buf);
        System.out.println(output);
    }
}



// byte[] input = new byte[] {0x01,0x02};
// ByteBuf.readBytes(input, input.length);
// String serverName  = new String(input);  
// System.out.println(input);
