package potionbrewer;

import basemod.*;
import basemod.abstracts.CustomSavable;
import basemod.devcommands.ConsoleCommand;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import potionbrewer.cards.AbstractPotionbrewerCard;
import potionbrewer.cards.FollowupCard;
import potionbrewer.cards.PotionTrackingCard;
import potionbrewer.characters.Invalid;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.events.JunkPileEvent;
import potionbrewer.optioncards.ChoosePotion;
import potionbrewer.orbs.Reagent;
import potionbrewer.orbs.ReagentList;
import potionbrewer.patches.PotionTracker;
import potionbrewer.potions.*;
import potionbrewer.powers.HoarderPower;
import potionbrewer.powers.PotionTrackingPower;
import potionbrewer.relics.*;
import potionbrewer.util.AssetLoader;
import potionbrewer.util.IDCheckDontTouchPls;
import potionbrewer.util.TextureLoader;
import potionbrewer.util.WidePotionHelper;
import potionbrewer.variables.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpireInitializer
public class PotionbrewerMod implements
        CustomSavable<String>,
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        OnCardUseSubscriber,
        OnStartBattleSubscriber,
        PostBattleSubscriber,
        PostEnergyRechargeSubscriber,
        PostInitializeSubscriber,
        PostPotionUseSubscriber,
        StartActSubscriber,
        StartGameSubscriber {

    private static String modID;

    private static final Logger logger = Logger.getLogger(PotionbrewerMod.class.getName());
    private static final String DELIM = ", ";
    public static SpireConfig config;

    private static final String MODNAME = "Potionbrewer";
    private static final String AUTHOR = "NotInTheFace";
    private static final String DESCRIPTION = "A custom character with potion synergies." +
            "\n- Includes 16 new potions" +
            "\n- Catalyze mechanic (power up certain cards by using potions)" +
            "\n- Collect Reagents from monsters in the spire and use them to brew potions or create custom cards.";

    public static final Color BREWER_CYAN = CardHelper.getColor(0, 180, 239);

    private static final String ATTACK_CYAN_BG = "potionbrewerResources/images/512/bg_attack.png";
    private static final String SKILL_CYAN_BG = "potionbrewerResources/images/512/bg_skill.png";
    private static final String POWER_CYAN_BG = "potionbrewerResources/images/512/bg_power.png";

    private static final String ENERGY_ORB_POTIONBREWER = "potionbrewerResources/images/512/card_cyan_orb.png";
    private static final String CARD_ENERGY_ORB = "potionbrewerResources/images/512/card_small_orb.png";

    private static final String ATTACK_POTIONBREWER_PORTRAIT = "potionbrewerResources/images/1024/bg_attack.png";
    private static final String SKILL_POTIONBREWER_PORTRAIT = "potionbrewerResources/images/1024/bg_skill.png";
    private static final String POWER_POTIONBREWER_PORTRAIT = "potionbrewerResources/images/1024/bg_power.png";
    private static final String ENERGY_ORB_POTIONBREWER_PORTRAIT = "potionbrewerResources/images/1024/card_cyan_orb.png";

    private static final String POTIONBREWER_CHARACTER_BUTTON_PNG = "potionbrewerResources/images/charSelect/PotionbrewerCharacterButton.png";
    private static final String CHARACTER_SELECT_PORTRAIT = "potionbrewerResources/images/charSelect/CharacterSelect.png";
    public static final String POTIONBREWER_SHOULDER = "potionbrewerResources/images/char/potionbrewer/shoulder.png";
    public static final String POTIONBREWER_SHOULDER_2 = "potionbrewerResources/images/char/potionbrewer/shoulder2.png";
    public static final String POTIONBREWER_CORPSE = "potionbrewerResources/images/char/potionbrewer/corpse.png";

    public static final String BADGE_IMAGE = "potionbrewerResources/images/Badge.png";

    public static final String POTIONBREWER_SKELETON_ATLAS = "potionbrewerResources/images/char/potionbrewer/skeleton.atlas";
    public static final String POTIONBREWER_SKELETON_JSON = "potionbrewerResources/images/char/potionbrewer/skeleton.json";

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

    public static String assetPath(String path) {
        return "potionbrewerAssets/" + path;
    }

    public static AssetLoader assets = new AssetLoader();

    public static ArrayList<Reagent> reagents;
    public static boolean potionIsFromCard = false;
    public static TonicLibrary tonicLibrary;
    public static PotionLibrary potionLibrary;
    public static ReagentList reagentList;

    public PotionbrewerMod() {
        logger.info("Subscribe to BaseMod hooks");

        reagents = new ArrayList<>();

        BaseMod.subscribe(this);


        setModID("potionbrewer");


        logger.info("Done subscribing");

        logger.info("Creating the color " + Potionbrewer.Enums.COLOR_CYAN.toString());

        BaseMod.addColor(Potionbrewer.Enums.COLOR_CYAN, BREWER_CYAN, BREWER_CYAN, BREWER_CYAN,
                BREWER_CYAN, BREWER_CYAN, BREWER_CYAN, BREWER_CYAN,
                ATTACK_CYAN_BG, SKILL_CYAN_BG, POWER_CYAN_BG, ENERGY_ORB_POTIONBREWER,
                ATTACK_POTIONBREWER_PORTRAIT, SKILL_POTIONBREWER_PORTRAIT, POWER_POTIONBREWER_PORTRAIT,
                ENERGY_ORB_POTIONBREWER_PORTRAIT, CARD_ENERGY_ORB);

        logger.info("Done creating the color");


        BaseMod.addSaveField("potionbrewer:reagents", this);
        logger.info("Done adding mod savable fields");
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
                POTIONBREWER_CHARACTER_BUTTON_PNG, CHARACTER_SELECT_PORTRAIT, Potionbrewer.Enums.POTIONBREWER);

        receiveEditPotions();
        logger.info("Added " + Potionbrewer.Enums.POTIONBREWER.toString());
    }

    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");


        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);

        logger.info("Adding mod settings");


        try {
            config = new SpireConfig("PotionbrewerMod", "potionbrewerConfig");
            config.load();
            PotionbrewerTipTracker.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");


        ModPanel panel = new ModPanel();
        panel.addUIElement(
                new ModLabel("Thanks for trying this character! I hope you like it.",
                        350.0F,
                        700.0F,
                        Settings.CREAM_COLOR,
                        FontHelper.charDescFont,
                        panel,
                        (b) -> {
                        }
                )
        );

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, panel);

        logger.info("Done loading badge Image and mod options");

        // Console Commands
        ConsoleCommand.addCommand("prototype", PrototypeConsoleCommand.class);
        ConsoleCommand.addCommand("reagent", ReagentConsoleCommand.class);
        ConsoleCommand.addCommand("potioncard", PotionCardConsoleCommand.class);

        // Save/Load fields
        tonicLibrary = new TonicLibrary();
        BaseMod.addSaveField("potionbrewer:TonicLibrary", tonicLibrary);
        potionLibrary = new PotionLibrary();
        BaseMod.addSaveField("potionbrewer:PotionLibrary", potionLibrary);
        reagentList = new ReagentList();
        BaseMod.addSaveField("potionbrewer:ReagentList", reagentList);

        // Events
        BaseMod.addEvent(JunkPileEvent.ID, JunkPileEvent.class, TheCity.ID);

        // WIDE Potions
        if (Loader.isModLoaded("widepotions")) {
            WidePotionHelper.setupWidePotions();
        }
    }

    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");


        BaseMod.addPotion(AcidPotion.class, AcidPotion.LIQUID_COLOR, AcidPotion.HYBRID_COLOR, AcidPotion.SPOTS_COLOR, AcidPotion.POTION_ID, null);
        BaseMod.addPotion(BarricadePotion.class, BarricadePotion.LIQUID_COLOR, BarricadePotion.HYBRID_COLOR, BarricadePotion.SPOTS_COLOR, BarricadePotion.POTION_ID, null);
        BaseMod.addPotion(BlacksmithPotion.class, BlacksmithPotion.LIQUID_COLOR, BlacksmithPotion.HYBRID_COLOR, BlacksmithPotion.SPOTS_COLOR, BlacksmithPotion.POTION_ID, null);
        BaseMod.addPotion(BoundlessPotion.class, BoundlessPotion.LIQUID_COLOR, BoundlessPotion.HYBRID_COLOR, BoundlessPotion.SPOTS_COLOR, BoundlessPotion.POTION_ID, null);
        BaseMod.addPotion(CleansingPotion.class, CleansingPotion.LIQUID_COLOR, CleansingPotion.HYBRID_COLOR, CleansingPotion.SPOTS_COLOR, CleansingPotion.POTION_ID, null);
        BaseMod.addPotion(DiscountPotion.class, DiscountPotion.LIQUID_COLOR, DiscountPotion.HYBRID_COLOR, DiscountPotion.SPOTS_COLOR, DiscountPotion.POTION_ID, null);
        BaseMod.addPotion(EndurancePotion.class, EndurancePotion.LIQUID_COLOR, EndurancePotion.HYBRID_COLOR, EndurancePotion.SPOTS_COLOR, EndurancePotion.POTION_ID, null);
        BaseMod.addPotion(FreezingPotion.class, FreezingPotion.LIQUID_COLOR, FreezingPotion.HYBRID_COLOR, FreezingPotion.SPOTS_COLOR, FreezingPotion.POTION_ID, null);
        BaseMod.addPotion(HastePotion.class, HastePotion.LIQUID_COLOR, HastePotion.HYBRID_COLOR, HastePotion.SPOTS_COLOR, HastePotion.POTION_ID, null);
        BaseMod.addPotion(InfectionPotion.class, InfectionPotion.LIQUID_COLOR, InfectionPotion.HYBRID_COLOR, InfectionPotion.SPOTS_COLOR, InfectionPotion.POTION_ID, null);
        BaseMod.addPotion(MidasPotion.class, MidasPotion.LIQUID_COLOR, MidasPotion.HYBRID_COLOR, MidasPotion.SPOTS_COLOR, MidasPotion.POTION_ID, null);
        BaseMod.addPotion(NoxiousPotion.class, NoxiousPotion.LIQUID_COLOR, NoxiousPotion.HYBRID_COLOR, NoxiousPotion.SPOTS_COLOR, NoxiousPotion.POTION_ID, null);
        BaseMod.addPotion(QuicksilverPotion.class, QuicksilverPotion.LIQUID_COLOR, QuicksilverPotion.HYBRID_COLOR, QuicksilverPotion.SPOTS_COLOR, QuicksilverPotion.POTION_ID, null);
        BaseMod.addPotion(SplittingPotion.class, SplittingPotion.LIQUID_COLOR, SplittingPotion.HYBRID_COLOR, SplittingPotion.SPOTS_COLOR, SplittingPotion.POTION_ID, null);
        BaseMod.addPotion(StunPotion.class, StunPotion.LIQUID_COLOR, StunPotion.HYBRID_COLOR, StunPotion.SPOTS_COLOR, StunPotion.POTION_ID, null);
        BaseMod.addPotion(ToxicPotion.class, ToxicPotion.LIQUID_COLOR, ToxicPotion.HYBRID_COLOR, ToxicPotion.SPOTS_COLOR, ToxicPotion.POTION_ID, null);
        TonicLibrary.tonicList.forEach((k, v) -> BaseMod.addPotion(v, null, null, null, k, Invalid.Enums.INVALID_PLAYER_CLASS));

        logger.info("Done editing potions");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");


        BaseMod.addRelicToCustomPool(new PotionKit(), Potionbrewer.Enums.COLOR_CYAN);

        BaseMod.addRelicToCustomPool(new AlchemistKit(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new BunsenBurner(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new ElricsMonocle(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new PaperSwan(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new DrinkingBird(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new RamsHorn(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new BottledElixir(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new BhaskarasWheel(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new AlchemistFlask(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new ToyAutogyro(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new MortarAndPestle(), Potionbrewer.Enums.COLOR_CYAN);
        BaseMod.addRelicToCustomPool(new SalesContract(), Potionbrewer.Enums.COLOR_CYAN);

        UnlockTracker.markRelicAsSeen(AlchemistKit.ID);
        UnlockTracker.markRelicAsSeen(BunsenBurner.ID);
        UnlockTracker.markRelicAsSeen(ElricsMonocle.ID);
        UnlockTracker.markRelicAsSeen(PaperSwan.ID);
        UnlockTracker.markRelicAsSeen(DrinkingBird.ID);
        UnlockTracker.markRelicAsSeen(RamsHorn.ID);
        UnlockTracker.markRelicAsSeen(BottledElixir.ID);
        UnlockTracker.markRelicAsSeen(BhaskarasWheel.ID);
        UnlockTracker.markRelicAsSeen(AlchemistFlask.ID);
        UnlockTracker.markRelicAsSeen(ToyAutogyro.ID);
        UnlockTracker.markRelicAsSeen(MortarAndPestle.ID);
        UnlockTracker.markRelicAsSeen(SalesContract.ID);

        BaseMod.addRelic(new BoosterPack(), RelicType.SHARED);
        BaseMod.addRelic(new Keyblade(), RelicType.SHARED);
        BaseMod.addRelic(new SlideRule(), RelicType.SHARED);
        BaseMod.addRelic(new Torch(), RelicType.SHARED);
        BaseMod.addRelic(new WarMedal(), RelicType.SHARED);

        UnlockTracker.markRelicAsSeen(BoosterPack.ID);
        UnlockTracker.markRelicAsSeen(Keyblade.ID);
        UnlockTracker.markRelicAsSeen(SlideRule.ID);
        UnlockTracker.markRelicAsSeen(Torch.ID);
        UnlockTracker.markRelicAsSeen(WarMedal.ID);
        logger.info("Done adding relics!");
    }

    @SuppressWarnings("GDXJavaUnsafeIterator")
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");

        pathCheck();

        logger.info("Add variables");

        BaseMod.addDynamicVariable(new TurnNumber());
        BaseMod.addDynamicVariable(new ReagentOneDamage());
        BaseMod.addDynamicVariable(new ReagentTwoDamage());
        BaseMod.addDynamicVariable(new ReagentThreeDamage());
        BaseMod.addDynamicVariable(new ReagentOneBlock());
        BaseMod.addDynamicVariable(new ReagentTwoBlock());
        BaseMod.addDynamicVariable(new ReagentThreeBlock());

        logger.info("Adding cards");

        TextureAtlas cardAtlas = ReflectionHacks.getPrivateStatic(AbstractCard.class, "cardAtlas");

        TextureAtlas myCardAtlas = assets.loadAtlas(assetPath("images/cards/cards.atlas"));
        for (TextureAtlas.AtlasRegion region : myCardAtlas.getRegions()) {
            cardAtlas.addRegion(getModID() + "/" + region.name, region);
        }

        new AutoAdd("Potionbrewer")
                .packageFilter(AbstractPotionbrewerCard.class)
                .setDefaultSeen(true)
                .cards();

        BaseMod.addCard(new ChoosePotion());
        UnlockTracker.unlockCard(ChoosePotion.ID);

        logger.info("Done adding cards!");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings for mod with ID: " + getModID());


        String lang = "eng";

        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "zhs";
        } else if (Settings.language == Settings.GameLanguage.RUS) {
            lang = "rus";
        }

        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/" + lang + "/PotionbrewerMod-Card-Strings.json");


        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/" + lang + "/PotionbrewerMod-Power-Strings.json");


        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/" + lang + "/PotionbrewerMod-Relic-Strings.json");


        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/" + lang + "/PotionbrewerMod-Event-Strings.json");


        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/" + lang + "/PotionbrewerMod-Potion-Strings.json");


        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/" + lang + "/PotionbrewerMod-Character-Strings.json");


        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/" + lang + "/PotionbrewerMod-Orb-Strings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class,
                getModID() + "Resources/localization/" + lang + "/PotionbrewerMod-UI-Strings.json");

        BaseMod.loadCustomStringsFile(TutorialStrings.class,
                getModID() + "Resources/localization/" + lang + "/PotionbrewerMod-Tutorial-Strings.json");

        logger.info("Done editing strings");
    }

    @Override
    public void receiveEditKeywords() {

        String lang = "eng";

        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "zhs";
        } else if (Settings.language == Settings.GameLanguage.RUS) {
            lang = "rus";
        }

        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/" + lang + "/PotionbrewerMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveStartGame() {
        PotionLibrary.initializePotionList(AbstractDungeon.player.chosenClass);
        TonicLibrary.initialize();
        reagentList.initialize();
    }

    @Override
    public void receiveStartAct() {
        if (AbstractDungeon.floorNum == 0) {
            reagents.clear();
        }
    }

    @Override
    public void receivePostPotionUse(AbstractPotion potion) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p == null) {
            return;
        }

        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            Integer combat = PotionTracker.potionsUsedThisCombat.get(p);
            PotionTracker.potionsUsedThisCombat.set(p, combat + 1);

            Integer turn = PotionTracker.potionsUsedThisTurn.get(p);
            PotionTracker.potionsUsedThisTurn.set(p, turn + 1);

            for (AbstractCard c : p.hand.group) {
                c.triggerOnGlowCheck();
                if (c instanceof PotionTrackingCard) {
                    ((PotionTrackingCard) c).onUsePotion(potion);
                }
            }

            for (AbstractCard c : p.discardPile.group) {
                if (c instanceof PotionTrackingCard) {
                    ((PotionTrackingCard) c).onUsePotion(potion);
                }
            }

            for (AbstractCard c : p.drawPile.group) {
                if (c instanceof PotionTrackingCard) {
                    ((PotionTrackingCard) c).onUsePotion(potion);
                }
            }

            ArrayList<AbstractCreature> creatures = new ArrayList<>(AbstractDungeon.getCurrRoom().monsters.monsters);
            creatures.add(AbstractDungeon.player);
            for (AbstractCreature creature : creatures) {
                for (AbstractPower power : creature.powers) {
                    if (power instanceof PotionTrackingPower) {
                        ((PotionTrackingPower) power).onUsePotion(potion);
                    }
                }
            }
        }

        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            if (relic instanceof UsePotionRelic) {
                ((UsePotionRelic) relic).customOnUsePotion(potion);
            }
        }
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p == null) {
            return;
        }

        positionReagents();

        PotionTracker.potionsUsedThisCombat.set(p, 0);
        PotionTracker.potionsUsedThisTurn.set(p, 0);

        lastPlayedCardCostZero = false;
        zeroCostCardsThisTurn = 0;
        zeroCostCardsThisCombat = 0;
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        AbstractPlayer p = AbstractDungeon.player;
        PotionTracker.potionsUsedThisCombat.set(p, 0);
        PotionTracker.potionsUsedThisTurn.set(p, 0);
        lastPlayedCardCostZero = false;
        zeroCostCardsThisCombat = 0;
        zeroCostCardsThisTurn = 0;
    }

    @Override
    public String onSave() {
        return reagents.stream()
                .map(Object::toString)
                .collect(Collectors.joining(DELIM));
    }

    @Override
    public void onLoad(String reagentStr) {
        reagents.clear();
        if (reagentStr != null && !reagentStr.equals("")) {
            for (String r : reagentStr.split(DELIM)) {
                Reagent o = ReagentList.fromId(r);
                reagents.add(o);
            }
        }
    }

    public static int zeroCostCardsThisTurn = 0;
    public static int zeroCostCardsThisCombat = 0;
    public static boolean lastPlayedCardCostZero = false;

    @Override
    public void receiveCardUsed(AbstractCard card) {
        if (card instanceof FollowupCard) {
            return;
        }
        lastPlayedCardCostZero = (card.costForTurn == 0 || card.freeToPlayOnce) && !card.purgeOnUse;
        if (lastPlayedCardCostZero) {
            zeroCostCardsThisTurn += 1;
            zeroCostCardsThisCombat += 1;
        }
        for (AbstractCard inHand : AbstractDungeon.player.hand.group) {
            if (inHand instanceof FollowupCard) {
                inHand.triggerOnGlowCheck();
            }
        }
    }

    @Override
    public void receivePostEnergyRecharge() {
        PotionTracker.potionsUsedThisTurn.set(AbstractDungeon.player, 0);
        zeroCostCardsThisTurn = 0;
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    public static void positionReagents() {
        final float LEFT = 200f * Settings.xScale;
        final float RIGHT = 550f * Settings.xScale;

        IntStream.range(0, reagents.size())
                .forEachOrdered(i -> {
                    Reagent r = reagents.get(i);
                    r.tX = Interpolation.linear.apply(RIGHT, LEFT, ((float) i) / reagents.size());
                    r.hb.move(r.tX, r.tY);
                });
    }

    public static void addReagent(Reagent toAdd) {
        int max = 3;
        if (CardCrawlGame.isInARun() &&
                AbstractDungeon.currMapNode != null &&
                AbstractDungeon.getCurrRoom() != null &&
                AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractPower hoarderPower = AbstractDungeon.player.getPower(HoarderPower.POWER_ID);
            if (hoarderPower != null) {
                max += hoarderPower.amount;
            }
        }
        while (reagents.size() >= max) {
            reagents.remove(0);
        }
        reagents.add(toAdd);
        positionReagents();
    }

    public static Reagent oldestReagent() {
        if (reagents.size() > 0) {
            return reagents.get(0);
        }
        return null;
    }

    public static Reagent popReagent() {
        Reagent toRemove = oldestReagent();
        if (toRemove != null) {
            reagents.remove(toRemove);
            positionReagents();
        }
        return toRemove;
    }
}
