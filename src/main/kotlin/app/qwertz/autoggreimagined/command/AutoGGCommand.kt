package app.qwertz.autoggreimagined.command

import cc.polyfrost.oneconfig.utils.commands.annotations.Command
import app.qwertz.autoggreimagined.AutoGG
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import app.qwertz.autoggreimagined.AutoGG.Companion.config
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
val AGConfig = config

// Check the value of the enable/disable option for the current mod
class IsEnabled {
    fun EnabledCheck(): Boolean {
        if (AGConfig.enabled) {
            return true
        } else {
            return false
        }
    }
}
@Command(value = AutoGG.MODID, description = "Access the " + AutoGG.NAME + " Config")
class AutoGGCommand : CommandBase() {
    override fun getCommandName() = "gg"

    override fun getCommandUsage(sender: ICommandSender) = "/gg"

    override fun processCommand(sender: ICommandSender, args: Array<String>) {
        // Ensure that this command is only executed on the client side
        if (IsEnabled().EnabledCheck()) {
            AGConfig.openGui()
        } else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText("§4[§6§lAUTOGG REIMAGINED§4]§a: The mod is disabled in OneConfig. Please enable it."))
    }}

    // Make sure the command can be used by any player
    override fun canCommandSenderUseCommand(sender: ICommandSender) = true

}