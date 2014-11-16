import org.puppet.client.http.HttpClientController;

public class Main {
	public static void main(String[] argv) {
		HttpClientController httpClient = new HttpClientController();
		String s = httpClient.authentication(1234);
		System.out.println(s);
	}
}
