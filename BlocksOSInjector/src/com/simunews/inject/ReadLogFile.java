package com.simunews.inject;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class ReadLogFile {
    //private String path = "./logs/latest.log";
    private String path = "C:\\Users\\SimuNews\\Documents\\MinecraftTestServers\\vanilla_1_16_2\\logs\\latest.log";

    private String timeStamp;
    private String sender;
    private String label;
    private String[] args;

    public ReadLogFile() {

    }
    public ReadLogFile(boolean _local) {
        if(!_local)
            path = "../../logs/latest.log";
    }

    private String getCmd(String _msg) {
        if (_msg.substring(_msg.indexOf(".") + 1).contains(" ")) {
            return _msg.substring(_msg.indexOf(".") + 1).substring(0, _msg.substring(_msg.indexOf(".") + 1).indexOf(" "));
        } else {
            return _msg.substring(_msg.indexOf(".") + 1);
        }
    }
    private String[] getArgs(String _msg) {
        if(_msg.contains(" "))
            if(_msg.substring(_msg.indexOf(label) + label.length()).length() > 0)
                return _msg.substring(_msg.indexOf(label) + label.length() + 1).split(" ");
        return null;
    }
    private String getSender(String _msg) {
        String sender = null;
        sender = _msg.substring(_msg.indexOf("<") + 1, _msg.indexOf(">"));
        return sender;
    }

    public List<Object> readIt() {
        List<Object> cmd = new LinkedList<>();

        File f = new File(path);
        String msg = readFromLog(f);
        if(msg != null && msg.contains(": <") && msg.contains(".")) {
            timeStamp = getTimeStamp(msg);
            sender = getSender(msg);
            label = getCmd(msg);
            args = getArgs(msg);
        }
        if(msg != null && msg.contains("Stopping server")) {
            timeStamp = getTimeStamp(msg);
            label = "stop";
        }

        cmd.add(timeStamp);
        cmd.add(sender);
        cmd.add(label);
        cmd.add(args);

        return cmd;
    }

    private String getTimeStamp(String _msg) {
        return _msg.substring(1, _msg.indexOf("]"));
    }


    // Read last line of the file
    private String readFromLog(File file) {
        int lines = 0;
        StringBuilder builder = new StringBuilder();
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            long fileLength = file.length() - 2;
            // Set the pointer at the last of the file
            if(fileLength > 0) {
                randomAccessFile.seek(fileLength);
                for (long pointer = fileLength; pointer >= 0; pointer--) {
                    randomAccessFile.seek(pointer);
                    char c;
                    // read from the last one char at the time
                    c = (char) randomAccessFile.read();
                    // break when end of the line
                    if (c == '\n') {
                        break;
                    }
                    builder.append(c);
                }
                // Since line is read from the last so it
                // is in reverse so use reverse method to make it right
                builder.reverse();
                return builder.toString();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if(randomAccessFile != null){
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
