package example

import org.gradle.api.file.*
import org.gradle.api.provider.*

abstract class MyExt {
    abstract ConfigurableFileCollection getExtraFiles()
    abstract Property<String> getSomeString()
}

