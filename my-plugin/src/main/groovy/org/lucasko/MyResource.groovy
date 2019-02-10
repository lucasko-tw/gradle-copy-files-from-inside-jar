package org.lucasko


import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class MyResource extends DefaultTask {

    @TaskAction
    void downloadResource(){

        def src = "scripts/docker-compose/tomcat7/docker-compose.yml"

        def outputDir = "output"
        def dest = outputDir +"/"+ src

        this.copyFileFromInsideJarFile( src , dest )
    }


    void copyFileFromInsideJarFile(def src , def dest) {

        InputStream stream = null;
        OutputStream resStreamOut = null;
        try {
            ClassLoader cLoader = this.getClass().getClassLoader()
            // input stream
             stream = cLoader.getResourceAsStream(src);
            //note that each / is a directory down in the "jar tree" been the jar the root of the tree
            if(stream == null) {
                throw new Exception("Cannot get resource \"" + src + "\" from Jar file.");
            }
            int readBytes;
            byte[] buffer = new byte[4096];
            File outputFile = new File( dest  );
            outputFile.getParentFile().mkdirs()
            resStreamOut = new FileOutputStream( outputFile);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }

            println "Downloaded file to " + dest
        } catch (Exception ex) {
            throw ex;
        } finally {
            stream?.close();
            resStreamOut?.close();
        }

    }




}