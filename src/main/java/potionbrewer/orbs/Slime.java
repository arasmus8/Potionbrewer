package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import potionbrewer.PotionbrewerMod;
import potionbrewer.powers.SlimedPower;

public class Slime extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Slime");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Slime() {
        super(ORB_ID, img, orbString.NAME, DESC);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Slime();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new SmokeBomb();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectList.add(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, AbstractGameAction.AttackEffect.POISON, false));
        addToBot(new ApplyPowerAction(m, p, new SlimedPower(m, p, 2), 2));
    }

    static {
        ImageMaster.loadRelicImg("Ectoplasm", "ectoplasm.png");
        img = ImageMaster.getRelicImg("Ectoplasm");
    }
}