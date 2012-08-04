The simplest possible urlshortener written in java!

How to use:

    $ git clone https://github.com/jvassev/urlshortener.git
    $ cd urlshortener
    $ mvn clean install

The *-with-dependencies.jar is all you need. Try to start it from the console (you must have redis running on localhost on the default port):

    $ cd target
    $ java -jar *-with-dependencies.jar
    Customizations available
	    -Dport (8080) port to run webserver
	    -Dredis (localhost:6379) location of redis server
	    -Dnamespace (urls) root path in redis where to store program data
	    -Dserver (http://localhost:8080) root path in redis where to store program data
    2012-08-04 16:10:32.568:INFO:oejs.Server:jetty-8.y.z-SNAPSHOT
    2012-08-04 16:10:32.663:INFO:oejs.AbstractConnector:Started
    SelectChannelConnector@0.0.0.0:8080



Using curl to shorten the URL of this very file and GETting it over the short url:

    THIS_README=$(curl -s -d https://raw.github.com/jvassev/urlshortener/master/README.md -X POST http://localhost:8080)
    echo $THIS_README
    curl $THIS_README

