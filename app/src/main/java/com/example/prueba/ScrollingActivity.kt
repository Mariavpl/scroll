package com.example.prueba

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.prueba.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

         findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            if (findViewById<BottomAppBar>(R.id.bottom_app_bar).fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){
                findViewById<BottomAppBar>(R.id.bottom_app_bar).fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END

            }else{
                findViewById<BottomAppBar>(R.id.bottom_app_bar).fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
        }
    }*/

        binding.fab.setOnClickListener {
            if (binding.bottomAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END

            }else{
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
        }
        binding.bottomAppBar.setOnClickListener {
            Snackbar.make(binding.root, R.string.message_action_success, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .show()
        }
        binding.content.btnSkip.setOnClickListener { binding.content.cdAdd.visibility = View.GONE }

        binding.content.btnBuy.setOnClickListener {
            Snackbar.make(it, R.string.card_buying, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .setAction(R.string.card_to_go) {
                    Toast.makeText(this, R.string.card_historial, Toast.LENGTH_SHORT).show()
                }
                .show()

        }

        loadImage()

        binding.content.chEneablepass.setOnClickListener {
            binding.content.tilPassword.isEnabled = !binding.content.tilPassword.isEnabled
        }
        binding.content.etUrl.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            var errorStr: String? = null
            val url = binding.content.etUrl.text.toString()

            if (focused){
                if (url.isEmpty()){
                   errorStr  = getString(R.string.card_required)
            }else if (URLUtil.isValidUrl(url)){
                    loadImage(url)
            }else {
                errorStr = getString(R.string.card_invalid_url)
                }
            }
            binding.content.tilUrl.error = errorStr
        }
        binding.content.toggleButton.addOnButtonCheckedListener { _, checkedId, _ ->
            binding.content.root.setBackgroundColor(
                when(checkedId){
                    R.id.btnColor1 -> Color.MAGENTA
                    R.id.btnColor2 -> Color.BLUE
                    else -> Color.DKGRAY
                }
            )
        }
    }
   private fun loadImage(url: String = "http://i.yting.com/vi/uNhAHzUpsXQ/hqdefault.jpg") {
       Glide.with(this)
           .load(url)
           .centerCrop()
           .diskCacheStrategy(DiskCacheStrategy.ALL)
           .into(binding.content.imgCover)
   }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}