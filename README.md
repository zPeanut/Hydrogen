![alt text](https://raw.githubusercontent.com/zPeanut/Resources/master/hydrogen.png)

An open source, mixin based utility mod for Minecraft 1.8.9 built on Minecraft Forge.  
Developed by [Me] and [UltramoxX].

Current stable release: **1.10**

Join our discord!
https://discord.gg/dmw5N5X9p6

## Features

There are currently over 40 modules included in Hydrogen,  
including a fully customizable Ingame GUI, aswell as various  
render, combat and utility focused modules.  
  
Go ahead and try them out!

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

## Exporting the client using Forge MDK

After you have succesfully made changes to Hydrogen and want to export it, simply type  

`.\gradlew clean build`  

into your IDEA Terminal or Windows Command Prompt / Windows Powershell.  
You will then get a jar file located at  

`\build\libs`  

inside of your directory. Simply put that file into your mods folder inside .minecraft and you're good to go!

## Credits

**superblaubeere27** for providing most of the initial client base.  
**HeroCode** for providing the ClickGUI API.  
**Lemon** for providing the Settings system, used in the client.  
**All of my contributors on discord**, who've reported bugs and suggested modules over the past.

[me]: https://github.com/zPeanut
[UltramoxX]: https://github.com/Morten-Renner

