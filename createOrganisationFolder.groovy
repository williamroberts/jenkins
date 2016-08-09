import jenkins.model.Jenkins
import jenkins.branch.OrganizationFolder
import org.jenkinsci.plugins.github_branch_source.GitHubSCMNavigator
import com.cloudbees.hudson.plugins.folder.computed.PeriodicFolderTrigger
import com.cloudbees.hudson.plugins.folder.computed.DefaultOrphanedItemStrategy

// Create organisation folder
def githubAccountName = System.getenv("GITHUB_ACCOUNT")
def orgFolder = new OrganizationFolder(Jenkins.getInstance(), githubAccountName)

// Create Github navigator for organisation folder
def githubNavigator = new GitHubSCMNavigator("", githubAccountName, "github-credentials", "SAME")
orgFolder.getNavigators().push(githubNavigator)

// Set periodic trigger so the folder is at most 1 hour out of date
def periodicFolderTrigger = new PeriodicFolderTrigger("1h")
orgFolder.addTrigger(periodicFolderTrigger)

// Create orphaned item strategy for organisation folder
def orphanedItemStrategy = new DefaultOrphanedItemStrategy(true, "90", "90")
orgFolder.setOrphanedItemStrategy(orphanedItemStrategy)

// Add organisation folder to Jenkins
Jenkins.getInstance().putItem(orgFolder)

// Trigger initial computation of organisation folder
orgFolder.getComputation().run()
