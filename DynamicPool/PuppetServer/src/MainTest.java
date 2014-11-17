import org.puppet.client.socket.SocketClientController;
import org.puppet.client.socket.SocketClientHandler;

public class MainTest implements SocketClientHandler {

	private SocketClientController socketController;
	
	public MainTest(){
		socketController = new SocketClientController(this);
	}
	
	public static void main(String[] args) {
		MainTest main = new MainTest();
		main.start();
	}
	
	public void start(){
		socketController.start("127.0.0.1", 2225);
	}

	@Override
	public void messageReceive(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);
	}
}
