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
import com.megacrit.cardcrawl.potions.FearPotion;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import potionbrewer.PotionbrewerMod;

public class Spore extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Spore");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Spore() {
        super(ORB_ID, img, orbString.NAME);
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Spore();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new FearPotion();
    }

    @Override
    public void doActions(AbstractPlayer p, AbstractMonster m) {
        if (m == null) {
            for (AbstractMonster mm : AbstractDungeon.getMonsters().monsters) {
                this.addToBot(new ApplyPowerAction(mm, p, new VulnerablePower(mm, 1, false), 1));
            }
        } else {
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false), 1));
        }
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Odd Mushroom", "mushroom.png");
        img = ImageMaster.getRelicImg("Odd Mushroom");
    }
}