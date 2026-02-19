#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT_DIR"

if [[ ! -d "$ROOT_DIR/app" ]]; then
  echo "Android app module './app' not found; cannot produce APK."
  exit 2
fi

if [[ -f "$ROOT_DIR/gradlew" ]]; then
  "$ROOT_DIR/gradlew" :app:assembleDebug
else
  echo "gradlew not found, using system gradle"
  gradle :app:assembleDebug
fi

APK_PATH=$(find "$ROOT_DIR/app/build/outputs/apk/debug" -name '*.apk' | head -n 1 || true)
if [[ -z "$APK_PATH" ]]; then
  echo "Build finished but no APK was found."
  exit 3
fi

echo "APK created: $APK_PATH"
