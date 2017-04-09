package com.majipeng.nettyServer.router;

public interface Router<T> {
       
	   public boolean router(T content);
}
