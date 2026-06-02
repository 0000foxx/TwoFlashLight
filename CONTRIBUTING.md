# Contributing to TwoFlashLight

Thanks for your interest in contributing! This is a small, revived open-source
Android project and contributions are welcome.

## Project status

This is a legacy **Eclipse/ADT + Ant** project (`targetSdk 16`). A migration to a
modern **Gradle** build is the top item on the roadmap — help there is especially
welcome.

## Building

```sh
# legacy Ant build (requires an old Android SDK with Ant support)
cd Two_FlashLight
ant debug
```

## How to contribute

1. **Open an issue first** for anything non-trivial, so we can agree on the approach.
2. Fork the repo and create a feature branch.
3. Keep changes **focused and surgical** — one concern per pull request.
4. Open a pull request against `master` and fill in the PR template.

## Code style & architecture

- Java, following the existing **MVP** structure:
  - **View**: `CameraActivity` / `ICameraView`
  - **Presenter**: `CameraPresenter`
  - **Utility**: `ScreenWakeLockManager`, `TraceLogger`, `AdmobManager`
- Match the surrounding code's conventions rather than introducing new styles.

## Good first issues / help wanted

- Gradle migration (Eclipse/Ant → AGP).
- AndroidX migration (replace `support-v4`).
- Modern torch control via `CameraManager.setTorchMode()` (Camera2).
- Runtime permission handling for `CAMERA` / `WRITE_SETTINGS` (API 23+).

By contributing you agree that your contributions are licensed under the
project's **GPL-2.0** license.
