package krynox.yggdrasil.common.capability.spellbook;

import static krynox.yggdrasil.Yggdrasil.REG_HELPER_SPELL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import krynox.yggdrasil.common.spell.Spell;
import krynox.yggdrasil.common.spell.SpellWord;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;

public class SpellbookTree implements Spellbook {
    private Map<SpellWord, SpellbookTree> children;
    private Optional<Spell> leaf;

    private SpellbookTree(Optional<Spell> leaf, Map<SpellWord, SpellbookTree> children) {
        this.leaf = leaf;
        this.children = children;
    }

    /**
     * Constructor for leaves.
     */
    private SpellbookTree(Spell leaf) {
        this(Optional.of(leaf), new HashMap<>());
    }

    /**
     * Constructor for nodes.
     */
    public SpellbookTree() {
        this(Optional.empty(), new HashMap<>());
    }

    /**
     * Depth-first search on the tree. DFS chosen over BFS because of the high
     * branching factor, and very low average depth. The tree should always be
     * fairly small, anyway.
     * TODO - memoize this
     */
    @Override
    public Optional<List<SpellWord>> lookupWords(Spell spell) {
        return dfs(this, new ArrayList<>(), spell);
    }

    /**
     * DFS impl which returns the path to the target and performs the given action
     * on finding it.
     */
    private static Optional<List<SpellWord>> dfs(SpellbookTree node, ArrayList<SpellWord> pathToNode, Spell target) {
        for (Map.Entry<SpellWord, SpellbookTree> pair : node.children.entrySet()) {
            pathToNode.add(pair.getKey());

            if (pair.getValue().leaf.filter((spell) -> spell.equals(target)).isPresent()) {
                return Optional.of(pathToNode);
            }

            return dfs(pair.getValue(), (ArrayList<SpellWord>) pathToNode.clone(), target);
        }

        return Optional.empty();
    }

    @Override
    public boolean addSpell(Spell spell, List<SpellWord> words) {
        SpellbookTree tree = this;

        for (int i = 0; i < words.size(); i++) {
            SpellWord word = words.get(i);
            SpellbookTree child = tree.children.get(word);

            if (child == null) {
                if (i == words.size() - 1) {
                    tree.children.put(word, new SpellbookTree(spell));
                    return true;
                } else {
                    tree.children.put(word, new SpellbookTree());
                }

            } else if (child.leaf.isPresent()) {
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean removeSpell(Spell spell) {
        return removalHelper(this, spell);
    }

    private static boolean removalHelper(SpellbookTree node, Spell target) {
        for (Map.Entry<SpellWord, SpellbookTree> pair : node.children.entrySet()) {
            if (pair.getValue().leaf.filter((spell) -> spell.equals(target)).isPresent()) {
                node.children.remove(pair.getKey());
                return true;
            }

            Optional<List<SpellWord>> removed = dfs(pair.getValue(), new ArrayList<>(), target);
            if (removed.isPresent() && node.children.size() == 0) {
                node.children.remove(pair.getKey());
            }
            return removed.isPresent();
        }

        return false;
    }

    @Override
    public Optional<Spellbook> narrowSearch(SpellWord word) {
        return Optional.ofNullable(children.get(word));
    }

    @Override
    public Optional<Spell> getSpell() {
        return leaf;
    }

    @Override
    public Map<SpellWord, SpellbookTree> getChildren() {
        return children;
    }

    @Override
    public void update(Optional<Spell> leaf, Map<SpellWord, SpellbookTree> children) {
        this.leaf = leaf;
        this.children = children;
    }

    public static SpellbookTree deserialize(INBT nbt) {
        CompoundNBT root = (CompoundNBT) nbt;
        CompoundNBT children = root.getCompound("children");
        HashMap<SpellWord, SpellbookTree> childrenMap = new HashMap<>();

        for (String tag : children.keySet()) {
            //childrenMap.put(SpellWord.valueOf(tag), deserialize(children.getCompound(tag)));
        }

        Optional<Spell> leaf = Optional.ofNullable(root.getString("leaf")).flatMap(REG_HELPER_SPELL::get);
        return new SpellbookTree(leaf, childrenMap);
    }

    public static INBT serialize(SpellbookTree spellbook) {
        CompoundNBT root = new CompoundNBT();
        CompoundNBT children = new CompoundNBT();

        spellbook.getSpell().ifPresent((spell) -> root.put("leaf", StringNBT.valueOf(spell.getRegistryName().toString())));

        for (Map.Entry<SpellWord, SpellbookTree> pair : spellbook.getChildren().entrySet()) {
            children.put(pair.getKey().toString(), serialize(pair.getValue()));
        }

        root.put("children", children);
        return root;
    }

    public static void clone(SpellbookTree oldSB, SpellbookTree newSB) {
        newSB.children = oldSB.children;
        newSB.leaf = oldSB.leaf;
    }
}
