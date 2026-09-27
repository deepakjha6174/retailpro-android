# RetailPro Offline Android v2

This is a native Android offline architecture for **Vimal Jha Kirana Shop**. Unlike the earlier WebView wrapper, this version stores operational data in a local SQLite database on the phone, so billing can continue without internet.

## Offline-first design
- Local SQLite database: products, customers, suppliers, sales, sale items, purchases, purchase items and customer ledger.
- Billing, inventory search and barcode scanning do not require internet.
- Bulk purchase workflow is included as the base for multi-item purchase entry.
- Customer search and Udhari balance are local.
- Barcode camera uses ZXing; after the APK is installed, scanning itself does not require internet.
- The project is prepared for an optional future Sync module to PHP/MySQL.

## Important architecture change
The old APK was a WebView wrapper around PHP/MySQL and therefore was **not offline**. This project is a native offline app instead.

## Build
Open this folder in Android Studio, allow Gradle to download dependencies once, then:
`Build > Build APK(s)`

The APK can subsequently be used in the shop without internet. Internet is only needed for optional future synchronization/backup to the server.

## Recommended next phase
For full parity with the web version, add:
1. Complete invoice print/thermal printer integration.
2. Complete customer statement/date-range reports.
3. Full bulk-purchase persistence and supplier ledger.
4. Backup/restore to a local file and optional cloud/server sync.
5. Sync conflict handling between multiple phones/PCs.
6. Staff login and permissions.
