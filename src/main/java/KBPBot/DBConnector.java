package KBPBot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DBConnector {
    public static List<String> getAllSpecial(){
        return Arrays.asList("Т","П","Б","Д","Э","К","Л");
    }
    public static String[] getAllGroup(String special) {
        if(special.equals("Т")){
            return new String[]{"Т-916","Т-917","Т-916","Т-917"};
        }
        if(special.equals("П")){
            return new String[]{"П-916","П-917","П-916","П-917"};
        }
        if(special.equals("Б")){
            return new String[]{"Б-916","Б-917","Б-916","Б-917"};
        }
        if(special.equals("Д")){
            return new String[]{"Д-916","Д-917","Д-916","Д-917"};
        }
        if(special.equals("Э")){
            return new String[]{"Э-916","Э-917","Э-916","Э-917"};
        }
        if(special.equals("К")){
            return new String[]{"К-916","К-917","К-916","К-917"};
        }
        if(special.equals("Л")){
            return new String[]{"Л-916","Л-917","Л-916","Л-917"};
        }
        return null;
    }
    public static int getNextMessageId(){
        return new Random().nextInt(10000);
    }
}
