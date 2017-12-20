/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.demo.netty.echo;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handler implementation for the echo server.
 */
@Sharable // Indicate that a ChannelHandler can be safely shared by multiple channels
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    // Invoked when a Channel is registered to its EventLoop and able to handle IO
    super.channelRegistered(ctx);
    System.err.println("channel registered:" + ctx.channel().toString());
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    super.channelUnregistered(ctx);
    System.err.println("channel unregistered:" + ctx.channel().toString());
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    // Invoked when a Channel is active, the Channel is connected/bound and ready
    System.err.println("channel active:" + ctx.channel().toString());
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    // Invoked when a Channel leaves active state is no longer connected to its remote peer
    super.channelInactive(ctx);
    System.err.println("channel inactive:" + ctx.channel().toString());
  }

  @Override //
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    ctx.write(msg);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // Close the connection when an exception is raised.
    cause.printStackTrace();
    ctx.close();
  }
}
