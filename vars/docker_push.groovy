
def call(String imageName, String imageTag, String dockerHubUser) {
    withCredentials([
        usernamePassword(
            credentialsId: 'dockerhub-credentials',
            usernameVariable: 'dockerHubUser',
            passwordVariable: 'dockerHubPass'
        )
    ]) {
        sh '''
            echo "$dockerHubPass" | docker login \
                -u "$dockerHubUser" --password-stdin
        '''

        sh "docker push ${dockerHubUser}/${imageName}:${imageTag}"

        sh 'docker logout'
    }
}
