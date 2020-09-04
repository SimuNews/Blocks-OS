package com.simunews;

import com.simunews.inject.ReadLogFile;
import com.simunews.inject.plugin.Plugin;
import com.stevertus.objd.basic.Command;
import com.stevertus.objd.Say;
import com.stevertus.objd.basic.Scoreboard;
import com.stevertus.objd.basic.Widget;

import java.util.LinkedList;
import java.util.List;


//Widgets like spigot/bukkit...

public class Main {

    public static void main(String[] args) {

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Scoreboard.prefix = "blOS";

        List<Widget> w = new LinkedList<>();
        w.add(new Say("hello"));
        w.add(new Say("world"));
        w.add(new Command("/give @p apple"));
        w.add(new Scoreboard("testScore"));
        w.add(Scoreboard.trigger("testScore1", null));
        w.add(Scoreboard.remove("testScore", null));
        w.add(Scoreboard.click("testScore2", null));
        w.add(Scoreboard.setDisplay("testScore2"));
        BuildNBT buildNBT = new BuildNBT(w);

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        //SimplePluginLoader spl = new SimplePluginLoader();
        ReadLogFile rlf = new ReadLogFile();

        String tsNew, tsOld = "0";
        String command;
        List<Object> commandMsg;

        //GET COORDS
        //[Teleported $sender tp 0.000 0 0.000] => contains("Teleported $sender");

        //while
        while (true) {
            commandMsg = rlf.readIt();
            //System.out.println(rlf.readIt());

            //Check for new cmd
            tsNew = (String) commandMsg.get(0);
            if(tsNew != null && !tsNew.equalsIgnoreCase(tsOld)) {
                tsOld = tsNew;
                command = (String) commandMsg.get(2);
                System.out.println(commandMsg.get(1));
                System.out.println(commandMsg.get(2));
                String[] a = (String[]) commandMsg.get(3);
                if (a != null) {
                    for (int i = 0; i < a.length; i++)
                        System.out.println(a[i]);
                }

                System.out.println("------------");

                if(command.contains("\r"))
                    command = command.substring(0, command.indexOf("\r"));
                if(command.equalsIgnoreCase("stop"))
                    break;
            }

        }





        //EnablePlugin nePl = new EnablePlugin(new Plugin());

        /*try {
            NBTOutputStream nbtOut = new NBTOutputStream(new FileOutputStream(buildNBT + ".nbt"));
            nbtOut.writeTag(buildNBT.build());
            nbtOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}