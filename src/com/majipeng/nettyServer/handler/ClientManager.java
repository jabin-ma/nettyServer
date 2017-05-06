package com.majipeng.nettyServer.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

@Sharable
public class ClientManager extends ChannelInboundHandlerAdapter {
	private  static final ClientManager instance = new ClientManager();
	private  final ChannelGroup allChannel = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
//    private final HashMap<String,ChannelId> allChannelId=new HashMap<>();
//	AttributeKey<JID> JID_KEY = AttributeKey.valueOf("majipeng:JID");

	public static ClientManager getInstance() {
		return instance;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		allChannel.add(ctx.channel());
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//		allChannelId.remove(ctx.channel().id().asLongText());
		super.channelInactive(ctx);
	}

	
	public int count() {
		return allChannel.size();
	}

	public ChannelGroup getAllChannel() {
		return allChannel;
	}
	
	
	
//	public Channel findById(String longid){
//		ChannelId id=findChannelId(longid);
//		if(id==null)return null;
//		return allChannel.find(id);
//	}
	
//	private ChannelId findChannelId(String longid){
//		return allChannelId.get(longid);
//	}
}
