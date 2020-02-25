package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.DistilledChaosPotion;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import potionbrewer.PotionbrewerMod;

public class Bug extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Bug");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Bug() {
        super(ORB_ID, img, orbString.NAME, DESC);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Bug();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new DistilledChaosPotion();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -6), -6));
        if (m != null && !m.hasPower(ArtifactPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, 6), 6));
        }
    }

    static {
        ImageMaster.loadRelicImg("PreservedInsect", "insect.png");
        img = ImageMaster.getRelicImg("PreservedInsect");
    }
}