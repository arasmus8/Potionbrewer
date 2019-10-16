package potionbrewer.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;

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

            /*
            float posX = 900.0F * Settings.scale;
            float posY = 500.0F * Settings.scale;
            this.addToBot(new VFXAction(new SpeechTextEffect(posX, posY, Settings.ACTION_DUR_FAST, potion.name, DialogWord.AppearEffect.GROW_IN)));
            */

            BaseMod.publishPrePotionUse(potion);
            potion.use(target);
            BaseMod.publishPostPotionUse(potion);
        }
        tickDuration();
    }
}
