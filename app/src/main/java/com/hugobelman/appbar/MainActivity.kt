package com.hugobelman.appbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var favorito = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun setFavoriteIcon(menuItem: MenuItem) {
        val id = if (favorito) R.drawable.ic_favorite_white_24dp;
        else R.drawable.ic_favorite_border_white_24dp;

        menuItem.icon = ContextCompat.getDrawable(this, id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        setFavoriteIcon(menu?.findItem(R.id.favorito)!!)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorito -> {
                favorito = !favorito
                setFavoriteIcon(item)
            }

            R.id.compartir -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Dale like a mi página en Facebook: https://www.facebook.com/hcodeYoutube/")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

            R.id.salir -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
