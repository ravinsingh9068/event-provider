git clone https://gitlab.com/ravinder.leonlabs/eventProvider.git
cd eventProvider
mvn install dockerfile:build
docker run -p 3000:3000 -t ravindersengar84/eventProvider
