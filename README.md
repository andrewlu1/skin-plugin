# skin-plugin
皮肤apk 编译插件。
# V1.0.1
增加插件自动添加resmanager工具包的依赖，不用再手动添加依赖。

## 插件的工作原理：
插件自动拦截编译任务 processXXXResources. 并将processResources任务的输出【R.txt】 转换成一张资源索引表文件，写入工程的assets/skin/data文件。并一起编译入工程。
这样每个引用了插件的工程在assets目录中都隐含的包含了一个工程资源的索引表。 因此就可以相互之间进行索引查找映射并找到同名的资源ID. 
具体的资源查找逻辑已封装在resmanager.jar包中，并自动添加到工程依赖中。因此可以直接调用其API进行皮肤切换。

## 参看Resmanager：
https://github.com/andrewlu1/ResourceManager

# 使用：
 1. 添加maven 地址：
 
 ```gradle
 //因插件没有提交jcenter审核，因此目前需要添加具体的maven仓库地址。 后续稳定功能后会提交审核，这样就不必再添加仓库地址，可以从jcenter()中直接下载。
 buildscript.repositories {
        //++
        maven {url "https://dl.bintray.com/andrewlu1/maven/"}
 }
 buildscript.dependencies{
      //++
      classpath "cn.andrewlu.plugins:skinplugin:+"
 }
 allprojects.repositories{
       //++
       maven {url "https://dl.bintray.com/andrewlu1/maven/"}
 }
 ```
 2. module中build.gradle 中添加插件引用：
 ```gradle
  apply plugin: 'cn.andrewlu.plugins.skinplugin'
 ```
 3. build 工程。
 build完成后，系统自动下载了ResManager工具包依赖，可以在代码中直接调用ResManager实现酷炫的换肤功能了。
 
 # 示例工程：https://github.com/andrewlu1/ResManagerSample
 
 
