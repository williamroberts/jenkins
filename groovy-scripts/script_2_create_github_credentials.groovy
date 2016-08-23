import jenkins.model.Jenkins
import hudson.util.Secret
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import org.jenkinsci.plugins.plaincredentials.impl.StringCredentialsImpl
import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.domains.Domain

// Get the credentials 'store'
def store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

// Get token from environment
def githubUsername = System.getenv("GITHUB_USERNAME")
def githubToken = System.getenv("GITHUB_TOKEN") // Should be an access token generated from Github

// Create username/password credentials
def githubUsernamePasswordCredentials = new UsernamePasswordCredentialsImpl(
  CredentialsScope.GLOBAL,
  "github-username-password",
  "Github Username And Password",
  githubUsername,
  githubToken
)

// Save
store.addCredentials(Domain.global(), githubUsernamePasswordCredentials)

// Create secret text credentials (this type is required for the Github plugin to automatically register hooks etc)
def githubSecret = new Secret(githubToken)
def githubSecretCredentials = new StringCredentialsImpl(
  CredentialsScope.GLOBAL,
  "github-secret",
  "Github Secret",
  githubSecret
)

// Save
store.addCredentials(Domain.global(), githubSecretCredentials)
