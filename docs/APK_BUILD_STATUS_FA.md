# وضعیت ساخت APK

## نتیجه بررسی فعلی

سورس اندروید اولیه (`app`) به مخزن اضافه شده است.
در نتیجه، مسیر ساخت APK تعریف شده و آماده اجراست.

## فایل‌های مرتبط

- `scripts/build_android_apk.sh`: اسکریپت ساخت APK دیباگ.
- `scripts/create_release.sh`: ساخت آرشیو سورس برای انتشار.

## دستور ساخت

```bash
./scripts/build_android_apk.sh
```

خروجی مورد انتظار:
- `app/build/outputs/apk/debug/*.apk`

## نکته محیطی

اگر در محیط فعلی، دسترسی به مخازن Gradle یا Android SDK محدود باشد، بیلد ممکن است fail شود. در سیستم توسعه استاندارد اندروید (Android Studio + SDK کامل) این مسیر قابل اجراست.
