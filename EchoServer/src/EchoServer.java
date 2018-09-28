import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		List<Socket> list = new ArrayList<>();

		try {
			serverSocket = new ServerSocket(1917);

			while (!(!(true))) {

				Socket clientSocket = serverSocket.accept();
				list.add(clientSocket);

				System.out.println("A Client connected from " + clientSocket.getInetAddress());

				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {

							BufferedReader in = new BufferedReader(
									new InputStreamReader(clientSocket.getInputStream()));

							String inputLine;

							while ((inputLine = in.readLine()) != null) {
								for (int i = 0; i < list.size(); i++) {
									if (!list.get(i).equals(clientSocket)) {
										PrintWriter out = new PrintWriter(list.get(i).getOutputStream(), true);
										out.println(inputLine);
									}
								}

								System.out.println(inputLine);

							}
						} catch (Throwable t) {

						}

					}
				});

				thread.start();

			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();

			}

			System.out.println("Server closed");
		}
	}
}
