echo 'hello from Workflow'
node {
  stage 'Compile+Test'
  git url: '/Users/chenharel/Sparktale/Git/takipi-dev/site', branch: 'develop'
  try {
    sh "./activator compile test"
  } catch (_) {
    echo "EXCEPTION"
  }

  step([$class: 'JUnitResultArchiver', testResults: '**/target/test-reports/*.xml'])

  stage 'Package'

  sh "./activator dist"
  archive "target/universal/*.zip"

  echo "chen1"
  def newname = buildname("target/universal/siteplay-1.0-SNAPSHOT.zip")
  echo "chen1-1"
  echo "chen2: " + newname
}
echo "bye"

def buildname(name) {
  name - ".zip"// + "-${env.BUILD_NUMBER}.zip"
}
