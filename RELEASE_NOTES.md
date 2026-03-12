# Release Notes

## v0.1.2 (current)

### Added
- Provider failover core logic (`AiFailoverPlanner`) with 2 attempts and 10s timeout policy.
- Provider runtime state to disable failed endpoints and continue with remaining ordered providers.
- Optional prompt mode (`None` or selected template).
- Multiple-choice number extraction helper from AI text answers.
- Tests for failover plan, prompt resolution, and MCQ number extraction.

### Changed
- Updated Persian/English docs to keep browser UX close to base app and AI flow explicit in settings.

## v0.1.1

### Added
- Persian usage guide in `docs/USAGE_FA.md`.
- Source release packaging script `scripts/create_release.sh`.
- README release workflow and Persian documentation references.

## v0.1.0

### Added
- `aiassistant-core` module.
- Crop math (`CropMath`) with margin + clamp logic.
- Serializable AI settings domain model + JSON codec.
- Unit tests for crop math and settings serialization.
