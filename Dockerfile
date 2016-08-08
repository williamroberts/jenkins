FROM jenkins:2.7.1

# Install required plugins
RUN install-plugins.sh \
	github-branch-source \
	git \
	github-api \
	scm-api \
	credentials \
	github \
	git-client \
	ssh-credentials \
	matrix-project \
	workflow-scm-step 
	mailer \
	plain-credentials \
	token-macro \
	junit \
	script-security \
	workflow-step-api \
	structs;
