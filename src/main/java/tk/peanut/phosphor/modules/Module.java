package tk.peanut.phosphor.modules;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public abstract class Module {
    protected static final Minecraft mc = Minecraft.getMinecraft();
    private String name;
    private String description;
    private ModuleCategory category;
    private boolean canBeEnabled;
    private boolean hidden;
    private int keybind;
    private boolean state;

    protected Module(String name, String description, ModuleCategory moduleCategory) {
        this(name, description, moduleCategory, true, false, Keyboard.KEY_NONE);
    }

    protected Module(String name, String description, ModuleCategory category, boolean canBeEnabled, boolean hidden, int keybind) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.canBeEnabled = canBeEnabled;
        this.hidden = hidden;
        this.keybind = keybind;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public boolean isCanBeEnabled() {
        return canBeEnabled;
    }

    public boolean isHidden() {
        return hidden;
    }

    public int getKeybind() {
        return keybind;
    }

    public void setKeybind(int keybind) {
        this.keybind = keybind;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        if (state) {
            this.state = true;

            onEnable();
        } else {
            this.state = false;
            onDisable();

            }
    }

    protected void onEnable() {

    }

    protected void onDisable() {

    }
}
