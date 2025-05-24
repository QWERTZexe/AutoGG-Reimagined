package app.qwertz.autoggreimagined

import cc.polyfrost.oneconfig.utils.gui.GuiUtils
import net.minecraftforge.fml.common.Mod
import kotlinx.coroutines.*
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import app.qwertz.autoggreimagined.command.AutoGGCommand
import net.minecraftforge.common.MinecraftForge
import net.minecraft.client.Minecraft
import app.qwertz.autoggreimagined.AutoGG.Companion.config
import app.qwertz.autoggreimagined.config.AutoGGConfig
import app.qwertz.autoggreimagined.command.IsEnabled
import net.minecraft.util.ChatComponentText
import kotlin.math.roundToLong

@Mod(modid = AutoGG.MODID, name = AutoGG.NAME, version = AutoGG.VERSION)
class AutoGG {
    // Register the config and commands.

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent?) {
        config = AutoGGConfig
        MinecraftForge.EVENT_BUS.register(CommandEventHandler())
        ClientCommandHandler.instance.registerCommand(AutoGGCommand())

    }
    companion object {
        const val MODID: String = "@ID@"
        const val NAME: String = "@NAME@"
        const val VERSION: String = "@VER@"

        @Mod.Instance(MODID)
        lateinit var INSTANCE: AutoGG
        lateinit var config: AutoGGConfig
    }
}


var ggSaid = false
class CommandEventHandler {

    @SubscribeEvent
    fun onChatReceived(event: ClientChatReceivedEvent) {
        val message = event.message.unformattedText
        val triggers = config.GGTriggers.split(";")
        for (trigger in triggers) {
            if (trigger in message) {
                if (!ggSaid) {
                    ggSaid = true
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
        val triggers2 = config.AntiGGTriggers.split(";")
        for (trigger2 in triggers2) {
            if (event.message.unformattedText.contains(": $trigger2") && config.AntiGGSwitch) {
                // Cancel the event or manipulate the message to prevent it from being displayed
                event.isCanceled = true
                if (config.NotifySwitch) {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText("§4[§6§lAUTOGG REIMAGINED§4]§a: GG message hidden"))
                }
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