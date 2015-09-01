# quintype-app

A Leiningen template for use mostly by Quintype micro services. Feel free to fork this

## Defaults
The following choices are made for you by default
* UberJAR does not do AOT compilation. This makes building an uberjar really fast, but running the uberjar slower.
* Creates a ./run, that is compatible with daemon tools. The way to start the server is ./run (both on dev and prod). ./run accepts --config /path/to/config, and environment variables `JDK_PATH` and `LOG4J_CONF`
* Creates a ./bin/build-tarball, that can be used to generate a CI artifact. By default, it only contains run, and the uberjar. Could should add migrations too.
* Includes clj-log4j2 for logging
* Includes ring, and starts http on port 2001
* Includes cider-nrepl, and starts nrepl on port 2101 (even on prod. Please ssh port forward if you are doing this)
* Local .m2 directory


## Usage

```sh
lein new quintype-app some-app-name
```

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
