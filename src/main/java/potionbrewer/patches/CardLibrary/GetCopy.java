package potionbrewer.patches.CardLibrary;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import potionbrewer.cards.HazardousWaste;
import potionbrewer.cards.Prototype;
import potionbrewer.cards.RefinedProcess;

@SpirePatch(
        clz = CardLibrary.class,
        method = "getCopy",
        paramtypez = {String.class, int.class, int.class}
)
public class GetCopy {
    public static AbstractCard Postfix(AbstractCard __return, String id, int upgrades, int misc) {
        if (id.equals(Prototype.ID) && misc != 0) {
            ((Prototype) __return).hydrate();
        } else if (id.equals(RefinedProcess.ID)) {
            __return.baseDamage = __return.misc;
        } else if (id.equals(HazardousWaste.ID)) {
            __return.baseMagicNumber = __return.misc;
        }

        return __return;
    }
}
