package potionbrewer.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;

public class UseTempPotionAction extends AbstractGameAction {

    private AbstractPotion potion;

    public UseTempPotionAction(AbstractPotion potion, AbstractMonster target) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.potion = potion;
        this.target = target;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            this.isDone = true;

            PotionbrewerMod.potionIsFromCard = true;
            BaseMod.publishPrePotionUse(potion);
            potion.use(target);
            BaseMod.publishPostPotionUse(potion);
            PotionbrewerMod.potionIsFromCard = false;
        }
        tickDuration();
    }
}
