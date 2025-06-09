package com.example.effectivemarvel

inline fun lerp(a: Float, b: Float, t: Float): Float {
    return a + t * (b - a)
}