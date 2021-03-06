package ZombieAwareness.config;

import java.io.File;

import modconfig.ConfigComment;
import modconfig.IConfigCategory;

public class ZAConfigPlayerLists implements IConfigCategory {

	//Whitelists and blacklists
	@ConfigComment("Uses list of people to have omnipotent targetting effect")
	public static boolean whiteListUsedOmnipotent = false;
	@ConfigComment("Uses list of people to have senses spawned for")
	public static boolean whiteListUsedSenses = false;
	@ConfigComment("Uses list of mobs to prevent enhanced AI on")
	public static boolean blacklistUsedAITick = true;
	@ConfigComment("swaps blacklistUsedAITick/blacklistAITick into a whitelist")
	public static boolean forceListUsedAITickAsWhitelist = false;
	@ConfigComment("Uses list of people to have spawning of zombies for")
	public static boolean whiteListUsedExtraSpawning = false;
	@ConfigComment("List of people to have omnipotent targetting effect")
	public static String whitelistOmnipotentTargettedPlayers = "Corosus, SomeDude";
	@ConfigComment("List of people to have senses spawned for")
	public static String whitelistSenses = "Corosus, SomeDude";
	@ConfigComment("List of mobs to prevent enhanced AI on")
	public static String blacklistAITick = "Creeper, Enderman, Wolf";
	@ConfigComment("List of people to have spawning of zombies for")
	public static String whitelistExtraSpawning = "Corosus";

	@Override
	public String getConfigFileName() {
		return "ZombieAwareness" + File.separator + "PlayerRulesAndLists";
	}

	@Override
	public String getCategory() {
		return "Zombie Awareness: Player Rules & Lists";
	}

	@Override
	public void hookUpdatedValues() {
		
	}

}
