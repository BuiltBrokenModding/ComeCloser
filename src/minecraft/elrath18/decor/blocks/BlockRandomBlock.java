package elrath18.decor.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import elrath18.decor.IllustriousElements;

public class BlockRandomBlock extends Block 
	{	
		public BlockRandomBlock(int par1) 
		{
			super (par1, Material.rock);
			this.setCreativeTab(CreativeTabs.tabDecorations);
			this.setHardness(2f);
			this.setResistance(2f);
			this.setBlockName("rb");
			this.setStepSound(soundStoneFootstep);
			this.setTextureFile(IllustriousElements.BLOCK_TEXTURE_FILE);
			this.blockIndexInTexture = 32;
		    this.setLightValue(1);
		}
			  
		public int quantityDropped(Random par1Random)
	    {
	        return 1;
	    }
	   
	    public boolean renderAsNormalBlock()
	    {
	        return true;
	    }
	    
	    protected boolean canSilkHarvest()
	    {
	        return true;
	    }
	   
	    public int getBlockTextureFromSideAndMetadata(int side, int meta)
	    {
	        
	            return this.blockIndexInTexture+meta;
	    }
	    
	    public int damageDropped(int par1)
	    {
	        return par1;
	    }
	}




