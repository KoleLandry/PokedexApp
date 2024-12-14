package com.landrykole.pokedexapp;

import java.util.ArrayList;
import java.util.List;

public class WeaknessChecker {

    List<String> weaknessList = new ArrayList<>();
    List<String> resistanceList = new ArrayList<>();
    List<String> immunityList = new ArrayList<>();

    public void checkInteractions(String type) {
        switch (type) {
            case "Normal":
                // Normal is weak to Fighting
                weaknessList.add("Fighting");
                // Normal is immune to Ghost
                immunityList.add("Ghost");
                break;
            case "Fire":
                // Fire is weak to Water, Ground, and Rock
                weaknessList.add("Water");
                weaknessList.add("Ground");
                weaknessList.add("Rock");
                // Fire resists Fire, Grass, Ice, Bug, Steel, and Fairy
                resistanceList.add("Fire");
                resistanceList.add("Grass");
                resistanceList.add("Ice");
                resistanceList.add("Bug");
                resistanceList.add("Steel");
                resistanceList.add("Fairy");
                break;
            case "Water":
                // Water is weak to Electric and Grass
                weaknessList.add("Electric");
                weaknessList.add("Grass");
                // Water resists Fire, Water, Ice, Steel, and Fairy
                resistanceList.add("Fire");
                resistanceList.add("Water");
                resistanceList.add("Ice");
                resistanceList.add("Steel");
                break;
            case "Electric":
                // Electric is weak to Ground
                weaknessList.add("Ground");
                // Electric resists Electric, Flying, and Steel
                resistanceList.add("Electric");
                resistanceList.add("Flying");
                resistanceList.add("Steel");
                break;
            case "Grass":
                // Grass is weak to Fire, Ice, Poison, Flying, and Bug
                weaknessList.add("Fire");
                weaknessList.add("Ice");
                weaknessList.add("Poison");
                weaknessList.add("Flying");
                weaknessList.add("Bug");
                // Grass resists Water, Electric, Grass, and Ground
                resistanceList.add("Water");
                resistanceList.add("Electric");
                resistanceList.add("Grass");
                resistanceList.add("Ground");
                break;
            case "Ice":
                // Ice is weak to Fire, Fighting, Rock, and Steel
                weaknessList.add("Fire");
                weaknessList.add("Fighting");
                weaknessList.add("Rock");
                weaknessList.add("Steel");
                // Ice resists Ice
                resistanceList.add("Ice");
                break;
            case "Fighting":
                // Fighting is weak to Flying, Psychic, and Fairy
                weaknessList.add("Flying");
                weaknessList.add("Psychic");
                weaknessList.add("Fairy");
                // Fighting resists Bug, Rock, and Dark
                resistanceList.add("Bug");
                resistanceList.add("Rock");
                resistanceList.add("Dark");
                break;
            case "Poison":
                // Poison is weak to Ground and Psychic
                weaknessList.add("Ground");
                weaknessList.add("Psychic");
                // Poison resists Grass, Fighting, Poison, Bug, and Fairy
                resistanceList.add("Grass");
                resistanceList.add("Fighting");
                resistanceList.add("Poison");
                resistanceList.add("Bug");
                resistanceList.add("Fairy");
                break;
            case "Ground":
                // Ground is weak to Water, Grass, and Ice
                weaknessList.add("Water");
                weaknessList.add("Grass");
                weaknessList.add("Ice");
                // Ground resists Poison and Rock
                resistanceList.add("Poison");
                resistanceList.add("Rock");
                // Ground is immune to Electric
                immunityList.add("Electric");
                break;
            case "Flying":
                // Flying is weak to Electric, Ice, and Rock
                weaknessList.add("Electric");
                weaknessList.add("Ice");
                weaknessList.add("Rock");
                // Flying resists Grass, Fighting, and Bug
                resistanceList.add("Grass");
                resistanceList.add("Fighting");
                resistanceList.add("Bug");
                // Flying is immune to Ground
                immunityList.add("Ground");
                break;
            case "Psychic":
                // Psychic is weak to Bug, Ghost, and Dark
                weaknessList.add("Bug");
                weaknessList.add("Ghost");
                weaknessList.add("Dark");
                // Psychic resists Fighting and Psychic
                resistanceList.add("Fighting");
                resistanceList.add("Psychic");
                break;
            case "Bug":
                // Bug is weak to Fire, Flying, and Rock
                weaknessList.add("Fire");
                weaknessList.add("Flying");
                weaknessList.add("Rock");
                // Bug resists Grass, Fighting, Ground
                resistanceList.add("Grass");
                resistanceList.add("Fighting");
                resistanceList.add("Ground");
                break;
            case "Rock":
                // Rock is weak to Water, Grass, Fighting, Ground, and Steel
                weaknessList.add("Water");
                weaknessList.add("Grass");
                weaknessList.add("Fighting");
                weaknessList.add("Ground");
                weaknessList.add("Steel");
                // Rock resists Normal, Fire, Poison, and Flying
                resistanceList.add("Normal");
                resistanceList.add("Fire");
                resistanceList.add("Poison");
                resistanceList.add("Flying");
                break;
            case "Ghost":
                // Ghost is weak to Ghost and Dark
                weaknessList.add("Ghost");
                weaknessList.add("Dark");
                // Ghost resists Poison and Bug
                resistanceList.add("Poison");
                resistanceList.add("Bug");
                // Ghost is immune to Normal and Fighting
                immunityList.add("Normal");
                immunityList.add("Fighting");
                break;
            case "Dragon":
                // Dragon is weak to Ice, Dragon, and Fairy
                weaknessList.add("Ice");
                weaknessList.add("Dragon");
                weaknessList.add("Fairy");
                // Dragon resists Fire, Water, Electric, and Grass
                resistanceList.add("Fire");
                resistanceList.add("Water");
                resistanceList.add("Electric");
                resistanceList.add("Grass");
                break;
            case "Dark":
                // Dark is weak to Fighting, Bug, and Fairy
                weaknessList.add("Fighting");
                weaknessList.add("Bug");
                weaknessList.add("Fairy");
                // Dark resists Ghost and Dark
                resistanceList.add("Ghost");
                resistanceList.add("Dark");
                // Dark is immune to Psychic
                immunityList.add("Psychic");
                break;
            case "Steel":
                // Steel is weak to Fire, Fighting, and Ground
                weaknessList.add("Fire");
                weaknessList.add("Fighting");
                weaknessList.add("Ground");
                // Steel resists Normal, Grass, Ice, Flying, Psychic, Bug, Rock, Dragon, Steel, and Fairy
                resistanceList.add("Normal");
                resistanceList.add("Grass");
                resistanceList.add("Ice");
                resistanceList.add("Flying");
                resistanceList.add("Psychic");
                resistanceList.add("Bug");
                resistanceList.add("Rock");
                resistanceList.add("Dragon");
                resistanceList.add("Steel");
                resistanceList.add("Fairy");
                // Steel is immune to Poison
                immunityList.add("Poison");
                break;
            case "Fairy":
                // Fairy is weak to Poison and Steel
                weaknessList.add("Poison");
                weaknessList.add("Steel");
                // Fairy resists Fighting, Bug, and Dark
                resistanceList.add("Fighting");
                resistanceList.add("Bug");
                resistanceList.add("Dark");
                // Fairy is immune to Dragon
                immunityList.add("Dragon");
                break;
            default:
                break;
        }
    }

    public List<String> checkWeakness(String type1, String type2) {
        checkInteractions(type1);
        checkInteractions(type2);

        // TODO: If same type is on weaknesses twice, add it to a 4x weakness list
        // TODO: If same type is on resistances twice, add it to a 4x resistance list

        return weaknessList;

    }
}
