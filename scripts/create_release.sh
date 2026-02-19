#!/usr/bin/env bash
set -euo pipefail

if [[ $# -lt 1 ]]; then
  echo "Usage: $0 <version-tag-like-0.1.1>"
  exit 1
fi

VERSION="$1"
ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
OUT_DIR="$ROOT_DIR/build/releases"
ARCHIVE_NAME="camera-classifier-v${VERSION}-source.tar.gz"

mkdir -p "$OUT_DIR"

git -C "$ROOT_DIR" archive --format=tar.gz --output="$OUT_DIR/$ARCHIVE_NAME" HEAD

echo "Release archive created: $OUT_DIR/$ARCHIVE_NAME"
