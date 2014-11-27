package vn.edu.hust.student.dynamicpool.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.dal.client.entity.Client;
import vn.edu.hust.student.dynamicpool.dal.controller.HostMainController;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.exception.DALException;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

public class HostDataAccessLayerImpl implements DataAccessLayer {
	private Logger logger = LoggerFactory
			.getLogger(HostDataAccessLayerImpl.class);

	public HostDataAccessLayerImpl() {
		logger.debug("Contruct");
	}

	@Override
	public String getClientName() {
		return AppConst.DEFAULT_HOST_NAME;
	}

	@Override
	public void createHost() {
		logger.debug("create host");
		try {
			String key = HostMainController.getInstance().connectServer();
			HostMainController.getInstance().start();
			logger.info("create host success");
			EventDestination.getInstance().dispatchSuccessEventWithObject(
					EventType.DAL_CREATE_HOST, key);
		} catch (DALException e) {
			logger.error("cannot create host");
			EventDestination.getInstance().dispatchFailEventWithObject(
					EventType.DAL_CREATE_HOST, e);
		}
	}

	@Override
	public void joinHost(String key) {
		logger.error("cannot join host");
		EventDestination.getInstance().dispatchFailEventWithObject(
				EventType.DAL_JOIN_HOST,
				new DALException("host instance cannot join to another host",
						null));
	}
	
	@Override
	public void addDevice(DeviceInfo deviceInfo) {
		logger.info("add device success");
		EventDestination.getInstance().dispatchSuccessEvent(EventType.DAL_ADD_DEVICE);
	}
	
	@Override
	public void createFish(IFish fish) {
		logger.debug("create fish success");
		EventDestination.getInstance().dispatchSuccessEvent(EventType.DAL_CREATE_FISH);
	}

	@Override
	public void synchronization() {
		logger.debug("synchronization");
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeFish(Fish fish) {
		logger.info("remove fish success");
		EventDestination.getInstance().dispatchSuccessEvent(EventType.DAL_REMOVE_FISH);
	}
	
	@Override
	public void synchronous(List<IFish> fishes, String clientName) {
		logger.debug("synchronous");
		Client client = HostMainController.getInstance().getClientManager()
				.getClient(clientName);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(Field.COMMAND, Field.SYNCHORONOUS);
		data.put("fishManager", fishes);
		if (client != null) {
			client.send(data);
		}
	}
	
	@Override
	public void registerDone(Pool pool) {
		logger.debug("register done");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {

	}
}
