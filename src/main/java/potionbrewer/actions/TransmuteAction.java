package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import potionbrewer.cards.option.ChoosePotion;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TransmuteAction extends AbstractGameAction {
    private int potionChoiceCount;
    private DamageInfo info;

    public TransmuteAction(AbstractCreature target, DamageInfo info, int potionChoiceCount) {
        this.info = info;
        this.setValues(target, info);
        this.potionChoiceCount = potionChoiceCount;
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SMASH));
            this.target.damage(this.info);
            if ((((AbstractMonster) this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                ArrayList<AbstractCard> choices = ChoosePotion.getRandomPotionIdList(potionChoiceCount).stream()
                        .map(s -> new ChoosePotion(s, true))
                        .collect(Collectors.toCollection(ArrayList::new));
                this.addToTop(new ChooseOneAction(choices));
            }
        }

        this.tickDuration();
    }
}
