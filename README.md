![alt text](https://raw.githubusercontent.com/zPeanut/Resources/master/hydrogen.png)

An open source, mixin based ghost client for Minecraft 1.8.9 built on Minecraft Forge.  
Developed by [Me] and [UltramoxX].  
Contributions are highly welcome!

Current stable release: **1.12.3**  
  
Join our discord!  
https://discord.gg/dmw5N5X9p6

Full Changelog:  
https://zpeanut.github.io/main/changelog
  
Please remember that: **We are not responsible for any repercussions you face using this client.  
We are merely providing you with it. Use at your own risk.** 


## Features

There are currently over 50 modules included in Hydrogen,  
including a fully customizable Ingame GUI, aswell as various  
render, combat and utility focused modules.  
  
Go ahead and try them out!

## USER INSTALLATION

Before you install Hydrogen, make sure to have installed Minecraft Forge for Minecraft 1.8.9.

### Automatic Installation:

1. Download the [Installer] ([src code])
2. Select your version.
3. Enjoy the ride.

### Manual Installation:

1. Download the latest [release] of Hydrogen.
2. Drag and Drop ``hydrogen-x.x.x.jar`` into your directory for Forge mods (Win-Default: ``%appdata%/.minecraft/mods``).
3. Select your launcher profile with the respective Forge version.
4. Enjoy the ride.

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

### Dependencies:  
  
**[DarkMagician6's EventAPI]**, an open source EventAPI system, made for handling events across java based projects.  
**[Semver4j]**, A Library for semantic versioning in Java applications. Used to compare semantic versions in the client to check if the client is outdated.

### Credits:  
  
**superblaubeere27**, for providing most of the initial client base.  
**HeroCode**, for providing the ClickGUI API.  
**Lemon**, for providing the Settings system, used in the client.  
**All of my contributors on discord**, who've reported bugs and suggested modules over the past:
- ProfKambing
- QianHeJ
- neyled
- qaql
- S4rnth1l
- perry

[me]: https://github.com/zPeanut
[UltramoxX]: https://github.com/Morten-Renner
[SemVer4j]: https://github.com/vdurmont/semver4j
[DarkMagician6's EventAPI]: https://bitbucket.org/DarkMagician6/eventapi/src/master/
[Installer]: https://github.com/zPeanut/Hydrogen/releases/download/1.11.3/hydrogen-installer.exe
[src code]: https://github.com/zPeanut/python-stuff/blob/master/hydrogen-installer.pyw
[release]: https://github.com/zPeanut/Hydrogen/releases
