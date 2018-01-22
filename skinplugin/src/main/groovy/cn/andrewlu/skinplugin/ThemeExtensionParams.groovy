package cn.andrewlu.skinplugin

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import org.gradle.internal.impldep.com.google.gson.JsonArray
import org.gradle.internal.impldep.com.google.gson.JsonObject;

/**
 * 用于插件参数配置。
 */
class ThemeExtensionParams {

    public String name;
    //作者名称
    public String author;
    //描述信息
    public String description;
    //一些特性标签
    public String[] tags;
    //预览画面res/drawable-name
    public String[] previewDrawables;
    //最小允许apk版本。
    public int minCompileVersion;
    //最大允许apk版本。主工程版本在此区间内才是安全可靠的。
    public int maxCompileVersion;
    //dev userId
    public String userId;
    //dev apiKey
    public String apiKey;
    //resManager适配版本。不写默认将是+
    public String resToolVersion;

    public String toJSon() {
        def json = new JsonBuilder()
        json {
            name name
            author author
            description description
            tags tags
            previewDrawables previewDrawables
            minCompileVersion minCompileVersion
            maxCompileVersion maxCompileVersion
            userId userId
            apiKey apiKey
            resToolVersion resToolVersion
            createAt System.currentTimeMillis()
        }

        return json.toString();
    }
}