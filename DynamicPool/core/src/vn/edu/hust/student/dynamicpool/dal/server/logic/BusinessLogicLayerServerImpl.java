package vn.edu.hust.student.dynamicpool.dal.server.logic;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.implementors.BaseEventListener;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayerImpl;
import vn.edu.hust.student.dynamicpool.dal.HostDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;
import vn.edu.hust.student.dynamicpool.events.RegisterClientEvent;
import vn.edu.hust.student.dynamicpool.exception.BLLException;
import vn.edu.hust.student.dynamicpool.exception.DALException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.model.Segment;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;
import vn.edu.hust.student.dynamicpool.presentation.PresentationVoidCallback;

public class BusinessLogicLayerServerImpl extends BusinessLogicLayerImpl {
	private PoolManager poolManager = new PoolManager();
	private Logger logger = LoggerFactory
			.getLogger(BusinessLogicLayerServerImpl.class);
	private PresentationVoidCallback haveNewClientCallback;

	public BusinessLogicLayerServerImpl(
			PresentationVoidCallback haveNewClientCallback) {
		this.dataAccessLayer = new HostDataAccessLayerImpl();
		this.dataAccessLayer.registerEvent(dataAccessLayer);
		this.dataAccessLayer.addEventListener(AppConst.REGISTER_EVENT_NAME,
				new BaseEventListener(this, "onRegisterClientEventCallback"));
		this.haveNewClientCallback = haveNewClientCallback;
	}

	@Override
	public void createHost(PresentationBooleanCallback callback) {
		final PresentationBooleanCallback booleanCallback = callback;
		this.dataAccessLayer.createHost(new BusinessLogicDataCallback() {
			@Override
			public void callback(Object data, DALException ex) {
				createHostCallbackHander(booleanCallback, data, ex);
			}
		});
	}

	private void createHostCallbackHander(
			final PresentationBooleanCallback booleanCallback, Object data,
			DALException ex) {
		if (ex == null) {
			try {
				String key = (String) data;
				if (key != null && key != "") {
					this.saveKey(key);
					booleanCallback.callback(true, null);
				}
			} catch (Exception e) {
				booleanCallback.callback(false, new BLLException(
						"Cannot Create Host", e));
			}
		} else {
			booleanCallback.callback(false, new BLLException(
					"Cannot ceate host", ex));
		}
	}

	@Deprecated
	private void onRegisterClientEventCallback(Event e) {
		System.out.println("BusinessLogicLayerServer: event");
		try {
			RegisterClientEvent clientEvent = (RegisterClientEvent) e;
			DeviceInfo deviceInfo = (DeviceInfo) clientEvent.getDiviceInfo();
			String clientName = clientEvent.getClientName();
			addClientAndShowSetting(clientName, deviceInfo);
		} catch (Exception error) {
			logger.error("BusinessLogicLayerServer: Cannot cast to device info");
		}

	}

	private void addClientAndShowSetting(String clientName,
			DeviceInfo deviceInfo) {
		PoolServer pool = new PoolServer(clientName, deviceInfo);
		poolManager.add(pool);
		haveNewClientCallback.callback();
	}

	@Override
	public void addDevide(DeviceInfo deviceInfo,
			PresentationBooleanCallback callback) {
		PoolServer poolServer = new PoolServer(AppConst.DEFAULT_HOST_NAME,
				deviceInfo);
		this.poolManager.add(poolServer);
		super.addDeviceCallback(new PresentationBooleanCallback() {
			@Override
			public void callback(boolean isSuccess, Exception error) {

			}
		}, new ArrayList<Segment>(), null);
		callback.callback(true, null);
	}
	
}
