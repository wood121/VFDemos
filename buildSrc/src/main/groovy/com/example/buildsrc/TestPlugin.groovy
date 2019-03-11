import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
        target.task("pluginTest") {
            //获取闭包信息
            def extension = target.extensions.create("testExtension", TestPluginExtension)
            doLast {
                println(extension.message)
            }
        }
    }
}

