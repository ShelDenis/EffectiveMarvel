-keep class kotlin.Metadata { *; }
-keep class kotlin.jvm.** { *; }
-keep class kotlinx.** { *; }

-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**
-keepclassmembers class androidx.compose.** {
    <methods>;
    <fields>;
}

-keep class androidx.compose.material3.** { *; }
-dontwarn androidx.compose.material3.**

-keep class retrofit2.** { *; }
-keep class com.squareup.moshi.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
-keep class * {
    @retrofit2.http.* <methods>;
}

-keep class androidx.room.** { *; }
-keep class * extends androidx.room.RoomDatabase
-keep class * { @androidx.room.* <fields>; }
-keep class * { @androidx.room.* <methods>; }
-keepclassmembers class * {
    @androidx.room.* <methods>;
}

-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

-keep class androidx.lifecycle.** { *; }
-keepclassmembers class * {
    @androidx.lifecycle.* <methods>;
}

-keep class androidx.activity.** { *; }
-keep class androidx.lifecycle.ViewModel {
    <init>(...);
}

-keep class coil.** { *; }
-dontwarn coil.**

-keep class kotlin.Unit { *; }