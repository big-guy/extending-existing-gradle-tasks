import example.OriginalTask
import example.MyExt

plugins {
    id("example.myext")
}

tasks {
    val extended by getting(OriginalTask::class) {
        val myext = the<MyExt>()
        myext.someString.set("kotlin")
    }
}
