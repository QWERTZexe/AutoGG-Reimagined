package com.qwertz.autogg_reimagined

import cc.polyfrost.oneconfig.utils.gui.GuiUtils
import net.minecraftforge.fml.common.Mod
import kotlinx.coroutines.*
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import com.qwertz.autogg_reimagined.command.AutoGGCommand
import net.minecraftforge.common.MinecraftForge
import net.minecraft.client.Minecraft
import com.qwertz.autogg_reimagined.AutoGG.Companion.config
import com.qwertz.autogg_reimagined.command.IsEnabled
import net.minecraft.util.ChatComponentText
import kotlin.math.roundToLong

@Mod(modid = AutoGG.MODID, name = AutoGG.NAME, version = AutoGG.VERSION)
class AutoGG {
    // Register the config and commands.

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent?) {
        config = com.qwertz.autogg_reimagined.config.AutoGGConfig()
        MinecraftForge.EVENT_BUS.register(CommandEventHandler())
        ClientCommandHandler.instance.registerCommand(AutoGGCommand())

    }
    companion object {
        const val MODID: String = "@ID@"
        const val NAME: String = "@NAME@"
        const val VERSION: String = "@VER@"

        @Mod.Instance(MODID)
        lateinit var INSTANCE: AutoGG
        lateinit var config: com.qwertz.autogg_reimagined.config.AutoGGConfig
    }
}


var ggSaid = false
class CommandEventHandler {

    @SubscribeEvent
    fun onChatReceived(event: ClientChatReceivedEvent) {
        val message = event.message.formattedText
        if (config.GGTrigger in message) {
            if (!ggSaid) {
                // Send "gg" message here
                ggSaid = true  // Set the variable to true to indicate that "gg" has been said
                // Schedule a task to reset ggSaid after the delay
                val GGMessage = config.GGMessage
                if (IsEnabled().EnabledCheck()) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(GGMessage)
                    if (config.GG2ndSwitch) {
                        sendDelayedMsg()
                    }
                }
               allowgg()
            }

        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun sendDelayedMsg() {
        val time = (config.GG2ndDelay * 1000).roundToLong()
        GlobalScope.launch {

            delay(time)
            val GG2ndMessage = config.GG2ndMessage
            Minecraft.getMinecraft().thePlayer.sendChatMessage(GG2ndMessage)
        }

    }

    fun allowgg() {
        val time = 2000L
        GlobalScope.launch {

            delay(time)
            ggSaid = false
        }

    }
}