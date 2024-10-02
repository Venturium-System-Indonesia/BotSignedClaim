import home_bot.BotSatu;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {

        TelegramBotsLongPollingApplication botsApi = null;
        try {
            String botToken = "7884693944:AAF8C5r0AKLEny2VMtQ7Fq4b04RdeA5i-fA";
            botsApi = new TelegramBotsLongPollingApplication();
            botsApi.registerBot(botToken, new BotSatu());


        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

