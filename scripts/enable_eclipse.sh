#!/bin/sh

# Move the eclipse files out of the way, since they 
# seem to confuse VSCode

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd)"

ROOT="$(dirname "$SCRIPT_DIR")"

ECLIPSE_DIR="$ROOT/.eclipse"

if [ -d "$ECLIPSE_DIR" ]; then
    for file in ".project" ".classpath" ".settings"; do
        if [ -e "$ECLIPSE_DIR/$file" ]; then # Check if file or directory exists
            mv "$ECLIPSE_DIR/$file" "$ROOT"
        fi
done
fi


