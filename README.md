# TwoFlashLight (雙面手電筒)

A lightweight open-source Android flashlight that uses **both** the rear camera LED
**and** the front screen as light sources at the same time — adjust each
independently with simple drag gestures.

> ⚠️ **Status: archived (2015–2018).** This is a historical hobby project built in
> the Eclipse/ADT + Ant era (`targetSdk 16`). It is being revived as a case study
> in modernizing and maintaining a legacy open-source Android app. See
> [Roadmap](#roadmap).

## Features

- **Dual light source** — rear LED flash + front screen brightness simultaneously.
- **Gesture control**
  - Drag **left / right** → adjust rear LED brightness.
  - Drag **up / down** → adjust front screen luminance.
- **Stay-awake** — wake lock keeps the screen on while in use.
- **Background use** — press Home to keep the light running; notification while active.
- **i18n** — English and Traditional Chinese (`zh-rTW`) strings included.

## Architecture

Clean **MVP (Model–View–Presenter)** separation:

| Layer | Class | Responsibility |
|-------|-------|----------------|
| View | `CameraActivity` (`ICameraView`) | UI, gesture input |
| Presenter | `CameraPresenter` | brightness logic, camera/LED control |
| Utility | `ScreenWakeLockManager`, `TraceLogger`, `AdmobManager` | cross-cutting helpers |

Unit tests use Robotium (`test/` module).

## Build

This project predates Gradle and uses the legacy **Eclipse ADT / Ant** toolchain
(`build.xml`, `project.properties`, `target=android-16`). Migrating to Gradle is
the first item on the roadmap.

```sh
# legacy Ant build (requires an old Android SDK with Ant support)
cd Two_FlashLight
ant debug
```

## Permissions

`CAMERA`, `WAKE_LOCK`, `WRITE_SETTINGS`, `VIBRATE`, `INTERNET`,
`ACCESS_NETWORK_STATE`. The `INTERNET` permission exists only for the (legacy)
AdMob banner; further permission minimization is on the roadmap.

> The ad-unit ID has been removed from source — set `AD_UNIT_ID` in
> `AdmobManager.java` to your own ID, or remove the ad banner entirely.

## Roadmap

- [x] Add a proper `LICENSE` file (GPL-2.0).
- [x] Remove hardcoded AdMob ad-unit ID (use test ads by default).
- [x] Minimize permissions (drop `HARDWARE_TEST`, dedupe `WAKE_LOCK`).
- [ ] Migrate Eclipse/Ant → modern Gradle build.
- [ ] Update / remove outdated dependencies (legacy AdMob SDK, support-v4).
- [ ] Add CI and modern instrumented tests.

## License

This program is free software: you can redistribute it and/or modify it under the
terms of the **GNU General Public License version 2** as published by the Free
Software Foundation. See [`LICENSE`](LICENSE).

Copyright (C) 2015 foxxtseng.
