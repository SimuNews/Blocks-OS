package com.simunews.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class OSParser {

    private String path = "C:\\Users\\redst\\AppData\\Roaming\\.minecraft\\saves\\warpTest\\blocksos\\commands\\";//"./blocksos/commands";
    private String[] cmds;

    public OSParser() {
        StringBuilder sb = new StringBuilder();
        try {
            Files.walk(Paths.get(path))
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> sb.append(path.toString().substring(path.toString().lastIndexOf("\\") + 1) + "\n"));

            cmds = sb.toString().split("\n");



            File f;
            for (int i = 0; i < cmds.length; i++) {
                BufferedReader reader = new BufferedReader(new FileReader(path + cmds[i]));
                StringBuilder strBui = new StringBuilder();
                List<String> cmdLine = new LinkedList<>();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    strBui.append(line);
                    strBui.append("\n");
                }
                reader.close();

                line = strBui.toString();
                if(countOf(line, '(') != countOf(line, ')') || countOf(line, '{') != countOf(line, '}'))
                    return;

                String[] exp = line.split("[;{\n]");

                for (int j = 0; j < exp.length; j++) {
                    cmdLine.add(exp[j]);
                }
                System.out.println(line);
                parseFile(cmdLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String _deleteTab(String _content) {
        String[] begin = _content.split("\t");
        for (int j = 0; j < begin.length; j++) {
            if(!begin[j].equals("")) {
                return begin[j];
            }
        }
        return null;
    }

    private String[] _getNextCmd(String _content) {
        String[] cmd = new String[10];
        int i = 0;

        if(!_content.startsWith("//") && _content.contains("("))
            cmd[0] = _content.substring(0, _content.indexOf("("));

        for (int j = 0; j < countOf(_content.substring(_content.indexOf("(") + 1, _content.indexOf(")")), ',') + 1; j++) {

        }

        //System.out.println(cmd);
        return cmd;
    }

    private String[] parseFile(List<String> _content) {
        String str;
        String[] args = null;

        if(_content.get(0).startsWith("main(String[] args)")) {
            for (int i = 1; i < _content.size(); i++) {
                str = _deleteTab(_content.get(i));
                args = _getNextCmd(str);
            }
        }

        return args;
    }

    private int countOf(String _content, char _char) {
        int n = 0;

        for (int i = 0; i < _content.length(); i++) {
            if(_content.charAt(i) == _char)
                n++;
        }

        return n;
    }

    @Override
    public String toString() {
        return cmds[0].substring(0, cmds[0].indexOf("."));
    }
}
