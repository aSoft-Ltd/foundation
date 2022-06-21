# Roadmap

- [cache-test] Centralize common cache test

  HINT: At the moment every cache has almost an identical copy of tests. have a cache-test lib and let all tests inherit from it
- [cache-file] add cache-file multiplatform implementation by depending on okio
- [kuest-mock] Re include mock kuest client taking another jab at the HttpResponse class
- [koncurrent] clean up commented out dependencies on all new koncurrent modules
- [koncurrent-primitives-core] Add `ExecutorService` in koncurrent-primitives-core to support ExecutorService shutdown and termination

# 1.5.1

## Logger

- Added a Log type

## Kotlinx Collections Interoperable

- Added iListOf, iMutableListOf and iEmptyListOf methods

## Expect

- [x] Refined the expect API
- [x] toBe<T>() now smart casts the object to its underlying type

## Kuest

Added Kuest. A coroutine free http client built on top of koncurrent-pending monad

## Koncurrent

Added Koncurrent library to be used in non coroutine friendly environments
Add Few. A multiplatform coroutine free reactive monad
Added a mock executor to primitives to aid with testing

# 1.4.61

## Cache API

- Added remove(key: String) method to cache
- Added clear() method to cache
- Added namespace to CacheConfiguration

# 1.4.50

- Updated gradle from 7.3 to 7.3.3
- Updated kotlin from 1.5.30 to 1.6.10
- Added browser cache namespace

# 1.4.13

# 1.4.12

## Foundation Plugins

### Fixes

- [FP05](https://github.com/aSoft-Ltd/foundation/issues/5) Use A Single DevServer Configuration

## Foundation Runtimes

### Enhancements

- Added terminal library

## Dependencies

- Bumped kotlin from 1.5.31 to 1.6.0

## Documentation

- Updated main readme

# 1.4.11

- Added missing artifacts

# 1.4.10

- Bumped kotlin from 1.5.30 to 1.5.31
- Fixed Platform JS runtime error: https://github.com/aSoft-Ltd/foundation/issues/8
- Fixed runTest not running on JVM

# 1.4.0 : 2021.09.02

- Combining a collection of libs for the foundation project