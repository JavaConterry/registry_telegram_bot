package registry.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class Bot {

    private static final TelegramBot bot = new TelegramBot(System.getenv("BOT_TOKEN"));

    public void serve(){
        bot.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void process(Update update) {
        long chatId = update.message().chat().id();
        Message message = update.message();

        KeyboardMarkupBlank mainKeyboardMarkup =
                new KeyboardMarkupBlank(new String[]{"register📠", "documents📃"});
        KeyboardMarkupBlank registerKeyboardMarkup =
                new KeyboardMarkupBlank(new String[]{"register✅", "call off❌", "<- back"});
        KeyboardMarkupBlank registerDataKeyboardMarkup =
                new KeyboardMarkupBlank(new String[]{"choose date", "<- back to menu"});

        if(message.text().equals("/start")){
            mainKeyboardMarkup.execute(bot, chatId);
            System.out.println("all is right");
        }
        if(message.text().equals("register📠")){
            registerKeyboardMarkup.execute(bot, chatId);
        }
        if(message.text().equals("<- back")){
            mainKeyboardMarkup.execute(bot, chatId);
        }
        if(message.text().equals("register✅")){
            registerDataKeyboardMarkup.execute(bot, chatId);
        }
        if(message.text().equals("<- back to menu")){
            mainKeyboardMarkup.execute(bot, chatId);
        }

        bot.execute(new SendMessage(chatId, "bot_is_active"));
    }
}
