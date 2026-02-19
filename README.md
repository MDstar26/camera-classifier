# AI Browser (Android + AI Assistant)

This repository now includes:

1. A new Android app module (`app`) with a normal browser-like UI (toolbar + URL bar + web rendering via `WebView`).
2. The reusable `aiassistant-core` module for AI settings, crop math, provider failover, and prompt behavior.

## Current app behavior

- Standard browsing flow: URL input, Go, Back/Forward.
- Explicit `AI` action in toolbar.
- AI entry uses a visible BottomSheet (no hidden/invisible mode).
- Separate Settings screen for AI-related options.

## Build Android APK

```bash
./scripts/build_android_apk.sh
```

If `gradlew` is missing, the script falls back to system `gradle`.
Expected output path:
- `app/build/outputs/apk/debug/*.apk`

## Source release package

```bash
./scripts/create_release.sh 0.2.0
```

Output:
- `build/releases/camera-classifier-v0.2.0-source.tar.gz`

## Persian documentation

- `docs/USAGE_FA.md`
- `docs/AI_ASSISTANT_SPEC_FA.md`
- `docs/ARCHIVE_REQUESTS_FA.md`
- `docs/APK_BUILD_STATUS_FA.md`

## Notes

- AI logic is intentionally explicit and user-visible.
- No stealth/hidden behavior is implemented.
- For production browser parity (tabs, GeckoView, PixelCopy selection overlay), integrate this module into a full Reference Browser fork as the next milestone.
