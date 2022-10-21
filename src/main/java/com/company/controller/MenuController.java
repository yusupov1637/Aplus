package com.company.controller;

import com.company.Language;
import com.company.model.Meal;
import com.company.model.TelergamBotUser;
import com.company.model.UserStatus;
import com.company.util.InlineButtonUtil;
import com.company.util.LanguageUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuController {
    private MyEvosClone myEvosClone;
    TelergamBotUser telergamBotUser;
    public MenuController(MyEvosClone myEvosClone) {
        this.myEvosClone = myEvosClone;
    }

    public String handle(String text, Long chatId, Integer messageId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText((myEvosClone.language.equals(Language.Uz)?LanguageUtil.CATEGORY_HEADER_UZ:LanguageUtil.CATEGORY_HEADER_RU));
       // telergamBotUser.setStatus(UserStatus.Choosing);
        EditMessageText editMessageText=new EditMessageText();
        switch (text) {

            case LanguageUtil.cource_Ru ,LanguageUtil.cource_UZ -> {
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow keyboardRow = new KeyboardRow();
                KeyboardButton keyboardButton = new KeyboardButton("IELTS");
                KeyboardButton keyboardButton3 = new KeyboardButton("Pre-IELTS");
                KeyboardRow keyboardRow1 = new KeyboardRow();
                KeyboardButton keyboardButton1 = new KeyboardButton("SAT");
                KeyboardButton keyboardButton4 = new KeyboardButton("General English");
                KeyboardRow keyboardRow3 = new KeyboardRow();
                KeyboardButton keyboardButton2 = new KeyboardButton(myEvosClone.language.equals(Language.Uz)?LanguageUtil.BACK_UZ:LanguageUtil.BACK_RU);
                keyboardRow.add(keyboardButton);
                keyboardRow.add(keyboardButton3);
                keyboardRow1.add(keyboardButton1);
                keyboardRow1.add(keyboardButton4);
                keyboardRow3.add(keyboardButton2);
                keyboard.add(keyboardRow);
                keyboard.add(keyboardRow1);
                replyKeyboardMarkup.setKeyboard(keyboard);
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
                sendMessage.setChatId(chatId);

                myEvosClone.send(sendMessage);
            }

        }
        return "->";
    }

}
