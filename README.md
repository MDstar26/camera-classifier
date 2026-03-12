# AI Assistant Browser (GeckoView-compatible Core)

This repository contains a Kotlin core module for integrating a **visible and user-controlled** AI Assistant into a standard open-source Android browser base (e.g., Reference Browser).

## Product direction (important)

- Browser look/behavior should stay close to the base app (normal tabs/address/navigation UX).
- AI features must be placed in **Settings + explicit user action**.
- No hidden/invisible/camouflage behavior is implemented.

## What is implemented now

- `aiassistant-core` Gradle module (Kotlin/JVM).
- Selection crop math (`CropMath`) for loop/ellipse overlays with margin + viewport clamp.
- Serializable settings model for:
  - Providers
  - Prompt templates
  - Capture/privacy options
  - Provider priority order
  - Optional no-prompt mode
- Failover planning logic:
  - Provider priority list
  - 2 attempts per provider
  - 10s timeout per attempt
  - Runtime disable/skip failed providers
- Helper parser for multiple-choice style numeric option extraction from model output.
- Unit tests for crop math, settings serialization, and routing/failover/prompt resolution.

## Build & test

```bash
gradle :aiassistant-core:test
```

## Release workflow (current stage)

```bash
./scripts/create_release.sh 0.1.2
```

Output:
- `build/releases/camera-classifier-v0.1.2-source.tar.gz`

## Persian guide

- `docs/USAGE_FA.md`

## Current limitations

- This repository still does not contain a full GeckoView Android app module.
- No installable APK is produced yet from this repository state.
- Current deliverable is core business logic that can be plugged into the browser fork.
