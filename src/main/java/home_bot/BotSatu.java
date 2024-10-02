package home_bot;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import services.GeneratePDF;
import services.ServiceSatu;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BotSatu implements LongPollingSingleThreadUpdateConsumer {
    private TelegramClient telegramClient = new OkHttpTelegramClient("7884693944:AAF8C5r0AKLEny2VMtQ7Fq4b04RdeA5i-fA");

    @Override
    public void consume(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println(update.getMessage().getText());

            if (update.getMessage().getText().equalsIgnoreCase("/start")){

                try {
                    telegramClient.execute(ServiceSatu.response.greetingMessage(update));
                    log("Farras", "AS", Long.toString(1), "asd", "asd");
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (update.getMessage().getText().equalsIgnoreCase("/test_pdf")){

                try{
                    SendMessage sendMessage = SendMessage.builder().text("Prosess start").chatId(update.getMessage().getChatId().toString()).build();
                    Message message = telegramClient.execute(sendMessage);

                    Message message2 = telegramClient.execute(ServiceSatu.response.infoPDF(message.getMessageId(),sendMessage.getChatId(),"PDF sudah diterima"));

                    Serializable message3 = telegramClient.execute(ServiceSatu.response.updateInfoPDF(message2.getMessageId(),sendMessage.getChatId(),"Pengjujian PDF"));


                    GeneratePDF.processPDF("C:\\Users\\Administrator\\Downloads\\sample_def_aset.pdf","farras","123123");


                    System.out.println("Masuk sini pdf");
                    String host = "temp\\signed";
                    InputFile inputFile = new InputFile(new File(host+"\\sample_def_signed.pdf"));
                    telegramClient.execute(ServiceSatu.response.sendResponseOk(inputFile,update.getMessage().getChatId().toString(),"Ini caption-nya mas bro"));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            System.out.println(update);
            System.out.println("Disini");
        }
    }

    private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }
//    @Override
//    public String getBotToken() {
//        return "7884693944:AAF8C5r0AKLEny2VMtQ7Fq4b04RdeA5i-fA";
//    }

//    @Override
//    public void onUpdateReceived(Update update) {
//
//        String myId = "7083204931";
//        String message = update.getMessage().getText();
//        System.out.println(message);
//
//        try {
//
//            if (message.equalsIgnoreCase("/start")){
//                System.out.println("Run start");
//
//                execute(ServiceSatu.response.greetingMessage(update));
//            }
//
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//
//    }



}
