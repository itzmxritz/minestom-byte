# minestom-byte
Official bytemc minestom fork


Following changes:

Add object implementation:
- `Sign`
- `Banner`

Repository: 
```xml
<repositories>
    <repository>
        <id>bytemc</id>
        <url>https://artifactory.bytemc.de/artifactory/bytemc-public/</url>
    </repository>
</repositories>
```

Maven dependency:
```xml
<dependency>
    <groupId>net.bytemc</groupId>
    <artifactId>minestom-byte</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

Ivy dependency:
```xml
<dependency org="net.bytemc" name="minestom-byte" rev="1.0.0-SNAPSHOT">
    <artifact name="minestom-byte" ext="jar"/>
</dependency>
```

Gradle dependency:
```groovy
compile(group: 'net.bytemc', name: 'minestom-byte', version: '1.0.0-SNAPSHOT')
```

Sbt dependency:
```groovy
libraryDependencies += "net.bytemc" % "minestom-byte" % "1.0.0-SNAPSHOT"
```