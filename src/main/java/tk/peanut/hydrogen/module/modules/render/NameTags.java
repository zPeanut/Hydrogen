package tk.peanut.hydrogen.module.modules.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.apache.commons.codec.language.bm.Rule;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peanut on 08/02/2021
 */
@Info(name = "NameTags", description = "Enhanced Nametags", category = Category.Render)
public class NameTags extends Module {

    public static NameTags instance;

    public NameTags() {
        super(Keyboard.KEY_NONE, new Color(199, 255, 201));
        instance = this;
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Health", this, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("State", this, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Items", this, true));
    }


    public static String clearFormat(String s) {
        List<String> formats = new ArrayList<String>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\u00a7') {
                formats.add(s.substring(i, Math.min(i+2, s.length())));
            }
        }

        for(String st : formats) {
            s = s.replace(st, "");
        }
        return s;
    }

    public static List<String> getEnchantList(ItemStack stack) {
        List<String> eList = new ArrayList<String>();
        if (stack != null && stack.getEnchantmentTagList() != null) {
            for(int j = stack.getEnchantmentTagList().tagCount()-1; j >= 0; j--) {
                int enchantLevel = stack.getEnchantmentTagList().getCompoundTagAt(j).getInteger("lvl");
                int enchantID = stack.getEnchantmentTagList().getCompoundTagAt(j).getInteger("id");
                Enchantment enchant = Enchantment.getEnchantmentById(enchantID);

                if (enchant == null) {
                    continue;
                }

                String name = StatCollector.translateToLocal(enchant.getName());
                name = clearFormat(name);
                String level = enchantLevel+"";
                StatCollector.translateToLocal("enchantment.level." + enchantLevel);

                String[] parts = name.split(" ");

                String disp = "";
                for(String s : parts) {
                    disp+= s.substring(0, 1).toUpperCase();
                }
                disp+= " "+level;
                eList.add(disp);
            }
        }
        return eList;
    }

    public static double[] entityRenderPos(Entity e) {
        float p_147936_2_ = mc.timer.renderPartialTicks;

        double x = (e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)p_147936_2_)-mc.getRenderManager().viewerPosX;
        double y = (e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)p_147936_2_)-mc.getRenderManager().viewerPosY;
        double z = (e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)p_147936_2_)-mc.getRenderManager().viewerPosZ;

        return new double[] {x, y, z};
    }

    public void renderArmorESP(EntityLivingBase entity) {
        GlStateManager.depthMask(true);
        int xOff = -40;
        renderItem(entity.getHeldItem(), xOff, -18);
        for(int i = 0; i <= 3; i++) {
            ItemStack stack = entity.getCurrentArmor(3-i);
            if (stack == null) {
                continue;
            }
            renderItem(stack, xOff+((i+1)*16), -18);
        }
        mc.fontRendererObj.drawString("", (xOff), -44-(mc.fontRendererObj.FONT_HEIGHT), -1);
        GlStateManager.depthMask(false);
    }

    public void renderItem(ItemStack stack, int x, int y) {
        if (Hydrogen.getClient().settingsManager.getSettingByName("Items").isEnabled()) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
            List<String> eList = getEnchantList(stack);
            for(String s : eList) {
                mc.fontRendererObj.drawString(s, ((x+8)*2)-(mc.fontRendererObj.getStringWidth(s)/2), (y-7)*2-(mc.fontRendererObj.FONT_HEIGHT*eList.indexOf(s)), -1);
            }
            GlStateManager.popMatrix();
        }
        mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, x, y-2, "");
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, -150);
        mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y-4);
        GlStateManager.popMatrix();
    }

    public void render3DPost() {
        if (!isEnabled() || mc.theWorld == null || mc.theWorld.loadedEntityList == null) { return; }

        RenderHelper.enableStandardItemLighting();
        for(int i = 0; i < mc.theWorld.loadedEntityList.size(); i++) {
            Entity e = (Entity)mc.theWorld.loadedEntityList.get(i);

            if (e instanceof EntityLivingBase) {
                if (mc.getRenderManager().getEntityRenderObject(e) instanceof RendererLivingEntity) {
                    double[] p = entityRenderPos(e);
                    Utils.passSpecialRenderNameTags((EntityLivingBase)e, p[0], p[1], p[2]);
                }
            }
        }
        RenderHelper.disableStandardItemLighting();
    }
}
