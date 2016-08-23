import jenkins.model.Jenkins
import org.jenkinsci.plugins.github.config.GitHubServerConfig

def githubServerConfig = new GitHubServerConfig("github-secret")
def githubPluginConfig = Jenkins.instance.getExtensionList('org.jenkinsci.plugins.github.config.GitHubPluginConfig')[0]

githubPluginConfig.setConfigs([githubServerConfig])
githubPluginConfig.save()
