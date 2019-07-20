package org.jow.common.event;

import org.jow.core.interfaces.IEvent;
import org.jow.core.support.observer.Observer;

public class Event extends Observer{

	public static final Event instance = new Event();
	
	
	/**
	 * 发布事件
	 * @param event
	 */
	public static void fire(IEvent event) {
		instance.fireHandler(event.getKey(), null, event);
	}
	
	/**
	 * 发布事件，主事件 + 子事件
	 * @param subkey
	 * @param event
	 */
	public static void fire(Object subkey, IEvent event) {
		instance.fireHandler(event.getKey(), subkey, event);
	}
	
	
}
