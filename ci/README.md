# build ear
gradle clean build

# Build and push docker image
### Build image docker
docker build -t cf1dck.cfavorita.net/com.kruger.hero/hero-services:1.0.0-SNAPSHOT -f ci/docker/DockerfileCI .

### Login in docker for upload and download images
docker login cf1dck.cfavorita.net

### Upload docker image
docker push cf1dck.cfavorita.net/com.kruger.hero/hero-services:1.0.0-SNAPSHOT



# Build and push helm image
### Add nexus repo 
helm repo add helm-base https://cf1hlmnxs.cfavorita.net/repository/helm-base/ --username myUser --password myPassword

### Update dependencies
helm dep update ./ci/helm

### Package helm chart 
helm package ./ci/helm

### install plugin
helm plugin install --version master https://corenegocio@bitbucket.org/corporacionfavorita/helm-nexus-push.git

### Upload artefact
helm nexus-push . hero-services-1.0.0-snapshot.tgz  -u myUser -p myPassword  
O
helm nexus-push cf-helm-snapshots hero-services-1.0.0-snapshot.tgz  -u myUser -p myPassword  
O  
helm nexus-push . ./ci/helm  -u myUser -p myPassword
O  
helm nexus-push cf-helm-snapshot ./ci/helm  -u myUser -p myPassword
