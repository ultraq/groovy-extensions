
Groovy Extensions
=================

[![Build Status](https://travis-ci.com/ultraq/groovy-extensions.svg)](https://travis-ci.com/ultraq/groovy-extensions)
[![Maven Central](https://img.shields.io/maven-central/v/nz.net.ultraq.extensions/groovy-extensions.svg?maxAge=3600)](http://search.maven.org/#search|ga|1|g%3A%22nz.net.ultraq.extensions%22%20AND%20a%3A%22groovy-extensions%22)
[![License](https://img.shields.io/github/license/ultraq/groovy-extensions.svg?maxAge=2592000)](https://github.com/ultraq/groovy-extensions/blob/master/LICENSE.txt)

A collection of extensions for Groovy projects that I almost wish were a part of
the GDK.


Installation
------------

Minimum of Java 8 required.

Add a dependency to your project with the following co-ordinates:

 - GroupId: `nz.net.ultraq.extensions`
 - ArtifactId: `groovy-extensions`
 - Version: (check the badge above or [the list of releases](https://github.com/ultraq/groovy-extensions/releases)
   for available versions)


API
---

### Instance methods

#### Object.getOrCreate(String key, Closure create)

Retrieves a value from any object that supports the subscript operators (square
bracket notation, implemented by the `getAt`/`putAt` methods.  If there is no
value for the given key, then the `create` closure is executed and its return
value is set on the object for the key, and returned to the calling code.

```groovy
def myObject = map.getOrCreate('myKey') { ->
  return new MyObject()
}
```

### Static methods

#### Math.clamp(int value, int lower, int upper)

Clamp an `int` value to the given range.

#### Math.clamp(float value, float lower, float upper)

Clamp a `float` value to the given range.
