/*
 * settings.gradle.kts
 *
 * Root build: lists the ejercicios, wires in build-logic, and generates
 * IDE run configurations.
 */

rootProject.name = "programacion"

include("ejercicio01", "ejercicio02")
includeBuild("build-logic")

// Not covered by the Gradle docs: IntelliJ does not create run configurations
// from Gradle projects, so one per ejercicio is generated in .run/ on every
// sync. Other IDEs ignore it.
rootProject.children.forEach { p ->
    val f = rootDir.resolve(".run/${p.name}.run.xml")
    if (!f.exists()) {
        f.parentFile.mkdirs()
        f.writeText(
            """
            <component name="ProjectRunConfigurationManager">
              <configuration default="false" name="${p.name}"
                             type="Application" factoryName="Application">
                <option name="MAIN_CLASS_NAME" value="Main" />
                <module name="${rootProject.name}.${p.name}.main" />
                <method v="2">
                  <option name="Make" enabled="true" />
                </method>
              </configuration>
            </component>
            """.trimIndent() + "\n"
        )
    }
}
