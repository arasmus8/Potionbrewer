package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.option.ChoosePotion;
import potionbrewer.util.TextureLoader;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class AlchemistFlask extends CustomRelic {
    private static final int COUNT = 25;

    public static final String ID = PotionbrewerMod.makeID(AlchemistFlask.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("AlchemistsFlask.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("AlchemistsFlask.png"));

    public AlchemistFlask() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.SOLID);
        counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        ++this.counter;
        if (this.counter == COUNT) {
            this.counter = 0;
            this.flash();
            this.pulse = false;
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new ChooseOneAction(
                    ChoosePotion.getRandomPotionIdList(3).stream()
                            .map(ChoosePotion::new)
                            .collect(Collectors.toCollection(ArrayList::new))
            ));
        } else if (this.counter == COUNT - 1) {
            this.beginPulse();
            this.pulse = true;
        }
    }

    @Override
    public void atBattleStart() {
        if (counter == COUNT - 1) {
            this.beginPulse();
            this.pulse = true;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + COUNT + DESCRIPTIONS[1];
    }
}
