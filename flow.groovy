echo 'hello from Workflow'
node {
  git url: '/Users/chenharel/Sparktale/Git/takipi-dev/site', branch: 'develop'
  try {
    sh "./activator compile test"
  } catch (_) {
    echo "EXCEPTION"
  }
  step([$class: 'JUnitResultArchiver', testResults: '**/target/test-reports/*.xml'])
}
node {
  //sh "./activator dist"
  //step([$class: 'ArtifactArchiver', artifacts: '**/target/universal/*.zip', fingerprint: true])
  echo "chen1"
  newname = buildname("target/universal/siteplay-1.0-SNAPSHOT.zip")
  echo "chen2: " + newname
}

def buildname(name) {
  name - ".zip" + "-" + "${env.BUILD_NUMBER}" + ".zip"
}
