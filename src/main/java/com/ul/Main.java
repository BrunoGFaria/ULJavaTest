/*
 * (c) 2014 UL TS BV
 */
package com.ul;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
        Producer producer = new Producer(queue);
        producer.startProducing();

        Consumer consumer = new Consumer(queue);
        consumer.startConsuming();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // ignore this exception and allow main method to exit
        } finally {
            producer.stopProducing();
            consumer.stopConsuming();
        }
    }
}
