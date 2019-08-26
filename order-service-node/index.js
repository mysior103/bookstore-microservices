
console.log("node is starting");

const Eureka = require('eureka-js-client').Eureka;

const client = new Eureka({
    instance:{
        app: 'order-service',
        instanceId: 'order-service-node',
        hostName: 'localhost',
        ipAddr: '127.0.0.1',
        statusPageUrl: 'http://localhost:8080/info',
        port: {
            '$': 8082,
            '@enabled': 'true',
        },
        vipAddress: 'pl.podles',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
        registerWithEureka: true,
        fetchRegistry: true
    },eureka:{
        host: 'localhost',
        port: 8761,
        servicePath: '/eureka/apps/'
    }
});
const http = require('http');

const hostname = 'localhost';
const port = 8082;

const server = http.createServer(function (req,res) {
    res.statusCode = 200;
    res.setHeader('Content-Type', 'text/plain');
    res.end('Hello World\n');

});
server.listen(port,hostname,function () {
    console.log('Server is running at http://' + hostname + ':' + port);
});

client.logger.level('debug');

client.start(function (error) {
    console.log(error || 'complete')
});