import jenkins.model.Jenkins
import hudson.security.FullControlOnceLoggedInAuthorizationStrategy
import hudson.security.HudsonPrivateSecurityRealm

def instance = Jenkins.getInstance()

// Get Jenkins UI user credentials from environment
def username = System.getenv("JENKINS_LOGIN_USERNAME")
def password = System.getenv("JENKINS_LOGIN_PASSWORD")

// Init user
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount(username, password)
instance.setSecurityRealm(hudsonRealm)

// Give user full control
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()
