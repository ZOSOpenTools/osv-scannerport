node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/osv-scannerport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/osv-scannerport.git'), string(name: 'PORT_DESCRIPTION', value: 'Vulnerability scanner written in Go which uses the data provided by https://osv.dev' ), string(name: 'BUILD_LINE', value: 'STABLE'),  string(name: 'NODE_LABEL', value: "go_1215" ) ]
  }
}
