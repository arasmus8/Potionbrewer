package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class SlideRule extends CustomRelic {
    private static final int DAMAGE = 5;

    public static final String ID = PotionbrewerMod.makeID(SlideRule.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SlideRule.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("SlideRule.png"));

    public SlideRule() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public void onShuffle() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(
                new DamageAllEnemiesAction(
                        AbstractDungeon.player,
                        DamageInfo.createDamageMatrix(DAMAGE, true),
                        DamageInfo.DamageType.THORNS,
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
                )
        );
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DAMAGE + DESCRIPTIONS[1];
    }
}
