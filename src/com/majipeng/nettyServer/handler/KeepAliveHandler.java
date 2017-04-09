package com.majipeng.nettyServer.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import majipeng.utils.Log;

public class KeepAliveHandler extends ChannelDuplexHandler {
	static final String TAG = KeepAliveHandler.class.getSimpleName();

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
     Log.d(TAG, "用户未活跃");
	}
}
