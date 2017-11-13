package com.demo.netty.discard;

import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/18 <br/>
 * <B>版本：</B><br/>
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
  //

  public static void main(String[] args) {
    int val = 2;
    switch (val) {
      case 1:
        System.out.println("这个是1");
        break;
      case 2:
        System.err.println("这个是2");
      case 3:
        System.err.println("这个是3");
        break;
      default:
        System.err.println("这个是default");
        break;
    }

  }
}
