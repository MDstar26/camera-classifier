# AI Assistant Browser (GeckoView Plan + Core Module)

This repository now contains a Kotlin core module that implements two critical foundations for the requested Android browser fork:

1. **AI assistant settings model + serialization** (providers/prompts/capture/privacy compatible)
2. **Overlay crop math** for converting a free-form loop selection into a clamped crop rectangle with margin

> Note: The current repository did not include Mozilla Reference Browser sources. This commit adds the reusable core logic and tests first, so it can be integrated into a real GeckoView fork in Milestones 1-5.

## What was added

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
- Unit tests for:
  - Crop + margin + clamp
  - Settings serialization roundtrip

## Build & test

```bash
gradle :aiassistant-core:test
```

## Integration guidance for Android Reference Browser fork

When you fork `mozilla-mobile/android-components` (`reference-browser`):

1. Add this module (or copy files into an Android module).
2. Wire `CropMath.rectFromStroke(...)` to overlay path points.
3. Capture visible bitmap from GeckoView using `capturePixels` or `PixelCopy`.
4. Crop using returned rectangle, then downscale to ~1280-1600 width before upload.
5. Persist non-sensitive settings in SharedPreferences/DataStore and store API keys in EncryptedSharedPreferences.
6. Send active prompt + image to active provider.
7. Render response in BottomSheet with Copy/Close.

## Request/Response JSON shape (configurable concept)

Suggested default OpenAI-like request body:

```json
{
  "model": "gpt-4.1-mini",
  "input": [
    {
      "role": "user",
      "content": [
        { "type": "input_text", "text": "<active_prompt>" },
        { "type": "input_image", "image_base64": "<base64_jpeg>" }
      ]
    }
  ]
}
```

Response extraction strategy:
- Parse provider-specific `output_text` path, configurable per provider adapter.

## Current limitations

- No GeckoView app is included in this repository.
- No APK output is generated from this commit.
- Only visible-area crop math and settings foundation are implemented here.
