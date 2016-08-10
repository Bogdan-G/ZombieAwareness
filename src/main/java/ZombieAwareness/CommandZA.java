package ZombieAwareness;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.DimensionManager;
import CoroUtil.OldUtil;
import CoroUtil.pathfinding.PFQueue;
import CoroUtil.util.CoroUtil;

public class CommandZA extends CommandBase {

	private int intVal_ex = 0;
	private int intVal_ex_temp = -1;
	
	@Override
	public String getCommandName() {
		return "za";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		
		try {
			if (var2.length < 1)
	        {
				//exception throws dont seem to always get sent to player, do it manually
				CoroUtil.sendPlayerMsg((EntityPlayerMP) var1, "Invalid usage, example: '/za set maxZombiesNight 100, /za get count <entityname>, /za kill <entityname>', see ZAMod.cfg for all possible set configurations");
	            throw new WrongUsageException("Invalid usage");
	        }
	        else
	        {
	        	
	        	if (var2[0].equalsIgnoreCase("set")) {
	        		if (var2[1].equalsIgnoreCase("PFQueue")) {
	        			PFQueue.instance = null;
	        		} else {
	        			int intVal = 0;
			        	try {
			        		intVal = Integer.parseInt(var2[2]);
			        	} catch (Exception ex2) { intVal_ex++; } //silence!
			        	boolean boolVal = Boolean.parseBoolean(var2[2]);
			        	
			        	if (intVal > 0) {
			        		OldUtil.setPrivateValueBoth(ZombieAwareness.class, ZombieAwareness.instance, var2[1], var2[1], intVal);
			        	} else {
			        		OldUtil.setPrivateValueBoth(ZombieAwareness.class, ZombieAwareness.instance, var2[1], var2[1], boolVal);
			        	}
			        	
			        	CoroUtil.sendPlayerMsg((EntityPlayerMP) var1, var2[1] + " now set to: " + OldUtil.getPrivateValueBoth(ZombieAwareness.class, ZombieAwareness.instance, var2[1], var2[1]));
	        		}
		        	
		        	
		        	//System.out.println("new setting for max zombies: " + ZombieAwareness.maxZombiesNight);
		        	
		        	//if (var2[0] == "maxZombiesNight") ZombieAwareness.maxZombiesNight = Integer.valueOf(var2[1]);
	        	} else if (var2[0].equalsIgnoreCase("get")) {
	        		if (var2[1].equalsIgnoreCase("time")) {
	        			if (ZombieAwareness.lastSpawnSysTime > 0) {
	        				CoroUtil.sendPlayerMsg((EntityPlayerMP) var1, "last ZA spawn: " + (int)((System.currentTimeMillis() - ZombieAwareness.lastSpawnSysTime) / 1000F) + "s");
	        			} else {
	        				CoroUtil.sendPlayerMsg((EntityPlayerMP) var1, "none yet");
	        			}
	        		} else if (var2[1].equalsIgnoreCase("counts")) {
	        			CoroUtil.sendPlayerMsg((EntityPlayerMP) var1, "surface: " + ZombieAwareness.lastMobsCountSurface + ", caves: " + ZombieAwareness.lastMobsCountCaves);
	        		} else {
	        			CoroUtil.sendPlayerMsg((EntityPlayerMP) var1, var2[1] + " set to: " + OldUtil.getPrivateValueBoth(ZombieAwareness.class, ZombieAwareness.instance, var2[1], var2[1]));
	        		}
	        	}
	        	
	        }
		} catch (Exception ex) {
			System.out.println("Caught ZA command crash!!!");
			ex.printStackTrace();
		}
		if (intVal_ex != 0 && intVal_ex_temp != intVal_ex) {intVal_ex_temp = intVal_ex;System.out.println("intVal_ex: "+intVal_ex);}
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return par1ICommandSender.canCommandSenderUseCommand(this.getRequiredPermissionLevel(), this.getCommandName());
    }

}
