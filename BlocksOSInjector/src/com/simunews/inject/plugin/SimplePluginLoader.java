package com.simunews.inject.plugin;

import org.jnbt.CompoundTag;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimplePluginLoader {
    private static List<Map<?, ?>> classList;
    private Yaml yaml = new Yaml();
    private JarEntry jEntry = null;
    private JarFile jFile = null;
    private List<String> result = null;

    private Map<String, Object> objMap = new TreeMap<>();
    private List<Map<?, ?>> objMapList = new LinkedList<>();

    //plugin.yml entries
    private List<String> mainClassPath = new LinkedList<>();
    private List<String> pluginName = new LinkedList<>();
    private List<Double> version = new LinkedList<>();

    //PATH
    private String path = "./blocksos/plugins/";


    //static
    public static Object executeMethod(String _method, int i) {
        Method method = null;
        Class classToLoad = null;
        Object myClassObj = null;

        try {
            classToLoad = (Class) classList.get(i).get("class");
            method = classToLoad.getMethod(_method); //Get the method
            myClassObj = classToLoad.getDeclaredConstructor().newInstance();
            return method.invoke(myClassObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int length() {
        return classList.size();
    }


    public SimplePluginLoader() {

        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".jar")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //OPEN FILE
        InputStream reader = null;
        JarFile jarFile = null;
        Map<String, Object> obj = null;





        for (int i = 0; i < result.size(); i++) {
            try {
                //Read jar file and plugin.yml
                jarFile = new JarFile(result.get(i));
                JarEntry entry = jarFile.getJarEntry("plugin.yml");
                reader = jarFile.getInputStream(entry);


                //read plugin.yml
                if(reader != null)
                    obj = yaml.load(reader);

                //Don't load the plugin if key components are missing
                if(!obj.containsKey("name") || !obj.containsKey("main") || !obj.containsKey("version")) {
                    System.out.println("[Error] Missing name, main or version!");
                    result.remove(i);
                    continue;
                }

                //get mainClass
                pluginName.add((String) obj.get("name"));
                mainClassPath.add((String) obj.get("main"));
                version.add((double) obj.get("version"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //close Files
        try {
            if(jarFile != null)
                jarFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Enable
        for (int i = 0; i < result.size(); i++) {
            try {
                URLClassLoader cl = null;
                cl = URLClassLoader.newInstance(new URL[]{new URL("file://" + result.get(i))}); //Create a  new instance
                Class myClass = null;
                myClass = cl.loadClass(mainClassPath.get(i));   //Get class to execute

                objMap.put("class", myClass);
                objMap.put("main", mainClassPath.get(i));
                objMap.put("name", pluginName.get(i));
                objMap.put("version", version.get(i));
                objMapList.add(objMap);
                objMap.clear();

                /*Method method = null;
                method = myClass.getMethod("onEnable"); //Get the method
                Object myClassObj = null;
                myClassObj = myClass.newInstance();
                response = method.invoke(myClassObj);*/    //Execute the onEnable() method
                //------------------------------------------
                System.out.println(pluginName.get(i) + " " + version.get(i) + " loaded successfully");    //log that
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        classList = objMapList;

    }

    public static List<CompoundTag> executeAll() {
        List<CompoundTag> cTag = new LinkedList<>();



        return cTag;
    }

}
