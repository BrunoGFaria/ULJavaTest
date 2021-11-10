/*
 * (c) 2014 UL TS BV
 */
package com.ul;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    public enum Priority {
        HIGH(1), MEDIUM(2), LOW(3);
        Priority(Integer order){
            this.order = order;
        }
        Integer order;
    }

    private long timestamp;
    private Priority priority;
    private String text;

    public Message(long timestamp, Priority priority, String text) {
        this.timestamp = timestamp;
        this.priority = priority;
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getText() {
        return text;
    }

    public String toString(){
        return "Priority: " + this.priority.toString() + " Timestamp: " + this.formatTimeStamp(this.timestamp) +
                " Message Text: " + this.text;
    }

    private String formatTimeStamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date date = new Date(timestamp);
        return sdf.format(date);
    }
}
