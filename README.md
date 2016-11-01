# Jenkins
A 'one-command' deployable Jenkins CI running in Docker. Will deploy to Amazon EC2, and automatically scan the given Github account for repositories containing Jenkinsfiles, which will then be automatically built.


### Prerequisites
To deploy this version of Jenkins, you must have:
* A Github account
* An Amazon Web Services (AWS) account

You must also have installed on your machine:
* The `AWS CLI` and associated dependencies


### To view required parameters in JSON format:
```$ aws cloudformation validate-template --template-body file://cloudformation.json```

### Parameters

|     Parameter     |  Default Value   |   Type   | Description |
|-------------------|------------------|----------|-------------|
| `DomainName`      |                  | `String` | The domain at which you wish to browse Jenkins |
| `SubdomainName`   | `build`          | `String` | The subdomain at which you wish to browse Jenkins |
| `JenkinsUsername` | `admin`          | `String` | The username with which you want to authenticate with Jenkins |
| `JenkinsPassword` | `password`       | `String` | The password with which you want to authenticate with Jenkins |
| `GithubAccount`   | `williamroberts` | `String` | The name of the Github account with which you want to initialise Jenkins |
| `GithubUsername`  | `williamroberts` | `String` | The username with which you want Jenkins to authenticate with Github |
| `GithubToken`     |                  | `String` | The token with which you want Jenkins to autheticate with Github. This should be a 40 character hash generated as a Github personal access token. |
| `Region`          | `eu-west-1`      | `String` | The region into which to deploy the stack. |


### To Deploy
```aws cloudformation update-stack --stack-name jenkins --template-body file://cloudformation.json --capabilities=CAPABILITY_IAM --parameters  ParameterKey=DomainName,ParameterValue=$DOMAIN_NAME
ParameterKey=SubdomainName,ParameterValue=$SUBDOMAIN_NAME
ParameterKey=JenkinsUsername,ParameterValue=$JENKINS_USERNAME ParameterKey=JenkinsPassword,ParameterValue=$JENKINS_PASSWORD ParameterKey=GithubAccount,ParameterValue=$GITHUB_ACCOUNT
ParameterKey=GithubUsername,ParameterValue=$GITHUB_USERNAME
ParameterKey=GithubToken,ParameterValue=$GITHUB_TOKEN
ParameterKey=Region,ParameterValue=$REGION```
