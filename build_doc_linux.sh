rm -rf ./src/main/resources/ext/v1/
echo start generating API docs
#mkdir -p ./src/main/resources/ext/v1/
cp -R ./src/main/resources/apidoc/swagger/models/v1/components/. ./src/main/resources/ext/v1/
echo "done copying components"
cp -R ./src/main/resources/apidoc/resources/. ./src/main/resources/ext/v1/
echo done copying resources
json-dereference -s ./src/main/resources/apidoc/swagger/models/v1/api.yaml -o ./src/main/resources/ext/v1/swagger.json
echo done generating swagger.json
sed -i "s/{{updateDate}}/$(date -u +"%Y-%m-%d")/" ./src/main/resources/ext/v1/swagger.json
echo "done update update date for swagger.json"
#pause
