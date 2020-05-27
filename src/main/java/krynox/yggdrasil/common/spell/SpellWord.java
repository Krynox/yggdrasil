package krynox.yggdrasil.common.spell;

import krynox.yggdrasil.Yggdrasil;
import net.minecraft.util.ResourceLocation;

public class SpellWord {
    private final ResourceLocation glyph_texture;
    private final String word;

    public static final SpellWord BASE_1 = new SpellWord("Jor", "word_base1");
    public static final SpellWord BASE_2 = new SpellWord("Vell", "word_base2");
    public static final SpellWord BASE_3 = new SpellWord("Kyn", "word_base3");

    public SpellWord(String word, String texture_loc) {
        this.word = word;
        this.glyph_texture = new ResourceLocation(Yggdrasil.MODID, texture_loc);
    }

	public ResourceLocation getGlyph_texture() {
		return glyph_texture;
	}

	public String getWord() {
		return word;
	}
   
}
