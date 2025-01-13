package com.codegym.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TinderBoltApp extends SimpleTelegramBot {

    public static final String TELEGRAM_BOT_TOKEN = System.getenv("TELEGRAM_BOT_TOKEN"); //TODO: añadir el token del bot entre comillas
    public static final String OPEN_AI_TOKEN = "chat-gpt-token"; //TODO: añadir el token de ChatGPT entre comillas

    public TinderBoltApp() {
        super(TELEGRAM_BOT_TOKEN);
    }

    //TODO: escribiremos la funcionalidad principal del bot aquí

    public void startCommand(){
        String text = loadMessage("main");
        sendPhotoMessage("main");
        sendTextMessage(text);
    }

    public void hello(){
        String text = getMessageText();
        sendTextMessage("*Hello World*");
        sendTextMessage("_How are you?_");
        sendTextMessage("You Wrote : " + text);
        sendPhotoMessage("avatar_main");
        sendTextButtonsMessage("Launch Process",
                        "start", "Start",
                                "stop", "Stop");
    }

    public void helloButton(){
        String key = getButtonKey();
        if (key.equals("start")) {
            sendTextMessage("_The process has been launched._");
        } else {
            sendTextMessage("_Process has been stopped._");
        }
    }

    @Override
    public void onInitialize() {
        //TODO: y un poco más aquí :)
        addCommandHandler("start", this::startCommand);
        addMessageHandler(this::hello);
        addButtonHandler("^.*", this::helloButton);
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TinderBoltApp());
    }
}
