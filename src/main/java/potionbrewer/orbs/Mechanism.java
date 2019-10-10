package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.AncientPotion;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import potionbrewer.PotionbrewerMod;

public class Mechanism extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Mechanism");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Mechanism() {
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
        return new Mechanism();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new AncientPotion();
    }

    @Override
    public void doActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        if (m == null) {
            this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(30), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        } else {
            this.addToBot(new DamageAction(m, new DamageInfo(p, 30, DamageInfo.DamageType.NORMAL)));
        }
        this.addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), 1, false, true, false));
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("ClockworkSouvenir", "clockwork.png");
        img = ImageMaster.getRelicImg("ClockworkSouvenir");
    }
}