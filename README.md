# skin-plugin
皮肤apk 编译插件。
# V1.0.1
增加插件自动添加resmanager工具包的依赖，不用再手动添加依赖。
增加皮肤信息配置能力。可在皮肤apk中对皮肤的基础信息进行一番配置，用于客户端对皮肤进行管理。
### 具体配置：
```kotlin
//在 module的build.gradle中添加，与android{...} 同级。
skinConfig {
    name = "红色皮肤" //可选，可在Manifest.xml中配置application:label 作为name.
    author = "andrewlu"   //可选，作者名称。
    description = "这是一个测试皮肤工程"  //可选，皮肤描述内容。
    tags = ["美丽", "夜空蓝", "水晶效果"] // 可选，皮肤tag 标注，便于皮肤归类
    previewDrawables = ["preview_pic_0","preview_pic_1"] //可选，皮肤预览图片，放在drawable目录下，并在这里写入资源名称。
    minCompileVersion = 1   //可选，标记本皮肤版本适用的最低客户端版本。
    maxCompileVersion = 5   //可选，标记本皮肤版本适用的最高客户端版本。
    userId = "andrewlu1"    //可选，用于标明本皮肤的开发者id, 后续会用作皮肤来源校验，以防止皮肤被二次打包恶意篡改。
    apiKey = "1234567890qwertyuiop"  //可选，用于标记皮肤的开发者密钥，后续提供统一平台供皮肤开发者生成密钥并进行校验。以确认皮肤包的身份。
    resToolVersion = "+"    //可选，用户配置插件加载的resmanager工具包的依赖版本。默认为最新版本+，你可以指定特殊的版本如："1.0.1"
} 
```
以上配置信息会在编译时自动打包入apk内，以供resmanager进行解析/识别/校验等。


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
 
 
