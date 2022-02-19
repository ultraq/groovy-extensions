
Groovy Extensions
=================

[![Build Status](https://github.com/ultraq/groovy-extensions/actions/workflows/build.yml/badge.svg)](https://github.com/ultraq/groovy-extensions/actions)
[![codecov](https://codecov.io/gh/ultraq/groovy-extensions/branch/main/graph/badge.svg?token=IIV5XF3B1L)](https://codecov.io/gh/ultraq/groovy-extensions)
[![Maven Central](https://img.shields.io/maven-central/v/nz.net.ultraq.groovy/groovy-extensions.svg?maxAge=3600)](http://search.maven.org/#search|ga|1|g%3A%22nz.net.ultraq.groovy%22%20AND%20a%3A%22groovy-extensions%22)

A collection of extensions for Groovy projects that I almost wish were a part of
the GDK.


Installation
------------

Minimum of Java 8 required.

Add a dependency to your project with the following co-ordinates:

 - GroupId: `nz.net.ultraq.groovy`
 - ArtifactId: `groovy-extensions`
 - Version: `1.1.0`

Check the [project releases](https://github.com/ultraq/groovy-extensions/releases)
for a list of available versions.


API
---

### Instance methods

#### Object.getOrCreate(String key, Closure create)

Retrieves a value from any object that supports the subscript operators (square
bracket notation, implemented by the `getAt`/`putAt` methods.  If there is no
value for the given key, then the `create` closure is executed and its return
value is set on the object for the key, and returned to the calling code.

```groovy
assert !myObject['someProperty']
def result = map.getOrCreate('someProperty') { ->
  return 13
}
assert result == 13
assert myObject['someProperty'] == 13
```

#### Map.getOrCreate(Object key, Closure create)

Very similar to Groovy's `Map.get(Object key, Object defaultValue)` method, but
allows the default value to be created in a closure, similar to
[Object.getOrCreate()](#objectgetorcreatestring-key-closure-create).

#### ExecutorService.executeAndShutdown(int awaitValue = 5, TimeUnit awaitUnit = TimeUnit.SECONDS, Closure closure)

Execute the given closure, performing a shutdown after it has exited (see:
[shutdownAwaitTermination](#executorserviceshutdownawaitterminationint-awaitvalue--5-timeunit-awaitunit--timeunitseconds).)
Returns the result of the closure, if any.

```groovy
def executorService = Executors.newCachedThreadPool()
executorService.executeAndShutdown { e ->
  // Use the executorService within this closure
}
assert executorService.shutdown
```

#### ExecutorService.shutdownAwaitTermination(int awaitValue = 5, TimeUnit awaitUnit = TimeUnit.SECONDS)

Initiate a shutdown, waiting the specified amount of time before forcing
termination.

### Static methods

#### Math.clamp(int value, int lower, int upper)

Clamp an `int` value to the given range.

#### Math.clamp(float value, float lower, float upper)

Clamp a `float` value to the given range.
