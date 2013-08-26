package mod.gamenature.skullforge.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundManager {
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onSoundsLoaded(SoundLoadEvent event)
	{
		
		//Sounds
		event.manager.addSound("skullforge:paint.ogg");
		event.manager.addSound("skullforge:remove.ogg");
		
		//Print Load Message to Console.
		System.out.println("[SkullForge] Loading Sound Manager...");
	}
}