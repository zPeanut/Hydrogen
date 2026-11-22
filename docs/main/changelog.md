# Changelog

All changes of Hydrogen are documented in this File.  
Hydrogen adheres to [Semantic Versioning].  
  
Back to the [homepage].

## v1.12.5

released 4th july 2022

- avoid crash when using hydrogen together with sk1er mods

## v1.12.4

released 1st may 2022

- reach distance can now be modified to decimal places
- fixed random crashes due to arraylist

## v1.12.3

released 18th february 2022

- remove debug print lines when starting up
- removed bowaimbot (not working properly, readding in 1.13 or later)
- removed aimassist (not working properly, causing crashes, readding in 1.13 or later)
- removed targetselect (component of aimassist, future aimassist revamp wont have this)

## v1.12.2

released 18th february 2022

- **fixed a bug that was causing the game to crash when trying to eat (thanks toast!)**
- changed reach distance to int only values
- fix watermark settings not working in the **new** theme


## v1.12.1

released 1st februrary 2022

- added git commit hash to main menu version hover string
- reworked autoclicker to make it better
- reworked blur type in the clickgui to cover entire screen (ingame hud is now obscured by it)
- removed blur from hotbar module (causing issues with other OS's and optifine, aswell as OpenGL)
- fixed old sneak being activated without animations module being on
- fixed flickering sneak effect

## v1.12

released 22nd January 2022

- **new settings config using .json filetype** 
	- **your old settings WILL be imported over to the new configsystem**, provided you have launched 1.12 once
	- your old setting files will however be deleted. meaning starting an older version of hydrogen, none of your previously saved settings will be present.
- added potioneffects to hud (show active potion effects)
- added freecam module (noclip not working yet)
- added noswing module (disables swing animaion, client or server side)
- added hitanimation module ("german-like" different hit animation)
- added autotype module (using new text button), lets you execute text or a command on toggle
- added **animaitons module**, including:
	- **BlockHit** - adds back the pre 1.8 blockhit animation
	- **Rod** - scales the Rod back to pre 1.8 style
	- **Bow** - scales the Bow back to pre 1.8 style, clicking and shooting will be smooth rather than instant
	- **Third-person Block** - blocking in thirdperson is reverted back how it looked pre 1.8
	- **Armor Damage** - hitting an enemy in 1.8 doesnt turn the armor red, pre 1.8 it did. this module reverts back to how it was pre 1.8.
	- **Inventory Offset** - having an potion enabled, shifts the inventory to the right in 1.8. pre 1.8, this does not occur.
	- **Dropped Items** - makes dropped items appear 2D again, how it used to be pre 1.8
	- **Tab List** - reverts the tablist to how it looked pre 1.8.
- **added HUD styles**, current ones are:
	- **New** (current layout, using TTF font) _(default)_
	- **Classic** (legacy phosphor _(hydrogen's old name)_ layout, using minecraft font. present from versions 1.0 - 1.4)
	- **Tephra** (legacy tephra layout (imported from old tephra client, which hydrogen is based on))
- added text button to clickgui
- added particles setting to clickgui
- added rainbow color count setting to arraylist
- added arraylist sorting method setting
- added nameprotect name setting (using new text button)
- added nameprotect other user setting (changes other peoples names to a given value (using new text button))
- added new welcome text on main menu, on first startup
- added welcome text messages on first startup once you load a world
- deprecate old setting system, will be removed in later released
- main menu buttons have been centered
- the watermark module, mainmenu module and arraylist module are enabled by default on first startup
- fix minecraft font clickgui text being "squished"
- clickgui modules are sorted by alphabet again, not by length
- remove airstrafe and flight module (not used at all)
- remove hydrogen being shown on the forge mod list

this was released on commit 727.

## 1.11.4

released 4th January 2022

- add splash to main menu

## v1.11.3

released 3rd January 2022

- fix reach not working

## v1.11.2

released 1st January 2022

- fix autosprint still sprinting, even though its turned off

## v1.11.1

released 1st January 2022

- remove frame counter from main menu accidentally left in from dev build
- fix watermark not drawing
- fix non stable string showing on stable builds in main menu
- fix non visible modules showing during deactivation in arraylist

## v1.11

released 31st December 2021

- added breadcrumbs module
- added nobowfov module
- added cameraclip module
- added autobow module
- added antiblind module
- added welcome message to first time users of hydrogen
- added tooltip to version on main menu
- added coordinates style to info (1-line or 3-line)
- added changelog button to mainmenu
- change credits button on main menu to link to the github readme instead of seperate gui
- change default watermark to old
- change default hud font to Minecraft
- changed the update reminder - now at bottom of main menu
- move safewalk to movement category
- fix hotbar date and time locations
- remove dropshadow setting from hud

## v1.10

released 24th December 2021

- add ttfchat module
- add panic module 
- add nameprotect module
- add tracers module
- add murdermystery module
- add pingspoof module
- add visible setting to clickgui (whether they show up in the arraylist or not)
- add murdermystery setting to nametags
- add random ms setting to triggerbot and autoclicker
- add onclick setting to aimbot
- move nospeedfov to render category
- remove esp modes (not working)
- fix arraylist massively lagging when many modules were activated

## v1.9.1

released 6th September 2021

- added tooltips to clickgui
- fixed func_73734_a failed mixin apply on startup (thanks neyled!)
- fixed credits button in main menu being 1px longer

## v1.9

released 3rd September 2021

- added STap
- added BowAimbot
- added NoChatRect
- added autoclicker "type" mode (right click, left click)
- added autoclicker random ms option
- added unbind option to clickgui
- renamed "W-Tap" -> "WTap"
- renamed "aim assist" to "aimassist"
- moved WTap from movement to combat
- fixed nochatrect crashing sometimes
- mainmenu watermark is now drawn in ttf

## v1.8.3

released 27th July 2021

- fix update button not working when version is outdated

## v1.8.2

released 27th July 2021

- fix outdated check (update notification) for real now

## v1.8.1

released 27th July 2021

- fix chat dissapearing and chat history resetting when opening clickgui

## v1.8

released 26th July 2021

- add aim assist module
- add targets select module
- add fastbow module
- add hitbox module
- add reach module
- fix outdated check in main menu
- add setting to toggle font in clickgui (ttf / minecraft font)
- add color picker to gui
- main menu rect now has an outline
- outdated check now works on patch versions (1.8.**x**)
- fixed autoclicker clicking faster than intended
- added startup sound (toggleable)
- added credits screen to main menu
- settings with the same name dont overwrite themselves anymore due to settings now being saved to the module itself

## v1.7

released 14th July 2021

- added alt manager
- added alt manager button to main menu (seperate menu coming soon when more custom guis are coming)
- added thealtening support (in alt manager)
- errors are now showing up correctly in console log

## v1.6.3

released 11th July 2021

- add on click option to autoclicker
- remove custom blur on clickgui, uses minecraft default one now
- new default clickgui bind is now **LCONTROL**

## v1.6.2

released 17th May 2021

- move clickgui to hud category
- info (HUD) font is now drawn in correct SF UI font, instead of verdana

## v1.6.1

released 14th April 2021

- fix hotbar drawn incorrectly when opening debug (f3) mode
- fix crashing error ""ticksExisted" not located in target class" caused by wrong mixin
- fix crashing error ""func_183501_a" not located in target class" caused my wrong mixin
- correctly move clickgui to "hud / gui" category
- new symbol on "settings marker" in clickgui (now shows "v" when extended)

## v1.6

released 14th March 2021

- fix font issues in 1.5
- add command system
- add bind command
- add toggle command
- add help command
- the "H" on the new watermark is now lowercase
- hud hotbar is now blurred
- hotbar font has now been correctly fixed to SF_UI (previously verdana)
- more particles in main menu!

## v1.5

released 12th March 2021

- add custom main menu
- add toggleable option for custom main menu in hud category
- add rainbowsetting to main menu
- add outdated check
- add updater to main menu
- add nohurtcam
- fixed storageesp category
- add bedaura
- add bedesp
- remove velocity
- change font in hud from verdana -> SF-UI
- fix arraylist errors
- rename no-bob back to nobob
- add color and outline option to itemesp
- fix items drawing above nametags above yourself


## v1.4.2

released 26th February 2021

- fix several visual bugs

## v1.4.1

released 24th February 2021

- fix arraylist outline
- wtap now only happens when hitting near an enemy
- nobob has been renamed to "no-bob"

## v1.4

released 21st February 2021

- arraylist slide speed can now be changed
- custom hotbar added
- changed clickgui blur to overlap inventory and gui
- left aligned arraylist has been removed
- watermark background now has an outline
- eagle has been renamed to fastbridge
- airstrafe module has been added
- autoclicker now actually presses leftclick
- custom font has been added
- clickgui has now added ttf font setting
- the ingamehud is now drawn in ttf font (verdana)
- added toggleable 24/12h time format
- info alignment added (left or right)
- arraylist color now has a category specific option (i.e. combat is orange, player is yellow etc.)
- fixed drawrect bug in clickgui
- fixed crash bug when enabling fastbridge
- add antiafk module
- add velocity module
- add flight module
- add itemesp module

## v1.3

released 13th February 2021

- modules now stay enabled when closing and starting the game
- add nametags module
- add cheststealer module
- add inventorywalk module
- add clickgui module descriptions
- add nospeedfov module
- add trajectories module
- add nochatrect module
- add storageesp module
- setting values in clickgui now draw righthand sided
- keybind in clickgui now has a seperator to differienciate from other settings

## v1.2

released 8th February 2021

- fixed render bug caused by outlineesp
- add rainbow array list
- watermark now always stays on the left side
- coordinates, time and fps added to hud
- renamed to hydrogen
- add safewalk module
- add fullbright module
- made clickgui colors darker
- added blur option to clickgui
- added optional entity setting to outlineesp

## v1.1

released 6th February 2021

- added autoclicker cps support
- added triggerbot, with cps support
- hud no longer draws in array
- clickgui default key now lshift
- fixed outlineesp not drawing on players
- add nobob module

## v1.0

released 5th February 2021

- fixed game crash on startup
- fixed game crash when using sprint or eagle
- added chams module
- added autosprint
- added fastplace
- added clickgui
- added ingame hud
- added eagle module
- added saving clickgui values and binds

Hydrogen (then named Phosphor), was created on the 2nd February 2021.   
The original client which Hydrogen is based off of *(Tephra for Minecraft 1.8.8)*, was released on the 22nd October 2017.


[Semantic Versioning]: http://semver.org/spec/v2.0.0.html
[homepage]: https://zpeanut.github.io/
