package potionbrewer.events;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import potionbrewer.PotionbrewerMod;
import potionbrewer.orbs.*;

import static potionbrewer.PotionbrewerMod.makeEventPath;

/*
    Event to collect rare reagents
 */
public class JunkPileEvent extends AbstractImageEvent {
    public static final String ID = PotionbrewerMod.makeID("JunkPileEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = makeEventPath("JunkPileEvent.png");

    private JunkPileEvent.CurScreen screen;
    private int digDamage = 5;
    private int digDeepDamage = 10;

    public JunkPileEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);

        screen = CurScreen.START;

        if (AbstractDungeon.ascensionLevel >= 15) {
            digDamage = 8;
            digDeepDamage = 15;
        }

        imageEventText.setDialogOption(OPTIONS[0]); // Dig
        imageEventText.setDialogOption(OPTIONS[14]); // Leave
    }

    @Override
    protected void buttonEffect(int buttonIndex) {
        switch (screen) {
            case START: // starting screen
                switch (buttonIndex) {
                    case 0: // dig
                        screen = CurScreen.COMMON;
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[1]);
                        imageEventText.setDialogOption(OPTIONS[2]);
                        imageEventText.setDialogOption(OPTIONS[3]);
                        imageEventText.setDialogOption(OPTIONS[4] + digDamage + OPTIONS[5]);
                        break;
                    case 1: // leave
                        screen = CurScreen.END;
                        imageEventText.updateBodyText(DESCRIPTIONS[5]);
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[14]);
                        openMap();
                        break;
                }
                break;
            case COMMON: // choose a common reagent or dig deeper
                switch (buttonIndex) {
                    case 0: // Feather
                        PotionbrewerMod.reagents.add(new Beak());
                        break;
                    case 1: // Silk
                        PotionbrewerMod.reagents.add(new Silk());
                        break;
                    case 2: // Tooth
                        PotionbrewerMod.reagents.add(new Tooth());
                        break;
                    case 3: // Dig Deeper
                        screen = CurScreen.UNCOMMON;
                        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
                        CardCrawlGame.sound.play("BLUNT_FAST");
                        AbstractDungeon.player.damage(new DamageInfo(null, digDamage, DamageInfo.DamageType.HP_LOSS));
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[6]);
                        imageEventText.setDialogOption(OPTIONS[7]);
                        imageEventText.setDialogOption(OPTIONS[8]);
                        imageEventText.setDialogOption(OPTIONS[9] + digDeepDamage + OPTIONS[5]);
                        break;
                }
                if (buttonIndex < 3) {
                    screen = CurScreen.END;
                    imageEventText.updateBodyText(DESCRIPTIONS[4]);
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(OPTIONS[14]);
                }
                break;
            case UNCOMMON: // choose an uncommon reagent or dig deeper
                switch (buttonIndex) {
                    case 0: // Steel
                        PotionbrewerMod.reagents.add(new Steel());
                        break;
                    case 1: // Barb
                        PotionbrewerMod.reagents.add(new Barb());
                        break;
                    case 2: // Bludgeon
                        PotionbrewerMod.reagents.add(new Bludgeon());
                        break;
                    case 3: // Dig deepest
                        screen = CurScreen.RARE;
                        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.LONG, false);
                        CardCrawlGame.sound.play("BLUNT_FAST");
                        AbstractDungeon.player.damage(new DamageInfo(null, digDeepDamage, DamageInfo.DamageType.HP_LOSS));
                        imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[10]);
                        imageEventText.setDialogOption(OPTIONS[11]);
                        imageEventText.setDialogOption(OPTIONS[12]);
                        imageEventText.setDialogOption(OPTIONS[13]);
                        break;
                }
                if (buttonIndex < 3) {
                    screen = CurScreen.END;
                    imageEventText.updateBodyText(DESCRIPTIONS[4]);
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(OPTIONS[14]);
                }
                break;
            case RARE: // choose a rare reagent
                switch (buttonIndex) {
                    case 0: // Storybook
                        PotionbrewerMod.reagents.add(new Storybook());
                        break;
                    case 1: // Train Ticket
                        PotionbrewerMod.reagents.add(new TrainTicket());
                        break;
                    case 2: // Treasure Chest
                        PotionbrewerMod.reagents.add(new Chest());
                        break;
                    case 3: // Radiance
                        PotionbrewerMod.reagents.add(new Radiance());
                        break;
                }
                imageEventText.updateBodyText(DESCRIPTIONS[4]);
                imageEventText.clearAllDialogs();
                imageEventText.setDialogOption(OPTIONS[14]);
                screen = CurScreen.END;
                break;
            case END:
                imageEventText.updateDialogOption(0, OPTIONS[14]);
                imageEventText.clearRemainingOptions();
                openMap();
        }
    }

    private enum CurScreen {
        START,
        COMMON,
        UNCOMMON,
        RARE,
        END;

        CurScreen() {
        }
    }
}
