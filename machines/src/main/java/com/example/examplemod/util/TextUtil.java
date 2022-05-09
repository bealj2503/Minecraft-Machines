package com.example.examplemod.util;

import com.example.examplemod.ExampleMod;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TextUtil {
    private static final String ENERGY_FORMAT = "%,d";

    public static IFormattableTextComponent translate(String prefix, String suffix, Object... params) {
        String key = String.format("%s FE / %s FE", prefix,  suffix);
        return new TranslationTextComponent(key, params);
    }
    public static IFormattableTextComponent name(String name, Object... params) {
        String key = String.format("%s", name);
        return new TranslationTextComponent(key, params);
    }
    public static IFormattableTextComponent energy(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energy", s1);
    }

    public static IFormattableTextComponent energyPerTick(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energyPerTick", s1);
    }

    public static IFormattableTextComponent energyWithMax(int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);

        return translate(s1, s2, s1, s2);
    }
}
