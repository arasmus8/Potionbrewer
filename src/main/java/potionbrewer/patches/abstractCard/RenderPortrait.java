package potionbrewer.patches.abstractCard;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import potionbrewer.cards.Prototype;

@SpirePatch(clz = AbstractCard.class, method = "renderPortrait")
public class RenderPortrait {
    public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
        if (__instance instanceof Prototype) {
            ((Prototype) __instance).renderPortrait(sb);
        }
    }
}
