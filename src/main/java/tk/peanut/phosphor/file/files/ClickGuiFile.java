package tk.peanut.phosphor.file.files;

import tk.peanut.phosphor.file.FileManager;
import tk.peanut.phosphor.modules.modules.render.ClickGUI;
import tk.peanut.phosphor.ui.clickgui.clickgui.ClickGui;
import tk.peanut.phosphor.ui.clickgui.clickgui.component.Frame;

/**
 * Created by peanut on 03/02/2021
 */
public class ClickGuiFile {

    private static FileManager clickGuiCoord = new FileManager("clickgui", "Phosphor");

    public ClickGuiFile() {
        try {
            loadClickGui();
        } catch (Exception e) {
        }
    }

    public static void saveClickGui() {
        try {
            clickGuiCoord.clear();
            for (Frame frame : ClickGui.frames) {
                clickGuiCoord.write(frame.category.name() + ":" + frame.getX() + ":" + frame.getY() + ":" + frame.isOpen());
            }
        } catch (Exception e) {
        }
    }

    public static void loadClickGui() {
        try {
            for (String s : clickGuiCoord.read()) {
                String panelName = s.split(":")[0];
                float panelCoordX = Float.parseFloat(s.split(":")[1]);
                float panelCoordY = Float.parseFloat(s.split(":")[2]);
                boolean extended = Boolean.parseBoolean(s.split(":")[3]);
                for (Frame frame : ClickGui.frames) {
                    if (frame.category.name().equalsIgnoreCase(panelName)) {
                        frame.setX((int) panelCoordX);
                        frame.setY((int) panelCoordY);
                        frame.setOpen(extended);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
