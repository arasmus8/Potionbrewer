package potionbrewer.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.ChemicalSpill;
import potionbrewer.cards.Collect;
import potionbrewer.cards.PotionbrewerDefend;
import potionbrewer.cards.PotionbrewerStrike;
import potionbrewer.patches.PotionTracker;
import potionbrewer.relics.PotionKit;

import java.util.ArrayList;
import java.util.logging.Logger;

import static potionbrewer.PotionbrewerMod.*;
import static potionbrewer.characters.Potionbrewer.Enums.COLOR_CYAN;

public class Potionbrewer extends CustomPlayer {
    private static final Logger logger = Logger.getLogger(PotionbrewerMod.class.getName());
    
    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass POTIONBREWER;
        @SpireEnum(name = "BREWER_CYAN_COLOR")
        public static AbstractCard.CardColor COLOR_CYAN;
        @SpireEnum(name = "BREWER_CYAN_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
    
    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 3;
    
    private static final String ID = makeID("Potionbrewer");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;
    
    public static final String[] orbTextures = {
            "potionbrewerResources/images/char/potionbrewer/orb/layer1.png",
            "potionbrewerResources/images/char/potionbrewer/orb/layer2.png",
            "potionbrewerResources/images/char/potionbrewer/orb/layer3.png",
            "potionbrewerResources/images/char/potionbrewer/orb/layer4.png",
            "potionbrewerResources/images/char/potionbrewer/orb/layer5.png",
            "potionbrewerResources/images/char/potionbrewer/orb/layer6.png",
            "potionbrewerResources/images/char/potionbrewer/orb/layer1d.png",
            "potionbrewerResources/images/char/potionbrewer/orb/layer2d.png",
            "potionbrewerResources/images/char/potionbrewer/orb/layer3d.png",
            "potionbrewerResources/images/char/potionbrewer/orb/layer4d.png",
            "potionbrewerResources/images/char/potionbrewer/orb/layer5d.png",};
    
    public Potionbrewer(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "potionbrewerResources/images/char/potionbrewer/orb/vfx.png", null,
                new SpriterAnimation(
                        "potionbrewerResources/images/char/potionbrewer/Spriter/Potionbrewer.scml"));
        
        
        initializeClass(null,
                
                THE_DEFAULT_SHOULDER_1,
                THE_DEFAULT_SHOULDER_2,
                THE_DEFAULT_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
        
        
        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        
        
        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);
    }
    
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }
    
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        
        logger.info("Begin loading starter Deck Strings");

        retVal.add(PotionbrewerStrike.ID);
        retVal.add(PotionbrewerStrike.ID);
        retVal.add(PotionbrewerStrike.ID);
        retVal.add(PotionbrewerStrike.ID);
        retVal.add(PotionbrewerDefend.ID);
        retVal.add(PotionbrewerDefend.ID);
        retVal.add(PotionbrewerDefend.ID);
        retVal.add(PotionbrewerDefend.ID);
        retVal.add(Collect.ID);
        retVal.add(ChemicalSpill.ID);
        return retVal;
    }
    
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        
        retVal.add(PotionKit.ID);

        UnlockTracker.markRelicAsSeen(PotionKit.ID);

        return retVal;
    }
    
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("CHEST_OPEN", 1.25f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }
    
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "CHEST_OPEN";
    }
    
    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }
    
    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_CYAN;
    }
    
    @Override
    public Color getCardTrailColor() {
        return PotionbrewerMod.BREWER_CYAN;
    }
    
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }
    
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }
    
    @Override
    public AbstractCard getStartCardForEvent() {
        return new ChemicalSpill();
    }
    
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }
    
    @Override
    public AbstractPlayer newInstance() {
        return new Potionbrewer(name, chosenClass);
    }
    
    @Override
    public Color getCardRenderColor() {
        return PotionbrewerMod.BREWER_CYAN;
    }
    
    @Override
    public Color getSlashAttackColor() {
        return PotionbrewerMod.BREWER_CYAN;
    }
    
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.POISON,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }
    
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }
    
    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    @Override
    public void applyStartOfTurnPostDrawPowers() {
        super.applyStartOfTurnPostDrawPowers();
        PotionTracker.potionsUsedThisTurn.set(this, 0);
        PotionbrewerMod.turnNumber += 1;
    }
}
