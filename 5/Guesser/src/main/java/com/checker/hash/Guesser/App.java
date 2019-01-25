package com.checker.hash.Guesser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class App {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		while (true) {

			System.out.println("Starting new game!");

			ClientConfig clientConfig = new DefaultClientConfig();

			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource("http://localhost:8080/hash-guesser/game/getHash");

			Builder builder = webResource.accept(MediaType.APPLICATION_JSON);

			ClientResponse response = builder.get(ClientResponse.class);

			if (response.getStatus() == 200) {
				JSONObject jsonObject = new JSONObject(response.getEntity(String.class));

				int bytesLenght = jsonObject.getInt("bytes lenght");
				String receivedHashCode = jsonObject.getString("hash");

				MessageDigest md = MessageDigest.getInstance("MD5");

				int i = 0;
				
				long time = System.currentTimeMillis();
				
				while (true) {
					
					byte[] generatedBytes = new byte[bytesLenght];
					new Random().nextBytes(generatedBytes);
					String bytesToString = Base64.getEncoder().encodeToString(generatedBytes);

					md.update(generatedBytes);
					byte[] hashArray = md.digest();
					String hashCode = DatatypeConverter.printHexBinary(hashArray).toString();

					jsonObject = new JSONObject();
					
					if (hashCode.equals(receivedHashCode)) {
						webResource = client.resource(
								"http://localhost:8080/hash-guesser/game/guessInput");

						jsonObject.put("input", bytesToString);
						jsonObject.put("hash", hashCode);
						
						response = builder.post(ClientResponse.class, jsonObject.toString());
						
						if (response.getStatus() == 406) {
							System.out.println("Your guess was wrong!");
						} else {
							System.out.println("\nYou guessed right " + bytesLenght + " byte long array's hash!");
							System.out.println("It took you only: " + (i + 1) + " tries!");
							System.out.println("And time of " + (System.currentTimeMillis() - time) + " milliseconds!");			
							break;
						}
					} else {
						System.out.println("Does not equal :( Try again");
					}
					
					i++;
				}
				
				break; //<--- comment if you don't want to break the loop
				
			} else
				System.out.println("Oopsie, smth went wrong!");
		}
	}
}
