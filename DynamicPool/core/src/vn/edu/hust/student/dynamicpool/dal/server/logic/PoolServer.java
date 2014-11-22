package vn.edu.hust.student.dynamicpool.dal.server.logic;

import java.util.ArrayList;
import java.util.List;
import vn.edu.hust.student.dynamicpool.model.*;

public class PoolServer extends Pool implements IPoolServer  {
	private String clientName;
	private DeviceInfo deviceInfo;
	private List<Segment> segments = new ArrayList<>();
	
	public PoolServer(String clientName, DeviceInfo device){
		this.setClientName(clientName);
		setDeviceInfo(device);
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
		this.setPosition(PoolConverter.convertDeviceInfoToRect(deviceInfo));
	}

	@Override
	public List<Segment> getSegments() {
		return segments;
	}
	
	@Override
	public String getClientName() {
		return this.clientName;
		
	}

	@Override
	public void setClientName(String clientName) {
		this.clientName=clientName;
		
	}

//	@Override
//	public ArrayList<IFish> updatePosition(float detatime) {
//		ArrayList<IFish> fishes = (ArrayList<IFish>) fishManager.getFishs();
//		Rectangle poolPosition = position.getPosition();
//
//		for (int i = 0; i < fishes.size(); i++) {
//
//			Fish fish = (Fish) fishes.get(i);
//			
//			fish.update(detatime);
//			if (fish.getFishState() == FishState.HIT_X) {
//
//				if (fish.getPoint().getY() == poolPosition.getMinY()
//						|| fish.getPoint().getY() == poolPosition.getMaxY()) {
//
//					fish.getTrajectory().setDirection(Oxy.ox);
//				}
//
//			} else if (fish.getFishState() == FishState.HIT_Y) {
//				if (fish.getPoint().getX() == poolPosition.getMinX()
//						|| fish.getPoint().getX() == poolPosition.getMaxX()) {
//
//					fish.getTrajectory().setDirection(Oxy.oy);
//				}
//			} else if (fish.getFishState() == FishState.NOT_PASS) {
//
//				// xu ly phan cho ca chuyen dong tren vung co the di qua
//			}
//
//			FishPosition nextPosition = (FishPosition) fish
//					.checkPosition(detatime + AppConst.timePass);
//
//			fish.setFishState(this, position);
//
//		}
//
//		return fishes;
//
//	}
	
}
