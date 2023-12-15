package com.fexl.deckedout.game.serializers;

import java.io.IOException;

import com.fexl.deckedout.game.Deck;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class DeckTypeAdapter extends TypeAdapter<Deck>{

	@Override
	public Deck read(JsonReader reader) throws IOException {
		return null;
	}

	@Override
	public void write(JsonWriter writer, Deck deck) throws IOException {
		
		
	}

}
