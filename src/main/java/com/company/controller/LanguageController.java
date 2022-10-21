package com.company.controller;

import com.company.Language;
import com.company.util.LanguageUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class LanguageController {
    private MyEvosClone myEvosClone;

    public LanguageController(MyEvosClone myEvosClone) {
        this.myEvosClone = myEvosClone;
    }


    public String handle(String text, Long chatId, Integer messageId) {
        SendMessage sendMessage = new SendMessage();
        if (text.equals(LanguageUtil.CHANGE_LANG_UZ) || text.equals(LanguageUtil.CHANGE_LANG_RU)) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton keyboardButton1 = new KeyboardButton("\uD83C\uDDEC\uD83C\uDDE7 English");
            KeyboardButton keyboardButton2 = new KeyboardButton("\uD83C\uDDFA\uD83C\uDDFF O'zbekcha");
            KeyboardRow keyboardRow1 = new KeyboardRow();
            KeyboardButton keyboardButton3 = new KeyboardButton(myEvosClone.language.equals(Language.Uz) ? LanguageUtil.BACK_UZ : LanguageUtil.BACK_RU );

            keyboardRow.add(keyboardButton1);
            keyboardRow.add(keyboardButton2);
            keyboardRow1.add(keyboardButton3);
            keyboard.add(keyboardRow);
            keyboard.add(keyboardRow1);
            replyKeyboardMarkup.setKeyboard(keyboard);

            sendMessage.setText("Buyurtma bermoqchi bolgan manzilingiz to'grimi");
            sendMessage.setChatId(chatId);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);

            myEvosClone.send(sendMessage);
        }

        return "->";

    }
}
