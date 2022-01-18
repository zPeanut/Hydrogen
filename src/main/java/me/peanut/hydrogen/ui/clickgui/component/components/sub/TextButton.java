package me.peanut.hydrogen.ui.clickgui.component.components.sub;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;
import me.peanut.hydrogen.ui.clickgui.component.Component;
import me.peanut.hydrogen.ui.clickgui.component.components.Button;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Created by peanut on 02/01/2022
 */
public class TextButton extends Component {

    private boolean hovered;
    private final Button parent;
    private final Setting set;
    private int offset;
    private int x;
    private int y;
    private final Module mod;
    private boolean isEditing;
    private float editUpdateTicks;
    private Minecraft mc = Minecraft.getMinecraft();
    private String displayString;
    private float preClick;

    public TextButton(Setting set, Button button, Module mod, int offset) {
        this.set = set;
        this.parent = button;
        this.mod = mod;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
        this.displayString = set.getText();
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
    }

    @Override
    public void renderComponent() {
        float onethird = 1.3333333333333f;
        this.editUpdateTicks += 0.1f * Utils.deltaTime;
        if(this.editUpdateTicks > 80) {
            this.editUpdateTicks = 0;
        }

        int c1 = new Color(17, 17, 17, 140).getRGB(); // 0x88111111
        int c3 = new Color(34, 34, 34, 140).getRGB(); // 0x88222222

        RenderUtil.rect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth() * 1), parent.parent.getY() + offset + 12, hovered ? 0x99000000 : 0x88000000);
        RenderUtil.rect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, c1);

        if(this.isEditing) {
            RenderUtil.rect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth() * 1), parent.parent.getY() + offset + 12, 0x33000000);
        }

        GL11.glPushMatrix();
        GL11.glScalef(0.75f,0.75f, 0.75f);
        if(Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getMode().equalsIgnoreCase("TTF")) {

            if(this.isEditing) {
                String displayedStringTTF = Utils.abbreviateString(displayString, 27);

                FontHelper.verdana.drawStringWithShadow("§7" + displayedStringTTF, (parent.parent.getX() + 3) * onethird + 5, (parent.parent.getY() + offset + 2) * onethird, Color.WHITE);
            } else {
                String displayedStringTTF = Utils.abbreviateString(displayString, 19);

                FontHelper.verdana.drawStringWithShadow(this.set.getName() + ": " + displayedStringTTF, (parent.parent.getX() + 3) * onethird + 5, (parent.parent.getY() + offset + 2) * onethird, Color.WHITE);
            }

        } else {
            if(this.isEditing) {
                String displayedStringMC = Utils.abbreviateString(displayString, 21);
                mc.fontRendererObj.drawStringWithShadow(displayedStringMC, (parent.parent.getX() + 3) * onethird + 5, (parent.parent.getY() + offset + 2) * onethird + 2, -1);
            } else {
                String displayedStringMC = Utils.abbreviateString(displayString, 14);
                mc.fontRendererObj.drawStringWithShadow(this.set.getName() + ": " + displayedStringMC, (parent.parent.getX() + 3) * onethird + 5, (parent.parent.getY() + offset + 2) * onethird + 2, -1);
            }
        }

        GL11.glPopMatrix();
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.hovered = isMouseOnButton(mouseX, mouseY);
        this.y = parent.parent.getY() + offset;
        this.x = parent.parent.getX();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if(isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
            if(System.currentTimeMillis() - this.preClick <= 250L) {
                this.isEditing = true;
            }
            this.preClick = System.currentTimeMillis();
        } else {
            this.isEditing = false;
        }
    }

    @Override
    public void keyTyped(char typedChar, int key) {
        if(this.isEditing) {
            if(key == 14) {
                if(this.displayString.length() > 0) {
                    this.displayString = this.displayString.substring(0, this.displayString.length() - 1);
                }
                return;
            }
            if(ChatAllowedCharacters.isAllowedCharacter(typedChar) || typedChar == '§') {
                this.displayString += typedChar;
            }
            set.setText(this.displayString);
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
    }
}
