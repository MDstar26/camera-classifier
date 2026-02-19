# AI Assistant Browser (GeckoView Plan + Core Module)

This repository currently contains a Kotlin core module for integrating an **explicit and transparent** AI assistant into a browser base (e.g., Mozilla Reference Browser + GeckoView).

## Transparency and safety principles

- No hidden/invisible AI mode.
- No disguised controls for bypassing supervision.
- AI actions must be user-initiated and visible in UI/settings.

## What is available now

- `aiassistant-core` Gradle module (Kotlin/JVM)
- Settings domain models:
  - Providers
  - Prompt templates
  - Capture/privacy options
- JSON codec via `kotlinx.serialization`
- Crop rectangle math utility:
  - Calculates bounding box from stroke points
  - Applies margin
  - Clamps to visible viewport bounds
- Provider failover router:
  - Provider priority ordering
  - Automatic suspension after 2 failed attempts (configurable)
  - Move to next provider in list
- MCQ answer hint extractor:
  - Extracts visible numeric hint (1..4) from AI response text for dedicated UI field
- Unit tests for:
  - Crop + margin + clamp
  - Settings serialization roundtrip
  - Provider failover behavior
  - Answer hint extraction

## Build & test

```bash
gradle :aiassistant-core:test
```

## Release workflow (current stage)

To create a source release archive for the current state:

```bash
./scripts/create_release.sh 0.1.2
```

Output:
- `build/releases/camera-classifier-v0.1.2-source.tar.gz`

## Persian user guide

A Persian usage guide is available at:
- `docs/USAGE_FA.md`

## Integration guidance for Android Reference Browser fork

When integrating into `mozilla-mobile/android-components` (`reference-browser`):

1. Keep browser UI like base app (standard toolbar/settings).
2. Add AI options under Settings (not in intrusive foreground UI).
3. Add visible screenshot selection mode and send action.
4. Use `CropMath.rectFromStroke(...)` for selected region + margin.
5. Capture visible bitmap using GeckoView `capturePixels` / `PixelCopy`.
6. Use provider list + ordering and `ProviderFailoverRouter` for retries/failover.
7. Optional prompt list: user selects a prompt or "no prompt".
8. Display full AI result in a normal bottom sheet.
9. If result seems MCQ-style, show extracted option number in a separate explicit UI element.

## Current limitations

- No GeckoView Android app module is included in this repository yet.
- No installable APK is generated from this repository in the current stage.
- Current deliverable is reusable core logic for later milestones.
