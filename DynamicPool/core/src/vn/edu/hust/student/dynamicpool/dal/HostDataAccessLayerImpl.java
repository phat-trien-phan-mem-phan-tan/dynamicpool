package vn.edu.hust.student.dynamicpool.dal;

import java.net.UnknownHostException;
import java.util.ArrayList;
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
	public void joinHost(String key) throws DALException {
		throw new DALException("host cannot join host", null);
	}

	@Override
	public void createHost() {
		logger.debug("create host");
		String key = null;
		try {
			key = HostMainController.getInstance().connectServer();
			HostMainController.getInstance().start();
			logger.info("create host success with key: {}", key);
			EventDestination.getInstance().dispatchSuccessEventWithObject(
					EventType.DAL_CREATE_HOST, key);
		} catch (DALException e) {
			logger.error("cannot create host {}", e.getMessage());
			
		} catch (UnknownHostException e) {
			logger.debug("cannot get ip lan {}", e.getMessage());
		}
	}

	@Override
	public void addDevice(DeviceInfo deviceInfo) {
		logger.debug("send add device request");
		deviceInfo.setClientName(this.getClientName());
		EventDestination.getInstance().dispatchSuccessEventWithObject(
				EventType.DAL_ADD_DEVICE_REQUEST, deviceInfo);
	}

	@Override
	public void updateSettingToClient(String clientName, Pool pool) {
		logger.debug("send setting to client {}", clientName);
		if (clientName == AppConst.DEFAULT_HOST_NAME) {
			EventDestination.getInstance().dispatchSuccessEventWithObject(
					EventType.DAL_UPDATE_SETTINGS_RESPONSE, pool);
		} else {
			sendSetingToClient(clientName, pool);
		}
	}

	private void sendSetingToClient(String clientName, Pool pool) {
		// TODO Thanh viet
		Map<String, Object> map = new HashMap<>();
		map.put(Field.COMMAND, Field.SEND_SETTINGS);
		map.put(Field.POOL, pool);
		Client client = HostMainController.getInstance().getClientManager()
				.getClient(clientName);
		if (client != null) {
			client.send(map);
		} else {
			logger.error("not found for clientName {}", clientName);
		}
	}

	@Override
	public void requestCreateFish(IFish fish) {
		logger.debug("request creat fish sent");
		List<Object> list = new ArrayList<Object>();
		list.add(fish);
		list.add(this.getClientName());
		EventDestination.getInstance().dispatchSuccessEventWithObject(
				EventType.DAL_CREATE_FISH_REQUEST, list);
	}

	@Override
	public void respondCreateFishRequest(String clientName, boolean isSuccess,
			IFish fish) {
		logger.debug("create fish respond");
		if (clientName == getClientName()) {
			if (isSuccess) {
				EventDestination.getInstance().dispatchSuccessEventWithObject(
						EventType.DAL_CREATE_FISH_RESPONSE, fish);
			} else {
				EventDestination.getInstance().dispatchFailEvent(
						EventType.DAL_CREATE_FISH_RESPONSE);
			}
		} else {
			sendFishToClient(clientName, isSuccess, fish);
		}
	}
	
	private void sendFishToClient(String clientName, boolean isSuccess,
			IFish fish) {
		logger.debug("send fish to client: {}", clientName);
		logger.debug(fish == null ? "fish null" : "fish id: "
				+ fish.getFishId());
		Client client = HostMainController.getInstance().getClientManager()
				.getClient(clientName);
		if (client != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Field.COMMAND, Field.SEND_FISH);
			map.put(Field.FISH, fish);
			map.put(Field.SUCCESSFUL, isSuccess);
			client.send(map);
		} else {
			logger.error("Not found for clientName {}", clientName);
		}
	}

	@Override
	public void synchronization() {
		logger.debug("synchronization");
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFish(String clientName, Fish fish) {
		logger.info("remove fish success");
		EventDestination.getInstance().dispatchSuccessEvent(
				EventType.DAL_REMOVE_FISH);
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
	public void exit() {

	}
}
