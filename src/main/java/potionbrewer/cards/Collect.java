package potionbrewer.cards;

import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.FtueTip;
import potionbrewer.PotionbrewerMod;
import potionbrewer.PotionbrewerTipTracker;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.orbs.Reagent;
import potionbrewer.util.*;
import potionbrewer.vfx.CollectEffect;

import java.util.HashMap;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerTipTracker.REAGENTS;

public class Collect extends AbstractPotionbrewerCard {

    public static final String ID = PotionbrewerMod.makeID(Collect.class.getSimpleName());

    public static final TutorialStrings TUTORIAL_STRINGS = CardCrawlGame.languagePack.getTutorialString(REAGENTS);
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 0;

    private static final HashMap<String, Class<? extends Reagent>> monsterReagents = new HashMap<>();

    public Collect() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        exhaust = true;
        cardsToPreview = new Distill();
    }

    public static AbstractOrb getOrbForMonster(AbstractMonster m) {
        if (monsterReagents.containsKey(m.id)) {
            try {
                Class<? extends Reagent> clz = monsterReagents.get(m.id);
                return clz.newInstance();
            } catch (Exception d) {
                System.out.println("Problem with orb for monster! m=" + m);
            }
        }
        return PotionbrewerMod.reagentList.randomReagent();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new CollectEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY)));
        addToBot(new MakeTempCardInHandAction(new Distill()));
        AbstractOrb orb = getOrbForMonster(m);
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
        if (!PotionbrewerTipTracker.hasShown(REAGENTS)) {
            PotionbrewerTipTracker.neverShowAgain(REAGENTS);
            AbstractDungeon.ftue = new FtueTip(TUTORIAL_STRINGS.LABEL[0], TUTORIAL_STRINGS.TEXT[0], (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, FtueTip.TipType.COMBAT);
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    static {
        // Base Game Monsters
        SpireReagentHelper.setupReagentsForCollect(monsterReagents);

        // TheJungle
        if (Loader.isModLoaded("TheJungle")) {
            JungleReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        // TheFactory
        if (Loader.isModLoaded("TheFactory")) {
            FactoryReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        //ReplayTheSpire
        if (Loader.isModLoaded("ReplayTheSpireMod")) {
            ReplayReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        //InfiniteSpire
        if (Loader.isModLoaded("infinitespire")) {
            InfinitespireReagentHelper.setupReagentsForCollect(monsterReagents);
        }


        //Hubris
        if (Loader.isModLoaded("hubris")) {
            HubrisReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        //Conspire
        if (Loader.isModLoaded("conspire")) {
            ConspireReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        //MimicMod
        if (Loader.isModLoaded("mimicmod")) {
            MimicmodReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        //StrawberrySpire
        if (Loader.isModLoaded("StrawberrySpire")) {
            StrawberryReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        if (Loader.isModLoaded("hollowmod")) {
            HollowknightReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        //Gensokyo
        if (Loader.isModLoaded("Gensokyo")) {
            GensokyoReagentHelper.setupReagentsForCollect(monsterReagents);
        }


        //Wandering Minibosses
        if (Loader.isModLoaded("wanderingMiniBosses")) {
            WanderingMinibossesReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        //Pale of the Ancients
        if (Loader.isModLoaded("paleoftheancients")) {
            PaleoftheancientsReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        //Hallownest
        if (Loader.isModLoaded("Hallownest")) {
            HallownestReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        //Elementarium
        if (Loader.isModLoaded("Elementarium")) {
            ElementariumReagentHelper.setupReagentsForCollect(monsterReagents);
        }

        //Menagerie
        if (Loader.isModLoaded("Menagerie")) {
            MenagerieReagentHelper.setupReagentsForCollect(monsterReagents);
        }

    }
}