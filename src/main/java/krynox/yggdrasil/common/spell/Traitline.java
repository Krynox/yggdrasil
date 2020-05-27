package krynox.yggdrasil.common.spell;

import com.google.common.collect.ImmutableList;

import net.minecraftforge.registries.ForgeRegistryEntry;

public class Traitline extends ForgeRegistryEntry<Traitline> {
    private final String name;
    private final SpellWord word;
    private final ImmutableList<Spell> spells;

    public Traitline(String name, String word, Spell[] spells) {
        this.name = name;
        this.word = new SpellWord(word, "word_" + name);
        this.spells = ImmutableList.copyOf(spells);
    }

	public String getUnlocaizedName() {
		return name;
	}

    public SpellWord getWord() {
        return word;
    }

	public ImmutableList<Spell> getSpells() {
		return spells;
	}

}
