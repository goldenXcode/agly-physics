package com.agly.physics.domain;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

public class HighScores implements Serializable {

	private ArrayList<Score> highScores;

	int capacity;
	
	public HighScores() {
		capacity = 5;
		highScores = new ArrayList<Score>(capacity);

	}
	
	public void addScore(String name, int value) {
		
		for (int i=0; i < capacity; i++) {
			Score current = highScores.get(i);
			if (value > current.getValue()) {
				highScores.add(i, new Score(name, value));
				if (this.capacity > highScores.size()) {
					highScores.remove(highScores.size() - 1);
				}
				return;
			}
		}
		
	}
	
	public int getScorePosition(int value) {
		
		for (int i=0; i < capacity; i++) {
			Score current = highScores.get(i);
			if (value > current.getValue()) {
				return i;
			}
		}
		
		return -1;
		
	}

	@Override
	public void write(Json json) {
		
		for (int i = highScores.size(); i < capacity; i++) {
			highScores.add(new Score("unknown", 0));
			//highScores.add(2);
		}
		
		//json.writeValue("highScores", highScores);
		json.writeValue("highScores", highScores, ArrayList.class, Score.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		
		highScores = json.readValue("highScores", ArrayList.class, Score.class,
				jsonData);
		
		for (int i = highScores.size(); i < capacity; i++) {
			highScores.add(new Score("unknown", 0));
			//highScores.add(2);
		}
	}


	/**
	 * @return the highScores
	 */
	public ArrayList<Score> getHighScores() {
		return highScores;
	}

	/**
	 * @return the maxCapacity
	 */
	public int getCapacity() {
		return capacity;
	}

}
