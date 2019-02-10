package org.lucasko

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.getTasks().create("downloadResource", MyResource.class)
                {
                };

    }
}