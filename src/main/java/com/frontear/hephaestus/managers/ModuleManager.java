package com.frontear.hephaestus.managers;

import com.frontear.hephaestus.modules.*;
import com.frontear.hephaestus.modules.api.Module;

import java.util.ArrayList;

public class ModuleManager {
    public ArrayList<Module> moduleList = new ArrayList<Module>();

    public ModuleManager() {
        moduleList.add(new Sprint());
        moduleList.add(new Fullbright());
        moduleList.add(new AutoClicker());
        moduleList.add(new NoFOV());

        moduleList.add(new Panic());
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> enabledModules = new ArrayList<Module>();
        for (Module module : moduleList) {
            if (module.getState()) {
                enabledModules.add(module);
            }
        }

        return enabledModules;
    }
}
