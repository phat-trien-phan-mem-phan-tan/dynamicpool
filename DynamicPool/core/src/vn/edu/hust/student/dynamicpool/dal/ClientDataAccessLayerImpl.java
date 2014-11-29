package vn.edu.hust.student.dynamicpool.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.dal.controller.ClientMainController;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.exception.DALException;

public class ClientDataAccessLayerImpl implements DataAccessLayer {
	private String clientName;
	private Logger logger = LoggerFactory
			.getLogger(ClientDataAccessLayerImpl.class);

	public ClientDataAccessLayerImpl() {
		logger.debug("Contruct");
		setClientName(UUID.randomUUID().toString());
	}

	@Override
	public String getClientName() {
		return clientName;
	}

	private void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public void createHost() {
		logger.error("Cannot create host");
		EventDestination.getInstance().dispatchFailEventWithObject(
				EventType.DAL_CREATE_HOST,
				new DALException("Default client cannot create host", null));
	}

	@Override
	public void joinHost(String key) {
		logger.debug("join host");
		try {
			ClientMainController.getInstance().start(key);
			logger.info("join host success");
			EventDestination.getInstance().dispatchSuccessEvent(
					EventType.DAL_JOIN_HOST);
		} catch (DALException e) {
			logger.error("cannot join host");
			EventDestination.getInstance().dispatchFailEventWithObject(
					EventType.DAL_JOIN_HOST,
					new DALException("cannot join host", e));
		}

	}
	
	@Override
	public void addDevice(DeviceInfo deviceInfo) {
		logger.debug("add device");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Field.COMMAND, Field.ADD_DEVICE);
		map.put("device", deviceInfo);
		map.put("clientName", this.getClientName());
		ClientMainController.getInstance().getClientSocketController()
		.sendMessage(map);
	}
	
	@Override
	public void requestCreateFish(IFish fish) {
		logger.debug("create fish");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("fish", fish);
		ClientMainController.getInstance().getClientSocketController()
				.sendMessage(data);
	}

	@Override
	public void synchronization() {
		logger.debug("synchronization");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFish(Fish fish) {
		logger.debug("remove fish");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Field.COMMAND, Field.REMOVE_FISH);
		map.put("fish", fish);
		ClientMainController.getInstance().getClientSocketController()
				.sendMessage(map);
	}

	@Override
	public void synchronous(List<IFish> fishes, String clientName) {
		logger.debug("synchronous");
		// TODO Auto-generated method stub

	}

	@Override
	public void registerDone(Pool pool) {
		logger.debug("register done");
		// TODO Auto-generated method stub

	}

	@Override
	public void exit() {

	}

	@Override
	public void respondCreateFishRequest(String clientName, boolean isSuccess,
			IFish fish) {
		// TODO Auto-generated method stub
		
	}
}
