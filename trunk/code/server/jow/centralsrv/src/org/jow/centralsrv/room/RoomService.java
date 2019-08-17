package org.jow.centralsrv.room;

import org.jow.core.Port;
import org.jow.core.Service;
import org.jow.core.config.CentralConfig;
import org.jow.core.gen.proxy.DistrClass;

/**
 * 全局房间服
 * 
 * @author Yangxianmeng
 *
 */
@DistrClass
public class RoomService extends Service {

	public RoomService(Port port) {
		super(port);
	}
	
	@Override
	public Object getId() {
		return CentralConfig.SERV_ROOM;
	}
	
	
}
