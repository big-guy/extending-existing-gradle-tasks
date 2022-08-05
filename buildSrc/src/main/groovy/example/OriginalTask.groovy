package example

import org.gradle.api.*
import org.gradle.api.tasks.*
import org.gradle.api.file.*

abstract class OriginalTask extends DefaultTask {
    @OutputFile
    abstract RegularFileProperty getOutputFile()

    @TaskAction
    void doIt() {
        outputFile.get().asFile.text = "Hello\n"
    }
}
