package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.PotionSlot;
import potionbrewer.PotionbrewerMod;
import potionbrewer.patches.relics.PotionStackField;
import potionbrewer.util.TextureLoader;

import java.util.List;
import java.util.stream.Collectors;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class RamsHorn extends CustomRelic implements CustomSavable<List<RamsHorn.PotionStack>> {
    public static final String ID = PotionbrewerMod.makeID(RamsHorn.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("RamsHorn.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("RamsHorn.png"));

    public RamsHorn() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.SOLID);
        tips.clear();
        tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public List<PotionStack> onSave() {
        return AbstractDungeon.player.potions.stream().map(potion -> {
            if (potion instanceof PotionSlot) {
                return new PotionStack("", -1);
            }
            int stacks = PotionStackField.stackCount.get(potion);
            return new PotionStack(potion.ID, stacks);
        }).collect(Collectors.toList());
    }

    @Override
    public void onLoad(List<PotionStack> potionStacks) {
        potionStacks.forEach(potionStack -> {
            if (!potionStack.id.equals("")) {
                AbstractDungeon.player.potions.stream()
                        .filter(potion -> potion.ID.equals(potionStack.id))
                        .findFirst()
                        .ifPresent(potion -> {
                            PotionStackField.stackCount.set(potion, potionStack.stack);
                            potion.initializeData();
                        });
            }
        });
    }

    public static class PotionStack {
        public final String id;
        public final int stack;

        public PotionStack(String id, int stack) {
            this.id = id;
            this.stack = stack;
        }
    }
}
