package krynox.yggdrasil.common.capability.stats;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;

public class StatsImpl implements Stats {

    private int barrier = 0;
    private int momentum = 0;

    private StatsImpl(int barrier, int momentum) {
        this.barrier = barrier;
        this.momentum = momentum;
    }

    public StatsImpl() {
        this(0, 0);
    }
    
	@Override
	public int getBarrier() {
		return barrier;
	}

	@Override
	public void incrementBarrier(int v) {
        barrier += v;
	}

	@Override
	public void decrementBarrier(int v) {
        barrier -= v;
	}

	@Override
	public int getMomentum() {
		return momentum;
	}

	@Override
	public void incrementMomentum(int v) {
        momentum += v;
	}

	@Override
	public void decrementMomentum(int v) {
        momentum -= v;
	}
    
    public static StatsImpl deserialize(INBT nbt) {
        CompoundNBT root = (CompoundNBT) nbt;
        int b = root.getInt("barrier");
        int m = root.getInt("momentum");
        return new StatsImpl(b,m);
    }

    public static INBT serialize(StatsImpl stats) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("barrier", stats.barrier);
        nbt.putInt("momentum", stats.momentum);
        return nbt;
    }

    public static void clone(StatsImpl oldStats, StatsImpl newStats) {
        newStats.barrier = oldStats.barrier;
        newStats.momentum = oldStats.momentum;
    }
}
