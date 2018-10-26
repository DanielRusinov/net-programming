package org.elsys.netprog.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class GamesBank {

	private List<Game> games = new ArrayList<>();
	
	public Game create() {
		Game game = new Game();
		games.add(game);
		return game;
	}
	
	public List<Game> getGames() {
		return games;
	}
	
	public Game getGameById(String str) {
		for(int i = 0; i < games.size(); i++) {
			Game game = games.get(i);
			if(game.getGameId().equals(str)) return game;
		}
		return null;
	}

	public JSONArray gamesAsJson() {
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();		
		
		for(int i = 0; i < games.size(); i++) {
			String temp = games.get(i).getSecret().toString();
			if(games.get(i).isSuccess() == false) temp = "****";
			
			jObject.put("gameId", games.get(i).getGameId());
			jObject.put("turnsCount", games.get(i).getTurnsCount());
			jObject.put("secret", temp);
			jObject.put("success", games.get(i).isSuccess());
			
			jArray.put(jObject);
		}		
		
		return jArray;
	}
}
