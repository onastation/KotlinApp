package com.example.piano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.view.MotionEvent
import android.widget.Button
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {

    private lateinit var keysResourse: Array<Int>
    private lateinit var keys: Array<Button>

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        keysResourse = arrayOf(R.raw.c, R.raw.cis, R.raw.d, R.raw.dis, R.raw.e, R.raw.f, R.raw.fis, R.raw.g, R.raw.gis, R.raw.a, R.raw.b, R.raw.h, R.raw.c2, R.raw.cis2, R.raw.d2, R.raw.dis2, R.raw.e2, R.raw.f2, R.raw.fis2, R.raw.g2, R.raw.gis2, R.raw.a2, R.raw.b2, R.raw.h2)
        keys = arrayOf(findViewById(R.id.C), findViewById(R.id.Cis), findViewById(R.id.D), findViewById(R.id.Dis), findViewById(R.id.E), findViewById(R.id.F), findViewById(R.id.Fis), findViewById(R.id.G), findViewById(R.id.Gis), findViewById(R.id.A), findViewById(R.id.B), findViewById(R.id.H), findViewById(R.id.C2), findViewById(R.id.Cis2), findViewById(R.id.D2), findViewById(R.id.Dis2), findViewById(R.id.E2), findViewById(R.id.F2), findViewById(R.id.Fis2), findViewById(R.id.G2), findViewById(R.id.Gis2), findViewById(R.id.A2), findViewById(R.id.B2), findViewById(R.id.H2))



        keys.forEach { key ->
            val indexKey = keys.indexOf(key)

            val color = (key.background as ColorDrawable).color
            key.setOnTouchListener { v, event ->
                val mp = MediaPlayer.create(applicationContext, keysResourse[indexKey])
                when (event?.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_HOVER_ENTER, MotionEvent.ACTION_HOVER_MOVE, MotionEvent.ACTION_POINTER_DOWN -> {
                        key.setBackgroundColor(Color.GRAY)
                        key.invalidate()
                        mp.start()
                        Timer().schedule(2500) {
                            mp.stop()
                            mp.reset()
                            mp.release()
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        Timer().schedule(100) {
                            key.setBackgroundColor(color)
                            key.invalidate()
                        }
                    }


                }
                v?.onTouchEvent(event) ?: true

            }
        }

    }
}