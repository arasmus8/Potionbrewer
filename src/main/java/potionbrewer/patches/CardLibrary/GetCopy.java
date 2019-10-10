package potionbrewer.patches.CardLibrary;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import potionbrewer.cards.Prototype;

@SpirePatch(
        clz = CardLibrary.class,
        method = "getCopy",
        paramtypez = {String.class, int.class, int.class}
)
public class GetCopy {
    public static AbstractCard Postfix(AbstractCard __return, String id, int upgrades, int misc) {
        if (id.equals(Prototype.ID) && misc != 0) {
            ((Prototype) __return).hydrate();
        }

        return __return;
    }
}
