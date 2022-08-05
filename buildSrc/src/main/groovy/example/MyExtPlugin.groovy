package example

import org.gradle.api.*

abstract class MyExtPlugin implements Plugin<Project> {
    void apply(Project project) {
        // Other plugin that cannot be changed
        project.tasks.withType(OriginalTask) {
            outputFile.convention(project.layout.buildDirectory.file(name))
        }
        project.tasks.register("extended", OriginalTask)

        // Plugin that extends an existing task/plugin

        project.tasks.withType(OriginalTask).configureEach {
            // Register the extension as a new API
            def myext = project.objects.newInstance(MyExt)
            extensions.add(MyExt, "myext", myext)
            myext.someString.convention("foo")

            // Wire new API into runtime API
            inputs.property("someString", myext.someString)
            inputs.files("extraFiles", myext.extraFiles)

            // Use new values
            doLast {
                println("someString = " + myext.someString.get())
                println("extraFiles = " + myext.extraFiles.files)
            }
        }
    }
}