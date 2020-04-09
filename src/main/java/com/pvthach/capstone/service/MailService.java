package com.pvthach.capstone.service;

import com.pvthach.capstone.farming.model.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(20);

    private void sendEmail(MailDTO dto) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("jonathan.geepacific@gmail.com");
        msg.setTo(dto.getTo());

        msg.setSubject(dto.getSubject());
        msg.setText(dto.getText());

//        javaMailSender.send(msg);

        quickService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    javaMailSender.send(msg);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }


//    public void sendOrderProcessing(Ordering order){
//        String name = order.getOrderBy();
//        String content = "Hi " + name + ", Your order " + "'" + order.getOrderId() + "'" + " is been processing. We will inform you when it's processed";
//        MailDTO dto = new MailDTO("", order.getEmail(), "Order Is Been Processing", content);
//        sendEmail(dto);
//    }
//
//    public void sendOrderProcessed(Ordering order){
//        String name = order.getOrderBy();
//        String content = "Hi " + name + ", Your order " + "'" + order.getOrderId() + "'" + "has been processed. We will inform you when we deliver";
//        MailDTO dto = new MailDTO("", order.getEmail(), "Order Is Been Processed", content);
//        sendEmail(dto);
//    }
//
//    public void sendOrderDelivering(Ordering order){
//        String name = order.getOrderBy();
//        String content = "Hi " + name + ", Your order " + "'" + order.getOrderId() + "'" + " is in delivering progress.";
//        MailDTO dto = new MailDTO("", order.getEmail(), "Order Is Been Delivering", content);
//        sendEmail(dto);
//    }
//
//    public void sendOrderFinished(Ordering order){
//        String name = order.getOrderBy();
//        String content = "Hi " + name + ", Your order " + "'" + order.getOrderId() + "'" + " was already finished. Thank you so much for your ordering";
//        MailDTO dto = new MailDTO("", order.getEmail(), "Order Finished", content);
//        sendEmail(dto);
//    }
//
//    public void sendOrderCancelled(Ordering order){
//        String name = order.getOrderBy();
//        String content = "Hi " + name + ", Your order " + "'" + order.getOrderId() + "'" + " has been cancelled";
//        MailDTO dto = new MailDTO("", order.getEmail(), "Order Cancelled", content);
//        sendEmail(dto);
//    }
}
