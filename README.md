### Building Plugin

[Document](https://guides.gradle.org/writing-gradle-plugins/)


```groovy
├── README.md
├── my-plugin
│   ├── build.gradle
│   ├── gradlew
│   ├── settings.gradle
│   └── src
│       └── main
│           ├── groovy
│           │   └── org
│           │       └── lucasko
│           │           ├── MyPlugin.groovy
│           │           └── MyResource.groovy
│           └── resources
│               │  
│               └── scripts
│                   ├── README.md
│                   └── docker-compose
│                       ├── tomcat7
│                       │   └── docker-compose.yml
│                       └── tomcat8
│                           └── docker-compose.yml
└── my-project
    ├── build.gradle

```



MyResource.groovy

```groovy
@TaskAction
void downloadResource(){

    def src = "scripts/docker-compose/tomcat7/docker-compose.yml"

    def outputDir = "output"
    def dest = outputDir +"/"+ src

    this.copyFileFromInsideJarFile( src , dest )
}

```


Building jar file.

```sh
cd gradle-standalone-plugin/my-plugin

./gradlew clean build publishToMavenLocal

# It should create my-plugin-1.3.jar file.
ls -l  ~/.m2/repository/org/lucasko/my-plugin/1.3/my-plugin-1.3.jar 
```



### Copy file from Inside Jar file

Run Tasks

```sh
$ cd gradle-standalone-plugin/my-project 


$ ./gradlew downloadResource

> Task :downloadResource
Downloaded file to output/scripts/docker-compose/tomcat7/docker-compose.yml

```
