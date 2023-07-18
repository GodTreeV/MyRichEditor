# MyRichEditor
Android rich editor

## Make aar
```kotlin
  ./gradlew build
```

## Use aar lib to your project
```kotlin
  dependencies {
      // ...
      implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
      implementation(files("libs/richeditor-release.aar"))
  }
```
