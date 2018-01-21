# skin-plugin
皮肤apk 编译插件。
# V1.0.1
增加插件自动添加resmanager工具包的依赖，不用再手动添加依赖。

## 插件的工作原理：
插件自动拦截编译任务 processXXXResources. 并将processResources任务的输出【R.txt】 转换成一张资源索引表文件，写入工程的assets/skin/data文件。并一起编译入工程。
这样每个引用了插件的工程在assets目录中都隐含的包含了一个工程资源的索引表。 因此就可以相互之间进行索引查找映射并找到同名的资源ID. 
具体的资源查找逻辑已封装在resmanager.jar包中，并自动添加到工程依赖中。因此可以直接调用其API进行皮肤切换。

# 参看Resmanager 工程：
https://github.com/andrewlu1/ResourceManager
