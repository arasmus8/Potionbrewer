package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FirePotion;
import com.megacrit.cardcrawl.powers.WeakPower;
import potionbrewer.PotionbrewerMod;

public class Wax extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Wax");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Wax() {
        super(ORB_ID, img, orbString.NAME);
        damages = true;
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Wax();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new FirePotion();
    }

    @Override
    public void doActions(AbstractPlayer p, AbstractMonster m) {
        if (m == null) {
            this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(5), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SMASH));
            for (AbstractMonster mm : AbstractDungeon.getMonsters().monsters) {
                this.addToBot(new ApplyPowerAction(mm, p, new WeakPower(mm, 1, false), 1));
            }
        } else {
            this.addToBot(new DamageAction(m, new DamageInfo(p, 5, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 1, false), 1));
        }
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Blue Candle", "blueCandle.png");
        img = ImageMaster.getRelicImg("Blue Candle");
    }
}