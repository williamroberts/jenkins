/**
 * The first Groovy script that runs as Jenkins starts up.
 * This script checks for the existence of all required environment variables.
 * If any of them are not set, the script will use System.exit to kill Jenkins.
 * This works because the script is executed in the same JVM as Jenkins.
 */

 void err(missingEnvVarKey) {
   def config = new HashMap()
   config.putAll(getBinding().getVariables())
   def out = config['out']
   out.println "**************************************************************************************************************************"
   out.println "**************************************************************************************************************************"
   out.println "The " + missingEnvVarKey + " environment variable is not set. This must be set to for Jenkins to be auto-configured on start-up. Please run this build again with the " + missingEnvVarKey + " environment variable set correctly."
   out.println "**************************************************************************************************************************"
   out.println "**************************************************************************************************************************"
 }

void checkEnvVars(envVars) {
  def shouldExit = false
  envVars.each { envVar ->
    if (System.getenv(envVar) == null) {
      err(envVar)
      shouldExit = true
    }
  }
  if (shouldExit) {
    System.exit(1)
  }
}

checkEnvVars(["JENKINS_LOGIN_USERNAME", "JENKINS_LOGIN_PASSWORD", "GITHUB_USERNAME", "GITHUB_PASSWORD"])
