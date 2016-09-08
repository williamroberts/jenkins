import jenkins.model.JenkinsLocationConfiguration

def jenkinsUrl = System.getenv("JENKINS_URL")
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
jenkinsLocationConfiguration.setUrl(jenkinsUrl)
jenkinsLocationConfiguration.save()
