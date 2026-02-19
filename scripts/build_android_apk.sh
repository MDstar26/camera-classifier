#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"

if [[ ! -f "$ROOT_DIR/gradlew" ]]; then
  echo "Android wrapper (gradlew) not found."
  echo "This repository currently contains only aiassistant-core and documentation, not a full Android app module."
  echo "APK build is not possible until Reference Browser/Android app sources are added."
  exit 2
fi

if [[ ! -d "$ROOT_DIR/app" ]]; then
  echo "Android app module './app' not found; cannot produce APK."
  exit 2
fi

cd "$ROOT_DIR"
./gradlew :app:assembleDebug

APK_PATH=$(find "$ROOT_DIR/app/build/outputs/apk/debug" -name '*.apk' | head -n 1 || true)
if [[ -z "$APK_PATH" ]]; then
  echo "Build finished but no APK was found."
  exit 3
fi

echo "APK created: $APK_PATH"
