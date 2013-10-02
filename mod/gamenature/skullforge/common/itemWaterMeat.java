package mod.gamenature.skullforge.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.client.renderer.texture.IconRegister;

public class itemWaterMeat extends ItemFood {

	public itemWaterMeat(int par1, int par2, boolean par3) {
		super(par1, par2, par3);
		// Not needed, because it automatically goes there for food items
		// this.setCreativeTab(CreativeTabs.tabFood);
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
	this.itemIcon = ir.registerIcon("skullforge:RehydratedMeat");
	}

}
