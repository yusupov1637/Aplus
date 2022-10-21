package com.company.controller;

import com.company.Database;
import com.company.Language;
import com.company.model.Meal;
import com.company.model.TelergamBotUser;
import com.company.model.UserStatus;
import com.company.util.InlineButtonUtil;
import com.company.util.LanguageUtil;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

public class MyEvosClone extends TelegramLongPollingBot {
    private static final String Token = "5413749330:AAGq27o59G8fK68rQMTgLo3qhHCJHcVOHls";
    private static final String UserName = "aplus_academy_bot";
    private GeneralController generalController;
    Database database = new Database();
    private SavatController savatController;
    private List<TelergamBotUser> users = new LinkedList<>();
    List<Meal> mealList = new LinkedList<>();
    private Integer count = 0;
    Language language = Language.Uz;

    private MenuController menuController;
    private LanguageController languageController;

    private Integer zaqazid = 1;
    private String phoneNumber = null;
    private String userrname = null;
    private String coursename = null;
    private Location location;
    private LocalDateTime now;
    private Double total;
    Map<String, Double> meals = new HashMap();

    public MyEvosClone() {
        this.generalController = new GeneralController(this);
        this.menuController = new MenuController(this);
        this.savatController = new SavatController(this);
        this.languageController = new LanguageController(this);

    }
//569153259 Sh
    //428619012 Izzat
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message message1 = update.getMessage();
        try {
            SendMessage sendMessage = new SendMessage();
            if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                String data = callbackQuery.getData();
                Message message = callbackQuery.getMessage();
                EditMessageText editMessageText = new EditMessageText();
                switch (data) {
                    case "advanced C1,C2", "intermediate B1,B2", "elementary A1,A2" -> {
                        PrintWriter printWriter = new PrintWriter(new FileWriter("Users.txt", true));
                        printWriter.println();
                        printWriter.write(userrname + "\n" + phoneNumber + " \n" + data + "\n ");
                        printWriter.println();
                        printWriter.flush();
                        printWriter.close();
                        SendMessage sendToAdmin = new SendMessage();
                        sendToAdmin.setChatId("1489952519");
                        sendToAdmin.setText("Student haqida ma'lumot " + userrname + "\n" + "Telefon raqam " + phoneNumber + " \n" + "English level " + data + "\n"+ "Course "+ coursename);
                        send(sendToAdmin);

                        sendMessage.setChatId(message.getChatId());
                        sendMessage.setText(language.equals(Language.Uz) ? LanguageUtil.acceptedUz : LanguageUtil.acceptedEng);
                        send(sendMessage);
                    }
                    /*case "/plus" -> {
                        count++;
//                         editMessageText = new EditMessageText();
                        editMessageText.setChatId(message.getChatId());
                        editMessageText.setMessageId(message.getMessageId());
                        editMessageText.setText(menuController.b + " " + menuController.sum);
                        editMessageText.setReplyMarkup(InlineButtonUtil.keyboardMarkup(InlineButtonUtil.rowCollection(
                                InlineButtonUtil.row(InlineButtonUtil.button("-", "/minus")),
                                InlineButtonUtil.row(InlineButtonUtil.button(count.toString(), "/count")),
                                InlineButtonUtil.row(InlineButtonUtil.button("+", "/plus")),
                                InlineButtonUtil.row(InlineButtonUtil.button(language.equals(Language.Uz) ? LanguageUtil.Save_UZ : LanguageUtil.Sava_Ru, "/saveto"))

                        )));
                        ;
                        send(editMessageText);


                       *//* String substring = message.getText().substring(0, 24);
                        editMessageText.setMessageId(message.getMessageId());
                        editMessageText.setChatId(message.getChatId());
                        editMessageText.setText(substring + "  " + String.valueOf(count));
                        editMessageText.setReplyMarkup(message.getReplyMarkup());*//*


                    }*/

                    case "/buyurtma" -> {
                        now = LocalDateTime.now();

                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        replyKeyboardMarkup.setSelective(true);
                        replyKeyboardMarkup.setResizeKeyboard(true);
                        List<KeyboardRow> keyboard = new ArrayList<>();
                        KeyboardRow keyboardRow1 = new KeyboardRow();
                        KeyboardButton keyboardButton1 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.SentN_uz : LanguageUtil.SentN_ru);
                        keyboardButton1.setRequestContact(true);
                        KeyboardButton keyboardButton2 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.BACK_UZ : LanguageUtil.BACK_RU);
                        keyboardRow1.add(keyboardButton1);
                        keyboardRow1.add(keyboardButton2);
                        keyboard.add(keyboardRow1);
                        replyKeyboardMarkup.setKeyboard(keyboard);
                        sendMessage.setText("Telefon raqamingizni quyidagi formatda yuboring yoki kiriting: +998 ** *** ** **\n" +
                                "Eslatma: Agar siz onlayn buyurtma uchun Click yoki Payme orqali toʻlashni rejalashtirmoqchi boʻlsangiz, tegishli xizmatda hisob qaydnomasi roʻyxatdan oʻtgan telefon raqamini koʻrsating.");
                        sendMessage.setReplyMarkup(replyKeyboardMarkup);
                        sendMessage.setChatId(message.getChatId());
                        send(sendMessage);
                    }
                }
            } else if (message1 != null) {

                if (message1.hasLocation()) {
                    location = message1.getLocation();

                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setSelective(true);
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    List<KeyboardRow> keyboard = new ArrayList<>();
                    KeyboardRow keyboardRow = new KeyboardRow();
                    KeyboardButton keyboardButton1 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.Yoq_Uz : LanguageUtil.Yoq_Ru);
                    KeyboardButton keyboardButton2 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.HA_Uz : LanguageUtil.HA_Ru);
                    KeyboardRow keyboardRow1 = new KeyboardRow();
                    KeyboardButton keyboardButton3 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.BACK_UZ : LanguageUtil.BACK_RU);

                    keyboardRow.add(keyboardButton1);
                    keyboardRow.add(keyboardButton2);
                    keyboardRow1.add(keyboardButton3);
                    keyboard.add(keyboardRow);
                    keyboard.add(keyboardRow1);
                    replyKeyboardMarkup.setKeyboard(keyboard);

                    sendMessage.setText(language.equals(Language.Uz) ? LanguageUtil.TrueLoc_Uz : LanguageUtil.TrueLoc_Ru);
                    sendMessage.setChatId(message1.getChatId());
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                    send(sendMessage);


                } else if (message1.hasContact()) {
                    phoneNumber = message1.getContact().getPhoneNumber();
                    sendMessage.setChatId(message1.getChatId());
                    sendMessage.setText(language.equals(Language.Uz) ? LanguageUtil.levelUz : LanguageUtil.levelEng);
                    sendMessage.setReplyMarkup(InlineButtonUtil.keyboardMarkup(InlineButtonUtil.rowCollection(
                            InlineButtonUtil.row(InlineButtonUtil.button("Elementary A1,A2", "elementary A1,A2", "\uD83E\uDD64")),
                            InlineButtonUtil.row(InlineButtonUtil.button("Intermediate B1,B2", "intermediate B1,B2", "\uD83E\uDD64")),
                            InlineButtonUtil.row(InlineButtonUtil.button("Advanced C1,C2", "advanced C1,C2", "\uD83E\uDD64")),
                            InlineButtonUtil.row(InlineButtonUtil.button("Back", "⬅️ Ortga", "\uD83E\uDD64"))

                    )));
                    send(sendMessage);

                } else {
                    String text = message1.getText();

                    SendMessage sendMessage1 = new SendMessage();
                    sendMessage1.setChatId(message1.getChatId());
                    SendPhoto sendPhoto = new SendPhoto();
                    User user = message1.getFrom();
                    System.out.println(user.getFirstName() + " " + message1.getText() + " " + message1.getMessageId());
                    if (text != null) {
                        if (text.equals("/start") || text.equals(LanguageUtil.BACK_UZ) || text.equals(LanguageUtil.BACK_RU)) {
                            System.out.println(message1.getChatId());
                            this.send(this.generalController.handle(text, message1.getChatId(), message1.getMessageId()));
                        } else if (text.equals(LanguageUtil.MENU_UZ) || text.equals(LanguageUtil.MENU_RU)
                                || text.equals(LanguageUtil.cource_UZ) || text.equals(LanguageUtil.cource_Ru)
                                || text.equals("Lavash")
                                || text.equals(LanguageUtil.Yoq_Ru) || text.equals(LanguageUtil.Yoq_Uz) || text.equals(LanguageUtil.HA_Ru) || text.equals(LanguageUtil.HA_Uz)
                                || text.equals(LanguageUtil.BACK_RU) || text.equals(LanguageUtil.BACK_UZ)
                                || text.equals(LanguageUtil.BACK_RU1) || text.equals(LanguageUtil.BACK_UZ1) || text.equals("Set")
                                || text.equals("COMBO+") || text.equals("Kids COMBO") || text.equals("\uD83E\uDD69Mol Go'shti")
                                || text.equals("\uD83C\uDF57Tovuq Go'shti")
                                || text.equals("Pepsi 0.5") || text.equals("Pepsi 1.5") || text.equals("Pepsi 0.4") || text.equals("Ichimliklar")
                                || text.equals("Burger") || text.equals("Gamburger") || text.equals("Chizburger")
                        ) {
                            this.send(this.menuController.handle(text, message1.getChatId(), message1.getMessageId()));
                        }  else if (text.equals("IELTS")) {
                            File myPhoto = new File("ielts.png");
                            InputFile inputFile = new InputFile();
                            inputFile.setMedia(myPhoto);
                            sendPhoto.setChatId(message1.getChatId());
                            sendPhoto.setPhoto(inputFile);
                            coursename = message1.getText();
                            sendPhoto.setCaption(language.equals(Language.Uz) ? LanguageUtil.ielts_uz : LanguageUtil.ielts_ru);
                            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                            replyKeyboardMarkup.setSelective(true);
                            replyKeyboardMarkup.setResizeKeyboard(true);
                            List<KeyboardRow> keyboard = new ArrayList<>();
                            KeyboardRow keyboardRow = new KeyboardRow();
                            KeyboardRow keyboardRow2 = new KeyboardRow();
                            KeyboardButton keyboardButton2 = new KeyboardButton( "IELTS Standard");
                            KeyboardButton keyboardButton4 = new KeyboardButton( "IELTS Express");
                            KeyboardButton keyboardButton5 = new KeyboardButton( "IELTS Advanced");
                            KeyboardButton keyboardButton3 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.BACK_UZ : LanguageUtil.BACK_RU);

                            keyboardRow.add(keyboardButton2);
                            keyboardRow.add(keyboardButton4);
                            keyboardRow.add(keyboardButton5);
                            keyboardRow2.add(keyboardButton3);
                            keyboard.add(keyboardRow);
                            keyboard.add(keyboardRow2);
                            replyKeyboardMarkup.setKeyboard(keyboard);
                            sendPhoto.setReplyMarkup(replyKeyboardMarkup);

                            send(sendPhoto);
                        } else if (text.equals("Pre-Ielts")) {
                            File myPhoto = new File("ielts.png");
                            InputFile inputFile = new InputFile();
                            inputFile.setMedia(myPhoto);
                            sendPhoto.setChatId(message1.getChatId());
                            sendPhoto.setPhoto(inputFile);
                            coursename = message1.getText();
                            sendPhoto.setCaption(language.equals(Language.Uz) ? LanguageUtil.ielts_uz : LanguageUtil.ielts_ru);
                            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                            replyKeyboardMarkup.setSelective(true);
                            replyKeyboardMarkup.setResizeKeyboard(true);
                            List<KeyboardRow> keyboard = new ArrayList<>();
                            KeyboardRow keyboardRow = new KeyboardRow();
                            KeyboardButton keyboardButton2 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.applicationuz : LanguageUtil.applicationru);
                            KeyboardButton keyboardButton3 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.BACK_UZ : LanguageUtil.BACK_RU);

                            keyboardRow.add(keyboardButton2);
                            keyboardRow.add(keyboardButton3);
                            keyboard.add(keyboardRow);
                            replyKeyboardMarkup.setKeyboard(keyboard);
                            sendPhoto.setReplyMarkup(replyKeyboardMarkup);

                            send(sendPhoto);
                        } else if (text.equals("General English")) {
                            java.io.File myPhoto = new File("GENERAL-ENGLISH-FEATURED-IMAGE.png");
                            InputFile inputFile = new InputFile();
                            inputFile.setMedia(myPhoto);
                            sendPhoto.setChatId(message1.getChatId());
                            sendPhoto.setPhoto(inputFile);
                            coursename = message1.getText();
                            sendPhoto.setCaption(language.equals(Language.Uz) ? LanguageUtil.ielts_uz : LanguageUtil.ielts_ru);
                            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                            replyKeyboardMarkup.setSelective(true);
                            replyKeyboardMarkup.setResizeKeyboard(true);
                            List<KeyboardRow> keyboard = new ArrayList<>();
                            KeyboardRow keyboardRow = new KeyboardRow();
                            KeyboardButton keyboardButton2 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.applicationuz : LanguageUtil.applicationru);
                            KeyboardButton keyboardButton3 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.BACK_UZ : LanguageUtil.BACK_RU);

                            keyboardRow.add(keyboardButton2);
                            keyboardRow.add(keyboardButton3);
                            keyboard.add(keyboardRow);
                            replyKeyboardMarkup.setKeyboard(keyboard);
                            sendPhoto.setReplyMarkup(replyKeyboardMarkup);

                            send(sendPhoto);
                        } else if (text.equals("SAT")) {
                            File myPhoto = new File("sat.jpg");
                            InputFile inputFile = new InputFile();
                            inputFile.setMedia(myPhoto);
                            sendPhoto.setChatId(message1.getChatId());
                            sendPhoto.setPhoto(inputFile);
                            coursename = message1.getText();
                            sendPhoto.setCaption(language.equals(Language.Uz) ? LanguageUtil.ielts_uz : LanguageUtil.ielts_ru);
                            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                            replyKeyboardMarkup.setSelective(true);
                            replyKeyboardMarkup.setResizeKeyboard(true);
                            List<KeyboardRow> keyboard = new ArrayList<>();
                            KeyboardRow keyboardRow = new KeyboardRow();
                            KeyboardButton keyboardButton2 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.applicationuz : LanguageUtil.applicationru);
                            KeyboardButton keyboardButton3 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.BACK_UZ : LanguageUtil.BACK_RU);

                            keyboardRow.add(keyboardButton2);
                            keyboardRow.add(keyboardButton3);
                            keyboard.add(keyboardRow);
                            replyKeyboardMarkup.setKeyboard(keyboard);
                            sendPhoto.setReplyMarkup(replyKeyboardMarkup);

                            send(sendPhoto);
                        } else if (text.equals(LanguageUtil.applicationuz) || text.equals(LanguageUtil.applicationru)) {
                            sendMessage1.setChatId(message1.getChatId());
                            sendMessage1.setText(language.equals(Language.Uz) ? LanguageUtil.aboutUz : LanguageUtil.aboutEng);
                            send(sendMessage1);
                        } /*else if (text.equals(LanguageUtil.Buyurtma_uz) || text.equals(LanguageUtil.Buyurtma_ru)) {

                            TelergamBotUser botUser1 = new TelergamBotUser();
                            botUser1.setName(message1.getFrom().getFirstName() + " " + message1.getFrom().getLastName());
                            botUser1.setMeals(meals);
                            botUser1.setLocation(location);
                            botUser1.setPhoneNumber(phoneNumber);
                            botUser1.setBookedAt(now.toString());
                            botUser1.setTotalsum(total);
                            database.saveClient(menuController.b, botUser1.getTotalsum(), message1.getFrom().getFirstName());
                            PrintWriter printWriter = new PrintWriter(new FileWriter("Users.txt", true));
                            printWriter.println();
                            printWriter.write(botUser1.getName() + "\n" + botUser1.getPhoneNumber() + " \n" + botUser1.getLocation() + "\n " + botUser1.getMeals().toString() + "\n " + botUser1.getBookedAt() + "\n " + botUser1.getTotalsum() + "#\n ");
                            printWriter.println();
                            printWriter.flush();
                            printWriter.close();
                            sendMessage1.setText(botUser1.toString());
                            sendMessage1.setChatId(message1.getChatId());
                            send(sendMessage1);

                        }*/ else if (text.equals(LanguageUtil.SETTINGS_UZ) || text.equals(LanguageUtil.SETTINGS_RU)) {
                            sendMessage1.setChatId(message1.getChatId());
                            sendMessage1.setText(language.equals(Language.Uz) ? LanguageUtil.CATEGORY_HEADER_UZ : LanguageUtil.CATEGORY_HEADER_RU);
                            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                            replyKeyboardMarkup.setSelective(true);
                            replyKeyboardMarkup.setResizeKeyboard(true);
                            List<KeyboardRow> keyboard = new ArrayList<>();
                            KeyboardRow keyboardRow = new KeyboardRow();
                            KeyboardButton keyboardButton2 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.CHANGE_LANG_UZ : LanguageUtil.CHANGE_LANG_RU);
                            KeyboardButton keyboardButton3 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.BACK_UZ : LanguageUtil.BACK_RU);

                            keyboardRow.add(keyboardButton2);
                            keyboardRow.add(keyboardButton3);
                            keyboard.add(keyboardRow);
                            replyKeyboardMarkup.setKeyboard(keyboard);
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);

                            send(sendMessage1);
                        } else if (text.equals(LanguageUtil.CHANGE_LANG_UZ) || text.equals(LanguageUtil.CHANGE_LANG_RU)) {
                            this.languageController.handle(text, message1.getChatId(), message1.getMessageId());

                        } else if (text.equals("\uD83C\uDDFA\uD83C\uDDFF O'zbekcha")) {
                            sendMessage1.setChatId(message1.getChatId());
                            if (language.equals(Language.Uz)) {
                                sendMessage1.setText(LanguageUtil.UZ);
                            } else {
                                language = Language.Uz;
                                sendMessage1.setText(LanguageUtil.UZ);
                            }
                            send(sendMessage1);
                        } else if (text.equals("\uD83C\uDDEC\uD83C\uDDE7 English")) {
                            sendMessage1.setChatId(message1.getChatId());
                            if (language.equals(Language.Ru)) {
                                sendMessage1.setText(LanguageUtil.RU);
                            } else {
                                language = Language.Ru;
                                sendMessage1.setText(LanguageUtil.RU);
                            }
                            send(sendMessage1);
                        } else {
                            userrname = message1.getText();
                            sendMessage1.setChatId(message1.getChatId());
                            sendMessage1.setText(language.equals(Language.Uz) ? LanguageUtil.SHARE_CONTACT_UZ : LanguageUtil.SHARE_CONTACT_RU);
                            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                            replyKeyboardMarkup.setSelective(true);
                            replyKeyboardMarkup.setResizeKeyboard(true);
                            List<KeyboardRow> keyboard = new ArrayList<>();
                            KeyboardRow keyboardRow = new KeyboardRow();
                            KeyboardButton keyboardButton2 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.SentN_uz : LanguageUtil.SentN_ru);
                            KeyboardButton keyboardButton3 = new KeyboardButton(language.equals(Language.Uz) ? LanguageUtil.BACK_UZ : LanguageUtil.BACK_RU);
                            keyboardButton2.setRequestContact(true);

                            keyboardRow.add(keyboardButton2);
                            keyboardRow.add(keyboardButton3);
                            keyboard.add(keyboardRow);
                            replyKeyboardMarkup.setKeyboard(keyboard);
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);
                            send(sendMessage1);
                        }


                    } else {
                        sendMessage1.setText("false");
                        send(sendMessage1);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void send(String handle) {
    }


    public void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(EditMessageText editMessageText) {
        try {
            execute(editMessageText);
            System.out.println("keldi");
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(SendLocation sendLocation) {
        try {
            execute(sendLocation);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
 /*   public void send(CodeMessage codeMessage) {
        try {
            switch (codeMessage.getType()) {
                case MESSAGE:
                    execute(codeMessage.getSendMessage());
                    break;
                case EDIT:
                    execute(codeMessage.getEditMessageText());
                    break;
                case MESSAGE_VIDEO:
                    execute(codeMessage.getSendMessage());
                    execute(codeMessage.getSendVideo());
                    break;
                default:
                    break;
            }
        } catch (TelegramApiException e) {
           e.printStackTrace();
        }


    }*/


    @Override
    public String getBotUsername() {
        return UserName;
    }

    @Override
    public String getBotToken() {
        return Token;
    }


}
