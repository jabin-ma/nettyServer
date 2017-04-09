package com.majipeng.nettyServer;

import java.net.URISyntaxException;

import com.majipeng.nettyServer.handler.ChannelInit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import majipeng.utils.Log;

public class NettyServer {

	public static void main(String[] args) throws URISyntaxException {
		startServer();
//		User user=new User();
//		user.setName("TEST");
//	    user.setPassword("11111");
//		new UserDaoImpl().add(user);
	}

	private static void startServer() {
		NioEventLoopGroup boss = new NioEventLoopGroup(1);
		NioEventLoopGroup worker = new NioEventLoopGroup(100);
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(new ChannelInit())
					.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture f = server.bind(9999).sync();
			Log.d("NettyServer", "服务器已启动");
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
}
