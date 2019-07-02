package org.jow.common.event;

import org.jow.core.interfaces.IEvent;
import org.jow.core.support.observer.Observer;

public class Event extends Observer {
	
	public static final Event instance = new Event();
	
	
	/**
	 * 发布事件
	 * @param event
	 */
	public static void fire(IEvent event) {
		instance.fireHandler(event.getKey(), null, event);
	}
	
	/**
	 * 发布事件主事件 + 子事件
	 * @param subKey
	 * @param event
	 */
	public static void fire(Object subKey, IEvent event) {
		instance.fireHandler(event.getKey(), subKey, event);
	}
	
}
