package com.majipeng.nettyServer.handler;

import com.majipeng.nettylib.utils.Log;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MessageHandler extends ChannelInboundHandlerAdapter {
	static final String TAG = "MessageHandler";
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		if(msg instanceof Protocol){
//			RouterByMothed rou=new RouterByMothed();
//			rou.router((Protocol) msg);
//		}
		Log.d(TAG, msg.toString());
		super.channelRead(ctx, msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Log.d(TAG, "channelActive");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Log.d(TAG, "channelInactive");
		super.channelInactive(ctx);
	}

//	private boolean router(Request req) {
//		
//		ServerConfig sconf = ServerConfig.getInstance("router.conf");
//		Router rou = sconf.getClass(req.getAction(), Router.class);
//		if (rou == null)
//			return false;
//		rou.router(req.getContent());// 分发处理事件内容
//		return true;
//	}
}
