package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.StunPotion;

public class SuperSpore extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("SuperSpore");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public static final int DAMAGE_PER_STACK = 5;

    public SuperSpore() {
        super(ORB_ID, img, orbString.NAME, DESC);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new SuperSpore();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new StunPotion();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        int debuffCount = (int) m.powers.stream().filter(power -> power.type == AbstractPower.PowerType.DEBUFF).count();
        addToBot(new LoseHPAction(m, p, DAMAGE_PER_STACK * debuffCount, AbstractGameAction.AttackEffect.POISON));
    }

    static {
        ImageMaster.loadRelicImg("Odd Mushroom", "mushroom.png");
        img = ImageMaster.getRelicImg("Odd Mushroom");
    }
}