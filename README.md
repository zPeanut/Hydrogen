# Hydrogen
A mixin based ghost client for Minecraft 1.8.9 built on Minecraft Forge.  
Originally designed as a MCP Client (called Tephra), it is now being ported to Forge.  
Over time, more and more features will be added.  

Developed by [Me] and [UltramoxX].

## Features

There are currently 20 cool modules included in Hydrogen.  
More modules are all being ported over from the previously mentioned MCP client.  
  
The current included Modules are:

### Combat

-  AutoClicker
-  TriggerBot
-  Velocity

### Movement

-  AirStrafe
-  Eagle
-  Flight
-  Sprint
-  W-Tap

### Player

-  AntiAFK
-  AutoRespawn
-  ChestStealer
-  FastPlace
-  InventoryWalk
-  NoSpeedFOV
-  SafeWalk

### Render

-  Chams
-  ClickGUI
-  ESP
-  Fullbright
-  HUD
-  ItemESP
-  NameTags
-  NoBob
-  NoChatRect
-  StorageESP
-  Trajectories
-  Main Menu

### Hud (customizable Modules)

-  ArrayList
-  Hotbar
-  Info
-  Watermark

## Setting up

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

