package cn.andrewlu.skinplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class SkinPluginImpl implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.extensions.create("skinConfig", ThemeExtensionParams)

        project.afterEvaluate {

            ThemeExtensionParams extensionParams = project.extensions.getByType(ThemeExtensionParams)
            String resManagerVer = "+";
            if (extensionParams.resToolVersion != null) {
                resManagerVer = extensionParams.resToolVersion;
            }
            //add resmanager 依赖。
            project.dependencies.implementation("cn.andrewlu:resmanager:$resManagerVer")
            println("resmanager ver set to:${resManagerVer}")

            //======================================================================================
            TaskHandler handler = new TaskHandler()
            project.tasks.each { task ->
                task.doLast {
                    project.android.applicationVariants.all { variant ->
                        handler.handle(project, task, variant.name)
                    }
                }
            }
        }
    }

}