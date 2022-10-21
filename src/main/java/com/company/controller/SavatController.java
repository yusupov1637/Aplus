package com.company.controller;

import com.company.Language;
import com.company.model.TelergamBotUser;
import com.company.util.InlineButtonUtil;
import com.company.util.LanguageUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SavatController {
    private MyEvosClone myEvosClone;

    public SavatController(MyEvosClone myEvosClone) {
        this.myEvosClone = myEvosClone;
    }
    public String handle(String text, Long chatId, Integer messageId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        TelergamBotUser telergamBotUser=new TelergamBotUser();
       /* if (text.equals( LanguageUtil.Saved_Uz) ||text.equals( LanguageUtil.Saved_Ru)){
            sendMessage.setChatId(chatId);
            sendMessage.setText(myEvosClone.language.equals(Language.Uz)?LanguageUtil.Buyurtma_uz:LanguageUtil.Buyurtma_ru+"\n"+myEvosClone.meals.toString());
            sendMessage.setReplyMarkup(InlineButtonUtil.keyboardMarkup(InlineButtonUtil.rowCollection(
                    InlineButtonUtil.row(InlineButtonUtil.button(myEvosClone.language.equals(Language.Uz)?LanguageUtil.BuyurtB_Uz:LanguageUtil.BuyurtB_Ru,"/buyurtma")),
                    InlineButtonUtil.row(InlineButtonUtil.button(myEvosClone.language.equals(Language.Uz)?LanguageUtil.ToClear_Uz:LanguageUtil.ToClear_Ru,"/tozalash")),
                    InlineButtonUtil.row(InlineButtonUtil.button(myEvosClone.language.equals(Language.Uz)?LanguageUtil.BACK_UZ1:LanguageUtil.BACK_RU1,"/ortga"))
            )));
            myEvosClone.send(sendMessage);
        }*/
        return "->";
    }
}
