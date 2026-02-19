# Release Notes

## v0.1.1 (current)

### Added
- APK build guard script `scripts/build_android_apk.sh` for when Android app sources are available.
- Archive docs for older requirements and package continuity (`docs/ARCHIVE_REQUESTS_FA.md`, `docs/APK_BUILD_STATUS_FA.md`).
- Persian usage guide in `docs/USAGE_FA.md`.
- Source release packaging script `scripts/create_release.sh` to generate a tagged snapshot archive.
- README section for release workflow and Persian documentation.
- Added AI assistant behavior spec in Persian (`docs/AI_ASSISTANT_SPEC_FA.md`) with explicit (non-hidden) UX and failover policy.
- Added core failover planning (`ProviderFailoverEngine`) and answer-choice extraction utility.
- Added optional prompt attachment control and retry policy fields in assistant settings model.

### Changed
- Clarified that current deliverable is the reusable AI core module, not a full Android APK yet.

## v0.1.0

### Added
- `aiassistant-core` module.
- Crop math (`CropMath`) with margin + clamp logic.
- Serializable AI settings domain model + JSON codec.
- Unit tests for crop math and settings serialization.
