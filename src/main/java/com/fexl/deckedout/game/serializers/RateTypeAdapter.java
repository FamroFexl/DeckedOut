/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/

package com.fexl.deckedout.game.serializers;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.fexl.deckedout.game.random.Rate;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class RateTypeAdapter extends TypeAdapter<Rate<?>> {

	@Override
	public Rate<String> read(JsonReader reader) throws IOException {
		LinkedHashMap<String, Integer> rate = new LinkedHashMap<String, Integer>();
		
		reader.beginObject();
		
		while(reader.hasNext()) {
			rate.put(reader.nextName(), reader.nextInt());
		}
		
		reader.endObject();
		
		return new Rate<String>(rate);
	}

	@Override
	public void write(JsonWriter writer, Rate<?> rate) throws IOException {
		writer.beginObject();
		
		for(Entry<?, Integer> entry : rate.getRate().entrySet()) {
			writer.name((String) entry.getKey()).value(entry.getValue());
		}
		
		writer.endObject();
	}

}
