import jenkins.model.Jenkins

void purgeDirectory(File directory) {
  for (File file: directory.listFiles()) {
    if (file.isDirectory()) {
      purgeDirectory(file)
    }
    file.delete();
  }
}

// Remove Groovy hooks so they don't get executed when the Jenkins app restarts
def jenkinsHome = System.getenv("JENKINS_HOME")
purgeDirectory(new File(jenkinsHome + "/init.groovy.d/"))
