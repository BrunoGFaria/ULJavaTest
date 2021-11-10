/*
 * (c) 2014 UL TS BV
 */
package com.ul;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class Consumer {

    private BlockingQueue<Message> queue;
    private Thread consumerThread = null;
    List<Message> list = new ArrayList<>();

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void startConsuming() throws IOException {
        consumerThread = new Thread(new Runnable() {
            FileWriter fw = new FileWriter("stdOut.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            @Override
            public void run() {
                while (true) {
                    try {
                        Message message = queue.take();
                        list.add(message);
                        //System.out.println(message.toString());
                        if (list.size() == 10){
                            this.printBufferedMsgs(list, bw);
                        }
                    } catch (InterruptedException | IOException e) {
                        // executing thread has been interrupted, exit loop
                        try {
                            bw.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        break;
                    }
                }
            }

            private void printBufferedMsgs(List<Message> list, BufferedWriter fw) throws IOException {
                list.sort(new Comparator<Message>() {
                    @Override
                    public int compare(Message m1, Message m2) {
                        return Integer.compare(m1.getPriority().order, m2.getPriority().order);
                    }
                });
                for (Message m:list){
                    bw.write(m.toString());
                    bw.newLine();
                }
                list.clear();
            }
        });
        consumerThread.start();
    }

    public void stopConsuming() {
        consumerThread.interrupt();
    }
}
