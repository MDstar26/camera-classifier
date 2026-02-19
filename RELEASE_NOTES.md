# Release Notes

## v0.1.2 (current)

### Added
- `ProviderFailoverRouter` for ordered provider failover after failed retries.
- `AnswerHintExtractor` to parse explicit MCQ option numbers (1..4) from AI response text.
- Unit tests for failover router and answer hint extraction.

### Changed
- README and Persian guide updated for transparent (non-hidden) AI behavior.
- Clarified browser-base-like UX requirement with AI configured through Settings.

## v0.1.1

### Added
- Persian usage guide in `docs/USAGE_FA.md`.
- Source release packaging script `scripts/create_release.sh`.
- README section for release workflow and Persian documentation.

## v0.1.0

### Added
- `aiassistant-core` module.
- Crop math (`CropMath`) with margin + clamp logic.
- Serializable AI settings domain model + JSON codec.
- Unit tests for crop math and settings serialization.
