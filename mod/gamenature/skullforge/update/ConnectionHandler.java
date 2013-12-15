package mod.gamenature.skullforge.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import mod.gamenature.skullforge.common.SkullForge;
import net.minecraft.client.Minecraft;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class ConnectionHandler implements IConnectionHandler {

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
		try {
			if (UpdateCheck.isUpdateAvailable()) {
				netHandler.getPlayer().sendChatToPlayer(ChatMessageComponent.createFromText("["+EnumChatFormatting.GOLD +"SkullForge"+EnumChatFormatting.RESET+"] An Update is available for this mod. Check http://goo.gl/ya0VpP for more info. (Your Version: "+EnumChatFormatting.AQUA+SkullForge.version+EnumChatFormatting.RESET+")"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			netHandler.getPlayer().sendChatToPlayer(ChatMessageComponent.createFromText("["+EnumChatFormatting.GOLD +"SkullForge"+EnumChatFormatting.RESET+"]"+EnumChatFormatting.RED+" Unable to contact the Update Server!"));
		}
		
		
		//Uncomment for testing purposes only kthxbye :D
		//netHandler.getPlayer().sendChatToPlayer(ChatMessageComponent.createFromText("["+EnumChatFormatting.GOLD +"SkullForge"+EnumChatFormatting.RESET+"] Version "+EnumChatFormatting.AQUA+SkullForge.version+EnumChatFormatting.RESET+" loaded!"));
	}
	


	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {

	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {

	}

	@Override
	public void connectionClosed(INetworkManager manager) {

	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {

	}

}
