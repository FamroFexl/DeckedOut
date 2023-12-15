/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/

package com.fexl.deckedout.game.serializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.fexl.deckedout.game.random.Rate;
import com.fexl.deckedout.game.zones.DOZone;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import net.minecraft.core.BlockPos;

public class DOZoneTypeAdapterFactory implements TypeAdapterFactory {
	
	@Override
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
		
		if(DOZone.class.isAssignableFrom(typeToken.getRawType())) {
			return DOZoneAdapter.get(gson, typeToken);
		}
		return null;
		
	}
	
	public static final class DOZoneAdapter extends TypeAdapter<DOZone> {
		private final Gson gson;
		private final TypeToken<?> typeToken;
		
		private DOZoneAdapter(Gson gson, TypeToken<?> typeToken) {
			this.gson = gson;
			this.typeToken = typeToken;
		}
		
		@SuppressWarnings("unchecked")
		private static <T> TypeAdapter<T> get(Gson gson, TypeToken<?> typeToken) {
            // Wrap TypeAdapter in nullSafe so we don't need to do null checks
            return (TypeAdapter<T>) new DOZoneAdapter(gson, typeToken).nullSafe();
        }
		
		@Override
		public DOZone read(JsonReader reader) throws IOException {
			int level = 0;
			List<Integer> pos1 = new ArrayList<Integer>();
			List<Integer> pos2 = new ArrayList<Integer>();
			Rate<String> zoneRate = null;
			
			reader.beginObject();
			
			while(reader.hasNext()) {
				String name = reader.nextName();
				if(name.equals("level") && reader.peek() == JsonToken.NUMBER) {
					level = reader.nextInt();
					System.out.println("Level: " + level);
				}
				else if(name.equals("pos1") && reader.peek() == JsonToken.BEGIN_ARRAY) {
					reader.beginArray();
					for(int i = 0; i < 3; i++) {
						pos1.add(reader.nextInt());
					}
					reader.endArray();
				}
				else if(name.equals("pos2") && reader.peek() == JsonToken.BEGIN_ARRAY) {
					reader.beginArray();
					for(int i = 0; i < 3; i++) {
						pos2.add(reader.nextInt());
					}
					reader.endArray();
				}
				else if(name.equals("rate") && reader.peek() == JsonToken.BEGIN_OBJECT) {
					zoneRate = gson.fromJson(reader, Rate.class);
					for(Entry<String, Integer> entry : zoneRate.getRate().entrySet()) {
						System.out.println(entry);
					}	
				}
				else {
					reader.skipValue();
				}
			}
			
			reader.endObject();
			//reader.close();
			
			BlockPos blockPos1 = new BlockPos(pos1.get(0), pos1.get(1), pos1.get(2));
			BlockPos blockPos2 = new BlockPos(pos2.get(0), pos2.get(1), pos2.get(2));
			
			DOZone zone = new DOZone(blockPos1, blockPos2);
			zone.setLevel(level);
			zone.setRate(zoneRate);
			
			System.out.println("Level Zone: " + zone.getLevel());
			
			return zone;
		}

		@Override
		public void write(JsonWriter writer, DOZone doZone) throws IOException {
			writer.beginObject();
			
			writer.name("level").value(doZone.getLevel());
			
			writer.name("pos1");
			writer.beginArray();
				writer.value(doZone.zone.get(0).getX());
				writer.value(doZone.zone.get(0).getY());
				writer.value(doZone.zone.get(0).getZ());
			writer.endArray();
			
			writer.name("pos2");
			writer.beginArray();
				writer.value(doZone.zone.get(1).getX());
				writer.value(doZone.zone.get(1).getY());
				writer.value(doZone.zone.get(1).getZ());
			writer.endArray();
			
			writer.name("rate");
			gson.getAdapter(Rate.class).write(writer, doZone.rate);
			
			writer.endObject();
		}
		
	}

}
