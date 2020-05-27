package krynox.yggdrasil.common.capability.cooldowns;

import krynox.yggdrasil.common.spell.Spell;

public interface Cooldowns {
    int getTimeRemaining(Spell spell);

    void setTimeRemaining(Spell spell, int millis);
    
    void putOnCooldown(Spell spell);
}
