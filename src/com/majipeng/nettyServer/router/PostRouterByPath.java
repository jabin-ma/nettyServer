package com.majipeng.nettyServer.router;

import io.netty.handler.ssl.ApplicationProtocolConfig.Protocol;
//自己不处理事件,分发
public class PostRouterByPath extends AbsRouterToRouter{
	public PostRouterByPath() {
		super("routerbypath.router");
	}
	@Override
	public String getHandleKey(Protocol request) {
		return request.findRequestLine().parserURI().getPath();
	}
}
