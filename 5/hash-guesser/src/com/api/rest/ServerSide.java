package com.api.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;
import java.util.Random;

@Path("/game")
public class ServerSide {
	
	private static int lenght = 3;
	private static String bytesToString;
	private static String hashCode;
	
	@POST
	@Path("/guessInput")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response guessInput(@FormParam("input") String input, @FormParam("hash") String hash) throws JSONException {
		
		if(input.equals(bytesToString) && hash.equals(hashCode)) {
			return Response.status(200).build();
		} else return Response.status(406).build();
	}
	
	@GET
	@Path("/getHash")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getHash() throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		byte[] generatedBytes = new byte[lenght];
		new Random().nextBytes(generatedBytes);
		bytesToString = Base64.getEncoder().encodeToString(generatedBytes);	
		
		md.update(generatedBytes);
		byte[] hashArray = md.digest();
		hashCode = DatatypeConverter.printHexBinary(hashArray).toString();
		
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("bytes lenght", generatedBytes.length);
		jsonObject.put("hash", hashCode);
		
		return Response.ok().entity(jsonObject.toString()).build();				
	}	
}
