# Roadmap

## Koncurrent

- [ ] Test cascaded Pending object and make sure that the behaviour is predictable on all platform

## Kuest

- [ ] Fix failing mock tests

## Koncurrent

- [ ] Clean up build.gradle.kts for koncurrent-pending core & coroutines modules
- [ ] clean up commented out dependencies on all new koncurrent modules
- Create a reliable bodyAsText method after a get request
- Add `ExecutorService` in koncurrent-primitives-core to support ExecutorService shutdown and termination

```
      _ _ _ _ _ _   _ _ _ _ _ _ _ 
   .              |
 .                !
.                 |
.                 !
 .                !
    _ _ _ _ _ _ _ | - - - -
  .               !
.                 |
.                 !
.                 |
 .                !
    _ _ _ _ _ _ _ | 
```

# 1.5.1

## Kuest

- [x] Migrate away from old koncurrent dependencies into new ones
    - [x] change import coordinates from old packages into new ones

## Koncurrent

- Added a koncurrent module family
- [x] Split up concurrent-core into multi modules
    - [x] koncurrent primitives (containing Executors & Executor)
    - [x] koncurrent later      (containing Later implementation) { depends on koncurrent-primitives }
        - [x] Move all later extension functions into package koncurrent.later
    - [x] koncurrent pending    (containing the pending abstraction) { depends on koncurrent-later }
        - [x] Move all pending extension function into package koncurrent.pending
        - [x] Fix failing tests after module split

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