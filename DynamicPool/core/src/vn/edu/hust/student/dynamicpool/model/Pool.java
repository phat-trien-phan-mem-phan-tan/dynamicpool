package vn.edu.hust.student.dynamicpool.model;


import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.bll.FishManager;
import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.bll.IFishManager;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPool;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPoolPosition;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.PoolPosition;

public class Pool implements IPool{


	private int id;
	private ArrayList<Segment> segmentList;
	private IPoolPosition position;
	private float diagonal;//duong cheo thiet bi
	
	private IFishManager fishManager;
	
	
	public Pool(IPoolPosition position,IFishManager fishManager, float diagonal){
		
		this.id = 0;
		this.position = position;
		this.fishManager = fishManager;
		this.diagonal=diagonal;
		
	}
	
	public Pool(IPoolPosition position,IFishManager fishManager,float diagnoal,int id){
		this.id = id;
		this.position = position;
		this.fishManager = fishManager;
		this.diagonal=diagnoal;
	}
	
	
	public Pool(){
		this(new PoolPosition(),new FishManager());
	}

	@Override
	public int getPoolId() {
		
		return id;
	}

	public void setPoolId(int id){
		this.id = id;
	}
	
	
	@Override
	public IPoolPosition getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public IFishManager getFishCollection() {
		// TODO Auto-generated method stub
		
		return fishManager;
	}

	@Override
	public ArrayList<Segment> getSegments() {
		
		return segmentList;
	}

	@Override
	public void setSegments(ArrayList<Segment> segments) {
		
		segmentList = segments;
	}

	@Override
	public IFish updatePosition(float detatime) {
		
		
		
		return null;
	}
	
	
	
	
	
	
}
