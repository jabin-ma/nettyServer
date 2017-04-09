package com.majipeng.nettyServer.router.process;

import com.majipeng.nettyServer.router.AbsRouterToRouter;

import majipeng.protocol.Protocol;

public abstract class AbsRequestProcess extends AbsRouterToRouter {
	@Override
	public String getHandleKey(Protocol request) {
		return null;//不分发任何key
	}
	
	@Override
	public abstract boolean handleRequest(Protocol request);
	
}
