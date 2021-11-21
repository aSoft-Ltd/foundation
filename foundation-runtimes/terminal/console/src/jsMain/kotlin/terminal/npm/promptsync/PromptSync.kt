package terminal.npm.promptsync

import platform.utils.common.require

internal val prompt = require<() -> ((String) -> String)>("prompt-sync")()