package com.majipeng.nettyServer.router;

import majipeng.protocol.Protocol;

public class RouterByMothed extends AbsRouterToRouter {
	public static String CONF_NAME = "routerbymothed.router";

	public RouterByMothed() {
		super(CONF_NAME);
	}

	@Override
	public String getHandleKey(Protocol request) {
		return request.findRequestLine().getMothed();
	}
}
