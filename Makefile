build_java:
	./gradlew clean build bootJar --refresh-dependencies

build_docker: build_java
	docker buildx build --platform=linux/amd64 -t tendosurveystack-tendosurveyrepod94dd114-oyp2tbjina14 .
	docker tag tendosurveystack-tendosurveyrepod94dd114-oyp2tbjina14:latest 598603975419.dkr.ecr.us-west-2.amazonaws.com/tendosurveystack-tendosurveyrepod94dd114-oyp2tbjina14:latest

build_publish_docker: build_docker
	docker push 598603975419.dkr.ecr.us-west-2.amazonaws.com/tendosurveystack-tendosurveyrepod94dd114-oyp2tbjina14:latest

cdk_build:
	cd deploy; npm run build

cdk_synth: cdk_build
	cd deploy; cdk synth

cdk_diff: cdk_build
	cd deploy; cdk diff

cdk_deploy: cdk_build
	cd deploy; cdk deploy