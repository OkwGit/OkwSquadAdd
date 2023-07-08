package com.example;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatListener {
    private boolean shouldPlaySound = true; // For Phoenix Pot Chest Notify!


    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        String playerName = Minecraft.getMinecraft().thePlayer.getName();
        String message = event.message.getUnformattedText();

        if (message.contains(", " + playerName + ".")
                || message.contains("- " + playerName + ",")
                || message.contains(", " + playerName)
                || message.matches(".*YELLOW: \\d+ -.*")
                || message.matches(".*GREEN: \\d+ -.*")
//                || message.contains("0") // REMOVE
                || message.matches(".*RED: \\d+ -.*")
                ||message.matches(".*BLUE: \\d+ -.*")) {
        //if (message.contains(", " + playerName + ".")|| message.contains("- " + playerName + ",")||message.contains(", " + playerName) ) {
            String modifiedMessage = message.replace(",", "").replace(".", "").replace("§7", "");
            String finalMessage = EnumChatFormatting.BLUE + "squad add " + EnumChatFormatting.GREEN + modifiedMessage +EnumChatFormatting.YELLOW + " [CopySquad]";
            String unformattedFinalMessage = "/squad add " + modifiedMessage;

            ChatComponentText chatComponent = new ChatComponentText(finalMessage);

            // Set up the clickable chat style
            ChatStyle clickableStyle = new ChatStyle();
            clickableStyle.setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, unformattedFinalMessage));
            chatComponent.setChatStyle(clickableStyle);

            Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponent);

            //Phoenix Pot Ches  t Notify!
        } if ( shouldPlaySound && message.contains("0§a/6") && message.contains("RESURRECTION")) {
//        } if (shouldPlaySound && message.contains("GATHERING 0")) {
            SoundUtil.playGameStartSound();
            Minecraft.getMinecraft().ingameGUI.displayTitle(EnumChatFormatting.LIGHT_PURPLE+"Phoenix Tear", "", 10, 20, 10);

            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You found a chest with"+ EnumChatFormatting.LIGHT_PURPLE+" Phoenix Tear"+EnumChatFormatting.AQUA+"."));
            shouldPlaySound = false;

        }
        else if (message.contains("4§a/6") && message.contains("RESURRECTION")) {
            shouldPlaySound = true;
        }
    }
}
