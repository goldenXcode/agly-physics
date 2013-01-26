package com.agly.physics.domain;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

public class Score implements Serializable {
	
	public String name;
	public Integer value;
	
	public Score(String name, Integer value) {
		super();
		this.name = name;
		this.value = value;
	}

	public Score() {
		super();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public void write(Json json) {
		json.writeType(Score.class);
		json.writeValue("name", name, String.class);
		json.writeValue("value", value, Integer.class);
		
		//json.writeValue(name, value);
		

		
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		name = json.readValue( "name", String.class, jsonData );
		value = json.readValue( "value", Integer.class, jsonData );
	}

}