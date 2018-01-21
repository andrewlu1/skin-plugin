package cn.andrewlu.skinplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class SkinPluginImpl implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.afterEvaluate {
            TaskHandler handler = new TaskHandler()
            project.tasks.each { task ->
                task.doLast {
                    project.android.applicationVariants.all { variant ->
                        handler.handle(project, task, variant.name)
                    }
                }
            }
            //add resmanager 依赖。
            project.dependencies.implementation("cn.andrewlu:resmanager:+")
        }
    }

}