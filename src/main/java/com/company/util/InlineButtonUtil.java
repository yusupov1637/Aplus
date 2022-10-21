package com.company.util;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InlineButtonUtil {
    public static InlineKeyboardButton button(String text, String callback){
        InlineKeyboardButton button=new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callback);
        return button;
    }
    public static InlineKeyboardButton button(String text, String callback,String emoji){
        String parseToUnicode = EmojiParser.parseToUnicode(emoji + " " + text);
        InlineKeyboardButton button=new InlineKeyboardButton();
        button.setText(parseToUnicode);
        button.setCallbackData(callback);
        return button;
    }
    public static List<InlineKeyboardButton> row(InlineKeyboardButton... inlineKeyboardButtons){
        List<InlineKeyboardButton> row=new ArrayList<>();
        row.addAll(Arrays.asList(inlineKeyboardButtons));
        return row;

    }
    public static List<List<InlineKeyboardButton>> rowCollection(List<InlineKeyboardButton> ... rows){
        List<List<InlineKeyboardButton>> collection=new LinkedList<>();
        collection.addAll(Arrays.asList(rows));
        return collection;
    }
    public static InlineKeyboardMarkup keyboardMarkup(List<List<InlineKeyboardButton>> collection){
        InlineKeyboardMarkup inlineKeyboardMarkup=new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(collection);
        return inlineKeyboardMarkup;
    }

/*    private ReplyKeyboardMarkup getInitKeyboard(String text) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton(text);

        keyboardButton.setRequestLocation(true);
        keyboardRow.add(keyboardButton);
        keyboard.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }*/
}
