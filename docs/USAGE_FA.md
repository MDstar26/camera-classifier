# راهنمای استفاده (فارسی)

## هدف نسخه فعلی

این پروژه در وضعیت فعلی، **هسته منطقی AI Assistant** را برای ادغام در مرورگر اندروید (بر پایه Reference Browser/GeckoView) فراهم می‌کند.

- ظاهر و رفتار مرورگر باید شبیه نسخه پایه و کاملاً عادی بماند.
- قابلیت AI باید شفاف باشد و از مسیر تنظیمات/اکشن واضح کاربر فعال شود.
- قابلیت مخفی/نامرئی/استتاری در این پروژه پیاده‌سازی نشده است.

## قابلیت‌های هسته‌ای اضافه‌شده

1. محاسبه Crop از مسیر انتخاب کاربر (bounding box + margin + clamp)
2. مدل تنظیمات AI شامل:
   - لیست Providerها (ChatGPT/Gemini/Proxy/...) 
   - ترتیب Providerها (قابل مرتب‌سازی)
   - Prompt templateها
   - انتخاب Prompt یا «بدون Prompt»
3. سیاست Failover:
   - هر Provider حداکثر ۲ تلاش
   - هر تلاش با timeout ده‌ثانیه‌ای
   - در صورت شکست کامل، Provider موقتاً غیرفعال و از بعدی استفاده می‌شود
4. استخراج عدد گزینه تستی از پاسخ متنی AI (برای نمایش در UI به صورت شفاف)

## اجرا و تست

```bash
gradle :aiassistant-core:test
```

اگر در محیط شما خطای Java/Gradle بود:

```bash
JAVA_HOME=<path-to-jdk21> gradle :aiassistant-core:test
```

## ساخت ریلیز سورس در این مرحله

```bash
./scripts/create_release.sh 0.1.2
```

خروجی:
- `build/releases/camera-classifier-v0.1.2-source.tar.gz`

## سیاست آپدیت مستندات

در هر تغییر بعدی، این سه فایل به‌روزرسانی می‌شوند:
1. `README.md`
2. `docs/USAGE_FA.md`
3. `RELEASE_NOTES.md`
