# Programación

Exercises for the course, all in a single repository and a single Gradle
build. Each exercise is an independent `ejercicioNN` folder.

Requires a JDK 25. Gradle downloads itself through the wrapper.

## Contents

- [Exercise map](#exercise-map)
- [Opening in IntelliJ IDEA](#opening-in-intellij-idea)
- [Opening in Eclipse](#opening-in-eclipse)
- [From the terminal](#from-the-terminal)
- [New exercise](#new-exercise)
- [Code style](#code-style)

## Exercise map

Each activity asks for one or more exercises. The teacher may expect one repo
per activity; here they all live together, and this table is the index.

| Activity | Exercise       | Description                   |
|----------|----------------|-------------------------------|
| 1        | `ejercicio01`  | Hello world                   |
|          | `ejercicio02`  | Greeting with keyboard input  |

Folder numbering is global and sequential; it does not restart per activity.
When adding an exercise, add its row under its activity.

## Opening in IntelliJ IDEA

1. File, Open. Select the repo root, the folder containing
   `settings.gradle.kts`.
2. IntelliJ detects Gradle and imports the project. If asked, choose
   "Open as Project".
3. If asked for a "Gradle JVM", pick JDK 25.
4. Wait for the sync to finish. The Gradle tab on the right should list
   `programacion`, the exercises and `build-logic`.

To run an exercise, pick it in the run configurations dropdown at the top
right and press the green triangle. Those entries are generated on every
Gradle sync. You can also open its `Main.java` and press the triangle next
to `main`.

Recommended: Settings, Build Execution Deployment, Build Tools, and under
"Sync project after changes in the build scripts" select "Any changes". No
manual reload needed afterward.

## Opening in Eclipse

You need a recent Eclipse with Java 25 support (2025-09 or later). The
"Eclipse IDE for Java Developers" package already includes the Gradle
integration (Buildship).

1. On startup, choose a workspace that is **not** the repo's parent folder
   nor the repo itself, for example `C:\Users\<user>\eclipse-workspace`. If
   the repo sits directly inside the workspace, Eclipse requires the project
   name to match the folder name and the import fails halfway.
2. File, Import, Gradle, Existing Gradle Project, Next.
3. Under "Project root directory" select the repo root. Next, Next, Finish,
   keeping the defaults so it uses the wrapper.
4. The Package Explorer should show `programacion`, one project per
   exercise, `build-logic` and `conventions`.

To run an exercise: right-click its `Main.java`, Run As, Java Application.
Output and keyboard input go through the Console tab. Eclipse names each
launch after the class, so they all show up as `Main`; rename them under
Run, Run Configurations.

Warnings Eclipse may show and what to do:

- "Project has no explicit encoding set". Right-click the project,
  Properties, Resource, Text file encoding, Other, UTF-8. Once per project,
  new exercises included. Or Window, Preferences, General, Workspace, and
  set "Missing project encoding" to Ignore.
- Question marks on every file. That is the Git integration (EGit) marking
  files not yet added. Not an error. To keep Git out of Eclipse: Window,
  Preferences, Version Control (Team), Git, Projects, untick "Automatically
  share projects located in a Git repository", and on the projects Team,
  Disconnect.
- "Auto share git projects" error on import. An internal EGit failure during
  import. Safe to ignore; the import completes anyway.

## From the terminal

```
gradlew build                   # compiles every exercise
gradlew :ejercicio01:run        # runs ejercicio01
```

On Windows, `gradlew.bat`. Needs `JAVA_HOME` pointing at JDK 25, or `java`
on the PATH.

## New exercise

1. Create `ejercicio03/src/main/java/Main.java` with a
   `public static void main`.
2. Copy `ejercicio01/build.gradle.kts` to `ejercicio03/` and fix the header.
3. Add `"ejercicio03"` to the `include(...)` in `settings.gradle.kts`.
4. Add its row to the exercise map in this README.
5. IntelliJ: reload Gradle, or it reloads itself if auto sync is on.
   Eclipse: right-click `programacion`, Gradle, Refresh Gradle Project.

IDE-generated files (`.idea`, `.run`, `.project`, `.settings`, `bin`) are in
`.gitignore`. They can be deleted at any time; they are regenerated on open.

## Code style

The Oracle "Code Conventions for the Java Programming Language" are followed:
4-space indent, 80 columns, 8-space continuation, K&R braces (opening brace
on the same line, methods and classes included, unlike C functions).
https://www.oracle.com/java/technologies/javase/codeconventions-contents.html

The `.editorconfig` at the root encodes it. IntelliJ applies it on Reformat
Code (Ctrl+Alt+L). Eclipse needs the "EditorConfig Eclipse" plugin for the
basic keys; its formatter does not read the `ij_*` keys.
