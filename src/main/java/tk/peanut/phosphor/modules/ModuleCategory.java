package tk.peanut.phosphor.modules;

public enum ModuleCategory {
    RENDER("Render"), MOVEMENT("Movement"), COMBAT("Combat"), MISC("Misc"), PLAYER("Player"), WORLD("World"), FUN("Fun");

    private String name;

    ModuleCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
