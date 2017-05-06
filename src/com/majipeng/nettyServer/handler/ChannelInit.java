package com.majipeng.nettyServer.handler;

import com.majipeng.nettylib.utils.Log;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.spdy.SpdyFrameCodec;
import io.netty.handler.codec.spdy.SpdyHttpDecoder;
import io.netty.handler.codec.spdy.SpdyHttpEncoder;
import io.netty.handler.codec.spdy.SpdyVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class ChannelInit extends ChannelInitializer<SocketChannel> {
	static final String TAG = "ChanelInit";
	EventExecutorGroup mThreadPool = new DefaultEventExecutorGroup(16);// 16线程处理

	private static final int MAX_SPDY_CONTENT_LENGTH = 1024 * 1024;// 1mb

	@Override
	protected void initChannel(SocketChannel sc) throws Exception {
		ChannelPipeline ppl = sc.pipeline();
		ppl.addLast(new LoggingHandler(LogLevel.INFO));// Log
		ppl.addLast(ClientManager.getInstance());// 管理用户

		// ppl.addLast(new StringDecoder(Charset.forName("UTF-8")));//字符串解码
		// ppl.addLast(new ProtocolDecoder(true));//解码
		// ppl.addLast(new StringEncoder(Charset.forName("UTF-8")));//编码
		// ppl.addLast(new ProtocolEncoder());

		// spdy
		ppl.addLast(new SpdyFrameCodec(SpdyVersion.SPDY_3_1));
		ppl.addLast(new SpdyHttpEncoder(SpdyVersion.SPDY_3_1));
		ppl.addLast(new SpdyHttpDecoder(SpdyVersion.SPDY_3_1, MAX_SPDY_CONTENT_LENGTH));

		ppl.addLast(new IdleStateHandler(-1, -1, 25));// 检测心跳超时 客户端25秒内未活跃
		ppl.addLast(new KeepAliveHandler());// 心跳消息处理
		ppl.addLast(mThreadPool, new MessageHandler());// 消息处理器
		Log.d(TAG, "Channel is open:" + sc);
	}
}
