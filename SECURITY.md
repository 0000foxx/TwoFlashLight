# Security Policy

## Supported Versions

| Version | Supported |
|---------|-----------|
| 1.0.x   | ✅        |
| < 1.0   | ❌        |

## Reporting a Vulnerability

Please report security issues **privately** — do not open a public issue for
security problems.

- Preferred: GitHub **private vulnerability reporting**
  (repository **Security** tab → **Report a vulnerability**), or
- contact the maintainer directly.

When reporting, please include the affected version, reproduction steps, and the
expected impact. We aim to acknowledge reports within 7 days.

## Hardening notes (tracked on the roadmap)

This is a revived legacy Android app; the following are known and tracked:

- Bundled **legacy AdMob SDK** and **`support-v4`** are outdated — updates planned.
- **`WRITE_SETTINGS`** is used to adjust screen brightness; permission minimization
  is planned.
- The AdMob **ad-unit ID has been removed from source**; the app defaults to test
  ads so that forks never bill impressions to anyone else's account.
