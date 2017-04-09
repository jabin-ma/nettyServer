package com.majipeng.nettyServer.router.process;

import com.alibaba.fastjson.JSON;
import com.majipeng.nettyServer.handler.ClientManager;

import io.netty.channel.Channel;
import majipeng.model.User;
import majipeng.protocol.Protocol;
import majipeng.protocol.ResponseLine;
import majipeng.utils.Log;
import majipeng.utils.RequestUtils;

public class LogonProcess extends AbsRequestProcess {
	@Override
	public boolean handleRequest(Protocol request) {
		Log.d(TAG, request.toJson());
		String type = request.getHeaderValue((Protocol.HEADER_TYPE));
		String content = request.getContent();
		String from = request.getHeaderValue(Protocol.HEADER_FROM);
		switch (type) {
		case Protocol.MimeType.JSON:
			User logonUser = JSON.parseObject(content, User.class);
			if (logonUser.getName().equals("majipeng")) {
				Channel ch = ClientManager.getInstance().findById(from);
				Protocol response = Protocol.obtain();
				response.setResponseLine(new ResponseLine(200, "ok"));
				response.setRequestLine(request.findRequestLine());
				RequestUtils.send(ch, response);
			}
			break;
		default:
			break;
		}

		return false;
	}
}
