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

        Keyboard registryKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"register", "<- back"})
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);

        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"I want to be registred", "<- back"})
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);


        if(message.text().equals("/start")){
            SendMessage sn = new SendMessage(chatId, "mmmmm");
            sn.replyMarkup(registryKeyboardMarkup);
            bot.execute(sn);
        }




        if(update.message().text().equals("register")){
            SendMessage sm = new SendMessage(chatId, "ooooooooooo");
            sm.replyMarkup(replyKeyboardMarkup);
            bot.execute(sm);
        }

        bot.execute(new SendMessage(chatId, "Hello!"));
    }
}
