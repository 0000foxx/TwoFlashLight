# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/).

## [1.0.0] - 2026-06-02

First tagged release. Revives the project from archive with open-source hygiene;
app behaviour is unchanged.

### Added
- GPL-2.0 `LICENSE` file (the project declared GPLv2 but shipped no license file).
- Full `README` (features, MVP architecture, build instructions, roadmap).
- `SECURITY.md`, `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`.
- Issue and pull-request templates under `.github/`.
- Repository description and topics.

### Changed
- AdMob: removed the hardcoded ad-unit ID from source; default to test ads.

### Removed
- `HARDWARE_TEST` permission and a duplicate `WAKE_LOCK` from `AndroidManifest.xml`.

[1.0.0]: https://github.com/0000foxx/TwoFlashLight/releases/tag/v1.0.0
