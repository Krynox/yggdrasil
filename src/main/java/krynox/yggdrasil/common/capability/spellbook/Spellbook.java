package krynox.yggdrasil.common.capability.spellbook;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import krynox.yggdrasil.common.spell.Spell;
import krynox.yggdrasil.common.spell.SpellWord;

public interface Spellbook {
    Optional<Spellbook> narrowSearch(SpellWord word);
    
    Optional<List<SpellWord>> lookupWords(Spell spell);
    
    boolean addSpell(Spell spell, List<SpellWord> words);

    boolean removeSpell(Spell spell);

    Optional<Spell> getSpell();

    Map<SpellWord, SpellbookTree> getChildren();

    void update(Optional<Spell> leaf, Map<SpellWord, SpellbookTree> children);
}
