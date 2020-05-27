package krynox.yggdrasil.common.capability.stats;

public interface Stats {
    int getBarrier();

    void incrementBarrier(int v);

    void decrementBarrier(int v);

    int getMomentum();

    void incrementMomentum(int v);

    void decrementMomentum(int v);
}
