package com.javarush.task.task24.SimpeChat;

public class Person implements Runnable {
    private final Mail mail;

    public Person(Mail mail) {
        this.mail = mail;
    }

    @Override
    public synchronized void run() {
        String name = Thread.currentThread().getName();
        synchronized (mail) {
            try {
                Thread.sleep(1000);
                //сделайте что-то тут - do something here
                mail.setText("Person [" + name + "] wrote an email 'Мое первое сообщение!'");
                mail.notifyAll();
                //сделайте что-то тут - do something here
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
