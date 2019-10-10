package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.ToxicPotion;

public class FeyFire extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("FeyFire");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public FeyFire() {
        super(ORB_ID, img, orbString.NAME);
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new FeyFire();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new ToxicPotion();
    }

    @Override
    public void doActions(AbstractPlayer p, AbstractMonster m) {
        if (m == null) {
            for (AbstractMonster mm : AbstractDungeon.getMonsters().monsters) {
                this.addToBot(new ApplyPowerAction(mm, p, new WeakPower(mm, 3, false), 3));
                this.addToBot(new ApplyPowerAction(mm, p, new VulnerablePower(mm, 3, false), 3));
            }
        } else {
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 3, false), 3));
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 3, false), 3));
        }
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Lantern", "lantern.png");
        img = ImageMaster.getRelicImg("Lantern");
    }
}