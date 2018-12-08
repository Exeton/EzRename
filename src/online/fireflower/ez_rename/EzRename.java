package online.fireflower.ez_rename;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class EzRename extends JavaPlugin {

    private static HashMap<String, String> colorNamesAndHexValues = new HashMap<>();
    private static char colorCodeSymbol = '*';

    @Override
    public void onEnable(){

        this.getCommand("EzRename").setExecutor(new RenameCommand());

        //Colors
        registerColorCode("Dark Red", "4");
        registerColorCode("Red", "c");
        registerColorCode("Gold", "6");
        registerColorCode("Orange", "6");
        registerColorCode("Yellow", "e");
        registerColorCode("Dark Green", "2");
        registerColorCode("Green", "a");
        registerColorCode("Aqua", "b");
        registerColorCode("Dark Aqua", "3");
        registerColorCode("Dark Blue", "1");
        registerColorCode("Blue", "9");
        registerColorCode("Pink", "d");
        registerColorCode("Light Purple", "d");
        registerColorCode("Purple", "5");
        registerColorCode("Dark Purple", "5");
        registerColorCode("White", "f");
        registerColorCode("Light gray", "7");
        registerColorCode("Gray", "8");
        registerColorCode("Black", "0");

        //Formats
        registerColorCode("Bold", "l");
        registerColorCode("Strike", "m");
        registerColorCode("Strike Through", "m");
        registerColorCode("Crossed out", "m");
        registerColorCode("Underline", "n");
        registerColorCode("Underlined", "n");
        registerColorCode("Italic", "o");
        registerColorCode("Italics", "o");
        registerColorCode("Reset", "r");
        registerColorCode("Clear", "r");
        registerColorCode("Colorless", "r");

        //Format Codes (i.e. b = bold, s = strike, etc)
        registerColorCode("b", "l");
        registerColorCode("s", "m");
        registerColorCode("u", "n");
        registerColorCode("i", "o");
    }

    private static void registerColorCode(String colorName, String hexValue){

        if (colorName.contains(" ")){
            colorNamesAndHexValues.put(colorName.replace(" ", "").toLowerCase(), hexValue);
            colorNamesAndHexValues.put(colorName.replace(" ", "_").toLowerCase(), hexValue);
        }
        else{
            colorNamesAndHexValues.put(colorName.toLowerCase(), hexValue);
        }
    }

    public static String parseColoredText(String coloredText){

        int pos = 0;
        while (pos < coloredText.length() && coloredText.indexOf(colorCodeSymbol, pos) >= 0){


            int nextPos = coloredText.indexOf(colorCodeSymbol, pos);
            int nextNextPos = coloredText.indexOf(colorCodeSymbol, nextPos + 1);
            if (nextNextPos < 0)
                break;

            if (nextNextPos - nextPos == 1){//We want strings like &black& not &&
                pos = nextPos + 1;
                continue;
            }

            String colorName = coloredText.substring(nextPos + 1, nextNextPos);

            if (colorNamesAndHexValues.containsKey(colorName.toLowerCase())){

                String hexValue = colorNamesAndHexValues.get(colorName.toLowerCase());
                coloredText = coloredText.replace( colorCodeSymbol + colorName + colorCodeSymbol, "&" + hexValue);
                pos = coloredText.indexOf('*');
            }
            else{
                pos = nextPos + 1;
            }
        }
        return ChatColor.translateAlternateColorCodes('&',ChatColor.RESET + coloredText);
    }
}
