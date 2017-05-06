package com.majipeng.nettyServer.router;

import com.majipeng.nettyServer.utils.ServerConfig;
import com.majipeng.nettylib.utils.Log;

import io.netty.handler.ssl.ApplicationProtocolConfig.Protocol;

//提供一个需要处理的key，该类可以帮助你自动分发请求
public abstract class AbsRouterToRouter implements Router<Protocol> {
	private ServerConfig routerConf;

	public final String TAG = Log.getTag(getClass());

	public AbsRouterToRouter(String conf) {
		routerConf = ServerConfig.getInstance(conf);
	}

	public AbsRouterToRouter() {
	}

	@Override
	public boolean router(Protocol request) {
		if (routerConf != null) {
			Router ro = routerConf.getClass(getHandleKey(request), Router.class);
			Log.d(TAG, "Key:"+getHandleKey(request)+" Router:"+ro);
			if (ro != null) {
				ro.router(request);
				return true;
			}
		}
		return handleRequest(request);
	}

	public boolean handleRequest(Protocol request) {
		Log.d(TAG, "无法分发:"+request.toJson());
		return false;
	}
	public abstract String getHandleKey(Protocol request);
}
