package mod.gamenature.skullforge.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.texture.IconRegister;

public class itemHotBones extends Item {

	public itemHotBones(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMisc);
		
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
	this.itemIcon = ir.registerIcon("skullforge:HotBones");
	}


}
