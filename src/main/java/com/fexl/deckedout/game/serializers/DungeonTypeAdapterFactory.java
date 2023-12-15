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

import com.fexl.deckedout.game.dungeon.Dungeon;
import com.fexl.deckedout.game.zones.DOZone;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class DungeonTypeAdapterFactory implements TypeAdapterFactory {

	@Override
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
		final Class<? super T> clazz = typeToken.getRawType();
		if(Dungeon.class.isAssignableFrom(clazz)) {
			return DungeonAdapter.get(gson, typeToken);
		}
		return null;
	}
	
	public static final class DungeonAdapter extends TypeAdapter<Dungeon> {

		private final Gson gson;
		private final TypeToken<?> typeToken;
		
		private DungeonAdapter(Gson gson, TypeToken<?> typeToken) {
			this.gson = gson;
			this.typeToken = typeToken;
		}
		
		@SuppressWarnings("unchecked")
		private static <T> TypeAdapter<T> get(Gson gson, TypeToken<?> typeToken) {
            // Wrap TypeAdapter in nullSafe so we don't need to do null checks
            return (TypeAdapter<T>) new DungeonAdapter(gson, typeToken).nullSafe();
        }
		
		@Override
		public Dungeon read(JsonReader reader) throws IOException {
			int maxClank = 0;
			int maxClankBlock = 0;
			int maxHazardBlock = 0;
			String emberItem = "changeMe";
			
			LinkedHashMap<Integer, Integer> maxTreasure = new LinkedHashMap<Integer, Integer>();
			LinkedHashMap<Integer, Integer> maxEmbers = new LinkedHashMap<Integer, Integer>();
			
			ArrayList<DOZone> clankZones = new ArrayList<DOZone>();
			ArrayList<DOZone> treasureZones = new ArrayList<DOZone>();
			ArrayList<DOZone> artifactZones = new ArrayList<DOZone>();
			ArrayList<DOZone> mobZones = new ArrayList<DOZone>();
			
			reader.beginObject();
			
			while(reader.hasNext()) {
				String name = reader.nextName();
				if(name.equals("maxClank") && reader.peek() == JsonToken.NUMBER) {
					maxClank = reader.nextInt();
				}
				else if(name.equals("maxClankBlock") && reader.peek() == JsonToken.NUMBER) {
					maxClankBlock = reader.nextInt();
				}
				else if(name.equals("maxHazardBlock") && reader.peek() == JsonToken.NUMBER) {
					maxHazardBlock = reader.nextInt();
				}
				else if(name.equals("frostEmberItem") && reader.peek() == JsonToken.STRING) {
					emberItem = reader.nextString();
				}
				else if(name.equals("maxTreasure") && reader.peek() == JsonToken.BEGIN_OBJECT) {
					reader.beginObject();
					
					while(true) { 
						if(reader.peek() != JsonToken.END_OBJECT) {
							maxTreasure.put(Integer.parseInt(reader.nextName()), reader.nextInt());
						}
						else {
							break;
						}
					}
					
					reader.endObject();
				}
				else if(name.equals("maxEmbers") && reader.peek() == JsonToken.BEGIN_OBJECT) {
					reader.beginObject();
					
					while(true) { 
						if(reader.peek() != JsonToken.END_OBJECT) {
							maxEmbers.put(Integer.parseInt(reader.nextName()), reader.nextInt());
						}
						else {
							break;
						}
					}
					
					reader.endObject();
				}
				else if(name.equals("zones") && reader.peek() == JsonToken.BEGIN_OBJECT) {
					reader.beginObject();
					
					while(reader.hasNext()) {
						String zoneType = reader.nextName();
						if(zoneType.equals("clankZones") && reader.peek() == JsonToken.BEGIN_OBJECT) {
							reader.beginObject();
								while(reader.peek() == JsonToken.NAME) {
									String zoneName = reader.nextName();
									if(reader.peek() == JsonToken.BEGIN_OBJECT) {
										DOZone doZone = gson.fromJson(reader, DOZone.class);
										clankZones.add(doZone);
									}
								}
							reader.endObject();
						}
						else if(zoneType.equals("treasureZones") && reader.peek() == JsonToken.BEGIN_OBJECT) {
							reader.beginObject();
								while(reader.peek() == JsonToken.NAME) {
									String zoneName = reader.nextName();
									if(reader.peek() == JsonToken.BEGIN_OBJECT) {
										DOZone doZone = gson.fromJson(reader, DOZone.class);
										treasureZones.add(doZone);
									}
								}
							reader.endObject();
						}
						else if(zoneType.equals("artifactZones") && reader.peek() == JsonToken.BEGIN_OBJECT) {
							reader.beginObject();
								while(reader.peek() == JsonToken.NAME) {
									String zoneName = reader.nextName();
									if(reader.peek() == JsonToken.BEGIN_OBJECT) {
										DOZone doZone = gson.fromJson(reader, DOZone.class);
										artifactZones.add(doZone);
									}
								}
							reader.endObject();
						}
						else if(zoneType.equals("mobZones") && reader.peek() == JsonToken.BEGIN_OBJECT) {
							reader.beginObject();
								while(reader.peek() == JsonToken.NAME) {
									String zoneName = reader.nextName();
									if(reader.peek() == JsonToken.BEGIN_OBJECT) {
										DOZone doZone = gson.fromJson(reader, DOZone.class);
										mobZones.add(doZone);
									}
								}
							reader.endObject();
						}
					}
					reader.endObject();
				}
			}
			
			Dungeon dungeon = new Dungeon();
			dungeon.setMaxClank(maxClank);
			dungeon.setMaxClankBlock(maxClankBlock);
			dungeon.setMaxHazardBlock(maxHazardBlock);
			dungeon.frostEmberItem = emberItem;
			
			dungeon.maxTreasure = maxTreasure;
			dungeon.maxEmbers = maxEmbers;
			
			dungeon.clankZones = clankZones;
			dungeon.treasureZones = treasureZones;
			dungeon.artifactZones = artifactZones;
			dungeon.mobZones = mobZones;
			
			return dungeon;
		}

		@Override
		public void write(JsonWriter writer, Dungeon dungeon) throws IOException {
			writer.beginObject();
			
				writer.name("maxClank").value(dungeon.getMaxClank());
				writer.name("maxClankBlock").value(dungeon.getMaxClankBlock());
				writer.name("maxHazardBlock").value(dungeon.getMaxHazardBlock());
				writer.name("frostEmberItem").value("changeMe");
		
				writer.name("maxTreasure").beginObject();
				for(Entry<Integer, Integer> entry : dungeon.maxTreasure.entrySet()) {
					writer.name(entry.getKey().toString()).value(entry.getValue());
				}
				writer.endObject();
					
				writer.name("maxEmbers").beginObject();
					for(Entry<Integer, Integer> entry : dungeon.maxEmbers.entrySet()) {
						writer.name(entry.getKey().toString()).value(entry.getValue());
					}
				writer.endObject();
					
				writer.name("zones").beginObject();
				
					writer.name("clankZones").beginObject();
						for(DOZone entry : dungeon.clankZones) {
							writer.name(entry.getName());
							gson.getAdapter(DOZone.class).write(writer, entry);
						}
					writer.endObject();
					
					writer.name("treasureZones").beginObject();
						for(DOZone entry : dungeon.treasureZones) {
							writer.name(entry.getName());
							gson.getAdapter(DOZone.class).write(writer, entry);
						}
					writer.endObject();
					
					writer.name("artifactZones").beginObject();
						for(DOZone entry : dungeon.artifactZones) {
							writer.name(entry.getName());
							gson.getAdapter(DOZone.class).write(writer, entry);
						}
					writer.endObject();
					
					writer.name("mobZones").beginObject();
						for(DOZone entry : dungeon.mobZones) {
							writer.name(entry.getName());
							gson.getAdapter(DOZone.class).write(writer, entry);
						}
					writer.endObject();
				
				writer.endObject();
			writer.endObject();
			
		}
		
	}

}
