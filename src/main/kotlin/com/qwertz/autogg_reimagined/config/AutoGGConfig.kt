package com.qwertz.autogg_reimagined.config

import com.qwertz.autogg_reimagined.AutoGG
import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.config.annotations.Text
import cc.polyfrost.oneconfig.config.annotations.Slider
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import cc.polyfrost.oneconfig.config.data.OptionSize


class AutoGGConfig : Config(Mod(AutoGG.NAME, ModType.UTIL_QOL, "/AutoGG.png"), AutoGG.MODID + ".json") {
    init {
        initialize()
    }
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
    @Text(name = "TRIGGER", size = OptionSize.DUAL)
    var GGTrigger: String = "§e§lWINNER"

}