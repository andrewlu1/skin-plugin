package cn.andrewlu.skinplugin

import org.gradle.api.Project
import org.gradle.api.Task;

class TaskHandler {

    public TaskHandler() {
    }

    public void handle(Project project, Task task, String variant) {
        //generateReleaseSources
        //processDebugResources
        String taskName = "process${variant.charAt(0).toUpperCase()}${variant.substring(1)}Resources"
        if (task.name == taskName) {
            println("${task}:start process Resource with R.txt >>>>>>>>>>>>>>")
            if (task.outputs.hasOutput) {
                for (File file : task.outputs.files) {
                    println("task.outputs.file:${file.absolutePath}")
                    if (!file.exists()) return

                    if (file.name == "R.txt") {
                        File assetDir = new File(project.android.sourceSets.main.assets.srcDirs.path.getAt(0))
                        File skinDir = new File(assetDir, "skins")
                        boolean mkdir = skinDir.mkdirs()
                        File dataFile = new File(skinDir, "data")
                        println("dataFile.createNewFile:${dataFile.absolutePath},$mkdir")
                        FileInputStream inputStream = new FileInputStream(file)
                        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(dataFile))

                        //write header
                        ThemeExtensionParams head = project.extensions.getByType(ThemeExtensionParams)
                        String headStr = head != null ? head.toJSon() : ""
                        writeHeader(outputStream, headStr)

                        List<String> lines = inputStream.readLines()
                        lines.each { line ->
                            String[] split = line.split(" ")
                            if (split.length < 4) return
                            if (split[0].endsWith("[]")) return
                            try {
                                outputStream.writeInt(split[2].hashCode())
                                if (split[3].startsWith("0x")) {
                                    outputStream.writeInt(Integer.valueOf(split[3].substring(2), 16))
                                } else {
                                    outputStream.writeInt(Integer.valueOf(split[3]))
                                }
                            } catch (Exception e) {
                                e.printStackTrace()
                                println("resource:${split[2]},failed to generate resId!")
                            }
                        }
                        inputStream.close()
                        outputStream.close()
                        break
                    }
                }
            } else {
                println("task:${task} has no outputFiles")
            }
            println(">>>>>>>>>>>>>>  end process Resource with R.txt")
        }
    }
    private final String magic = "SKIN"

    private void writeHeader(DataOutputStream outputStream, String header) {
        if (header == null) header = ""
        println("HEAD:${header}")
        outputStream.writeUTF(magic)
        outputStream.writeUTF(header)
    }
}