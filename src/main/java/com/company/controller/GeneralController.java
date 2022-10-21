package com.company.controller;

import com.company.Language;
import com.company.util.InlineButtonUtil;
import com.company.util.LanguageUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneralController {
    private MyEvosClone myEvosClone;

    public GeneralController(MyEvosClone myEvosClone) {
        this.myEvosClone = myEvosClone;
    }

    public String handle(String text, Long chatId, Integer messageId) {
        SendMessage sendMessage = new SendMessage();
        SendPhoto sendPhoto=new SendPhoto();
        sendMessage.setChatId(chatId);
        if (text.equals("/start") ||text.equals(LanguageUtil.BACK_UZ)||text.equals(LanguageUtil.BACK_RU)) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton keyboardButton = new KeyboardButton(myEvosClone.language.equals(Language.Uz) ? LanguageUtil.cource_UZ : LanguageUtil.cource_Ru);
            KeyboardRow keyboardRow1 = new KeyboardRow();

            KeyboardRow keyboardRow2= new KeyboardRow();
            KeyboardButton keyboardButton2 = new KeyboardButton(myEvosClone.language.equals(Language.Uz) ? LanguageUtil.Fikr_Uz : LanguageUtil.Fikr_Ru);
            KeyboardButton keyboardButton3 = new KeyboardButton(myEvosClone.language.equals(Language.Uz) ? LanguageUtil.SETTINGS_UZ : LanguageUtil.SETTINGS_RU);

            keyboardRow.add(keyboardButton);
            keyboardRow2.add(keyboardButton2);
            keyboardRow2.add(keyboardButton3);

            keyboard.add(keyboardRow);
            keyboard.add(keyboardRow1);
            keyboard.add(keyboardRow2);
            File myPhoto = new File("ielts-smapse.jpg");
            InputFile inputFile = new InputFile();
            inputFile.setMedia(myPhoto);

            replyKeyboardMarkup.setKeyboard(keyboard);
            sendPhoto.setReplyMarkup(replyKeyboardMarkup);
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setCaption(myEvosClone.language.equals(Language.Uz) ? LanguageUtil.title_uz : LanguageUtil.title_ru);
            myEvosClone.send(sendPhoto);

        } else if (text.equals("/help")) {
            String msg="Nima yordam kerak [inline URL]{http://www.youtube.com/}";
            sendMessage.setText(msg);
            sendMessage.setParseMode("Markdown");
            sendMessage.disableWebPagePreview();

        }


        return "->";
    }
}
