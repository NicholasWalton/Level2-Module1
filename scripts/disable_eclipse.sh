#!/bin/sh

# Move the eclipse files out of the way, since they 
# seem to confuse VSCode

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd)"

ROOT="$(dirname "$SCRIPT_DIR")"

ECLIPSE_DIR="$ROOT/.eclipse"

if [ ! -d "$ECLIPSE_DIR" ]; then
    mkdir "$ECLIPSE_DIR"
fi

# Move .project, .classpath, and .settings into .eclipse if they exist
for file in ".project" ".classpath" ".settings"; do
    if [ -e "$ROOT/$file" ]; then # Check if file or directory exists
        mv "$ROOT/$file" "$ECLIPSE_DIR"
    fi
done