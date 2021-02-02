package tk.peanut.phosphor.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * @author TheSlowly
 */
public class Shader {

    private static final String VERTEX_SHADER = "#version 130\n" +
            "\n" +
            "void main() {\n" +
            "    gl_TexCoord[0] = gl_MultiTexCoord0;\n" +
            "    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n" +
            "}";
    private Minecraft mc = Minecraft.getMinecraft();
    private int program;
    private long startTime;

    /**
     * @param fragment The fragment shader code (not the resource location)
     */
    public Shader(@NotNull String fragment) {
        program = glCreateProgram();
        startTime = System.currentTimeMillis();
        initShader(fragment);
    }

    private void initShader(@NotNull String frag) {
        int vertex = glCreateShader(GL_VERTEX_SHADER), fragment = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(vertex, VERTEX_SHADER);
        glShaderSource(fragment, frag);
        glValidateProgram(program);
        glCompileShader(vertex);
        glCompileShader(fragment);
        glAttachShader(program, vertex);
        glAttachShader(program, fragment);
        glLinkProgram(program);
    }

    public void renderFirst() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glUseProgram(program);
    }

    public void renderSecond() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        ScaledResolution sr = new ScaledResolution(mc);
        glBegin(GL_QUADS);
        glTexCoord2d(0, 1);
        glVertex2d(0, 0);
        glTexCoord2d(0, 0);
        glVertex2d(0, sr.getScaledHeight());
        glTexCoord2d(1, 0);
        glVertex2d(sr.getScaledWidth(), sr.getScaledHeight());
        glTexCoord2d(1, 1);
        glVertex2d(sr.getScaledWidth(), 0);
        glEnd();
        glUseProgram(0);
    }

    public void bind() {
        glUseProgram(program);
    }

    public int getProgram() {
        return program;
    }

    @NotNull
    public Shader uniform1i(@NotNull String loc, int i) {
        glUniform1i(glGetUniformLocation(program, loc), i);
        return this;
    }

    @NotNull
    public Shader uniform2i(@NotNull String loc, int i, int i1) {
        glUniform2i(glGetUniformLocation(program, loc), i, i1);
        return this;
    }

    @NotNull
    public Shader uniform3i(@NotNull String loc, int i, int i1, int i2) {
        glUniform3i(glGetUniformLocation(program, loc), i, i1, i2);
        return this;
    }

    @NotNull
    public Shader uniform4i(@NotNull String loc, int i, int i1, int i2, int i3) {
        glUniform4i(glGetUniformLocation(program, loc), i, i1, i2, i3);
        return this;
    }

    @NotNull
    public Shader uniform1f(@NotNull String loc, float f) {
        glUniform1f(glGetUniformLocation(program, loc), f);
        return this;
    }

    @NotNull
    public Shader uniform2f(@NotNull String loc, float f, float f1) {
        glUniform2f(glGetUniformLocation(program, loc), f, f1);
        return this;
    }

    @NotNull
    public Shader uniform3f(@NotNull String loc, float f, float f1, float f2) {
        glUniform3f(glGetUniformLocation(program, loc), f, f1, f2);
        return this;
    }

    @NotNull
    public Shader uniform4f(@NotNull String loc, float f, float f1, float f2, float f3) {
        glUniform4f(glGetUniformLocation(program, loc), f, f1, f2, f3);
        return this;
    }

    @NotNull
    public Shader uniform1b(@NotNull String loc, boolean b) {
        glUniform1i(glGetUniformLocation(program, loc), b ? 1 : 0);
        return this;
    }

    public void addDefaultUniforms() {
        glUniform2f(glGetUniformLocation(program, "resolution"), mc.displayWidth, mc.displayHeight);
        float time = (System.currentTimeMillis() - this.startTime) / 1000f;
        glUniform1f(glGetUniformLocation(program, "time"), time);
    }
}
