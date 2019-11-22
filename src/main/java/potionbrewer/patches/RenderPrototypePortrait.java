package potionbrewer.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import potionbrewer.cards.Prototype;

public class RenderPrototypePortrait {
    @SpirePatch(clz = AbstractCard.class, method = "renderPortrait")
    public static class RenderPortrait {
        public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
            if (__instance instanceof Prototype) {
                ((Prototype) __instance).renderPortrait(sb);
            }
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderPortrait")
    public static class RenderLargePortrait {
        public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
            AbstractCard card = (AbstractCard) ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (card instanceof Prototype) {
                ((Prototype) card).renderLargePortrait(sb);
            }
        }
    }
}
