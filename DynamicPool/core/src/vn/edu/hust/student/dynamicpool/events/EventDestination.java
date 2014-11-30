package vn.edu.hust.student.dynamicpool.events;

import java.lang.reflect.InvocationTargetException;
import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.exceptions.*;
import com.eposi.eventdriven.implementors.*;

public class EventDestination {
	private BaseEventDispatcher eventDispatcher = new BaseEventDispatcher();
	private static EventDestination _instance = null;

	public static EventDestination getInstance() {
		if (_instance == null)
			_instance = new EventDestination();
		return _instance;
	}

	public EventDestination() {

	}

	public void addEventListener(EventType eventType,
			BaseEventListener baseEventListener) {
		eventDispatcher.addEventListener(eventType.toString(),
				baseEventListener);
	}

	public void dispatchSuccessEvent(EventType eventType) {
		dispatchEvent(eventType, true, null, null);
	}

	private void dispatchEvent(EventType eventType, boolean isSuccess, Object targetObject, Exception error) {
		try {
			EventResult eventResult = new EventResult(isSuccess, targetObject, error);
			eventDispatcher.dispatchEvent(new Event(eventType.toString(), eventResult));
		} catch (InvocationTargetException | IllegalAccessException
				| NoSuchMethodException | InvalidHandlerMethod
				| NoContextToExecute e) {
			System.err.println(EventDestination.class.toString() + " "
					+ e.getMessage());
		}
	}
	
	public void dispatchFailEvent(EventType eventType) {
		dispatchEvent(eventType, false, null, null);
	}
	
	public void dispatchSuccessEventWithObject(EventType eventType, Object targetObject) {
		dispatchEvent(eventType, true, targetObject, null);
	}
	
	public void dispatchFailEventWithExeption(EventType eventType, Exception error) {
		dispatchEvent(eventType, false, null, error);
	}
	
	public void dispatchFailEventWithObject(EventType eventType, Exception error, Object targetObject) {
		dispatchEvent(eventType, false, targetObject, error);
	}

	public static boolean parseEventToBoolean(Event event) {
		if (EventResult.class.isInstance(event.getTarget())) {
			return ((EventResult)event.getTarget()).isSuccess();
		}
		try {
			boolean isSuccess = (boolean) event.getTarget();
			return isSuccess;
		} catch (Exception e) {
			return false;
		}
	}

	public static Object parseEventToTargetObject(Event event) {
		if (EventResult.class.isInstance(event.getTarget())) {
			return ((EventResult)event.getTarget()).getTargetObject();
		}
		return event.getTarget();
	}
}