package ItsGh0ul.Better_Than_Stairs;

import net.minecaft.src.*;

public class BlockCornerStairs extends Block {

	public BlockCornerStairs(int id) {
		super(id, Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setBlockName("cornerStairs");
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving placer) {
		int facing = MathHelper.floor_double((placer.rotationYaw * 4F / 360F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, facing);
		updateShape(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborId) {
		updateShape(world, x, y, z);
	}

	private void updateShape(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		int facing = meta & 3;

		// Compute left/right offsets
		int dx = 0, dz = 0;
		if (facing == 0) dz = -1;
		if (facing == 1) dx = 1;
		if (facing == 2) dz = 1;
		if (facing == 3) dx = -1;

		boolean left = isStair(world, x + dz, y, z - dx);
		boolean right = isStair(world, x - dz, y, z + dx);

		int shape = 0; // straight
		if (left) shape = 1;   // inner
		if (right) shape = 2;  // outer

		world.setBlockMetadataWithNotify(x, y, z, facing | (shape << 2));
	}

	private boolean isStair(World world, int x, int y, int z) {
		return world.getBlockId(x, y, z) == this.blockID;
	}
}
