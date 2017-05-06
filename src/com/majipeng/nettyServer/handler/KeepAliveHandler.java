package com.majipeng.nettyServer.handler;

import com.majipeng.nettylib.utils.Log;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

public class KeepAliveHandler extends ChannelDuplexHandler {
	static final String TAG = KeepAliveHandler.class.getSimpleName();

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
     Log.d(TAG, "用户未活跃");
	}
}
