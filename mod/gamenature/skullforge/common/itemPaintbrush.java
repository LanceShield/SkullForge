package mod.gamenature.skullforge.common;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class itemPaintbrush extends Item {

	public itemPaintbrush(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setMaxStackSize(1);
		this.setMaxDamage(9);
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
	this.itemIcon = ir.registerIcon("skullforge:pb");
	}
	@Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            // If we can't edit this block for some reason, get out
            return false;
        } 
        else 
        {
	        int blockid;
	        // See what the block's ID is
	        blockid = par3World.getBlockId(par4, par5, par6);
	
	        if (blockid == Block.skull.blockID) {
	        	// Do something if it's a skull
	        	// Get the tileentity data
                TileEntity skullentity = par3World.getBlockTileEntity(par4, par5, par6);

                // Check to make sure a tileentity exists
                if (skullentity != null && skullentity instanceof TileEntitySkull)
                {
                    // Get the names
                	String newname = par2EntityPlayer.getEntityName();
                    String currentname = ((TileEntitySkull)skullentity).getExtraType();
                    
                    // Check to see if it's currently not named
                    if (((TileEntitySkull)skullentity).getSkullType() == 3 && currentname == "" && newname != "") { 
	                    ((TileEntitySkull)skullentity).setSkullType(3, newname); // Line that updates the head's NBT
	                    par3World.notifyBlockChange(par4, par5, par6, 0);  // Not sure what the final argument here does
	                    par1ItemStack.damageItem(1, par2EntityPlayer); // Damage the paintbrush
	                    
	                    // Now I need to send a packet to clients, saying "Oi, go update!"
	                    if (!par3World.isRemote) {

	                    	// Construct the packet
		                    NBTTagCompound tag = new NBTTagCompound();
		                    skullentity.writeToNBT(tag);
	                    	Packet132TileEntityData packet = new Packet132TileEntityData(par4, par5, par6, 4, tag); // 4 is the magic number for the action_type for updating heads
		                    
		                    // Now go and send it to everyone in the world who is watching the chunk
					        int loopPlayers;
					        EntityPlayer playerinloop;
					        // EntityPlayerMP versions of the instances
					        EntityPlayerMP p1;
					        EntityPlayerMP p2;
					        
					        if (par2EntityPlayer instanceof EntityPlayerMP) {
					        	p1 = (EntityPlayerMP)par2EntityPlayer;
						        for (loopPlayers = 0; loopPlayers < par3World.playerEntities.size(); ++loopPlayers)
						        {
						            playerinloop = (EntityPlayer)par3World.playerEntities.get(loopPlayers);
						            if (playerinloop instanceof EntityPlayerMP) {
						            	p2 = (EntityPlayerMP)playerinloop;
						            	// Check to see if the two players are in the same dimension
						            	if (p1.worldObj == p2.worldObj) {
						            		// This currently just sends out the update to everybody on the same dimension
						                    PacketDispatcher.sendPacketToPlayer(packet, (Player)p2);

									        int var3;
									        int var4;
								            var3 = MathHelper.abs_int(MathHelper.floor_double((p2.posX - par4) / 16.0D));
								            var4 = MathHelper.abs_int(MathHelper.floor_double((p2.posZ - par6) / 16.0D));
								            if (var3 <= 32 && var4 <= 32) { // should work up to extreme render distance

								            }

						            	}
						            }
						        }		                    
					        }

	                    }
	                    
	                    return true;
                    }
                }
	        }
        }
		return false;
    }	

}
