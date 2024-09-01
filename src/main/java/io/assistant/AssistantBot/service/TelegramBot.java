package io.assistant.AssistantBot.service;

import io.assistant.AssistantBot.configuration.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig botConfig;

    static final String HELP_TEXT = "This is assistant bot who will help you.\n" +
            "You can execute commands from the main menu on the left or by typing a command.\n\n" +
            "Type /start to see welcome message.";

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;

        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Start work with bot."));
        listOfCommands.add(new BotCommand("/mydata", "Personal user data."));
        listOfCommands.add(new BotCommand("/deletedata", "Delete user data."));
        listOfCommands.add(new BotCommand("/help", "Info how to use this bot."));
        listOfCommands.add(new BotCommand("/settings", "Set your preferences."));
        try{
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        }
        catch (TelegramApiException e){
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            final String userName = update.getMessage().getChat().getUserName();

            switch (messageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName(), userName);
                    break;
                case "/help":
                    sendMessage(chatId, HELP_TEXT);
                    break;
                default:
                    sendMessage(chatId, "Sorry, I don't know this command.");
            }
        }
    }

    private void startCommandReceived(long chatId, String firstName, String userName) {
        String answer = "Hi, " + firstName + ", nice to meet you!";
        sendMessage(chatId, answer);
        log.info("Message sent to user " + userName);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try{
            execute(message);
        }
        catch (TelegramApiException e) {
            log.error(String.valueOf(e));
        }
    }

}
