![alt text](https://raw.githubusercontent.com/zPeanut/Resources/master/hydrogen.png)

A mixin based ghost client for Minecraft 1.8.9 built on Minecraft Forge.  
Originally designed as a MCP Client (called Tephra), it is now being ported to Forge.  
Over time, more and more features will be added.  

Current stable release: **1.7**

Developed by [Me] and [UltramoxX].

Join our discord!
https://discord.gg/dmw5N5X9p6

## Features

There are currently 34 cool modules included in Hydrogen.  
More modules are all being ported over from the previously mentioned MCP client.  
  
The current included Modules are:

### Combat

-  AutoClicker
-  TriggerBot

### Movement

-  AirStrafe
-  Eagle
-  Flight
-  Sprint
-  W-Tap

### Player

-  AntiAFK
-  AutoRespawn
-  BedAura
-  ChestStealer
-  FastPlace
-  InventoryWalk
-  NoSpeedFOV
-  SafeWalk

### Render

-  BedESP
-  Chams
-  ClickGUI
-  ESP
-  Fullbright
-  HUD
-  ItemESP
-  NameTags
-  NoBob
-  NoHurtCam
-  NoChatRect
-  StorageESP
-  Trajectories

### Hud / Gui (customizable Modules)

-  ArrayList
-  Hotbar
-  HUD
-  Info
-  Main Menu
-  Watermark

## USER INSTALLATION

1. Install Forge for Minecraft 1.8.9
2. Drag and Drop ``hydrogen-x.x.x.jar`` into your directory for Forge mods (Win-Default: ``%appdata%/.minecraft/mods``).
3. Enjoy the ride.

## Setup with Forge MDK

Hydrogen runs on Gradle. Make sure you have it is installed correctly before setting up.

1. Clone the Repository under `https://github.com/zPeanut/Hydrogen.git`
2. Open command prompt and CD into the directory.
3. Depending on which IDEA you use, different commands have to be ran.
    - IntelliJ: `gradlew setupDecompWorkspace idea genIntellijRuns build`
    - Eclipse: `gradlew setupDecompWorkspace eclipse build`
4. Open the folder in your preferred IDEA, depending which you chose above.
5. Open your run configuration and type `-Dfml.coreMods.load=tk.peanut.hydrogen.injection.MixinLoader` into your VM options

## Credits

**superblaubeere27** for providing most of the initial ClientBase  
**HeroCode** for providing the ClickGUI API.  
**Hexeption** for providing the OutlineESP code.

[me]: https://github.com/zPeanut
[UltramoxX]: https://github.com/Morten-Renner

