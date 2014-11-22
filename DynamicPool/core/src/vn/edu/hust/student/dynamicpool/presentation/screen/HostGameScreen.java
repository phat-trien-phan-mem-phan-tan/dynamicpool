package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.WorldController;
import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;

public class HostGameScreen extends GameScreen {

	protected HostGameScreen(WorldRenderer worldRenderer,
			WorldController worldController) {
		super(worldRenderer, worldController);
	}
	
	@Override
	protected void createInputprocessors() {
		this.defaultInputProcessor = new HostGSDefaultInputProcessor(
				worldController);
		this.selectFishInputProcessor = new HostGSSelectFishInputProcessor(
				worldController);
		this.selectTrajectoryInputProcessor = new HostGSSelectTranjectoryInputPorcessor(
				worldController);
	}
	
}
