package tk.peanut.phosphor.module;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;

public class Module {

    public String name;
    public String description;
    public int keyBind;
    public int color;


    public boolean visible;


    private Module module;
    public Category category;
    public static Minecraft mc = Minecraft.getMinecraft();
    public boolean toggled;
    public String suffix;
    private int slide = 0;

    public Module(String name, String description, int keyBind, Category category, int color) {
        this.name = name;
        this.description = description;
        this.keyBind = keyBind;
        this.category = category;
        this.color = color;
        this.suffix = "";
        setup();
    }

    public int getSlide() {
        return slide;
    }

    public void setSlide(int slide) {
        this.slide = slide;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean getState() {
        return this.isEnabled();
    }

    public static String capitalize(String line)
    {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName(String name) {
        return name;
    }


    public Module getModule() {
        return this.module;
    }
    public boolean isToggled() {
        return toggled;
    }

    public int getKeybind() {
        return keyBind;
    }

    public int getKeyBind() {
        return keyBind;
    }

    public boolean setDisabled() {
        return toggled = false;
    }

    public boolean setEnabled() {
        return toggled = true;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void onUpdate() {}

    public boolean isEnabled() {
        return toggled;
    }

    public boolean isVisible() {
        return visible;
    }

    public void toggle() {
        this.toggled = (!this.toggled);
        if(this.toggled) {
            onEnable();
            this.setSuffix("");
        }else{
            onDisable();
        }

    }
    public void onEnable() {
        EventManager.register(this);

    }
    public void onDisable() {
        EventManager.unregister(this);
    }

    public void onPre() {}

    public void setup() {
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Object getCategory() {
        return category;
    }

    public void onRender() {
    }

    public void render3DPost() {

    }

    public void onPreUpdate() {}




}