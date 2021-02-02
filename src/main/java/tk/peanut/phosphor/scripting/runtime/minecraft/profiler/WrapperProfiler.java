package tk.peanut.phosphor.scripting.runtime.minecraft.profiler;

import net.minecraft.profiler.Profiler;

import java.util.List;

public class WrapperProfiler {
    private Profiler real;

    public WrapperProfiler(Profiler var1) {
        this.real = var1;
    }

    public Profiler unwrap() {
        return this.real;
    }

    public void clearProfiling() {
        this.real.clearProfiling();
    }

    public void startSection(String var1) {
        this.real.startSection(var1);
    }

    public void endSection() {
        this.real.endSection();
    }

    public List getProfilingData(String var1) {
        return this.real.getProfilingData(var1);
    }

    public void endStartSection(String var1) {
        this.real.endStartSection(var1);
    }

    public String getNameOfLastSection() {
        return this.real.getNameOfLastSection();
    }

    public boolean isProfilingEnabled() {
        return this.real.profilingEnabled;
    }

    public void setProfilingEnabled(boolean var1) {
        this.real.profilingEnabled = var1;
    }
}
