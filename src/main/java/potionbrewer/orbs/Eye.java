package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SneckoOil;
import potionbrewer.PotionbrewerMod;

public class Eye extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Eye");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Eye() {
        super(ORB_ID, img, orbString.NAME, DESC);
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Eye();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new SneckoOil();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        AbstractCard tmp = p.masterDeck.getRandomCard(true);
        p.limbo.addToBottom(tmp);
        tmp.current_x = p.hb.cX;
        tmp.current_y = p.hb.cY;
        tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = (float) Settings.HEIGHT / 2.0F;
        if (m != null) {
            tmp.calculateCardDamage(m);
        } else {
            m = AbstractDungeon.getRandomMonster();
            tmp.calculateCardDamage(m);
        }

        tmp.purgeOnUse = true;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, tmp.energyOnUse, true, true), true);
    }

    static {
        ImageMaster.loadRelicImg("Snecko Eye", "sneckoEye.png");
        img = ImageMaster.getRelicImg("Snecko Eye");
    }
}