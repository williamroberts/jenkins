FROM jenkins:2.7.2


# Install Docker daemon
USER root
RUN apt-get update \
  && apt-get install -y apt-transport-https ca-certificates \
  && echo "deb https://apt.dockerproject.org/repo debian-jessie main" > /etc/apt/sources.list.d/docker.list \
  && apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D \
  && apt-get update \
  && apt-get install -y docker-engine


# Add Jenkins user to Docker group so that we don't need to install/use sudo
RUN usermod -aG docker jenkins
USER jenkins


# Disable start-up wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false


# Required envvars listed here for reference
ENV JENKINS_LOGIN_USERNAME \
		JENKINS_LOGIN_PASSWORD \
		GITHUB_ACCOUNT \
		GITHUB_USERNAME \
		GITHUB_PASSWORD


# Copy hook scripts into Jenkins for automated post-init configuration
COPY checkEnvVars.groovy \
			createCredentials.groovy \
			createOrganisationFolder.groovy \
			createUser.groovy \
			$JENKINS_HOME/init.groovy.d/


# Install default Jenkins plugins
RUN install-plugins.sh \
	ace-editor \
	ant \
	antisamy-markup-formatter \
	branch-api \
	build-timeout \
	cloudbees-folder \
	credentials \
	credentials-binding \
	durable-task \
	email-ext \
	external-monitor-job \
	git \
	git-client \
	git-server \
	github \
	github-branch-source \
	github-organization-folder \
	gradle \
	handlebars \
	icon-shim \
	junit \
	jquery-detached \
	ldap \
	mailer \
	mapdb-api \
	matrix-auth \
	matrix-project \
	momentjs \
	pam-auth \
	pipeline-build-step \
	pipeline-input-step \
	pipeline-rest-api \
	pipeline-stage-step \
	pipeline-stage-view \
	plain-credentials \
	script-security \
	scm-api \
	ssh-credentials \
	ssh-slaves \
	structs \
	subversion \
	timestamper \
	token-macro \
	windows-slaves \
	workflow-api \
	workflow-cps \
	workflow-cps-global-lib \
	workflow-durable-task-step \
	workflow-job \
	workflow-multibranch \
	workflow-scm-step \
	workflow-step-api \
	workflow-support \
	ws-cleanup \
	workflow-basic-steps \
	workflow-aggregator;


# Install my required plugins
RUN install-plugins.sh \
	chucknorris \
	credentials \
	docker-workflow \
	git \
	github \
	github-branch-source \
	git-client \
	greenballs \
	junit \
	mailer \
	matrix-project \
	plain-credentials \
	scm-api \
	script-security \
	ssh-credentials \
	structs \
	token-macro \
	workflow-scm-step \
	workflow-step-api;
