package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;


@Path("/game")
public class GameController {
	private static GamesBank bank = new GamesBank();
	
	@POST
	@Path("/startGame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response startGame() throws URISyntaxException{
		Game game = new Game();
		bank.getGames().add(game);
		return Response.created(new URI("/games")).status(201).entity(game.getGameId()).build();
	}
	
	@PUT
	@Path("/guess/{id}/{guess}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response guess(@PathParam("id") String gameId, @PathParam("guess") String guess) throws Exception{
		if(bank.getGameById(gameId) == null) return Response.status(404).build();
		else {
			Game game = bank.getGameById(gameId);
			try {
				game.checkBullsAndCows(Integer.parseInt(guess));
			} catch (Exception e) {
				return  Response.status(400).build();
			}
			
			return Response.status(200).entity(game.gameAsJson().toString()).build();
		}
	}
	
	@GET
	@Path("/games")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() {				
		return Response.status(200).entity(bank.gamesAsJson().toString()).build();
	}
}
