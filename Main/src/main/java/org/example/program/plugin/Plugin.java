package org.example.program.plugin;

import org.example.program.containers.Manager;
import org.example.program.topbar.TopContainer;

import java.util.Map;

public interface Plugin {
    public void setup(TopContainer topContainer, Manager manager);

    public void postSetup();
}
