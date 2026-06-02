# Reviving TwoFlashLight — a maintenance case study

> A short, honest account of bringing an 8-year-dormant Android app back to life,
> and using AI-assisted workflows (Codex) to do the modernization.

## Background

TwoFlashLight (雙面手電筒) is a small Android flashlight app first written in 2015
and last touched in 2018. It uses **both** the rear camera LED and the front screen
as light sources, with drag gestures to control each independently. It was built in
the **Eclipse/ADT + Ant** era with a clean **MVP** structure, and then archived.

It is not a widely-used project. This document is not a claim of popularity — it is a
record of a different kind of value: **a realistic, reproducible example of how a
single maintainer can revive and modernize a legacy open-source project.**

## Why this matters

A large share of open source is exactly this: small, dormant, single-maintainer
repos that still contain useful, well-structured code. The hard part is rarely the
idea — it's the *maintenance tax* of dragging a 2015 codebase up to 2026 standards.
That tax is precisely where AI-assisted tooling has leverage.

## What was done (phase 1 — housekeeping)

Done as a deliberately minimal, low-risk first pass:

- Added a proper **GPL-2.0 LICENSE** (the project declared GPLv2 but shipped no
  license file — a real compliance gap).
- Replaced an empty README with real documentation.
- Ran a **security review** of the full git history (no secret leaks found) and
  removed a hardcoded AdMob ad-unit ID so forks can't bill impressions to anyone.
- Cleaned up over-broad manifest permissions (`HARDWARE_TEST`, duplicate `WAKE_LOCK`).
- Added `SECURITY.md`, `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`, issue/PR templates,
  a `CHANGELOG`, and the first tagged release (`v1.0.0`).

## What's next (phase 2 — modernization)

Tracked on the README roadmap:

- Migrate **Eclipse/Ant → Gradle (AGP)**.
- Replace bundled jars with managed dependencies; migrate `support-v4` → **AndroidX**.
- Modernize the torch control via `CameraManager.setTorchMode()` and add **runtime
  permissions** for API 23+.
- Add CI and modern instrumented tests.

Each of these is a natural fit for AI-assisted PR review and refactoring, and the
process is being kept public so it can serve as a template for other maintainers
sitting on archived projects.
