import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {
	public static void main(String[] args) throws IOException {

		Socket echoSocket = null;

		try {
			echoSocket = new Socket("localhost", 1917);
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String userInput;
						while ((userInput = stdIn.readLine()) != null) {
							out.println(userInput);
						}
					} catch (Throwable t) {

					}

				}
			});
			thread.start();
			
			String serverInput;
		    while ((serverInput = in.readLine()) != null) {
		        System.out.println(serverInput);
		    }

		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (echoSocket != null && !echoSocket.isClosed()) {
				echoSocket.close();
			}
		}
	}
}