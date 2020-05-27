package krynox.yggdrasil.common.capability.loadout;

import java.util.List;

import krynox.yggdrasil.common.spell.Traitline;

public interface Loadout {
    
    boolean equipTraitline(Traitline traitline);

    boolean dequipTraitline(Traitline traitline);

    List<Traitline> getEquippedTraitlines();
}
