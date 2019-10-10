package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SkillPotion;
import potionbrewer.PotionbrewerMod;

public class SerpentSkull extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("SerpentSkull");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public SerpentSkull() {
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
        return new SerpentSkull();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new SkillPotion();
    }

    @Override
    public void doActions(AbstractPlayer p, AbstractMonster m) {
        if (m == null) {
            this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(12), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SMASH));
        } else {
            this.addToBot(new DamageAction(m, new DamageInfo(p, 12, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        }
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Snake Skull", "snakeSkull.png");
        img = ImageMaster.getRelicImg("Snake Skull");
    }
}