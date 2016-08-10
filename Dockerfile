FROM jenkins:2.7.2


# Install Docker daemon
USER root
RUN apt-get update && \
    apt-get install -y apt-transport-https ca-certificates && \
    echo "deb https://apt.dockerproject.org/repo debian-jessie main" > /etc/apt/sources.list.d/docker.list && \
    apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D && \
    apt-get update && \
    apt-get install -y docker-engine=1.12.0-0~jessie


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
COPY groovy-scripts/*.groovy /usr/share/jenkins/ref/init.groovy.d/
USER root
RUN chown -R jenkins:jenkins /usr/share/jenkins/ref/init.groovy.d
USER jenkins


# Install Jenkins plugins - combination of default and my favourites
RUN install-plugins.sh \
	cloudbees-folder \
	junit \
	antisamy-markup-formatter \
	pam-auth \
	script-security \
	matrix-project \
	windows-slaves \
	mailer \
	ldap \
	token-macro \
	external-monitor-job \
	icon-shim \
	matrix-auth \
	build-timeout \
	credentials \
	structs \
	workflow-step-api \
	plain-credentials \
	credentials-binding \
	timestamper \
	ws-cleanup \
	ant \
	gradle \
	jquery-detached \
	workflow-api \
	workflow-support \
	workflow-job \
	pipeline-rest-api \
	handlebars \
	momentjs \
	pipeline-stage-view \
	pipeline-build-step \
	ace-editor \
	workflow-scm-step \
	scm-api \
	workflow-cps \
	ssh-credentials \
	git-client \
	git-server \
	workflow-cps-global-lib \
	branch-api \
	workflow-multibranch \
	durable-task \
	workflow-durable-task-step \
	pipeline-input-step \
	pipeline-stage-step \
	workflow-basic-steps \
	workflow-aggregator \
	git \
	github-organization-folder \
	mapdb-api \
	subversion \
	ssh-slaves \
	email-ext \
	github-branch-source \
	git \
	github-api \
	github \
	docker-workflow \
	chucknorris \
	greenballs \
  docker-commons \
  authentication-tokens;
