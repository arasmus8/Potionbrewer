package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.KeywordStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class PotionKit extends CustomRelic {
    
    public static final String ID = PotionbrewerMod.makeID(PotionKit.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PotionKit.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PotionKit.png"));

    private static final KeywordStrings KEYWORD_STRINGS = CardCrawlGame.languagePack.getKeywordString("potionbrewer:tonic");
    
    public PotionKit() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.SOLID);
        tips.clear();
        tips.add(new PowerTip(this.name, this.description));
        tips.add(new PowerTip("Tonic", "A smaller version of a regular potion that can't be found in shops or rewards."));
        initializeTips();
    }
    
    @Override
    public void atBattleStartPreDraw() {
        flash();
        AbstractPotion p = PotionbrewerMod.tonicLibrary.getRandomTonic();
        this.addToBot(new ObtainPotionAction(p));
    }
    
    @Override
    public void onEquip() {
        AbstractPlayer p = AbstractDungeon.player;
        p.potionSlots += 1;
        AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
