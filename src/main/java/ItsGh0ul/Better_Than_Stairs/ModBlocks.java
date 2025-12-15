package ItsGh0ul.Better_Than_Stairs;

import ItsGh0ul.Better_Than_Stairs.block.BlockCornerStairs;
import net.minecraft.src.Block;

public class ModBlocks {

	public static Block CORNER_STAIRS;

	public static void registerBlocks() {
		CORNER_STAIRS = new BlockCornerStairs(210); // Choose safe ID
	}
}
