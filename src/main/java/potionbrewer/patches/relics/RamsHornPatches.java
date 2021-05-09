package potionbrewer.patches.relics;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.relics.RamsHorn;

public class RamsHornPatches {
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "obtainPotion",
            paramtypez = {AbstractPotion.class}
    )
    public static class AbstractPlayerObtainPotionPatch {
        public static SpireReturn<Boolean> Prefix(AbstractPlayer _instance, AbstractPotion potionToObtain) {
            if (_instance.hasRelic(RamsHorn.ID) && _instance.hasPotion(potionToObtain.ID)) {
                for (int i = 0; i < _instance.potions.size(); i++) {
                    if (_instance.potions.get(i).ID.equals(potionToObtain.ID)) {
                        AbstractPotion potion = _instance.potions.get(i);
                        PotionStackField.stackCount.set(potion, PotionStackField.stackCount.get(potion) + 1);
                        potion.initializeData();
                        potionToObtain.setAsObtained(i);
                        potionToObtain.flash();
                        AbstractPotion.playPotionSound();
                        return SpireReturn.Return(true);
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractPotion.class,
            method = "render"
    )
    public static class AbstractPotionRenderPatch {
        public static void Postfix(AbstractPotion _instance, SpriteBatch sb) {
            if (PotionStackField.stackCount.get(_instance) > 1) {
                FontHelper.renderFontRightTopAligned(sb,
                        FontHelper.topPanelInfoFont,
                        Integer.toString(PotionStackField.stackCount.get(_instance)),
                        _instance.posX + 25.0F * Settings.scale,
                        _instance.posY - 9.0F * Settings.scale,
                        Color.WHITE);
            }
        }
    }

    @SpirePatch(
            clz = AbstractPotion.class,
            method = "getPotency",
            paramtypez = {}
    )
    public static class AbstractPotionGetPotencyPatch {
        public static int Postfix(int result, AbstractPotion _instance) {
            int stackCount = PotionStackField.stackCount.get(_instance);
            if (stackCount > 1) {
                int adjusted = result * stackCount;
                ReflectionHacks.setPrivate(_instance, AbstractPotion.class, "potency", adjusted);
                return adjusted;
            }
            return result;
        }
    }
}
