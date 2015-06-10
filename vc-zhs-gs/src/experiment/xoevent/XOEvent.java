package experiment.xoevent;

import java.util.EventObject;


public class XOEvent extends EventObject{
	/**
	 */
	private static final long serialVersionUID = 1L;

	public XOEvent(XOEventSource xoEventSource){
		super(xoEventSource);
	}
	
}
