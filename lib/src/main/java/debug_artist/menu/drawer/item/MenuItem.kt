package debug_artist.menu.drawer.item

import debug_artist.menu.drawer.item.input.InputItemListener
import debug_artist.menu.drawer.item.phoenix.RestartListener
import debug_artist.menu.drawer.item.spinner.SpinnerItemListener
import debug_artist.menu.report_bug.BugRepository

open class MenuItem
open class DividerMenuItem : MenuItem()

open class SwitchMenuItem(var checked: Boolean = false) : MenuItem()

open class ButtonMenuItem : MenuItem()
open class LabelMenuItem(val properties: Map<String, String>) : MenuItem()

open class StethoSwitchMenuItem(checked: Boolean = false) : SwitchMenuItem(checked)
open class LeakCanarySwitchMenuItem(checked: Boolean = false) : SwitchMenuItem(checked)
open class PicassoLogsSwitchMenuItem(checked: Boolean = false) : SwitchMenuItem(checked)

open class LynksButtonMenuItem : ButtonMenuItem()

open class PhoenixButtonMenuItem(val restartListener: RestartListener) : ButtonMenuItem()

open class SpinnerMenuItem(val id: Int,
                           val name: String,
                           val options: Array<String>,
                           val selectedItem: Int,
                           val listener: SpinnerItemListener) : ButtonMenuItem()

open class InputMenuItem(val id: Int,
                         val name: String,
                         val inputItemListener: InputItemListener) : ButtonMenuItem()

open class ReportBugSwitchMenuItem(checked: Boolean = false,
                                   val repositoryBuilder: BugRepository.Builder?)
  : SwitchMenuItem(checked)