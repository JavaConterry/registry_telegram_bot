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

        if(message.text().equals("/start")){
            bot.execute(new SendMessage(chatId, "aoaoaoaooa"));
        }
        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"first row button1", "first row button2"},
                new String[]{"second row button1", "second row button2"})
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);

//        SendMessage sm = new SendMessage(chatId, "a");
//        sm.replyMarkup(replyKeyboardMarkup);

        bot.execute(new SendMessage(chatId, "Hello!"));
    }
}
