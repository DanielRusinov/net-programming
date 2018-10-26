package org.elsys.netprog.rest;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONObject;

public class Game {

	private String gameId;
	private int cows = 0;
	private int bulls = 0;
	private int turnsCount = 0;
	private Integer secret;
	private boolean success = false;

	public Game() {
		gameId = UUID.randomUUID().toString();
		while (hasDuplicates(secret = ThreadLocalRandom.current().nextInt(1000, 10000)));
	}

	public static boolean hasDuplicates(Integer num) {
		if (num < 1000 || num >= 10000)
			return true;

		String str = num.toString();

		for (int i = 0; i < str.length() - 1; i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (str.substring(i, i + 1).equals(str.substring(j, j + 1)))
					return true;
			}
		}
		return false;
	}
	
	public void checkBullsAndCows(Integer guess) throws Exception {
		if(hasDuplicates(guess)) throw new Exception();
		
		bulls = 0;
		cows = 0;
		
		turnsCount++;

		String guessStr = guess.toString();
		String secretStr = secret.toString();
		
		for(int i = 0; i < secretStr.length(); i++) {
			if(secretStr.substring(i, i + 1).equals(guessStr.substring(i, i + 1))) bulls++;
			else if(secretStr.contains(guessStr.substring(i, i + 1))) cows++;
			
			if(this.getBulls() == 4) {
				cows = 0;
				this.setSuccess(true);
			}
		}
	}

	public JSONObject gameAsJson() {
		JSONObject jObject = new JSONObject();		
			
		jObject.put("gameId", this.getGameId());
		jObject.put("cowsNumber", this.getCows());
		jObject.put("bullsNumber", this.getBulls());
		jObject.put("turnsCount", this.getTurnsCount());
		jObject.put("success", this.isSuccess());
		
		return jObject;
	}
	
	public int getCows() {
		return cows;
	}

	public void setCows(int cows) {
		this.cows = cows;
	}

	public int getBulls() {
		return bulls;
	}

	public void setBulls(int bulls) {
		this.bulls = bulls;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getTurnsCount() {
		return turnsCount;
	}

	public void setTurnsCount(int turnsCount) {
		this.turnsCount = turnsCount;
	}

	public Integer getSecret() {
		return secret;
	}

	public void setSecret(Integer secret) {
		this.secret = secret;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}