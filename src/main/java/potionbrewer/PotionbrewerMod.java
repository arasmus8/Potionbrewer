package potionbrewer;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import potionbrewer.cards.*;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.events.IdentityCrisisEvent;
import potionbrewer.patches.PotionTracker;
import potionbrewer.potions.*;
import potionbrewer.relics.BottledPlaceholderRelic;
import potionbrewer.relics.DefaultClickableRelic;
import potionbrewer.relics.PlaceholderRelic;
import potionbrewer.relics.PlaceholderRelic2;
import potionbrewer.util.IDCheckDontTouchPls;
import potionbrewer.util.TextureLoader;
import potionbrewer.variables.DefaultCustomVariable;
import potionbrewer.variables.DefaultSecondMagicNumber;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class PotionbrewerMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        OnStartBattleSubscriber,
        PostInitializeSubscriber,
        PostPotionUseSubscriber {
    
    public static final Logger logger = LogManager.getLogger(PotionbrewerMod.class.getName());
    private static String modID;
    
    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true;
    
    private static final String MODNAME = "Potionbrewer";
    private static final String AUTHOR = "NotInTheFace";
    private static final String DESCRIPTION = "A custom character with potion synergies.";
    
    public static final Color BREWER_CYAN = CardHelper.getColor(0, 180, 239);
    
    private static final String ATTACK_DEFAULT_GRAY = "potionbrewerResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY = "potionbrewerResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY = "potionbrewerResources/images/512/bg_power_default_gray.png";
    
    private static final String ENERGY_ORB_DEFAULT_GRAY = "potionbrewerResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "potionbrewerResources/images/512/card_small_orb.png";
    
    private static final String ATTACK_DEFAULT_GRAY_PORTRAIT = "potionbrewerResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY_PORTRAIT = "potionbrewerResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY_PORTRAIT = "potionbrewerResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "potionbrewerResources/images/1024/card_default_gray_orb.png";
    
    private static final String THE_DEFAULT_BUTTON = "potionbrewerResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "potionbrewerResources/images/charSelect/DefaultCharacterPortraitBG.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "potionbrewerResources/images/char/potionbrewer/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "potionbrewerResources/images/char/potionbrewer/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "potionbrewerResources/images/char/potionbrewer/corpse.png";
    
    public static final String BADGE_IMAGE = "potionbrewerResources/images/Badge.png";
    
    public static final String THE_DEFAULT_SKELETON_ATLAS = "potionbrewerResources/images/char/potionbrewer/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "potionbrewerResources/images/char/potionbrewer/skeleton.json";
    
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    
    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }
    
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    
    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }
    
    public PotionbrewerMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);
        
        
        setModID("potionbrewer");
        
        
        logger.info("Done subscribing");
        
        logger.info("Creating the color " + Potionbrewer.Enums.COLOR_CYAN.toString());
        
        BaseMod.addColor(Potionbrewer.Enums.COLOR_CYAN, BREWER_CYAN, BREWER_CYAN, BREWER_CYAN,
                BREWER_CYAN, BREWER_CYAN, BREWER_CYAN, BREWER_CYAN,
                ATTACK_DEFAULT_GRAY, SKILL_DEFAULT_GRAY, POWER_DEFAULT_GRAY, ENERGY_ORB_DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY_PORTRAIT, SKILL_DEFAULT_GRAY_PORTRAIT, POWER_DEFAULT_GRAY_PORTRAIT,
                ENERGY_ORB_DEFAULT_GRAY_PORTRAIT, CARD_ENERGY_ORB);
        
        logger.info("Done creating the color");
        
        
        logger.info("Adding mod settings");
        
        
        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE");
        try {
            SpireConfig config = new SpireConfig("PotionbrewerMod", "theDefaultConfig", theDefaultDefaultSettings);
            
            config.load();
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
    }
    
    public static void setModID(String ID) {
        Gson coolG = new Gson();
        
        InputStream in = PotionbrewerMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
            modID = EXCEPTION_STRINGS.DEFAULTID;
        } else {
            modID = ID;
        }
        logger.info("Success! ID is " + modID);
    }
    
    public static String getModID() {
        return modID;
    }
    
    private static void pathCheck() {
        Gson coolG = new Gson();
        
        InputStream in = PotionbrewerMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = PotionbrewerMod.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            if (!packageName.equals(getModID())) {
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            }
            if (!resourcePathExists.exists()) {
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
            }
        }
    }
    
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        PotionbrewerMod defaultmod = new PotionbrewerMod();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + Potionbrewer.Enums.POTIONBREWER.toString());
        
        BaseMod.addCharacter(new Potionbrewer("Potionbrewer", Potionbrewer.Enums.POTIONBREWER),
                THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, Potionbrewer.Enums.POTIONBREWER);
        
        receiveEditPotions();
        logger.info("Added " + Potionbrewer.Enums.POTIONBREWER.toString());
    }
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        
        ModPanel settingsPanel = new ModPanel();
        
        
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                enablePlaceholder,
                settingsPanel,
                (label) -> {
                },
                (button) -> {
                    
                    enablePlaceholder = button.enabled;
                    try {
                        
                        SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
                        config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        
        settingsPanel.addUIElement(enableNormalsButton);
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        
        BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, TheCity.ID);
        
        
        logger.info("Done loading badge Image and mod options");
    }
    
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");


        BaseMod.addPotion(AcidPotion.class, AcidPotion.LIQUID_COLOR, AcidPotion.HYBRID_COLOR, AcidPotion.SPOTS_COLOR, AcidPotion.POTION_ID, Potionbrewer.Enums.POTIONBREWER);
        BaseMod.addPotion(CleansingPotion.class, CleansingPotion.LIQUID_COLOR, CleansingPotion.HYBRID_COLOR, CleansingPotion.SPOTS_COLOR, CleansingPotion.POTION_ID, Potionbrewer.Enums.POTIONBREWER);
        BaseMod.addPotion(DiscountPotion.class, DiscountPotion.LIQUID_COLOR, DiscountPotion.HYBRID_COLOR, DiscountPotion.SPOTS_COLOR, DiscountPotion.POTION_ID, Potionbrewer.Enums.POTIONBREWER);
        BaseMod.addPotion(FreezingPotion.class, FreezingPotion.LIQUID_COLOR, FreezingPotion.HYBRID_COLOR, FreezingPotion.SPOTS_COLOR, FreezingPotion.POTION_ID, Potionbrewer.Enums.POTIONBREWER);
        BaseMod.addPotion(HastePotion.class, HastePotion.LIQUID_COLOR, HastePotion.HYBRID_COLOR, HastePotion.SPOTS_COLOR, HastePotion.POTION_ID, Potionbrewer.Enums.POTIONBREWER);
        BaseMod.addPotion(MidasPotion.class, MidasPotion.LIQUID_COLOR, MidasPotion.HYBRID_COLOR, MidasPotion.SPOTS_COLOR, MidasPotion.POTION_ID, Potionbrewer.Enums.POTIONBREWER);
        BaseMod.addPotion(NoxiousPotion.class, NoxiousPotion.LIQUID_COLOR, NoxiousPotion.HYBRID_COLOR, NoxiousPotion.SPOTS_COLOR, NoxiousPotion.POTION_ID, Potionbrewer.Enums.POTIONBREWER);
        BaseMod.addPotion(QuicksilverPotion.class, QuicksilverPotion.LIQUID_COLOR, QuicksilverPotion.HYBRID_COLOR, QuicksilverPotion.SPOTS_COLOR, QuicksilverPotion.POTION_ID, Potionbrewer.Enums.POTIONBREWER);
        BaseMod.addPotion(SplittingPotion.class, SplittingPotion.LIQUID_COLOR, SplittingPotion.HYBRID_COLOR, SplittingPotion.SPOTS_COLOR, SplittingPotion.POTION_ID, Potionbrewer.Enums.POTIONBREWER);
        BaseMod.addPotion(ToxicPotion.class, ToxicPotion.LIQUID_COLOR, ToxicPotion.HYBRID_COLOR, ToxicPotion.SPOTS_COLOR, ToxicPotion.POTION_ID, Potionbrewer.Enums.POTIONBREWER);

        logger.info("Done editing potions");
    }
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        
        
        BaseMod.addRelicToCustomPool(new PlaceholderRelic(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new BottledPlaceholderRelic(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new DefaultClickableRelic(), Potionbrewer.Enums.COLOR_CYAN);
        
        
        BaseMod.addRelic(new PlaceholderRelic2(), RelicType.SHARED);
        
        
        UnlockTracker.markRelicAsSeen(BottledPlaceholderRelic.ID);
        logger.info("Done adding relics!");
    }
    
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        
        pathCheck();
        
        logger.info("Add variabls");
        
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        
        logger.info("Adding cards");
        
        
        BaseMod.addCard(new Collect());
        BaseMod.addCard(new DefaultSecondMagicNumberSkill());
        BaseMod.addCard(new DefaultCommonAttack());
        BaseMod.addCard(new DefaultAttackWithVariable());
        BaseMod.addCard(new DefaultCommonSkill());
        BaseMod.addCard(new DefaultCommonPower());
        BaseMod.addCard(new DefaultUncommonSkill());
        BaseMod.addCard(new DefaultUncommonAttack());
        BaseMod.addCard(new DefaultUncommonPower());
        BaseMod.addCard(new DefaultRareAttack());
        BaseMod.addCard(new DefaultRareSkill());
        BaseMod.addCard(new DefaultRarePower());
        
        logger.info("Making sure the cards are unlocked.");
        
        
        UnlockTracker.unlockCard(Collect.ID);
        UnlockTracker.unlockCard(DefaultSecondMagicNumberSkill.ID);
        UnlockTracker.unlockCard(DefaultCommonAttack.ID);
        UnlockTracker.unlockCard(DefaultAttackWithVariable.ID);
        UnlockTracker.unlockCard(DefaultCommonSkill.ID);
        UnlockTracker.unlockCard(DefaultCommonPower.ID);
        UnlockTracker.unlockCard(DefaultUncommonSkill.ID);
        UnlockTracker.unlockCard(DefaultUncommonAttack.ID);
        UnlockTracker.unlockCard(DefaultUncommonPower.ID);
        UnlockTracker.unlockCard(DefaultRareAttack.ID);
        UnlockTracker.unlockCard(DefaultRareSkill.ID);
        UnlockTracker.unlockCard(DefaultRarePower.ID);
        
        logger.info("Done adding cards!");
    }
    
    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());
        
        
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/PotionbrewerMod-Card-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/PotionbrewerMod-Power-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/PotionbrewerMod-Relic-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/PotionbrewerMod-Event-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/eng/PotionbrewerMod-Potion-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/PotionbrewerMod-Character-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/PotionbrewerMod-Orb-Strings.json");
        
        logger.info("Done edittting strings");
    }
    
    @Override
    public void receiveEditKeywords() {
        
        
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/PotionbrewerMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostPotionUse(AbstractPotion abstractPotion) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p == null) {
           return;
        }

        Integer combat = PotionTracker.potionsUsedThisCombat.get(p);
        PotionTracker.potionsUsedThisCombat.set(p, combat + 1);

        Integer turn = PotionTracker.potionsUsedThisTurn.get(p);
        PotionTracker.potionsUsedThisTurn.set(p, combat + 1);
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p == null) {
            return;
        }

        PotionTracker.potionsUsedThisCombat.set(p, 0);
        PotionTracker.potionsUsedThisTurn.set(p, 0);
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
