package com.fexl.deckedout.game.cards;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;

public class Cards {
	//Register cards
	private Card.CardBuilder builder;
	
	//Create custom card registry
	public static ResourceKey<Registry<Card>> cardsKey = ResourceKey.createRegistryKey(new ResourceLocation("deckedout", "cards"));
	public Registry<Card> CARDS = FabricRegistryBuilder.createSimple(cardsKey).attribute(RegistryAttribute.MODDED).buildAndRegister();
	
	//Register cards
	public Card register(String string, Card card) {
		return Registry.register(CARDS, string, card);
	}
	
	public Cards(Card.CardBuilder builder) {
		this.builder = builder;
	}
	
	//Stumble card (special)
	Card stumble = this.register("stumble", new BasicCard(this.builder).setClank(2).setRarity(Card.Rarity.SPECIAL).setMaxCount(-1).setEthereal()); //Stumble has no max count
	
	//Shop cards (special)
	Card payToWin = this.register("pay_to_win", new BasicCard(this.builder).setEmbers(10).setRarity(Card.Rarity.SPECIAL).setMaxCount(3).setEthereal());
	Card tacticalApproach = this.register("tactical_approach", new BasicCard(this.builder).setClank(4).setTreasure(4).setRarity(Card.Rarity.SPECIAL).setMaxCount(1).setEthereal().setPreGame());
	Card porkchopPower = this.register("porkchop_power", new PorkchopPower(this.builder));
	
	//Common cards
	Card sneak = this.register("sneak", new BasicCard(this.builder).setClankBlock(2).setRarity(Card.Rarity.COMMON).setMaxCount(3));
	Card stability = this.register("stability", new BasicCard(this.builder).setHazard(2).setRarity(Card.Rarity.COMMON).setMaxCount(5));
	Card treasureHunter = this.register("treasure_hunter", new BasicCard(this.builder).setTreasure(4).setRarity(Card.Rarity.COMMON).setMaxCount(5));
	Card emberSeeker = this.register("ember_seeker", new BasicCard(this.builder).setEmbers(2).setRarity(Card.Rarity.COMMON).setMaxCount(5));
	Card momentOfClarity = this.register("moment_of_clarity", new BasicCard(this.builder).setClankBlock(2).setHazardBlock(2).setTreasure(4).setEmbers(2).setRarity(Card.Rarity.COMMON).setMaxCount(5).setEthereal());
	
	//Uncommon cards
	Card evasion = this.register("evasion", new BasicCard(this.builder).setClankBlock(4).setRarity(Card.Rarity.UNCOMMON).setMaxCount(3));
	Card treadLightly = this.register("tread_lightly", new BasicCard(this.builder).setHazardBlock(4).setRarity(Card.Rarity.UNCOMMON).setMaxCount(3));
	Card frostFocus = this.register("frost_focus", new BasicCard(this.builder).setEmbers(4).setRarity(Card.Rarity.UNCOMMON).setMaxCount(3));
	Card lootAndScoot = this.register("loot_and_scoot", new LootAndScoot(this.builder));
	Card secondWind = this.register("second_wind", new SecondWind(this.builder));
	Card beastSense = this.register("beast_sense", new BeastSense(this.builder));
	Card boundingStrides = this.register("bounding_strides", new BoundingStrides(this.builder));
	Card recklessCharge = this.register("reckless_charge", new RecklessCharge(this.builder));
	Card sprint = this.register("sprint", new Sprint(this.builder));
	Card nimbleLooting = this.register("nimble_looting", new NimbleLooting(this.builder));
	Card smashAndGrab = this.register("smash_and_grab", new BasicCard(this.builder).setClank(2).setTreasure(13).setRarity(Card.Rarity.UNCOMMON).setMaxCount(3));
	Card quickstep = this.register("quickstep", new Quickstep(this.builder));
	Card suitUp = this.register("suit_up", new SuitUp(this.builder));
	Card adrenalineRush = this.register("adrenaline_rush", new AdrenalineRush(this.builder));
	
	//Rare cards
	Card eerieSilence = this.register("eerie_silence", new EerieSilence(this.builder));
	Card dungeonRepairs = this.register("dungeon_repairs", new BasicCard(this.builder).setClank(1).setHazardBlock(7).setRarity(Card.Rarity.RARE).setMaxCount(3));
	Card swagger = this.register("swagger", new Swagger(this.builder));
	Card chillStep = this.register("chill_step", new ChillStep(this.builder));
	Card speedRunner = this.register("speed_runner", new SpeedRunner(this.builder));
	Card eyesOnThePrize = this.register("eyes_on_the_prize", new EyesOnThePrize(this.builder));
	Card haste = this.register("haste", new Haste(this.builder));
	Card coldSnap = this.register("cold_snap", new ColdSnap(this.builder));
	Card silentRunner = this.register("silent_runner", new SilentRunner(this.builder));
	Card fuzzyBunnySlippers = this.register("fuzzy_bunny_slippers", new FuzzyBunnySlippers(this.builder));
	Card deepfrost = this.register("deepfrost", new Deepfrost(this.builder));
	Card brilliance = this.register("brilliance", new Brilliance(this.builder));
}
