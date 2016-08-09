import jenkins.model.Jenkins
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.domains.Domain

// Get the credentials 'store'
def store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

// Get credentials from environment
def githubUsername = System.getenv("GITHUB_USERNAME")
def githubPassword = System.getenv("GITHUB_PASSWORD") // Should be an access token generated from Github

// Create username/password credentials
def githubCredentials = new UsernamePasswordCredentialsImpl(
  CredentialsScope.GLOBAL,
  "github-credentials",
  "Github credentials",
  githubUsername,
  githubPassword
)

store.addCredentials(Domain.global(), githubCredentials)
