# Phosphor

A forge-mixin based ghost client for Minecraft 1.8.9.  
Originally designed as a MCP Client (called Tephra), it is now being ported to Forge.  
Over time, more and more features will be added.

## Setting up

Phosphor runs on Gradle. Make sure you have it is installed correctly before setting up.

1. Clone the Repository under `https://github.com/zPeanut/Phosphor.git`.
2. Open command prompt and CD into the directory.
3. Depending on which IDEA you use, different commands have to be ran.
    - IntelliJ: `gradlew setupDecompWorkspace idea genIntellijRuns build`
    - Eclipse: `gradlew setupDecompWorkspace eclipse build`
4. Open the folder in your preferred IDEA, depending which you chose above.
5. In your run configuration, type `-Dfml.coreMods.load=tk.peanut.phosphor.injection.MixinLoader` in your VM options

## Credits

**CCBlueX** for providing most of the initial ClientBase  
**HeroCode** for providing the ClickGUI API.  
**Hexeption** for providing the Mixin code for Chams and OutlineESP  
