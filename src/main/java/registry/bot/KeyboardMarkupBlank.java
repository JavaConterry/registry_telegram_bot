package registry.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

public class KeyboardMarkupBlank {

    public Keyboard replyMarkup;

    public KeyboardMarkupBlank(String[] buttonNames){
        replyMarkup = new ReplyKeyboardMarkup(buttonNames)
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);
    }

    public void execute(TelegramBot bot, long chatId){
        SendMessage sm = new SendMessage(chatId, "look into menu");
        sm.replyMarkup(replyMarkup);
        bot.execute(sm);
    }

    public void execute(TelegramBot bot, long chatId, String message){
        SendMessage sm = new SendMessage(chatId, message);
        sm.replyMarkup(replyMarkup);
        bot.execute(sm);
    }
}
