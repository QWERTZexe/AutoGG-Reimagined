package com.qwertz.autogg_reimagined.config

import com.qwertz.autogg_reimagined.AutoGG
import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.Header
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.config.annotations.Text
import cc.polyfrost.oneconfig.config.annotations.Slider
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import cc.polyfrost.oneconfig.config.data.OptionSize
import scala.tools.cmd.Opt


object AutoGGConfig : Config(Mod(AutoGG.NAME, ModType.UTIL_QOL, "/AutoGG.png"), AutoGG.MODID + ".json") {
    @Header(text = "AutoGG", size = OptionSize.DUAL)
    var abc: Boolean = false
    @Text(name = "GG MESSAGE")
    var GGMessage: String = "GG"
    @Switch(name = "ENABLE SECOND MESSAGE")
    var GG2ndSwitch: Boolean = false
    @Text(name = "SECOND MESSAGE", size = OptionSize.DUAL)
    var GG2ndMessage: String = "AutoGG Reimagined by QWERTZ_EXE!"
    @Slider(
        name = "SECOND MESSAGE DELAY (IN SECONDS)",
        min = 0f, max = 5f
    )
    var GG2ndDelay: Float = 1.0F
    @Text(name = "TRIGGERS (SEPERATE WITH ';')", size = OptionSize.DUAL, multiline = true)
    var GGTriggers: String = "Reward Summary;1st Killer;2nd Killer;3rd Killer;Damage Dealt"
    @Header(text = "AntiGG", size = OptionSize.DUAL)
    var abc2: Boolean = false
    @Switch(
        name = "HIDE GG MESSAGES"
    )
    var AntiGGSwitch: Boolean = false
    @Switch(
        name = "NOTIFY ON HIDDEN MESSAGES"
    )
    var NotifySwitch: Boolean = true
    @Text(name = "TRIGGERS (SEPERATE WITH ';')", size = OptionSize.DUAL, multiline = true)
    var AntiGGTriggers: String = "gg;GG;Good Game;Gg;gG;GoOd GaMe;gOoD GaMe"
    init {
        initialize()
    }
}