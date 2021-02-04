# Phosphor

A forge-mixin based ghost client for Minecraft 1.8.9.  
Client originally based on LiquidBase by CCBlueX.

## Setting up

Phosphor runs on Gradle. Make sure you have it is installed correctly before setting up.

1. Clone the Repository under `https://github.com/zPeanut/Phosphor.git`.
2. Open command prompt and CD into the directory.
3. Depending on which IDEA you use, different commands have to be ran.
    - IntelliJ: `gradlew setupDecompWorkspace idea genIntellijRuns build`
    - Eclipse: `gradlew setupDecompWorkspace eclipse build`
4. Open the folder in your preferred IDEA, depending which you chose above.
5. In your run configuration, type `-Dfml.coreMods.load=tk.peanut.phosphor.injection.MixinLoader` in your VM options
