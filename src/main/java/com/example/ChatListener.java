package com.example;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatListener {
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        String playerName = Minecraft.getMinecraft().thePlayer.getName();
        String message = event.message.getUnformattedText();

        if (message.contains(", " + playerName + ".")|| message.contains("- " + playerName + ",")||message.contains(", " + playerName) ) {
            String modifiedMessage = message.replace(",", "").replace(".", "").replace("§7", "");
            String finalMessage = EnumChatFormatting.BLUE + "squad add " + EnumChatFormatting.GREEN + modifiedMessage +EnumChatFormatting.YELLOW + " [CopySquadForCuteOvO]";
            String unformattedFinalMessage = "/squad add " + modifiedMessage;

            ChatComponentText chatComponent = new ChatComponentText(finalMessage);

            // Set up the clickable chat style
            ChatStyle clickableStyle = new ChatStyle();
            clickableStyle.setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, unformattedFinalMessage));
            chatComponent.setChatStyle(clickableStyle);

            Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponent);
        }
    }
}